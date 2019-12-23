# acm-skeleton

## Setup
1. Java JDK 1.8
2. Setup pre-commit, open `pre-commit.tpl`

## Start development

* Start project `./mvnw spring-boot:run`
* Create `application-local.yml` for custom properties.
* Correct format `./mvnw fmt:format`, if you are using IntelliJ IDEA, checkout [this guide line](https://github.com/google/google-java-format#intellij)
* Swagger-UI: http://localhost:8449/swagger-ui.html

## Deployment 

* Set `spring.profiles.active=production` to turn off Swagger-UI 
