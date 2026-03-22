# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Personal blog backend built with Spring Boot 4.0.4 and Java 21. The goal is to serve blog posts via REST API for a React frontend.

## Technology Stack

- **Spring Boot 4.0.4** with Spring Web MVC
- **Java 21**
- **PostgreSQL** (production database)
- **Flyway** for database migrations
- **Spring Security** (configured)
- **Spring Data JPA** for data persistence
- **Lombok** for boilerplate reduction
- **SpringDoc OpenAPI** for API documentation
- **Maven** for dependency management

## Development Commands

### Running the application
```bash
./mvnw spring-boot:run
```

The application uses Docker Compose support - PostgreSQL will start automatically via compose.yaml when running the app.

### Building
```bash
./mvnw clean package
```

### Running tests
```bash
./mvnw test
```

### Running a single test
```bash
./mvnw test -Dtest=ClassName#methodName
```

## Database Setup

PostgreSQL is configured via Docker Compose (compose.yaml):
- Database: `mydatabase`
- User: `myuser`
- Password: `secret`
- Port: 5432

Flyway migrations should be placed in `src/main/resources/db/migration/` following the naming convention `V{version}__{description}.sql` (e.g., `V1__create_posts_table.sql`).

## Project Structure

- **Package base**: `com.example.blog`
- **Main class**: `BlogApplication.java`
- **Configuration**: `src/main/resources/application.properties`

### Expected architecture pattern (to be implemented)

Follow Spring Boot layered architecture:
1. **Controller** layer (`controller/`) - REST endpoints, HTTP handling
2. **Service** layer (`service/`) - Business logic
3. **Repository** layer (`repository/`) - Data access with Spring Data JPA
4. **Model/Entity** layer (`model/` or `entity/`) - JPA entities
5. **DTO** layer (`dto/`) - Data transfer objects for API responses

## Security Notes

Spring Security is included. For initial development of a public read-only blog, you may need to configure it to permit public access to GET endpoints while securing any future admin/write endpoints.

## API Documentation

SpringDoc OpenAPI is configured. Once the app is running, access Swagger UI at:
```
http://localhost:8080/swagger-ui.html
```

## Current State

This is a fresh Spring Boot project with dependencies configured but no domain logic implemented yet. The initial goal is to create a simple blog post API where users can retrieve and view blog posts.
