# Journal App API 

## Overview

The Journal App API is built using Spring Boot and MongoDB. It allows users to manage their journal entries with user-based authentication and provides weather forecasts.

## Features

- User Registration and Authentication
- Create, Read, Update, and Delete (CRUD) Journal Entries
- User-based Authentication
- Weather Forecast Integration

## Technologies Used

- Spring Boot
- MongoDB
- Java
- Maven

## Prerequisites

- Java 11 or higher
- Maven
- MongoDB

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/sunnysingh3972/journal-app-api.git
cd journal-app-api
```

### Configure MongoDB

Make sure MongoDB is running and configure the application properties with your MongoDB settings.

`src/main/resources/application.properties`:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/journaldb
```

### Build the Project

```bash
mvn clean install
```

### Run the Application

```bash
mvn spring-boot:run
```

## API Endpoints

### User Registration and Authentication

#### Register a New User

**POST** `/api/users/register`

**Request Body:**

```json
{
    "username": "johndoe",
    "password": "password123"
}
```

**Response:**

```json
{
    "message": "User registered successfully"
}
```

#### User Login

**POST** `/api/users/login`

**Request Body:**

```json
{
    "username": "johndoe",
    "password": "password123"
}
```

**Response:**

```json
{
    "token": "your-jwt-token"
}
```

### Journal Entries

#### Create a Journal Entry

**POST** `/api/journals`

**Request Header:**

```
Authorization: Bearer your-jwt-token
```

**Request Body:**

```json
{
    "title": "My First Journal Entry",
    "content": "Today was a good day...",
    "date": "2024-07-16"
}
```

**Response:**

```json
{
    "message": "Journal entry created successfully"
}
```

#### Get All Journal Entries

**GET** `/api/journals`

**Request Header:**

```
Authorization: Bearer your-jwt-token
```

**Response:**

```json
[
    {
        "id": "60c72b2f9b1e8b6f1d7b2f1c",
        "title": "My First Journal Entry",
        "content": "Today was a good day...",
        "date": "2024-07-16",
        "userId": "60c72b2f9b1e8b6f1d7b2f1b"
    },
    ...
]
```

#### Get a Specific Journal Entry

**GET** `/api/journals/{id}`

**Request Header:**

```
Authorization: Bearer your-jwt-token
```

**Response:**

```json
{
    "id": "60c72b2f9b1e8b6f1d7b2f1c",
    "title": "My First Journal Entry",
    "content": "Today was a good day...",
    "date": "2024-07-16",
    "userId": "60c72b2f9b1e8b6f1d7b2f1b"
}
```

#### Update a Journal Entry

**PUT** `/api/journals/{id}`

**Request Header:**

```
Authorization: Bearer your-jwt-token
```

**Request Body:**

```json
{
    "title": "Updated Journal Entry Title",
    "content": "Updated content...",
    "date": "2024-07-16"
}
```

**Response:**

```json
{
    "message": "Journal entry updated successfully"
}
```

#### Delete a Journal Entry

**DELETE** `/api/journals/{id}`

**Request Header:**

```
Authorization: Bearer your-jwt-token
```

**Response:**

```json
{
    "message": "Journal entry deleted successfully"
}
```

### Weather Forecast

#### Get Weather Forecast

**GET** `/api/weather?location={location}`

**Request Header:**

```
Authorization: Bearer your-jwt-token
```

**Response:**

```json
{
    "location": "New York",
    "forecast": "Sunny",
    "temperature": "25°C"
}
```

## Security

- JWT (JSON Web Token) is used for securing the endpoints.
- Each user action (create, read, update, delete journal entries) requires a valid JWT token.

---
