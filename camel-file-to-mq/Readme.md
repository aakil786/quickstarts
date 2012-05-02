#Introduction
This quickstart demonstrates a SwitchYard service uses a camel.implementation to route messages to an 
external ActiveMQ Broker. 


The SwitchYard configuration consists of two services

1. CamelFileGateway
2. ActiveMQService

## CamelFileGateway
This service used the Camel File Component to "listen" to the directory switchyard-service/input

## ActiveMQService
This service uses the SwitchYard Camel implementation with an inline route, which logs the message and then routes the message to activemq.


##Preqrequisites 
Maven

##Running the quickstart
1) Build all quickstart modules:

    mvn install

2) Start the ActiveMQ Broker:

	cd activemq-broker 
	mvn activemq:run
	
3) Start the AciveMQ Consumer (open a new console window)

    cd activemq-consumer
	mvn exec:java

4) Deploy the SwitchYard Service

	cd switchyard-service
	cp target/switchyard-quickstart-camel-file-to-mq-service.jar to $AS7/standalone/deployments

5) Copy a file to the input directory:

	cd switchyard-service
	cp test.txt input/

Now you'll be able to see output in the AS7 console log:

    INFO  [route1] (Camel (camel-2) thread #1 - file:///path/to/switchyward/quickstarts/camel-file-to-mq/switchyard-service/input) [message] 'hellow route
    '

And also see that the message has been received by the activemq-consumer:
 
    [ Received Message[1]
        [JMSMessageID : ID:7719.local-49957-1335960741661-0:2:1:1:1]
        [MessageType : TextMessage]
        [Text : hellow route]
    ]
    


## Further Reading

1. [Camel Service Documentation](https://docs.jboss.org/author/display/SWITCHYARD/Camel+Services)

