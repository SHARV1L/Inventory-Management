Inventory Management System:

This project is a basic Inventory Management System built using Spring Boot. It allows users to manage inventory items through a RESTful API. 
The project as of now covers creating, reading, updating, and deleting items in the inventory using PostgreSQl as our database.

You can save Username, Password, URL and other secret docs/creds in .env file as:
DB_URL=jdbc:postgresql://localhost:<portnumber>/inventory_management
DB_USERNAME=username
DB_PASSWORD=password


Features:
1.) Add new inventory items.
2.) Retrieve all items 
3.) specific item by ID.
4.) Update item details.
5.) Delete an item from the inventory.

Setup Instructions:


1.) git clone <repository-url>  // repository-url is provided (with .get extension) in the homepage of this repository.
    cd inventory-app

2.) Open src/main/resources/application.properties folder and add/update the folloiwng lines:

spring.datasource.url=jdbc:postgresql://localhost:5432/inventory_management
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

3.) Run the project:
We can either directly run our project after configuring using the IDEs e.g., IntelliJ or using command: ./mvnw spring-boot:run

4.) Testing the Endpoints:
Use Postman Application or Web Browser to test your application

API Endpoints:
1.) Get Request: http://localhost:8080/items
2.) Get Request: http://localhost:8080/items/{id}
3.) Post Request: http://localhost:8080/items
4.) Put Request: http://localhost:8080/items/{id}
5.) Delete Request: http://localhost:8080/items/{id}

