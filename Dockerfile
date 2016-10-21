FROM openjdk:8-jdk
EXPOSE 12345

ADD . /rpglifeappserver
WORKDIR /rpglifeappserver
RUN ./mvnw package
WORKDIR  /
RUN cp /rpglifeappserver/target/rpglifeapp-server-*.jar /rpglifeapp-server.jar \
    && rm -rf /rpglifeappserver

ENTRYPOINT ["java", "-jar", "rpglifeapp-server.jar"]
