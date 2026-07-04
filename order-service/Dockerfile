FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /workspace

COPY . .

RUN chmod +x mvnw && ./mvnw -pl order-service -am clean package -DskipTests


FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=build /workspace/order-service/target/order-service-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]