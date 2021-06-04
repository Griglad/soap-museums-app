# soap-museums-app
A Springboot soap crud web service which takes input data from the user (latitude,longitude) and finds the nearest famous museum in Greece's area. 
The nearest museum has a counter which increases +1 at each request.
Another Api method based on the input value returns the museums which have larger counter than the input value. 

Installation instructions

1.Download postgre (https://www.postgresql.org/download/)

2.Start the database.

3.Create a database named places.

4.Perform some monument inputs from data.sql manually.

5.In application.properties file use your own username and password.

6.mvn clean install

7.cd target

8.java -jar <yourfilename>.jar 
 



