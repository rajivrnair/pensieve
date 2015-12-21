# Docker-ized app

### Build pensieve_app
```shell
  $> docker build -t pensieve_app .
  $> docker-compose up
  $> docker-compose stop
```

### Manually run the database only
```shell
  $> docker-compose up pensieve-database
```
(or) without docker-compose
```shell
  $> docker run -p 5432  -e POSTGRES_USER=pensieve -e POSTGRES_PASSWORD=pensieve -e POSTGRES_DB=memories -d pensieve_db
```

### Connect to the docker database (192.168.99.100 - is my docker-machine's IP)
```shell
  $> psql -h 192.168.99.100 -p 5555 -U pensieve --password -d memories
```

### Hit the app to test - http://192.168.99.100:8888/pensieve/ping

### To clean up
```shell
  $> docker stop $(docker ps -a -q)
  $> docker rm $(docker ps -a -q)
  $> docker rmi $(docker images -q)
```
