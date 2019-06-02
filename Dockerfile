FROM java:8-jre

ENV API_HOME /usr/local/api
ENV PATH $API_HOME:$PATH
WORKDIR $API_HOME

ADD target/sample-spring-api*.jar ./api.jar

EXPOSE 8080
CMD ["java", "-jar", "api.jar"]
