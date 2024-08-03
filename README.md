# DKB Challenge

## Overview
This project is a URL shortening service built with Kotlin and Spring Boot. It provides RESTful endpoints to create short URLs and redirect to the original URLs.

## Technologies Used
- Kotlin
- Spring Boot
- Gradle

## Project Structure
- `src/main/kotlin/com/dkb/dkbchallange/`: Contains the main application and service classes.
- `src/main/kotlin/com/dkb/dkbchallange/rest/`: Contains the REST controller for handling HTTP requests.
- `src/main/kotlin/com/dkb/dkbchallange/repository/`: Contains the repository interface and its implementation for URL storage.
- `src/main/kotlin/com/dkb/dkbchallange/util/`: Contains utility classes.

## Getting Started

### Prerequisites
- JDK 11 or higher
- Gradle

### Running the Application
1. Clone the repository:
    ```sh
    git clone git@github.com:mramahy/dkb-challange.git
    ```

2. Build the project:
    ```sh
    ./gradlew build
    ```

3. Run the application:
    ```sh
    ./gradlew bootRun
    ```

## Docker

### Building the Docker Image

To build the Docker image, run the following command in the root directory of the project:
```sh
docker-compose build
```

### Running the Docker Container
To run the Docker container, use the following command:
```sh
docker-compose up
```

The application will start on `http://localhost:8080`.

## Controller

### ShortUrlController
The `ShortUrlController` handles HTTP requests for creating short URLs and redirecting to the original URLs.

#### Endpoints

##### Create Short URL
- **URL:** `/url`
- **Method:** `POST`
- **Request Param:** `url` (The original URL to be shortened)
- **Response:** The shortened URL

Example:
```sh
curl -X POST "http://localhost:8080/url" -d "url=https://www.example.com"
```

##### Redirect to Original URL
- **URL:**: `/url/{id}`
- **Method:** `GET`
- **Path Variable:** `id` (The ID of the shortened URL)
- **Response:** Redirects to the original URL

Example:
```sh 
curl -L http://localhost:8080/url/{id}
```

## Service

### ShortUrlService

The ShortUrlService is responsible for the business logic of creating and retrieving short URLs. It uses a repository to store and retrieve URLs.  

#### Methods

- `saveUrl(url: String): String`: Saves the original URL and returns the shortened URL.
- `getShortUrl(id: String): String?`: Retrieves the original URL based on the shortened URL ID.

## Repository

### InMemoryUrlRepository
The InMemoryUrlRepository is an in-memory implementation of the URL repository. It uses a ConcurrentHashMap to store URLs.  

#### Methods
`getUrl(id: String): String?`: Retrieves the original URL based on the ID.
`saveUrl(id: String, url: String)`: Saves the URL with the given ID.

## Testing

### Testing Mechanism
The project uses JUnit 5 and Spring Boot Test for testing. The tests are categorized into unit tests and integration tests.  

#### Unit Tests
Unit tests focus on testing individual components in isolation. For example, testing the ShortUrlService methods to ensure they work correctly.  

#### Integration Tests
Integration tests focus on testing the interaction between components. For example, testing the ShortUrlController endpoints to ensure they handle HTTP requests and responses correctly.