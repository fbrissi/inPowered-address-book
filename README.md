# inPowered Address Book

Spring Boot address book application built with Java 26 and Gradle.

## Requirements

- Docker
- [Kool](https://kool.dev), optional
- Java 26, only when running Gradle directly on the host

This project was created using [Kool.dev presets](https://kool.dev/docs/presets/about-presets) and the Kool development version. Kool provides convenient commands, but it is only a facilitator: the same commands can be run directly with Docker. The Java/Spring Boot preset is not available in the official Kool release yet; the work is proposed in [kool-dev/kool#542](https://github.com/kool-dev/kool/pull/542). This does not block running the project.

## Run With Kool

From the repository root:

```bash
kool run app
```

The application starts through Gradle in the `gradle:jdk26` container.

Other available commands:

```bash
kool run build
kool run lint
kool run test
kool run integration-test
```

## Run With Docker

Kool is not required. The direct Docker equivalent of `kool run app` is:

```bash
docker run --init --rm -w /app -i -t --volume .:/app:delegated gradle:jdk26 gradle bootRun --no-daemon
```

To build or test without Kool:

```bash
docker run --init --rm -w /app -i -t --volume .:/app:delegated gradle:jdk26 gradle build --no-daemon
docker run --init --rm -w /app -i -t --volume .:/app:delegated gradle:jdk26 gradle check --no-daemon
docker run --init --rm -w /app -i -t --volume .:/app:delegated gradle:jdk26 gradle test --no-daemon
docker run --init --rm -w /app -i -t --volume .:/app:delegated gradle:jdk26 gradle integrationTest --no-daemon
```

## Run With Local Java

With Java 26 available locally, use the Gradle wrapper:

```bash
./gradlew bootRun --no-daemon
./gradlew build --no-daemon
./gradlew test --no-daemon
./gradlew integrationTest --no-daemon
./gradlew codeCoverageVerification --no-daemon
./gradlew check --no-daemon
```

Gradle can also be run directly without Docker or Kool when a compatible Gradle installation is available:

```bash
gradle bootRun --no-daemon
gradle build --no-daemon
gradle test --no-daemon
gradle integrationTest --no-daemon
gradle codeCoverageVerification --no-daemon
gradle check --no-daemon
```

The wrapper is preferred for reproducible builds because it uses the repository-pinned Gradle version.

The Gradle toolchain requires Java 26. `.sdkmanrc` specifies Amazon Corretto `26.0.2-amzn` for SDKMAN users.

## Project Layout

- `src/main/java/fbrissi/dev/inPowered`: application source
- `src/test/unit/java`: unit tests
- `src/test/integration/java`: integration tests
- `build/reports/jacoco/codeCoverageReport/html/index.html`: HTML coverage report
- `src/main/resources/application.properties`: Spring Boot configuration
- `AddressBook.txt`: root-level input data file
- `kool.yml`: Kool command definitions

The Spring Boot entrypoint is `fbrissi.dev.inPowered.AddressBookApplication`.

## Releases

The `Release` GitHub Actions workflow can be started manually from the Actions tab. It validates and builds the application, creates a semantic-version tag, and generates a GitHub release. Deployment is intentionally not part of this repository's release flow.
