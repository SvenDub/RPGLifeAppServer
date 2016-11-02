FROM openjdk:8-jdk
EXPOSE 12345

ADD .mvn /rpglifeappserver/.mvn
ADD pom.xml mvnw mvnw.cmd /rpglifeappserver/
WORKDIR /rpglifeappserver
RUN ./mvnw dependency:resolve dependency:resolve-plugins -B
ADD . /rpglifeappserver
RUN ./mvnw package -B
WORKDIR  /
RUN cp /rpglifeappserver/target/rpglifeapp-server-*.jar /rpglifeapp-server.jar \
    && rm -rf /rpglifeappserver

ENTRYPOINT ["java", "-jar", "rpglifeapp-server.jar"]
