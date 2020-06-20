# soap-CulturalHeritage-app
A Springboot soap crud web service which takes input data from the user (latitude,longitude) and finds the nearest cultural heritage site
(monument,archaeological-site,museum)in Greece's area. 
The nearest cultural heritage site has a counter which is increased +1 at each request.
Another Api method based on the input value returns the monuments which have larger counter than the input value. 

Installation instructions

1.Download postgre (https://www.postgresql.org/download/)

2.start the database.

3.create a database named places.

4.perform some monument inputs from data.sql manually.

5.In application.properties file use your own username and password.

6.mvn clean install

7.cd target

8.java -jar <yourfilename>.jar 
 



