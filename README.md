# Car Workshop
 ###Introduction 

Project has been created in order to understand the concept of Servlet redirection, JavaScript selectors and functions, POST/GET requests, Builder design pattern and Bootstrap. Application contains four main screens:
 - Clients
 - Cars
 - Employees
 - Orders
 
 
 ###Technologies
 - Java 11
 - Bootstrap 4
 - JavaScript
 - JQuery 3.2
 - HTML
 - CSS
 - T-SQL
 
 ###How to run
 In order to run the application in prior you have to execute the SQL-script building the database.
 
 > src/main/java/additionals/tables.sql

Once database has been created create a build using Maven.
Application is running on the Apache Tomcat 8.5 version. 
 
 ###Features 
1. Clients:

   - Creating client
   - Editing client
   - Deleting client
   - Show All clients
2. Car:

   - Creating car
   - Editing car
   - Deleting car
   - Show All cars  
3. Employee:

   - Creating employee
   - Editing employee
   - Deleting employee
   - Show All employees     
4. Order:

   - Creating order
   - Editing order
   - Deleting order
   - Show All orders
   
--- 
5. Validations
    - 9 digits phone- umber , regex. 
    - Masks on primary constrains, JavaScript.
    - Caching foreign key constrains exception.
    
6. Additional functionalists  
    - Emails notification for clients with an upcoming in two weeks check-up date for car, add an email and email password in the Email class
        `String myAccountEmail = "xxx"; String password = "xxx";`
        
    - Report showing employee and number of hours/cost in particular time period.
    - Searching bar (right top corner) bases upon the url view address e.g. createcar 
    
7. To Do List:
    - Move all orders to archived table once they reach "DONE" status.
