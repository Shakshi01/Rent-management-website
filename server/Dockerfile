FROM eclipse-temurin:17-jdk-focal
WORKDIR /server
COPY target/server-0.0.1-SNAPSHOT.jar .
ENV SIMPLILEASE_DB_URL="localhost"
ENV SIMPLILEASE_DB_USER="root"
ENV SIMPLILEASE_DB_PASSWORD="password"
EXPOSE 8080
ENTRYPOINT ["java","-jar","server-0.0.1-SNAPSHOT.jar"]
