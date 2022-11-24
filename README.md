# customers device service API

API for managing customers and their devices.

### Requirements
For building and running the application you need:

- [JDK 17](https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
- [Maven 3](https://maven.apache.org)

### Repository

```shell
git clone https://github.com/Mauro2888/customers-device-service.git
```

### Running the application locally
```shell
mvn spring-boot:run
```

### H2 Database
The application uses an in-memory H2 database. 
You can access the console at [http://localhost:8081/h2-console](http://localhost:8081/h2-console) 
with the following credentials:
 - username: sa
 - password: password


### Swagger UI
Open your browser and navigate to [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
