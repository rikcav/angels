# Start Eureka Gateway
## Variaveis
É necessário configurar as seguintes variáveis:

`EUREKA_URI`: Endereço de conexão com o servidor Eureka.

## Start
Para executar o projeto é necessário instalar o
[Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) e o [Maven](https://maven.apache.org).

```shell
sudo apt update
sudo apt install openjdk-17-jre maven
```

Em seguida, dentro da pasta `/gateway`, execute os seguintes comandos para gerar o arquivo .jar e inicializar o servidor:
```shell
# Criar dentro target o gateway-0.0.1-SNAPSHOT.jar
mvn clean install

# Executa o .jar
java -jar target/gateway-0.0.1-SNAPSHOT.jar
```


## Start docker
Configure o arquivo compose.yaml de acordo com suas preferências e, em seguida, execute o seguinte comando:
```shell
docker-compose up --build
```