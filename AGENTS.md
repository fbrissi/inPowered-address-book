# Repository Guide

## Project

- This is a single-project Gradle build named `inPowered`; there are no subprojects.
- Main sources are under `src/main/java/fbrissi/dev/inPowered`; the Spring Boot entrypoint is `AddressBookApplication`.
- `AddressBook.txt` is a root-level input/data file; do not assume it is on the classpath.

## Toolchain

- Use Java 26. The Gradle toolchain requires Java 26, and `.sdkmanrc` specifies `java=26.0.2-amzn`.
- Use the Gradle wrapper (`./gradlew`), which is pinned to Gradle 9.7.1.
- This project was created with Kool.dev presets using the Kool development version. Kool is optional: `kool.yml` runs Gradle inside the `gradle:jdk26` Docker image, so Docker commands can be used directly.
- Kool does not currently have an official Java preset. The Java/Spring Boot preset work is proposed in [kool-dev/kool#542](https://github.com/kool-dev/kool/pull/542); this does not block running the project.

## Commands

- Build and test: `./gradlew build --no-daemon`
- Run Checkstyle for main and test sources: `./gradlew checkstyleMain checkstyleTest --no-daemon`
- Run all tests: `./gradlew test --no-daemon`
- Run the context test only: `./gradlew test --tests 'fbrissi.dev.inPowered.AddressBookApplicationTests' --no-daemon`
- Run the application: `./gradlew bootRun --no-daemon`
- Gradle can also run directly on the host when Java 26 and a compatible Gradle installation are available: `gradle bootRun --no-daemon`.
- Kool equivalents are `kool run build`, `kool run test`, and `kool run app`.
- The direct Docker equivalent of `kool run app` is `docker run --init --rm -w /app -i -t --volume .:/app:delegated gradle:jdk26 gradle bootRun --no-daemon`.

## Tests

- Tests use JUnit Platform via `useJUnitPlatform()` and currently include a Spring `@SpringBootTest` context-load test.
