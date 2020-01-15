# Demo App Shown at Meetup

## Meetup

https://www.meetup.com/gdgcincinnati/events/mxvdjrybccbtb/

## Source

The Book Repository section of this was adopted from Baeldung's Example:

* https://www.baeldung.com/spring-boot-start
* https://github.com/eugenp/tutorials/tree/master/spring-boot-bootstrap

## Getting Started

### Startup MySQL

```
$ docker run -d --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -p 3306:3306 mysql:5
```

### Create DB

```
$ mysql -h 127.0.0.1 -u root --password=my-secret-pw -e "create database \`gdg-demo\`;"
```

## Using

### Build from CLI

```
mvn clean package
```

### Run from CLI

```
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

### Book Repository

```bash
# Create some Books
$ curl -d '{ "title": "T1", "author": "A1" }' -H "Content-Type: application/json" -X POST localhost:8080/api/books
$ curl -d '{ "title": "T2", "author": "A1" }' -H "Content-Type: application/json" -X POST localhost:8080/api/books
$ curl -d '{ "title": "T3", "author": "A2" }' -H "Content-Type: application/json" -X POST localhost:8080/api/books
$ curl -d '{ "title": "T4", "author": "A2" }' -H "Content-Type: application/json" -X POST localhost:8080/api/books

# Get All Books
$ curl localhost:8080/api/books
[] 

# Get By ID
$ curl localhost:8080/api/books/1
{"id":1,"title":"T1","author":"A1"}

# Get By Author
$ curl localhost:8080/api/books/A1
[{"id":1,"title":"T1","author":"A1"},{"id":2,"title":"T2","author":"A1"}]

# Update a Book
$ curl -d '{ "id": 1, "title": "T444", "author": "A444" }' -H "Content-Type: application/json" -X PUT localhost:8080/api/books/1
{"id":1,"title":"T444","author":"A444"}

# Delete a Book
$ curl -X DELETE localhost:8080/api/books/1
```