FROM node:18-alpine AS react-builder

WORKDIR /app/frontend
COPY frontend/package*.json ./
RUN npm ci
COPY frontend/ ./
RUN npm run build

FROM maven:3.9.0-eclipse-temurin-17 AS java-builder

WORKDIR /app
COPY pom.xml .
COPY src/ ./src
COPY --from=react-builder /app/frontend/build ./src/main/resources/static

RUN mvn clean install -DskipTests

FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
COPY --from=java-builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
