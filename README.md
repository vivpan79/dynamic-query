To build & run with Maven:
```
./mvnw package && java -jar target/dynamic-query-0.1.0.jar
```
To build & run with Docker:
```
docker build -t com.telenor/dynamic-query .
docker run -p 8080:8080 com.telenor/dynamic-query
```
To build a Docker Image with Maven
```
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=com.telenor/dynamic-query
```
---
To check application is up go to http://localhost:8080 to see message
```
"Welcome to Telenor's take-home assignment: Dynamic Query"
```
To check for available phones go to 

http://localhost:8080/products?productType=phone&max_price=1000&productProperty:color=grön&city=Stockholm

To check for available subscriptions go to 

http://localhost:8080/products?productType=subscription&productProperty:gb_limit_min=10&city=Karlskrona

---

You can access the database using h2 console at following url

http://localhost:8080/h2-console/