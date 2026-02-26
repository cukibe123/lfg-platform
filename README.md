# LFG Platform

Backend-first project for an LFG (Looking For Group) platform.

## Tech Stack
- Java 21
- Spring Boot 4
- Spring Data JPA (Hibernate)
- Flyway
- PostgreSQL 15
- Maven Wrapper (`mvnw`)

## Repository Structure
- `backend/` Spring Boot backend
- `frontend/` frontend workspace (currently empty)

## Backend Architecture
Current backend follows layered separation:
- `api/` HTTP controllers and DTOs
- `app/` application services/use-cases and app-level ports
- `domain/` entities and domain repository contracts
- `infra/` persistence/security adapters (JPA adapter, BCrypt hasher)

## Prerequisites
- JDK 21+
- Docker Desktop

## Run PostgreSQL (Docker)
From `backend/`:

```powershell
docker compose up -d
```

Default DB config (from `application.yml`):
- DB: `dev-lfg-db`
- User: `nanaut`
- Password: `nanaut`
- Port: `5432`

## Run Backend
From `backend/`:

```powershell
.\\mvnw.cmd spring-boot:run
```

App runs on `http://localhost:8080`.

## Flyway Migrations
Migrations are under `backend/src/main/resources/db/migration`.

If DB is empty and startup fails with `missing table [users]`, run migration manually:

```powershell
.\\mvnw.cmd --% -U flyway:migrate -Dflyway.url=jdbc:postgresql://localhost:5432/dev-lfg-db -Dflyway.user=nanaut -Dflyway.password=nanaut
```

Then run the app again.

## Current API
### Register user
`POST /auth/register`

Request body:

```json
{
  "email": "test@example.com",
  "rawPassword": "secret"
}
```

Success (`201 Created`):

```json
{
  "id": "<uuid>",
  "email": "test@example.com",
  "status": "ACTIVE"
}
```

Duplicate email:
- Handled by `GlobalExceptionHandler`
- Returns `409 Conflict` with `ProblemDetail` payload

## Tests
Run all tests:

```powershell
.\\mvnw.cmd test
```

Run specific test:

```powershell
.\\mvnw.cmd -Dtest=RegisterUserServiceTest test
```

## Notes
- `spring.jpa.hibernate.ddl-auto=validate` is enabled, so schema must already exist.
- Flyway should create schema via migrations; use manual migrate command above when needed.