FROM adoptopenjdk/openjdk11:alpine-jre

LABEL maintainer="levisilvaz99 <andrei_e.n.d@hotmail.com>"

RUN mvn clean install

COPY target/demo-0.0.1-SNAPSHOT.jar backend-api.jar

CMD ["java", "-jar", "backend-api.jar"]
