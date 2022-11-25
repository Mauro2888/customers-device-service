# Customers-device-service API

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
 - username: customer
 - password: 
 - Jdbc-url: jdbc:h2:mem:customers

### Swagger UI
Open your browser and navigate to [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

### POST customer request example
[http://localhost:8081/api/v1/customer](http://localhost:8081/api/v1/customer)

```json
{
  "nome": "Luca",
  "cognome": "Cau",
  "codiceFiscale": "LCUCAU97S24H501M",
  "indirizzo": "Via Liguria",
  "devices": [
    {
      "status": "LOST"
    },
     {
      "status": "INACTIVE"
    }
  ]
}

```
JSON POST response example
```json
{
  "nome": "Luca",
  "cognome": "Cau",
  "codiceFiscale": "LCUCAU97S24H501M",
  "indirizzo": "Via Liguria",
  "devices": [
    {
      "id": "19cca969-a85b-4741-9a49-00afaad374e7",
      "status": "LOST"
    },
    {
      "id": "3ba73c11-f7f9-441b-b83b-5f5fef6959c2",
      "status": "INACTIVE"
    }
  ]
}
```