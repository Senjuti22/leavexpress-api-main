# LeaveXpress API

Spring Boot REST API for managing employee leave requests with MySQL persistence, HTTP Basic security, and role-based approval workflows.

## Overview

This project demonstrates a compact Spring Boot API for a leave request and approval system. It models the full leave lifecycle from application submission through status checks and manager decisions, while using layered services, repository-backed persistence, and method-level authorization.

## Concepts and Features Covered

- Spring Boot REST API setup
- Spring Data JPA repository pattern
- MySQL-backed persistence
- HTTP Basic authentication with Spring Security
- Method-level security using `@PreAuthorize`
- In-memory users with `EMPLOYEE` and `MANAGER` roles
- DTO-based leave creation and update flow
- `GET` endpoint for retrieving a leave request by ID
- `GET` endpoint for listing all leave requests
- `GET` endpoints for accepted and rejected leave filtering
- `GET` endpoint for checking leave approval status
- `POST` endpoint for applying for leave
- `PUT` endpoint for updating an existing leave request
- `DELETE` endpoint for deleting a leave request
- Manager-only endpoints for accepting and rejecting leave requests
- Custom exception handling for missing leave records

## Tech Stack

- Java 17
- Spring Boot 2.7
- Spring Web
- Spring Data JPA
- Spring Security
- MySQL
- Maven
- Lombok

## Project Structure

```text
LeaveXpress Template/
├── CHANGELOG.md
├── README.md
├── pom.xml
├── mvnw
├── mvnw.cmd
└── src/
    └── main/
        ├── java/com/CodingNinjas/LeaveXpress/
        │   ├── config/
        │   ├── controller/
        │   ├── dto/
        │   ├── exception/
        │   ├── model/
        │   ├── repository/
        │   ├── service/
        │   └── LeaveXpressApplication.java
        └── resources/
            └── application.yml
```

## How to Run

1. Open a terminal in the project root.
2. Update the MySQL connection values in `src/main/resources/application.yml` if needed.
3. Run `mvn test`.
4. Run `mvn spring-boot:run`.
5. Use HTTP Basic authentication with one of the configured users:
   Username: `john`
   Password: `john123`
   Role: `EMPLOYEE`

   Username: `manager`
   Password: `manager321`
   Role: `MANAGER`
6. Use the API under `http://localhost:8080/api/leave`.

Available endpoints:

- `GET /api/leave/{id}`
- `GET /api/leave/all`
- `GET /api/leave/accepted`
- `GET /api/leave/rejected`
- `GET /api/leave/status/{id}`
- `POST /api/leave/apply`
- `PUT /api/leave/{id}`
- `DELETE /api/leave/{id}`
- `POST /api/leave/accept/{id}`
- `POST /api/leave/reject/{id}`

Access notes:

- `EMPLOYEE` and `MANAGER` can read, apply, update, and delete leave requests
- only `MANAGER` can accept or reject leave requests

Example request body:

```json
{
  "type": "Annual Leave",
  "startDate": "2026-04-15",
  "endDate": "2026-04-18",
  "description": "Family event"
}
```

## Learning Highlights

- Demonstrates method-level authorization for workflow-style REST APIs
- Shows how role-based permissions can separate employee actions from manager approvals
- Uses a derived query to filter accepted and rejected leave requests
- Keeps the leave lifecycle compact and easy to understand while still covering meaningful domain behavior

## GitHub Metadata

- Suggested repository description: `Spring Boot REST API for managing employee leave requests with MySQL persistence, HTTP Basic security, and role-based approval workflows.`
- Suggested topics: `java`, `java-17`, `spring-boot`, `spring-security`, `spring-data-jpa`, `mysql`, `rest-api`, `leave-management`, `basic-auth`, `maven`, `learning-project`, `portfolio-project`
