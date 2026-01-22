# 🌱 Crop Monitoring System

The **Crop Monitoring System** is a Spring Boot–based backend application designed to help farmers and administrators manage crop data, production costs, environmental conditions, and selling profit in a secure and structured way.

This project uses **JWT-based authentication**, **role-based access control (ADMIN & FARMER)**, and follows clean REST API design.

---

## 🔧 Technologies Used

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- VS Code

---

## 👥 User Roles

### 👨‍🌾 FARMER
- Register & login
- Add crops
- Add production costs
- Add environment data (temperature, humidity, soil moisture, etc.)
- Add selling price & quantity
- View profit calculation

### 👨‍💼 ADMIN
- View all users
- View all crops
- View selling & profit reports (read-only)

---

## 🔐 Authentication & Security

- JWT-based login system
- Token contains **email + role**
- Role-based API access
- Stateless authentication
- Secure endpoints using Spring Security

---

## 📂 Main Modules

### 1️⃣ Authentication
- User registration
- User login
- JWT generation & validation

### 2️⃣ Crop Management
- Add crops
- View crops by farmer

### 3️⃣ Cost Management
- Add or update crop production cost
- Automatic total cost calculation

### 4️⃣ Environment Monitoring
- Store environment data per crop
- Retrieve environment history

### 5️⃣ Selling & Profit
- Add selling price and quantity
- Automatically calculate:
  - Total selling amount
  - Profit = selling amount − total cost

### 6️⃣ Admin Reports
- View all users
- View all crops
- View selling & profit data

---

## 🔗 API Overview

### 🔓 Public APIs
- `POST /api/auth/register`
- `POST /api/auth/login`

### 🔒 Farmer APIs (JWT required)
- `/api/crops/**`
- `/api/cost/**`
- `/api/environment/**`
- `/api/selling/**`

### 🔐 Admin APIs (ADMIN role only)
- `/api/admin/**`

---

## 🗄️ Database Tables

- users
- crops
- crop_cost
- environment_data
- selling_price

All relationships are handled using JPA annotations.

---

## ▶️ How to Run the Project

1. Clone the repository
   ```bash
   git clone https://github.com/your-username/crop-monitoring-system.git
