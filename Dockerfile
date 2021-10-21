FROM openjdk
LABEL maintainer="levisilvaz99 <andrei_e.n.d@hotmail.com>"
WORKDIR /app
COPY target/demo-0.0.1-SNAPSHOT.jar /app/backend-api.jar
EXPOSE 8080