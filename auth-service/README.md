# Auth Service

Proyecto base de auth-service con enfoque contract-first.

## Estructura Java

- api
  - dto
  - controller
- domain
  - mapper
  - policy
  - port
  - service
- infrastructure
  - decorator

## Contrato OpenAPI

- src/main/resources/openapi/auth-service-api.yml

## Endpoints

- POST /api/auth/login
- POST /api/auth/refresh
- POST /api/auth/logout
- GET /api/auth/me

## Ejecucion

1. mvn clean package
2. mvn spring-boot:run

## Swagger

- http://localhost:7001/swagger-ui.html
- http://localhost:7001/v3/api-docs
