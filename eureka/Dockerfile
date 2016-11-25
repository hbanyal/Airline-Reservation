
FROM java:8
VOLUME /tmp
ADD  target/eureka-1.0.jar eureka.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","/eureka.jar"]

