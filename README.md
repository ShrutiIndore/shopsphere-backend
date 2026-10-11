# ShopSphere Backend

## About the Project
ShopSphere Backend is a REST API developed using Java and Spring Boot for the ShopSphere e-commerce website. It provides APIs for retrieving products and managing customer orders.

## Technologies Used
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- REST APIs

## Features
- Retrieve product information through REST APIs
- Create customer orders
- Retrieve order information
- Store order data in a MySQL database
- Use Spring Data JPA and Hibernate for database operations
- Configure database credentials using environment variables

## API Endpoints

### Products
- GET `/api/products`

### Orders
- POST `/api/orders`
- GET `/api/orders`

## Database
The project uses MySQL hosted on Aiven.

Database connection settings are configured using environment variables.

## Deployment
The backend is deployed on Render.

https://shopsphere-backend-wo31.onrender.com

## Frontend
https://shopsphere-tau-three.vercel.app

## GitHub Repository
https://github.com/ShrutiIndore/shopsphere-backend
