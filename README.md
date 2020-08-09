![Issues](https://img.shields.io/github/issues/gbzarelli/jynx.svg) 
![Forks](https://img.shields.io/github/forks/gbzarelli/jynx.svg) 
![Stars](https://img.shields.io/github/stars/gbzarelli/jynx.svg) 
![Release Version](https://img.shields.io/github/release/gbzarelli/jynx.svg)

# Jynx ![Build WorkFlow](https://github.com/gbzarelli/jynx/workflows/Jynx%20Build%20Workflow/badge.svg)

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/292910e8ba154dcf8fb6eb6198e61cc0)](https://app.codacy.com/manual/gbzarelli/jynx?utm_source=github.com&utm_medium=referral&utm_content=gbzarelli/jynx&utm_campaign=Badge_Grade_Dashboard)

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
- [`GCP Vision`](https://cloud.google.com/vision) - Google Cloud Machine Learn
- [`GCP Storage`](https://cloud.google.com/storage) - Google Cloud Storage
- [`ORM Hibernate / Panache`](https://quarkus.io/guides/hibernate-orm-panache) - Hibernate ORM is the de facto JPA implementation
- [`GitHub Actions`](https://docs.github.com/en/actions) - Automate, customize, and execute your software development workflows (CI/CD)

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```
./gradlew quarkusDev
```

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
