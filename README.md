# 🍽️ Restaurant Ordering Microservices Platform

A scalable Restaurant Ordering System built using Java, Spring Boot, Spring Cloud, Apache Kafka, JWT Authentication, Eureka Service Discovery, API Gateway, and MySQL. The project demonstrates modern Microservices Architecture with both synchronous and asynchronous communication.

---

## 🚀 Features

* User Registration & Login
* JWT Authentication & Authorization
* Role-Based Access Control (RBAC)
* Restaurant Management
* Order Management
* Inventory Management
* API Gateway Routing
* Service Discovery using Eureka
* Inter-Service Communication using OpenFeign
* Event-Driven Architecture using Apache Kafka
* Global Exception Handling
* MySQL Database Integration
* RESTful API Development
* Postman Collection for API Testing

---

## 🏗️ Microservices

### 1. Auth Service

* User Registration
* User Login
* JWT Token Generation
* Token Validation
* Role-Based Authentication

### 2. Restaurant Service

* Add Restaurant
* View Restaurants
* Get Restaurant by ID
* Custom Exception Handling

### 3. Order Service

* Place Order
* Validate Restaurant via OpenFeign
* Publish Kafka Events
* Global Exception Handling

### 4. Inventory Service

* Consume Order Events
* Update Inventory Automatically
* Kafka Consumer Implementation

### 5. API Gateway

* Centralized Routing
* JWT Validation
* Request Filtering

### 6. Eureka Discovery Server

* Service Registration
* Service Discovery

### 7. Notification Service

* Notification Service Structure

### 8. Delivery Service

* Delivery Service Structure

---

## 🔄 Architecture

```text
Client
   │
   ▼
API Gateway
   │
   ├── Auth Service
   ├── Restaurant Service
   ├── Order Service
   │       │
   │       ▼
   │   Kafka Topic
   │       │
   │       ▼
   └── Inventory Service

Eureka Server
   │
   └── Service Discovery
```

---

## 🛠️ Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring MVC
* Spring Security
* Spring Data JPA

### Microservices

* Spring Cloud
* Eureka Server
* OpenFeign
* API Gateway

### Security

* JWT Authentication
* Role-Based Access Control (RBAC)

### Messaging

* Apache Kafka
* Kafka Producer
* Kafka Consumer

### Database

* MySQL

### Build Tool

* Maven

### Testing

* Postman

### Containerization

* Docker
* Docker Compose

### Version Control

* Git
* GitHub

### IDE

* Eclipse IDE

---

## 📂 Project Structure

```text
RestroDine
│
├── api-gateway
├── auth-service
├── restaurant-service
├── order-service
├── inventory-service
├── notification-service
├── delivery-service
├── discovery-server
├── kafka
├── Postman Collection
└── README.md
```

---

## 🔐 Authentication

The application uses JWT-based authentication.

### Roles

* ADMIN
* CUSTOMER
* DELIVERY_AGENT

### Authorization Header

```http
Authorization: Bearer <JWT_TOKEN>
```

---

## 📡 Kafka Event Flow

```text
Order Service
      │
      ▼
order-created
      │
      ▼
Inventory Service
      │
      ▼
Inventory Updated
```

---

## 📮 API Testing

A Postman Collection is included in the repository for testing all APIs.

Features Covered:

* User Registration
* User Login
* Restaurant APIs
* Order APIs
* Inventory APIs
* JWT Authentication
* API Gateway Routes

---

## ⚙️ Setup Instructions

### Clone Repository

```bash
git clone https://github.com/<your-username>/restaurant-ordering-microservices-platform.git
```

### Start Kafka

```bash
docker compose up -d
```

### Start Services

1. Eureka Discovery Server
2. API Gateway
3. Auth Service
4. Restaurant Service
5. Order Service
6. Inventory Service
7. Notification Service
8. Delivery Service

---

## 📈 Key Concepts Implemented

* Microservices Architecture
* Service Discovery
* API Gateway Pattern
* Event-Driven Architecture
* JWT Authentication
* Role-Based Authorization
* Exception Handling
* OpenFeign Communication
* Kafka Messaging
* Database Integration
* REST API Development

---

## 👨‍💻 Author
**Durgam Arun**
* Java Backend Developer
* Spring Boot Developer
* Microservices Enthusiast

LinkedIn: https://www.linkedin.com/in/durgam-arun/

GitHub: Add Your GitHub Profile URL
