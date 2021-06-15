# APITownSq
This API was developed with spring boot, spring web and its funcionality is to return permissions accordinly to the user email.

The data will be loaded from a text file.

## How to run
Copy the project.

Access the api folder.

Run the following command to start the application in the tomcat server: `mvn spring-boot:run`

The application will be exposed in the 8080 port.

Perform a GET request: `http://localhost:8080/api/{userEmail}` to get user permission. 

You will receive the data in the following format: ` <IdCondominium>;[(<Functionality>,<Permission>), ...] `
