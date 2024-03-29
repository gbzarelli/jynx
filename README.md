![Issues](https://img.shields.io/github/issues/gbzarelli/jynx.svg) 
![Forks](https://img.shields.io/github/forks/gbzarelli/jynx.svg) 
![Stars](https://img.shields.io/github/stars/gbzarelli/jynx.svg) 
![Release Version](https://img.shields.io/github/release/gbzarelli/jynx.svg)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/2e1e3fc1bed3498e8b55aff82c503b92)](https://app.codacy.com/manual/gbzarelli/jynx?utm_source=github.com&utm_medium=referral&utm_content=gbzarelli/jynx&utm_campaign=Badge_Grade_Dashboard)
![Build WorkFlow](https://github.com/gbzarelli/jynx/workflows/Jynx%20Build%20Workflow/badge.svg) 

# Jynx (Project under development...) 

<p align="center">
    <img src="./images/jynx.png" height="450">
</p>

Jynx is an asynchronous label image detector. It exposes an endpoint that receive any image, write in a storage, 
send to a processing queue using RabbitMQ and the consumer renders the image using Google Vision API.

The Jynx is my first project in Quarkus the Supersonic Subatomic Java Framework and the goal is improve my knowledge 
with this Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Technologies

- [`Quarkus`](https://quarkus.io/) - Framework
- [`PostgreSQL`](https://www.postgresql.org) - Database
- [`RabbitMQ`](https://www.rabbitmq.com) - Messaging Server
- [`ArchUnit`](https://www.archunit.org) - Unit test your Java architecture 
- [`GCP Vision`](https://cloud.google.com/vision) - Google Cloud Machine Learn
- [`GCP Storage`](https://cloud.google.com/storage) - Google Cloud Storage
- [`Hibernate ORM with Panache`](https://quarkus.io/guides/hibernate-orm-panache) - Hibernate ORM is the de facto JPA implementation
- [`GitHub Actions`](https://docs.github.com/en/actions) - Automate, customize, and execute your software development workflows (CI/CD)br

# How this project work

This project has one endpoint to register images, it writes that image in a storage, register the information in 
a database and send the identification in a messaging service to be async processed. This endpoint return an 
identification marked with processing status.

The async process will be started when the queue receives the message. The routine get the identification message 
to will recovery the information of database and recovery the image to will be process. The process send to label 
image detection service the image, and the response will be recorded in database and finally will notify the 
messaging service in other exchange.

<p align="center">
    <img src="./images/flux-jynx.png" width="250">
</p>

# Architecture

The architecture used in this project was based on `Clean Architecture` but more flexible with only one layer separated by packages.

<p align="center">
    <img src="./images/helpdev-clean-arch.png" height="450">
    <br>
    <a href="https://medium.com/luizalabs/descomplicando-a-clean-architecture-cf4dfc4a1ac6">
    Article about Clean Architecture by Guilherme Zarelli</a>
</p>

To guarantee this architecture, the dependency `ArchUnit` will be used.

<p align="center">
    <img src="./images/archunit.png" height="380">
    <br>
</p>

## Environments

| ENV                            	| Description             	| Default value   	|
|--------------------------------	|-------------------------	|-----------------	|
| DATABASE_URL                   	| Database URL            	| IN DEVELOP MODE 	|
| DATABASE_USERNAME              	| Database username       	| IN DEVELOP MODE 	|
| DATABASE_PASSWORD              	| Database Password       	| IN DEVELOP MODE 	|
| RABBITMQ_HOST                  	| RabbitMQ Host           	| IN DEVELOP MODE 	|
| RABBITMQ_VHOST                 	| RabbitMQ Vhost          	| IN DEVELOP MODE 	|
| RABBITMQ_USERNAME              	| RabbitMQ username       	| IN DEVELOP MODE 	|
| RABBITMQ_PASSWORD              	| RabbitMQ password       	| IN DEVELOP MODE 	|
| GOOGLE_APPLICATION_CREDENTIALS 	| File of GCP Credentials 	| NO              	|

## Running the application in dev mode

You can run the infrastructure needed for this project using the (`docker-compose.yml`)[./docker-compose.yml]

```
docker-compose up -d
```

You can run your application in dev mode that enables live coding using:

```
./gradlew quarkusDev
```

Access Swagger UI:

```
http://localhost:8080/q/swagger-ui
```

### Requests Samples

See the request samples in this file: [request-samples.html](./request-samples.html)

## Packaging and running the application

The application can be packaged using `./gradlew quarkusBuild`.
It produces the `jynx-1.0-SNAPSHOT-runner.jar` file in the `build` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/lib` directory.

The application is now runnable using `java -jar build/jynx-1.0-SNAPSHOT-runner.jar`.

If you want to build an _über-jar_, just add the `--uber-jar` option to the command line:
```
./gradlew quarkusBuild --uber-jar
```

## Creating a native executable

You can create a native executable using: `./gradlew build -Dquarkus.package.type=native`.

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: `./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true`.

You can then execute your native executable with: `./build/jynx-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling#building-a-native-executable.
