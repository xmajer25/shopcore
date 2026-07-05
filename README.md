# ShopCore

ShopCore is a small microservice e-shop backend built with Spring Boot.  
The project demonstrates service separation, JWT authentication, API Gateway routing, PostgreSQL persistence and Kafka-based asynchronous communication.

## Services

| Service | Port | Responsibility |
|---|---:|---|
| API Gateway | 8080 | Single entry point, routing, JWT validation |
| Product Service | 8081 | Product catalog |
| Auth Service | 8082 | User registration, login, JWT issuing |
| Order Service | 8083 | Draft orders, checkout, order status |
| Payment Service | 8084 | Mock payment processing |
| Kafka | 9094 | Async communication between services |

## Tech Stack

- Java 21
- Spring Boot
- Spring Security + JWT
- Spring Cloud Gateway
- Spring Data JPA
- PostgreSQL
- Kafka
- Docker Compose
- MapStruct
- Swagger / OpenAPI

## Run locally

From the project root:

```bash
docker-compose up -d --build