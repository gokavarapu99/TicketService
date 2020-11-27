# TicketService
TicketService - TotalNumberOfSeats, Hold the seats , Reserve the Seats.

Use case:

Implement a simple ticket service that facilitates the discovery, temporary hold, and final reservation of seats within a high-demand performance venue.

For example, see the seating arrangement below.

 

        ----------[[  STAGE  ]]----------

        ---------------------------------

        sssssssssssssssssssssssssssssssss

        sssssssssssssssssssssssssssssssss

        sssssssssssssssssssssssssssssssss

        sssssssssssssssssssssssssssssssss

        sssssssssssssssssssssssssssssssss

        sssssssssssssssssssssssssssssssss

        sssssssssssssssssssssssssssssssss

        sssssssssssssssssssssssssssssssss

        sssssssssssssssssssssssssssssssss

 

Your homework assignment is to design and write a Ticket Service that provides the following functions,

Find the number of seats available within the venue
Note: available seats are seats that are neither held nor reserved.
Find and hold the best available seats on behalf of a customer
Note: each ticket hold should expire within a set number of seconds. 
Reserve and commit a specific group of held seats for a customer
 

Requirements: 

Use a programming language that you are comfortable with. We work in Java, but we are more interested in understanding how you think than in language specifics.
The solution and tests should build.
A README file should be included in your submission that documents your assumptions and includes instructions for building the solution and executing the tests
Implementation mechanisms such as disk-based storage, a REST API, and a front-end GUI are not strictly required



Assumptions:
The Venue contains around 110 seats. Each row has 10 seats and total 11 rows of differnt category Balcony view , Stadium View and Front view. 
Seats will be assigned to customer based on FCFS basis and there is no option to select to view for customer as this tickets will be generated as per order. 
When user requests a number of seats and requests to hold . We hold for 900 seconds around 15 min and then the tickets will be available again for hold and reserving. 


Environment Setup :

OPEN JDK 15 
You can download here : 
https://docs.aws.amazon.com/corretto/latest/corretto-15-ug/downloads-list.html
Eclipse :
https://www.eclipse.org/downloads/packages/release/oxygen/3a/eclipse-ide-java-ee-developers
Build Tool: Maven 
Embedded Spring Boot server
Framework: Spring Boot
Language: Java 
Client: Postman
Database: In Memory Database H2 and tables and date will imported from data.sql file. 
Testing : JUnit , Mockito.

Jars will be downloaded from Maven Repository when you import to Eclipse and do mvn clean package. If from IDE just configure as clean package, jars will auto download to your .m2 folder. 


Building the Project :

Clone the Project :
https://github.com/gokavarapu99/TicketService.git

Upload to Eclipse IDE as maven project. 
Run as maven and configure as clean package and Build will get success as below. 


Make sure you installed maven and Java 15 before build and running the project. 

Once you build the project you can as java application from eclipse or command prompt and this will run on port 9090 . As i configured with port 9090 in application.properties. 

When you run the project you should see as below Tomcat initialized with port(s): 9090 (http)and should see Welcome to Walmart Ticketing System. 







We have total 3 Endpoints and build based on RESTFUL WEBSERVICES . 

1. To fetch the number of seats available within the venue which are neither held nor reserved

Request / EndPoint : http://localhost:9090/seats

Response :

{
    "noOfSeats": 110,
    "message": "Hi , We have 110 seats"
}

2. Find and hold the best available seats on behalf of a customer. Each ticket hold should expire within a set number of seconds. I gave 900 seconds as hold time. 

Request / Endpoint :

Here test@gmail.com is the customer email address and 2 is the number of seats requested by Customer to keep on hold. 

http://localhost:9090/hold?customerEmail=test@gmail.com&numSeats=2


3. Reserve and commit a specific group of held seats for a customer.

Request / EndPoint :

Here 1 is the holdId the customer received when hold the tickets and email address which he entered while holding the tickets. 

http://localhost:9090/reserve?seatHoldId=1&customerEmail=test@gmail.com



Response:

{
    "message": "Hurray...!!!! your tickets got Booked and your ConfirmationCode is 1",
    "customerEmail": "test@gmail.com",
    "confirmationNumber": 1
}

Tests successful :

1. TicketControllerTest

2. TicketServiceTest

3. Database Integeration Test / TicketServiceDatabaseTest.java





Future :
1. Try catch is not implemented . Can be implemented and we can add custom exception handling. 
2. Integrate with Frontend React or Reactnative using axios. 
3. Migrating from H2 to Mysql / oracle database. 
4 . Deploying to EC2 / VM and autoscaling based on load. 
5. Deploying to ElasticBeanstalk . 
6. Can be converted to Serverless lambda functions as per requirement. 





