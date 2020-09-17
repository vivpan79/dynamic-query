If you are using Maven, execute:

./mvnw package && java -jar target/dynamic-query-0.1.0.jar

and go to localhost:8080 to see your "Hello Docker World" message.

You can run it (if you are using Maven) with

docker build -t com.telenor/dynamic-query .
docker run -p 8080:8080 com.telenor/dynamic-query

Build a Docker Image with Maven

./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=com.telenor/dynamic-query

You can access the database using h2 console at following url
http://localhost:8080/h2-console/