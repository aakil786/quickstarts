Introduction
============
This quickstart demonstrates the usage of the Camel Component and it's binding feature, by binding to a JMS Queue. When a message arrives
in this queue the service will be invoked.

Running the quickstart
======================

JBoss AS 7
----------
1. To install SwitchYard to AS 7 follow the instructions presented here:
    http://community.jboss.org/wiki/SwitchYardOnAS7
2. Build the quickstart:
    mvn clean install
3. Start JBoss AS 7 in standalone mode:
    ./standalone --server-config=standalone-preview.xml
4. Start JBoss CLI and connect: 
    ./jboss-admin.sh file=src/main/test/resources/create-resources.cli
6. Deploy the quickstart
    cp target/switchyard-quickstarts-camel-jms-binding-0.2.0-SNAPSHOT.jar ${AS7}/standalone/deployments
7. Execute HornetQClient
    mvn exec:java -Dexec.mainClass="org.switchyard.quickstarts.camel.jms.binding.HornetQClient"
8. Check the server console for output from the service.


Starting the H2 Database console
--------------------------------
1. From your ${AS7} directory run:
    java -jar modules/com/h2database/h2/main/h2-1.2.145.jar


