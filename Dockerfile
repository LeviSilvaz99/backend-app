FROM adoptopenjdk/openjdk11:alpine-jre

LABEL maintainer="levisilvaz99 <andrei_e.n.d@hotmail.com>"

COPY --from=0 "target/demo-0.0.1-SNAPSHOT.jar" backend-api.jar

RUN mvn clean install

CMD ["java", "-jar", "backend-api.jar", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar backend-api.jar"]
