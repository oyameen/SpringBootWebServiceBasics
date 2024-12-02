FROM gradle:jdk21-alpine AS base

WORKDIR /app

COPY build.gradle settings.gradle ./
COPY src/ ./src/

RUN gradle clean build -x test --parallel

FROM base AS build

WORKDIR /application

COPY --from=base /app/build/libs/employee-web-service-1.0.jar output/app.jar
COPY --from=base /app/src/main/resources/application.properties config/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "output/app.jar"]