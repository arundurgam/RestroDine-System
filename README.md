# 🍽️ Restaurant Ordering Microservices Platform

A scalable Restaurant Ordering System built using Java, Spring Boot, Spring Cloud, Apache Kafka, JWT Authentication, Eureka Service Discovery, API Gateway, and MySQL. The project demonstrates modern Microservices Architecture with both synchronous and asynchronous communication.

## 🚀 Features

### Customer
- User Registration
- Login using JWT Authentication
- View Restaurants
- Place Food Orders
- Track Orders

### Admin
- Manage Restaurants
- Manage Inventory
- View Orders
- Manage Delivery

### System
- API Gateway
- Service Discovery (Eureka)
- Kafka Event Communication
- JWT Authentication
- Microservices Architecture

---

## 🛠 Tech Stack

### Frontend
- React
- React Router
- Axios
- Vite

### Backend
- Spring Boot
- Spring Security
- Spring Cloud Gateway
- Eureka Discovery Server
- Spring Data JPA
- Hibernate
- MySQL
- Kafka
- OpenFeign

### DevOps
- Git
- GitHub
- GitHub Actions
- Vercel

---

## 📁 Project Structure

```
RestroDine-System

├── api-gateway
├── auth-service
├── restaurant-service
├── order-service
├── inventory-service
├── delivery-service
├── notification-service
├── discovery-server
├── kafka
├── restrodine-frontend
└── README.md
```

---

## 🏗 Architecture

```
                React Frontend
                      │
                      ▼
              API Gateway
                      │
        ┌─────────────┼─────────────┐
        ▼             ▼             ▼
 Auth Service   Restaurant Service  Order Service
                      │
                      ▼
              Inventory Service

                      ▼
              Delivery Service

                      ▼
           Notification Service

                      │
                 Eureka Server
```

---

## 🔐 Authentication

- JWT Authentication
- Role Based Authorization

Roles

- ADMIN
- CUSTOMER
- DELIVERY_AGENT

---

## 📦 Microservices

### API Gateway

Handles routing and security.

### Discovery Server

Service Registration and Discovery.

### Auth Service

Registration

Login

JWT Token Generation

### Restaurant Service

Restaurant CRUD

Restaurant Listing

### Order Service

Place Orders

Order History

### Inventory Service

Inventory Management

### Delivery Service

Delivery Tracking

### Notification Service

Order Notifications

---

## ⚙️ Installation

### Clone Repository

```bash
git clone https://github.com/arundurgam/RestroDine-System.git
```

### Backend

```bash
cd auth-service
mvn spring-boot:run
```

Run remaining services similarly.

### Frontend

```bash
cd restrodine-frontend

npm install

npm run dev
```

---

## 🔄 CI/CD

GitHub Actions is configured to

- Checkout Repository
- Build Spring Boot Services
- Build React Application

Workflow file

```
.github/workflows/ci.yml
```

Every push to the main branch automatically triggers the build.

---

## ☁ Deployment

Frontend

Deployed using Vercel.

Backend

Spring Boot Microservices (ready for deployment to Java hosting platforms such as Render, Railway, AWS, or Azure).

## 👨‍💻 Author
**Durgam Arun**

**GitHub: https://github.com/arundurgam
LinkedIn: https://www.linkedin.com/in/durgam-arun/
Live Application (Vercel): https://vercel.com/durgam-aruns-projects/restro-dine-system**
