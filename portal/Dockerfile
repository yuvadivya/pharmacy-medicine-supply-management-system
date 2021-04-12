FROM java:8-jdk-alpine
EXPOSE 8080
COPY ./target/portal-0.0.1-SNAPSHOT.war /usr/app/
WORKDIR /usr/app
RUN sh -c 'touch portal-0.0.1-SNAPSHOT.war'
ENTRYPOINT ["java","-jar","portal-0.0.1-SNAPSHOT.war"]
