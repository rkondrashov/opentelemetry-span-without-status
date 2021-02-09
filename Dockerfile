FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
RUN mkdir /usr/local/share/opentelemetry
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v0.15.1/opentelemetry-javaagent-all.jar /usr/local/share/opentelemetry/opentelemetry-javaagent-all.jar
RUN chmod a=r /usr/local/share/opentelemetry/opentelemetry-javaagent-all.jar
COPY target/opentelemetry-span-without-status-1.0.0.jar /srv/app.jar
ENTRYPOINT ["java","-javaagent:/usr/local/share/opentelemetry/opentelemetry-javaagent-all.jar","-jar","/srv/app.jar"]
