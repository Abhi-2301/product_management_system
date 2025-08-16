# Product CRUD API

This is a Spring Boot project that provides REST APIs to manage products.  
It demonstrates CRUD operations, partial updates using PATCH, and advanced queries with pagination and filtering.  
Swagger is integrated for easy API documentation and testing.

---

## 🚀 Features
- Create a new product
- Fetch all products
- Get product by ID
- Update product (PUT)
- Partial update product (PATCH - only update non-null fields)
- Delete product
- Find product by name
- Find products within a price range
- Swagger UI for API testing

---

## 🛠️ Tech Stack
- **Java**  
- **Spring Boot**  
- **Hibernate / JPA**  
- **PostgreSQL**  
- **Swagger (Springdoc / OpenAPI)**  

---

## ⚡ How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/abhi-2301/product_management_system.git
   
2. Import project into Eclipse/IntelliJ as a Maven Project.
3. Configure PostgreSQL database in application.properties.
4. Run the project as Spring Boot App.
5. Open Swagger UI in your browser
	http://localhost:8080/swagger-ui/index.html  
	
	
| Method | Endpoint                      | Description                  |
| ------ | ----------------------------- | ---------------------------- |
| GET    | `/products`                   | Get all products             |
| GET    | `/products/get/{id}`          | Get product by ID            |
| POST   | `/products/save`              | Create new product           |
| PUT    | `/products/update/{id}`       | Update product by ID         |
| PATCH  | `/products/patch/{id}`        | Partially update product     |
| DELETE | `/products/delete/{id}`       | Delete product by ID         |
| GET    | `/products/name/{name}`       | Find product by name         |
| GET    | `/products/price/{min}/{max}` | Find products by price range |
	