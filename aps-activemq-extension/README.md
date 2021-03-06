#### Alfresco Process Service (APS) integration with Apache ActiveMQ (this can be extended to any JMS providers). 

This is a very simple extension project/jar demonstrating the integration of [Alfresco Process Services](https://www.alfresco.com/platform/process-services-bpm) with [Apache ActiveMQ](http://activemq.apache.org/)

This project is also explained in this [community blog](https://community.alfresco.com/community/bpm/blog/2017/05/23/integrating-alfresco-process-services-with-amazon-sqs-and-apache-activemq)


## Prerequisites
1. This example is built and tested against Alfresco Process Service Version 1.6.1 and ActiveMQ 5.14.5
2. Apache ActiveMQ must be installed and started. The extension project is built using the default credentials & ports which Active MQ use which are port 61616 and admin/admin respectively. Since I haven't externalized these properties you will need to recompile this project if you have a different connection setting.

## Configuration Steps

1. Deploy all the jar files available in this project to activiti-app/WEB-INF/lib. When using version 1.6.3 or newer (tested until 1.7.0), it seems using the activemq-all-*.jar is causing issues on startup. So, instead of copying the jars availabile in the project, install the following jars into lib.  Thanks to [Greg Harley](https://github.com/gdharley) for pointing this out!
```
spring-jms-4.3.8.RELEASE.jar
activemq-core-<version>.jar
spring-messaging-4.3.8.RELEASE.jar
javax.management.j2ee-api-1.1.1.jar
javax.jms-api-2.0.1.jar
And obviously, the extension library in this project too.
```
2. Import the "ActiveMQ App.zip" in this project into your instance and publish the app.
3. Start a process instance by sending a message to the queue "aps-inbound". Once the message is published, login to APS as "admin@app.activiti.com" (default user) and you will see that a process instance has been started upon a message publish. The process instance is started by the "ActiveMQListener.java" component in this project which is basically a JMS listener in APS. In the demo app, the "ActiveMQSender.java" class used to send a message to ActiveMQ from the process instance is exposed as a BPMN stencil(re-usable custom component) component. I have built the demo process in such a way that, if you use the string "Reply Back" as your input message, the process instance will send a message back to the JMS queue "aps-outbound" immediately. This demonstrates the outbound integration from APS to ActiveMQ.

