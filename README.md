# 📝 NotebookAPI

A secure, feature-rich RESTful Notebook API built with **Spring Boot**, leveraging **Spring Security**, **Spring Data JPA**, and **Lombok**. This application allows users to register, login, manage their personal notes, and perform CRUD operations with authentication-based access control.

---

## ⚙️ Technologies Used

- **Java 21+**
- **Spring Boot**
- **Spring Security** (with JWT or session-based authentication)
- **Spring Data JPA**
- **Lombok**
- **PostgreSQL**
- **Docker**

---
## 🚀 Getting Started

### Prerequisites
- Java 21+
- Maven
- Docker
---

##  Features

###  Authentication
- `POST /auth/register`: Register a new user
- `POST /auth/login`: Login with username and password
- `GET /authenticated/dashboard`: Fetch secure dashboard data
- `POST /authenticated/logout`: Logout the authenticated user

### ️ Notes API (Requires authentication)
- `POST /notes/create`: Create a new note
- `GET /notes/getNotes`: Fetch all notes of the user
- `GET /notes/getNoteById/{id}`: Fetch a single note by ID
- `PUT /notes/updateNote/{id}`: Update a note by ID
- `PUT /notes/toggleNoteStatus/{id}`: Toggle note status (`pinned`, `archived`, `deleted`)
- `DELETE /notes/deleteNote/{id}`: Permanently delete a note by ID

---

### Run Locally

**Import `.env` variables to the environment firs then run the following commands -**

```bash
  docker compose up -d
  ./mvnw spring-boot:run
```
