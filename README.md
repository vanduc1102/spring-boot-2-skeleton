# Wizeline-Spring-Boot-Skeleton

## Development

### Setup Environment

1. Install JDK-8 latest. Current version [Java SE 8u231](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
2. [Docker](https://docs.docker.com/docker-for-mac/)
3. Setup pre-commit hook, open `pre-commit.tpl`
4. (Optional) IntelliJ IDEA, checkout [this guide line](https://github.com/google/google-java-format#intellij)

### Start development

* Run `cp docker-compose.override-sample.yaml docker-compose.override.yaml`
* Start MySQL database: `docker-compose up`
* (Optional) Create `application-local.yml` for customizing properties.
* Start Spring Boot Application `./mvnw spring-boot:run`
* Swagger-UI: http://localhost:8449/swagger-ui.html

## Deployment 

* Set `spring.profiles.active=production` to turn off Swagger-UI 
