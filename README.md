# Tasks API - Nuevo SPA Code Challenge

REST API for task management built with Spring Boot, JWT authentication and API First methodology.

## Tech Stack

- Java 21
- Spring Boot 3.3.5
- Spring Security + JWT
- Spring Data JPA
- H2 (in-memory database)
- OpenAPI / Swagger UI
- API First (openapi-generator-maven-plugin)
- Lombok
- Maven

## Architecture

Hexagonal Architecture (Ports & Adapters):

```
com.nuevospa.tasks
├── application
│   ├── port
│   │   ├── in        # Input ports (use case interfaces)
│   │   └── out       # Output ports (repository interfaces)
│   └── usecase       # Use case implementations
├── domain
│   ├── model         # Pure domain models
│   └── exception     # Domain exceptions
└── infrastructure
├── adapter
│   ├── in
│   │   └── rest  # REST controllers
│   └── out
│       └── persistence  # JPA adapters
├── config        # Security, JWT, Swagger
└── entity        # JPA entities
```

## API First

The API contract is defined first in `src/main/resources/openapi.yml`.
The plugin generates interfaces and models automatically:

```bash
mvn generate-sources
```

Generated code is available at `target/generated-sources/openapi`.

## Running the app

```bash
mvn spring-boot:run
```

App runs on `http://localhost:8080`

## Swagger UI

```bash
http://localhost:8080/swagger-ui/index.html
```

## Preloaded data

**Users:**
| Username | Password | Email |
|---|---|---|
| admin | password | admin@nuevospa.com |
| john.doe | password | john@nuevospa.com |

**Task statuses:**
| ID | Name |
|---|---|
| c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33 | PENDING |
| d3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44 | IN_PROGRESS |
| e4eebc99-9c0b-4ef8-bb6d-6bb9bd380a55 | COMPLETED |
| f5eebc99-9c0b-4ef8-bb6d-6bb9bd380a66 | CANCELLED |

## Endpoints

### Authentication

#### POST /api/auth/login
```json
{
    "username": "admin",
    "password": "password"
}
```
Response:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9...",
    "type": "Bearer",
    "username": "admin"
}
```

### Tasks (requires Bearer token)

#### GET /api/tasks
Returns all tasks.

#### GET /api/tasks/{id}
Returns task by id.

#### POST /api/tasks
```json
{
    "title": "Implement login",
    "description": "Implement JWT authentication",
    "statusId": "c2eebc99-9c0b-4ef8-bb6d-6bb9bd380a33",
    "assignedUserId": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"
}
```

#### PUT /api/tasks/{id}
```json
{
    "title": "Implement login updated",
    "description": "Updated description",
    "statusId": "d3eebc99-9c0b-4ef8-bb6d-6bb9bd380a44",
    "assignedUserId": "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11"
}
```

#### DELETE /api/tasks/{id}
Returns 204 No Content.

## Running tests

```bash
mvn test
```

## HTTP Status codes

| Code | Meaning |
|---|---|
| 200 | OK |
| 201 | Created |
| 204 | No Content |
| 400 | Bad Request |
| 401 | Unauthorized |
| 404 | Not Found |
| 500 | Internal Server Error |