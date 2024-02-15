Box Delivery Service

Introduction
The Box Delivery Service is a RESTful API designed to manage the dispatching and delivery of small items using specialized boxes capable of carrying and delivering items to remote locations. 
This service provides functionalities for creating boxes, loading items into boxes, checking loaded items, monitoring available boxes, and checking the battery level of boxes.

Technologies Used: Java, Spring Boot, Spring Data JPA, Maven, RESTful API, JSON, Swagger

Functionalities

The Box Delivery Service offers the following functionalities:

Creating a Box: Clients can create a new box with specified attributes such as txref, weight limit, battery capacity, and state.


Loading Items into a Box: Clients can load items into a box, ensuring that the box is in IDLE state, the battery capacity is sufficient, and the total weight of items does not exceed the box's weight limit.

Checking Loaded Items: Clients can check the items loaded into a specific box.


Checking Available Boxes for Loading: Clients can retrieve a list of available boxes that can be loaded with items.


Checking Battery Level: Clients can check the battery level of a specific box.


Project Structure
src/main/java: Contains Java source code.

com.marvis.deliveryapi.controller: Contains controller classes for handling HTTP requests.

com.marvis.deliveryapi.data.model: Contains entity classes representing database tables.

com.marvis.deliveryapi.data.dtos: Contains data transfer object(request & responses) classes.

com.marvis.deliveryapi.exception: Contains custom exception classes and global exception handler class.

com.marvis.deliveryapi.repository: Contains Spring Data JPA repositories.

com.marvis.deliveryapi.service: Contains service classes (service interfaces and service implementation classes) for business logic.

src/test/java: Contains unit and integration test classes.

src/main/resources: Contains application properties and database schema files.


Build and Run Instructions
Clone the repository: git clone <https://github.com/Marvechenky/deliveryAPI>

Navigate to the project directory: cd deliveryapi

Build the project: mvn clean package

Run the application: java -jar target/deliveryapi.jar


Testing
Unit tests: Run mvn test to execute unit tests.


Integration tests: 
Run mvn verify to execute integration tests.


API Documentation
Box Delivery Service API Documentation

Introduction
Welcome to the API documentation for the Box Delivery Service. 
This API allows clients to manage the dispatching and delivery of small items using specialized boxes capable of carrying and delivering items to remote locations.

Base URL
The base URL for accessing the API is /api/v1

Authentication
Authentication is not required for accessing the public endpoints of this API.

Endpoints
1. Create a Box
   URL: http://api/v1/box/create
   
   Method: POST
   
   Description: Create a new box with specified attributes such as txref, weight limit, battery capacity, and state.
   
   Request Body:
   json
   

   {
   "txref": "string",
   "weightLimit": 0,
   "batteryCapacity": 0,
   "state": "IDLE"
   }

   Response: Returns the created box with its details.
   

3. Load Items into a Box
   URL: /api/v1/box/{txref}/load-items
   
   Method: POST
   
   Description: Load items into a box, ensuring that the box is in IDLE state, the battery capacity is sufficient, and the total weight of items does not exceed the box's weight limit.

   Path Parameters:
   txref (string): The transaction reference of the box to load items into.
   
   Request Body:

   json
   
   [
   {
   "name": "string",
   "weight": 0,
   "code": "string"
   }
   ]
   Response: Returns a success message if the items are loaded successfully.
   

4. Check Loaded Items in a Box
   URL: /api/v1/box/{txref}/view-items
   
   Method: GET
   
   Description: Retrieve the items loaded into a specific box.
   
   Path Parameters:
   txref (string): The transaction reference of the box to check loaded items.
   
   Response: Returns the list of items loaded into the box.
   

5. Check Available Boxes for Loading
   URL: /api/v1/box/available-boxes
   
   Method: GET
   
   Description: Retrieve a list of available boxes that can be loaded with items.
   
   Response: Returns the list of available boxes.


6. Check Battery Level of a Box
   URL: /api/v1/box/{txref}/battery-level
   
   Method: GET
   
   Description: Check the battery level of a specific box.
   
   Path Parameters:
   txref (string): The transaction reference of the box to check battery level.
   
   Response: Returns the battery level of the box.


   Errors:
   400 Bad Request: Invalid request parameters or missing required fields.
   
   404 Not Found: Resource not found.
   
   500 Internal Server Error: Unexpected server error.

   

API documentation is available at /api/swagger-ui/index.html endpoint when the application is running.


License
This project is licensed under the MIT License. 

