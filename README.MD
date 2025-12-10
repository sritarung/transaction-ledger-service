---

# **Transaction Ledger Service**

A high-integrity financial ledger system built with **Java 17**, **Spring Boot**, **PostgreSQL**, and **Docker Compose**.
Designed as a simplified version of real-world transaction engines used in **banking, trading, and compliance systems**.

This project demonstrates:

* Consistent transaction recording using **ACID-compliant operations**
* Ledger account management with **atomic balance updates**
* RESTful APIs following modern backend engineering practices
* Modular architecture (**Controller → Service → Repository → Entity**)
* Fully containerized database environment
* Production-ready patterns used in fintech systems (error handling, DTOs, idempotency-ready endpoints)

---

## 📌 **Why This Project Exists**

This service was built to showcase backend engineering skills aligned with roles at **Goldman Sachs**, including:

* Java backend development
* Distributed system thinking on ledger consistency
* Safe and predictable transaction handling
* Integrating with SQL databases using JPA/Hibernate
* Using modern frameworks and tools (Spring Boot, Docker, Lombok)
* Writing clean, testable, maintainable code

This mirrors internal GS systems such as **TxB (Transaction Banking)**, **Compliance Engineering**, and **FCO Technology**.

---

## 🚀 **Features**

### **1. Create Ledger Account**

* Auto-generated UUID
* Currency selection
* Zero-balance initialization
* Timestamped account creation

---

### **2. Record Transactions**

Supports:

* **CREDIT** → increases balance
* **DEBIT** → decreases balance

Each transaction is:

* Atomic
* Logged with amount, type, timestamp
* Persisted in PostgreSQL via JPA

---

### **3. Ledger Query APIs**

* Get account by ID
* List all accounts
* Retrieve transaction history

---

## 🧱 **Tech Stack**

| Component        | Technology             |
| ---------------- | ---------------------- |
| Language         | Java 17                |
| Framework        | Spring Boot 3          |
| Database         | PostgreSQL 16 (Docker) |
| ORM              | Hibernate / JPA        |
| Build Tool       | Maven                  |
| Containerization | Docker Compose         |
| Utility          | Lombok                 |

---

