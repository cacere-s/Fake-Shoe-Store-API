FROM openjdk:17-jdk-slim
ENV DB_URL=$DB_URL
ENV DB_USERNAME=$DB_USERNAME
ENV DB_PASSWORD=$DB_PASSWORD
ARG JAR_FILE=build/libs/ShoeStore-0.0.1-SNAPSHOT-plain.jar
COPY ${JAR_FILE} app_fakestoreapi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_fakestoreapi.jar"]