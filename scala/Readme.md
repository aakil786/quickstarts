# Introduction
This quickstart demonstrates implementing a service in SwitchYard using Scala. The implementation itself is very basic, the intention
of this quickstart is to act as a starting point for users that want to write SwitchYard services in Scala and save them the hassle
of setting things up, like testing, and also to act as a reference.

The service is bound to a directory by the use of the Camel Component. When a file named test.txt appears in this directory 
the service will be invoked.

This quickstart uses Scala 2.9.0-1.

#Deploying to JBoss AS 7
1. To install SwitchYard to AS 7 follow the instructions presented [here](http://community.jboss.org/wiki/SwitchYardOnAS7)
2. Build the quickstart:

    `mvn clean install`
    
3. Add a module for scala.
You can copy the module definition from _src/test/resources/modules_.
   The scala-lang jar file can be retrieved either from you local maven repository or by running
   
    `mvn dependency:copy-dependencies`
    
   The scala-lang jar file will be in target/dependencies along with all other dependant jars.
   
4. Start JBoss AS 7 in standalone mode:

     `./standalone.sh` 
     
5. Start JBoss CLI and connect: 

    `./jboss-admin.sh --connect`
    
6. Deploy the quickstart

     `deploy  /path/to/quickstarts/scala/target/switchyard-quickstarts-scala-0.2.0-SNAPSHOT.jar`
     
7. Trigger the service

    `cp src/test/resources/text.txt target/input`

#Eclipse
Please not that the same version of Scala should be used in Eclipse. If the versions do not match you may get compilation
errors. 

Also not that you may need to add a Scala Nature after importing the maven project into eclipse. Simply right click the
imported project and select _Configure_->_Add Scala Nature_.
