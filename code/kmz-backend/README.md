# KMZ Backend

**Digitalization and Valorization Platform for the Local Agricultural Supply Chain**  
Backend developed in **Spring Boot**.

---

## Requirements

- **Java 23** (required to run the packaged application)

> The database is in-memory, so no additional setup is required for local execution.

---

## API Documentation

The API documentation is available via **Swagger UI**:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

> Make sure the application is running to access Swagger UI.

---

## Running the Application

1. Clone the repository:
```bash
git clone <REPO_URL>
cd code/kmz-backend
```

2. Build the project:
```bash
./mvnw clean package
```

3. Run the packaged application:
```
java -jar target/kmz-backend-0.0.1-SNAPSHOT.jar
```

4. Access the API at http://localhost:8080.


## TODO

- Implement Marketplace
- Create processed products and offer packages
- Manage locations
- Fine tuned error handling
- Add unit and integration tests
- Add package of products
- Add ProcessedProduct entity
- ...