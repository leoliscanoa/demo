## API DEMO

# STACK
- Docker | Docker Compose V2
- JDK 17
- Maven

# Contenedores

- Tener instalado Docker y Docker compose

## Run

```shell
docker-compose -f docker-compose.yml -p demo up --force-recreate -d
```

## Down

```shell
docker-compose -f docker-compose.yml -p demo down --remove-orphans
```


# SpringBoot - Java

- Tener instalado maven

## Compile
```shell
mvn clean package
```

## Run
```shell
java -jar /target/app.jar
```

# HTTP
- count: hace referencia al número de objetos a generar
http://localhost:8080/chuck-norris/{count}