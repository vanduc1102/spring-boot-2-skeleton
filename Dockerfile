# our base build image
FROM maven:3.3-jdk-8-alpine as maven

# copy the project files
COPY ./pom.xml ./pom.xml

# build all dependencies
RUN mvn dependency:go-offline -B

# copy your other files
COPY ./src ./src

# build for release
RUN mvn package

# our final base image
FROM openjdk:8-jre-alpine

# set deployment directory
WORKDIR /workspace

# copy over the built artifact from the maven image
COPY --from=maven target/skeleton*.jar ./app.jar
COPY --from=maven src/main/resources/* ./

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
