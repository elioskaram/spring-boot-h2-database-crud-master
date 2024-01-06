# Spring Boot H2 CRUD Application

## Overview

This documentation provides a comprehensive guide to the Spring Boot H2 CRUD Application, a Java-based web application designed for managing a simple database of records. This project utilizes Spring Boot for its framework and H2, an in-memory database, for storing data.

## Getting Started

### Installation

**Prerequisites:**
- Java JDK 17 or later
- Maven 3.6 or later

**Steps:**
1. Clone the repository: `git clone [repository URL]`.
2. Navigate to the project directory: `cd [project directory]`.
3. Run the application: `mvn spring-boot:run`.

### Configuration

- **Database Configuration**: Located in `src/main/resources/application.properties`. Adjust settings here to change database behavior.
- **Port Configuration**: By default, the application runs on port 8080. This can be changed in the `application.properties` file.

## Technical Documentation

### Architecture

This application follows the MVC (Model-View-Controller) architecture.

- **Model**: Represents the data structure. Located under `src/main/java/com/project/model`.
- **View**: Thymeleaf templates for the UI. Located under `src/main/resources/templates`.
- **Controller**: Handles HTTP requests. Located under `src/main/java/com/project/controller`.

### Technologies Used

- **Spring Boot**: Simplifies the setup and development of new Spring applications.
- **H2 Database**: An in-memory database used for rapid development and testing.

## API Documentation

**Endpoints:**
- `GET /api/items`: Retrieves all items.
- `POST /api/item`: Creates a new item.
- `GET /api/item/{id}`: Retrieves an item by id.

**Sample Request:**
```json
POST /api/item
{
    "name": "Sample Item",
    "description": "This is a sample."
}
```


## Tutorials

**Creating Your First Record:**
1. Navigate to `http://localhost:8081/item/new`.
2. Fill in the details and submit.

## Troubleshooting

- **Application Doesn't Start**: Ensure that the correct Java and Maven versions are installed.
- **Database Connection Issues**: Verify the database configuration in `application.properties`.

## Contributing to the Project

Contributions are welcome. Please follow these guidelines:
- **Code Style**: Adhere to standard Java coding conventions.
- **Testing**: Add unit tests for new features.
- **Pull Requests**: Submit PRs with detailed descriptions.

## FAQ

**Q: Can I use a different database?**
A: Yes, modify the `application.properties` file to connect to a different database.

## License

This project is licensed under BSD license.

## Contact

For support or further questions, contact us at [elioskaram@gmail.com].

## Changelog

**v1.1.0** - Initial release with basic CRUD functionality.
