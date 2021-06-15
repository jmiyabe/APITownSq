# APITownSq
This API returns funcionalities and permissios accordinly to the user email.

## How to run;
Copy the project.
Access the api folder.
Run the following command to start the application in the tomcat server: `mvn spring-boot:run`
The application will be exposed in the 8080 port.
Perform a GET request: `http://localhost:8080/api/{userEmail}` to get user permission. 
