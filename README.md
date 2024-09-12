# Ticket Management System

This is a simple **Ticket Management System** built using **Spring Boot** with **H2 Database** and **Swagger** for API documentation. The application provides a RESTful API for managing tickets and users, allowing you to perform basic CRUD operations.

## Table of Contents

- [Features](#features)
- [Technologies](#technologies)
- [Requirements](#requirements)
- [Setup and Installation](#setup-and-installation)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Swagger Documentation](#swagger-documentation)
- [License](#license)

## Features

- User authentication and access control.
- CRUD operations for users and tickets.
- In-memory H2 database for simplicity.
- RESTful API with proper status codes.
- Unit testing with JUnit and Mockito.
- API documentation with Swagger.

## Technologies

- **Java 17**
- **Spring Boot 3.3.3**
- **Spring Data JPA**
- **H2 Database**
- **Swagger 3**
- **JUnit 5**
- **Mockito**
- **Maven**

## Requirements

- **Java 17+**
- **Maven 3.6+**

## Setup and Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/chris237/tickets1.git
   cd tickets1
   ```
2. Install the dependencies and build the project:
   ```bash
   mvn clean install
   ```
3. Run the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```
5. Access the application:
   * API Base URL: http://localhost:8080/api/v1/
   * H2 Database Console: http://localhost:8080/h2-console
   * Swagger UI: http://localhost:8080/swagger-ui.html
a. API Endpoints

The following are the main API endpoints for managing users and tickets.
User Endpoints:

    GET /api/v1/users - Get a list of all users.
    GET /api/v1/users/{id} - Get a specific user by ID.
    POST /api/v1/users - Create a new user.
    PUT /api/v1/users/{id} - Update an existing user.
    DELETE /api/v1/users/{id} - Delete a user.

Ticket Endpoints:

    GET /api/v1/tickets - Get a list of all tickets.
    GET /api/v1/tickets/{id} - Get a specific ticket by ID.
    POST /api/v1/tickets - Create a new ticket.
    PUT /api/v1/tickets/{id} - Update an existing ticket.
    DELETE /api/v1/tickets/{id} - Delete a ticket.

Testing

To run the unit tests, use the following command:
```bash
mvn test
```

Tests are written using JUnit 5 and Mockito.
Swagger Documentation

The API documentation is available through Swagger. Once the application is running, you can access it by visiting:

```bash
http://localhost:8080/swagger-ui.html
```
This provides a detailed view of all the available endpoints, along with the ability to execute API requests directly from the web interface.
License

This project is licensed under the MIT License. See the LICENSE file for more details.

### Breakdown of Sections:
1. **Features**: Lists the main functionalities.
2. **Technologies**: Lists the frameworks, libraries, and tools used.
3. **Setup and Installation**: Shows how to install and run the project.
4. **API Endpoints**: Provides a summary of the available REST endpoints.
5. **Testing**: Describes how to run the tests.
6. **Swagger Documentation**: Details how to access the Swagger UI for API documentation.

Feel free to modify the content according to any specific features or tools in your project.
