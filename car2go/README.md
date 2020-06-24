# Getting Started

Description:
===========
  The goal is to build a microservice which calculates the position of SHARE NOW
  vehicles inside strategic geojson-polygons and serve the cars and polygons via a
  REST API.

Architecture:
============
For designing the application I have choose Spring boot.It is open source Java-based framework used to create a micro Service.I have chose these technology:

* Java:8
* Spring Boot:2.3.1.RELEASE
* GSON:2.8.5
* Lombok:1.18.6
* Commons-IO: 2.6 
* Maven :4.0.0
* junit :4.12
* embedded Tomcat

Some key points:
===============

* I am using spring boot scheduler for calling vehicles service API every 10 second.
* Time interval is configured in properties files.
* I am using spring profile for deploying multiple environment.
* Implemented Spring boot actuator for checking health for service.
* I am not using any database for dumping json object.I used ArrayList to store JSON Object. 
* I have written unit test case only successful path for services and api.
* Longitute and Latitude have different value, So in verifying the vehicle values in roundoff to 2 digits mechanism. 
* I did not add unit test case in maven build. So we need to test by mannuly. 

Open points:
============
* Vehicle Location service API is returning the information of vehicle in every refresh, which provide new data every point. 

For Run The Application:
=======================
* To start this application you must be run live data of vehicle service.

	````
	docker pull car2godeveloper/api-for-coding-challenge 
	docker run -d -p 3000:3000 car2godeveloper/api-for-coding-challenge
	````
	
* First build maven 
   
   ````
   mvn clean install
   ````

* Build docker Image 

   ````
   docker build -t car2go .
   ````
   
* Run Docker Image

  ````
  docker run -d -p 8080:8080 car2go
  ````

Note:
=====
Docker Container to Container communication I am using Inspect of running api-for-coding-challenge container to find IP address,
By using this IP address I am calling http://172.17.0.2:3000/vehicles/Stuttgart to fetch Vehicles from stuttgart location.


Dependencies:
============

* Starter of Spring web uses Spring MVC, REST and Tomcat as a default embedded server. The single spring-boot-starter-web dependency transitively pulls in all dependencies related to web development. It also reduces the build dependency count.
  		
		````
		
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		
		````
		
* Spring Boot Actuator is a sub-project of Spring Boot. It provides several production-grade services to your application out of the box.
      
     	````
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-actuator</artifactId>
		</dependency>
		````
		
* It provides a multitude of classes that enable developers to do common tasks easily and with much less boiler-plate code, that needs to be written over and over again for every single project.
	
      	````
		<dependency>
			<groupId>commons-io</groupId>
			<artifactId>commons-io</artifactId>
			<version>2.6</version>
		</dependency>
		````
		
* Gson is a Java library that can be used to convert Java Objects into their JSON representation. It can also be used to convert a JSON string to an equivalent Java object.
		
		````
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.8.5</version>
		</dependency>
		````
		
* Lombok is used to reduce boilerplate code for model/data objects, e.g., it can generate getters and setters for those object automatically by using.
		
		````
		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<version>1.18.6</version>
			<scope>provided</scope>
		</dependency>
		````
		
* Junit test case dependency for test functionality
		
		````
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>4.12</version>
			<scope>test</scope>
		</dependency>
		````

Key Queries:
===========
* `GET /api/cars/$polygonId`
A list of the Cars return which are available with in the polygon ID.

````
[{
    "polygonId": "58a58be9766d51540f779737",
    "vin": "2FTFX2767XYN3CS49",
    "numberPlat": "S-LE7107",
    "model": "SMART_42_CABRIO"
  },...]
````

* `GET /api/polygons/$vin`
It is search Car VIN is available in which Polygon. 

````
{
   "polygonId": "58a58be9766d51540f779737",
   "vin": "2FTFX2767XYN3CS49"
}
````

* `GET  /actuator/health`
It is use for to check health of the Services.

````
{
    "status": "UP",
    "components": {
        "diskSpace": {
            "status": "UP",
            "details": {
                "total": 254721126400,
                "free": 140566413312,
                "threshold": 10485760,
                "exists": true
            }
        },
        "ping": {
            "status": "UP"
        },
        "service": {
            "status": "UP",
            "details": {
                "API Car and Polygon Service": "Available"
            }
        }
    }
}
````