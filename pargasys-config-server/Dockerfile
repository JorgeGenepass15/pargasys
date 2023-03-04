FROM openjdk:11.0.13-slim

LABEL maintainer="Angel Leonard <angel.n.cabanillas@gmail.com>"

ARG JAR_FILE

RUN echo ${JAR_FILE}

COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]