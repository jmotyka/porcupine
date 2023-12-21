# porcupine
Spring Boot application that serves up patient level information via GET, POST, and PUT requests.

This application currently uses an in-memory database, but can easily be adapted to support a disk based DB.

## Getting Started
This respository requires Docker to be installed on the target machine.  Upon cloning you will need to build and package up a Docker image using the following command:
```
make build
```

Starting the application can be performed using:
```
make run
```

You should be able to issue a curl command for example patients that have been prepopulated in the database:
```
(base) jonmotyka@Jon-Motykas-MacBook-Pro porcupine % curl -v localhost:8080/patients | json_pp 
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0*   Trying 127.0.0.1:8080...
* Connected to localhost (127.0.0.1) port 8080 (#0)
> GET /patients HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.87.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Content-Type: application/hal+json
< Transfer-Encoding: chunked
< Date: Thu, 21 Dec 2023 16:01:29 GMT
< 
{ [714 bytes data]
100   707    0   707    0     0   2941      0 --:--:-- --:--:-- --:--:--  3008
* Connection #0 to host localhost left intact
{
   "_embedded" : {
      "patientList" : [
         {
            "_links" : {
               "patients" : {
                  "href" : "http://localhost:8080/patients"
               },
               "self" : {
                  "href" : "http://localhost:8080/patients/1"
               }
            },
            "createdAt" : "2023-12-21T16:01:01.305729",
            "dateOfBirth" : "1900-01-01T00:00:00",
            "email" : "johnsmith@gmail.com",
            "id" : 1,
            "modifiedAt" : null,
            "name" : "John Smith",
            "nextAppointment" : "2024-01-01T12:00:00"
         },
         {
            "_links" : {
               "patients" : {
                  "href" : "http://localhost:8080/patients"
               },
               "self" : {
                  "href" : "http://localhost:8080/patients/2"
               }
            },
            "createdAt" : "2023-12-21T16:01:01.361038",
            "dateOfBirth" : "1910-01-10T00:00:00",
            "email" : "joesmith@gmail.com",
            "id" : 2,
            "modifiedAt" : null,
            "name" : "Joe Smith",
            "nextAppointment" : "2024-01-01T12:00:00"
         }
      ]
   },
   "_links" : {
      "self" : {
         "href" : "http://localhost:8080/patients"
      }
   }
} 
```

## Integration Tests
Test harness can be ran after building the Docker image by using:
```
make test
```

There are no unit tests at the moment as the logic between the application layer and data model is simplistic.

