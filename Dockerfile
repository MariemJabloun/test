FROM openjdk:17-jdk-alpine
VOLUME /tmp
ADD target/canalplus-subscriber.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS /app.jar" ]
