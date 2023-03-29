## Prerequisites:
- Docker installed (`docker run hello-world` works for you)
- Java17 installed

## How to run the project
1. `docker run -d -e POSTGRES_PASSWORD=<password> -e POSTGRES_USER=<username> -i --name pop-sfi-container -p 5555:5432 paniodprogramowania/pop-sfi:1.1`
2. run the application
3. open swagger UI http://localhost:8080/swagger-ui/index.html 