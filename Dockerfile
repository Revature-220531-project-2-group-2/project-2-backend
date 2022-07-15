#Declare the base image - here is a light weight JDK 8 env setup
FROM openjdk:8-jdk-alpine

#Copy the jar produced from the mvn clean package phase form the target to the inside of the container
COPY /target/dungens-and-dragons-app-SNAPSHOT.jar dungens-and-dragons-app-SNAPSHOT.jar

#Expose port 5000 of the container
EXPOSE 5000

#Run the JAR when you run the container, thus executing the app
ENTRYPOINT ["java","-jar", "dungens-and-dragons-app-0.0.1-SNAPSHOT.jar"]
