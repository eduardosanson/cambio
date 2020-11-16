FROM openjdk:11.0.9.1-jre-slim
WORKDIR app
ADD target/*.jar app.jar
ARG PORT=8080
EXPOSE ${PORT}
CMD ["./app.jar"]