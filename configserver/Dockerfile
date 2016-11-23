
FROM java:8
VOLUME /tmp
ADD  target/config-server-1.0.jar config-server.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","/config-server.jar"]

