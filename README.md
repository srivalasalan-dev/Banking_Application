# Banking Management System

A Banking Management System built using **Spring Boot Microservices** to understand how enterprise banking applications are designed and developed.

This project was created as a learning project to explore Spring Boot, Microservices, Spring Security, JWT Authentication, Eureka Service Discovery, API Gateway, OpenFeign, JPA/Hibernate, and MySQL. It covers the complete banking flow from customer registration to fund transfer while following a microservices architecture.

---

## Features

### Authentication
- Customer Registration
- Customer Login
- JWT Authentication
- Password Encryption

### Customer Management
- View Customer Details
- Update Customer Information
- Search Customer
- Delete Customer

### Account Management
- Create Bank Account
- Deposit Money
- Withdraw Money
- Check Account Balance
- Search Account

### Transaction Management
- Fund Transfer
- View Transaction History

---

## Technologies Used

- Java 25
- Spring Boot
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Spring Cloud Eureka
- Spring Cloud Gateway
- OpenFeign
- Maven
- Lombok
- Validation API

---

## Project Structure

```
Banking-Microservices
│
├── eureka-server
├── api-gateway
├── auth-service
├── customer-service
├── account-service
├── transaction-service
└── README.md
```

---


Each service has its own responsibility and database, allowing them to work independently while communicating through REST APIs and OpenFeign.

---

## Databases

| Service | Database |
|---------|----------|
| Auth Service | auth_db |
| Customer Service | customer_db |
| Account Service | account_db |
| Transaction Service | transaction_db |

---

## Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/your-username/banking-microservices.git
```

### 2. Create the databases

```sql
CREATE DATABASE auth_db;
CREATE DATABASE customer_db;
CREATE DATABASE account_db;
CREATE DATABASE transaction_db;
```

### 3. Configure MySQL

Update the `application.yml` (or `application.properties`) file in each service with your MySQL username and password.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/auth_db
spring.datasource.username=root
spring.datasource.password=root
```

### 4. Start the services

Run the applications in the following order:

1. Eureka Server
2. API Gateway
3. Authentication Service
4. Customer Service
5. Account Service
6. Transaction Service

---

## API Overview

### Authentication

| Method | Endpoint |
|---------|----------|
| POST | `/auth/register` |
| POST | `/auth/login` |

### Customer

| Method | Endpoint |
|---------|----------|
| GET | `/customers` |
| GET | `/customers/{id}` |
| PUT | `/customers/{id}` |
| DELETE | `/customers/{id}` |
| GET | `/customers/search?email=` |

### Account

| Method | Endpoint |
|---------|----------|
| POST | `/accounts` |
| POST | `/accounts/deposit` |
| POST | `/accounts/withdraw` |
| GET | `/accounts/balance/{accountNumber}` |
| GET | `/accounts/search/{accountNumber}` |

### Transaction

| Method | Endpoint |
|---------|----------|
| POST | `/transactions/transfer` |
| GET | `/transactions/{accountNumber}` |

---

## Workflow

```
Customer Registration
        │
        ▼
Customer Login
        │
        ▼
JWT Token Generated
        │
        ▼
Create Bank Account
        │
        ▼
Deposit / Withdraw Money
        │
        ▼
Transfer Funds
        │
        ▼
View Transaction History
```

---

## Concepts Covered


- Core Java
- Collections Framework
- Exception Handling
- Multithreading
- Executor Framework
- Callable & Future
- SQL
- Spring Boot
- Spring Security
- JWT Authentication
- REST APIs
- JPA & Hibernate
- Bean Validation
- Global Exception Handling
- Microservices
- Eureka Service Discovery
- API Gateway
- OpenFeign

---


## Postman Collection

A Postman collection is included for testing all APIs.

Import the collection into Postman and execute the requests in the following order:

1. Register
2. Login
3. Create Account
4. Deposit
5. Withdraw
6. Transfer
7. View Transaction History

---


---

If you have any suggestions or improvements, feel free to open an issue or submit a pull request.
