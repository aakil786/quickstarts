package example;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author <a href="mailto:daniel.bevenius@gmail.com">Daniel Bevenius</a>
 *
 */
public class Main implements MessageListener {

    private static final String LISTEN_QUEUE = "exampleQueue";
    private static int _messageCounter;
    private Connection _connection;
    private static Main _consumerMain;

    /**
     * Main entry method.
     * 
     * @param args The command line arguments
     * @throws Exception is somethings goes wrong.
     */
    public static void main(final String... args) throws Exception {
        _consumerMain = new Main();
        _consumerMain.setupMessageListener();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                _consumerMain.closeConnection();
            }
        });

        Object sleep = new Object();
        synchronized (sleep) {
            sleep.wait();
        }
    }

    private void setupMessageListener() throws NamingException, JMSException {
        InitialContext context = null;
        try {
            context = new InitialContext();
            final ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("ConnectionFactory");
            _connection = connectionFactory.createConnection();
            final Session session = _connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            final Destination destination = (Destination)context.lookup(LISTEN_QUEUE);
            final MessageConsumer consumer = session.createConsumer(destination);
            consumer.setMessageListener(this);
            _connection.start();
            System.out.println("JMS Listener started");
        } catch (final NamingException e) {
            e.printStackTrace();
            throw e;
        } catch (final JMSException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try { context.close(); } catch (NamingException e) { e.printStackTrace(); }
        }
    }

    /**
     * MessageListener onMessage implementation.
     * 
     * @param message the javax.jms.Message object instance.
     */
    public void onMessage(final Message message) {
        _messageCounter++;
        System.out.println("\n[ Received Message[" + _messageCounter + "]");
        try {
            System.out.println("\t[JMSMessageID : " +  message.getJMSMessageID() + "]");
            if (message instanceof ObjectMessage) {
                System.out.println("\t[MessageType : ObjectMessage]");
                System.out.println("\t[Object : " + ((ObjectMessage)message).getObject() + "]");
            } else if (message instanceof TextMessage) {
                System.out.println("\t[MessageType : TextMessage]");
                System.out.println("\t[Text : \n" + ((TextMessage)message).getText() + "]");
            }
        } catch (final JMSException e) {
            e.printStackTrace();
        }
        System.out.println("]");
    }

    private void closeConnection() {
        System.out.println("Closing JMS Listener...");
        try {
            if (_connection != null) {
                _connection.close();
            }
        } catch (final JMSException e) {
            e.printStackTrace();
        }
    }
}
