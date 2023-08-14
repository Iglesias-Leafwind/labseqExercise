# Labseq Challenge
Exercise made with Quarkus framework.

## Tests
To run the tests: `mvn clean test integration-test`.

# Quarkus no Docker
Run the application: `./mvnw compile quarkus:dev`

# Quarkus with Docker
Inside the project run: `docker build -f src/main/docker/Dockerfile.jvm -t exer/labseq .`
And run: `docker run -i --rm -p 8080:8080 exer/labseq`

# How to interact with backend
Open the web page using the default url: `http://localhost:8080`

# Swagger API documentation
Acess by the following url: `http://localhost:8080/q/swagger-ui/`