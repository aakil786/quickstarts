package org.switchyard.quickstarts.camel.service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.camel.component.ActiveMQComponent;

/**
 * A CDI Producer for ActiveMQComponents.
 * 
 * @author Daniel Bevenius
 */
@ApplicationScoped
public class ActiveMQComponentFactory {
    
    /**
     * Creates an new instance of {@link ActiveMQComponent}.
     * 
     * @return {@link ActiveMQComponent} that has been newly created.
     */
    @Produces @Named ("activemq")
    public ActiveMQComponent createActiveMQComponent() {
        final ActiveMQComponent ac = new ActiveMQComponent();
        final ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL("tcp://localhost:61616");
        ac.setConnectionFactory(factory);
        return ac;
    }
 
}
