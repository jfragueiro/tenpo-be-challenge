# API rest, Math with login validator
## Take in consideration
- The logout is now made by the JWT token expire timeout, which is set in 180sec, about 3 minutes.
- The logout could be implemented with a blacklist with redis, or managing the IP from the user, or just in the DB, for a matter of time was made just with the timeout 
- User passwords are encrypted and manipulated with the encription SHA256


## Run this API

### Prerequisites

Having installed Java 8, Maven and Docker. If you have to install them, please visit the following links:
```
  -https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html
  
  -https://maven.apache.org
  
  -https://docs.docker.com
```

Fork and clone the repo: https://github.com/jfragueiro/tenpo-be-challenge.git

run:
```
- mvn clean install
- docker-compose up
```
If have any issues, you could pull the docker container from public hub 
```
docker pull joemaria30/math-tenpo-be-challenge:latest 
```
this are the Postman Collection to test the API
(to import, go to collections import, select link, and paste this link)
```
https://www.getpostman.com/collections/1bf302cfe1458c4348fd
```
for the POST: /api/v1/operations/multiply
you might add the token into the authorization bearer token
