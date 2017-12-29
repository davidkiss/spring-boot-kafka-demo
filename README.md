# Overview
This is a sample application to help you get started with Java / Spring Boot / Kafka.
 
Unlike other tutorials on the web, instead of hardcoding Kafka config params in the ```KafkaConfig``` class,
 it takes them from ```application.yaml``` config file (see ```spring.kafka``` properties)   

# Install Kafka
Follow the Kafka Quickstart tutorial to setup Kafka locally: https://kafka.apache.org/quickstart.

# Run Application
Run below command line in a terminal:
> mvn spring-boot:run

# Test Application
Open this url in your browser: http://localhost:8080/greetings?message=hello%20world.
  