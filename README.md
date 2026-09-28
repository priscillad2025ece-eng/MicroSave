# MicroSave — Self-Help Group Savings Tracker

A semester-project example built with Java 17, Spring Boot, Spring Data JPA, Jakarta Validation, MySQL, and a vanilla HTML/CSS/JavaScript frontend. Spring Boot serves the dashboard and REST API from the same origin.

## Run locally

1. Install Java 17+ and Maven, and start a local MySQL server.
2. Create the database (or let the configured MySQL URL create it for a user with that permission):

   ```sql
   CREATE DATABASE microsave;
   ```

3. Open `src/main/resources/application.properties` and replace `YOUR_PASSWORD` with your local MySQL root password. The project does not include a real password.
4. From the project root run:

   ```shell
   mvn spring-boot:run
   ```

5. Open [http://localhost:8080](http://localhost:8080). Hibernate creates/updates the five tables using the entity relationships (`ddl-auto=update`).

## Project structure

```text
src/main/java/com/example/microsave/
├── MicroSaveApplication.java
├── exception/
├── group/{controller,dto,entity,repository,service}/
├── member/{controller,dto,entity,repository,service}/
├── contribution/{controller,dto,entity,repository,service}/
├── loan/{controller,dto,entity,repository,service}/
└── repayment/{controller,dto,entity,repository,service}/
src/main/resources/
├── application.properties
└── static/{index.html,css/style.css,js/app.js}
```

## REST endpoints

| Feature | Endpoints |
| --- | --- |
| Groups | `GET/POST /api/groups`, `GET/PUT/DELETE /api/groups/{id}`, `GET /api/groups/{id}/available-pool` |
| Members | `GET/POST /api/members`, `GET/PUT/DELETE /api/members/{id}`, `GET /api/members/group/{groupId}` |
| Contributions | `GET/POST /api/contributions`, `GET/PUT/DELETE /api/contributions/{id}`, `GET /api/contributions/member/{memberId}`, `GET /api/contributions/member/{memberId}/total` |
| Loans | `GET/POST /api/loans`, `GET/PUT/DELETE /api/loans/{id}`, `GET /api/loans/member/{memberId}`, `GET /api/loans/total-outstanding` |
| Repayments | `GET/POST /api/repayments`, `GET/PUT/DELETE /api/repayments/{id}`, `GET /api/repayments/loan/{loanId}` |

All request and response bodies use feature DTOs. Validation failures return HTTP 400, missing records return 404, and business-rule conflicts return 409 with a JSON `message`.

## Business rules

- A group's available pool is the sum of its member contributions less outstanding balances of that group's loans.
- A loan cannot exceed the pool and a member cannot have two outstanding active loans.
- Repayments must be positive and cannot exceed the loan balance. A repayment and the corresponding loan balance/status update share one transaction.
- A fully repaid loan is marked `CLOSED`.
- Loans with repayment history cannot be edited or deleted; corrections should be entered as appropriate repayment transactions to preserve the audit trail.

## Viva architecture

```text
Browser (HTML/CSS/Fetch API) → REST Controller → DTO → Service → Repository → Entity → MySQL
```

The database tables are `groups`, `members`, `contributions`, `loans`, and `repayments`. Their foreign keys follow the one-to-many relationships in the project brief.
