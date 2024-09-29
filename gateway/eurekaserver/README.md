# Start Eureka Server
## Variaveis
É necessário configurar as seguintes variáveis:

`PORT`: Porta onde a aplicação deve rodar.

`EUREKA_URI`: Endereço de conexão com o servidor Eureka. Deve consistir no endereço de acesso ao servidor, seguido da 
porta e "/eureka" no final.
`DEPLOY_TYPE`: Tipo de Deploy que está sendo realizado. Valor recomendados para utilização são `dev`, `test` e `prod`;

## Start
Para executar o projeto é necessário instalar o 
[Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) e o [Maven](https://maven.apache.org).

```shell
sudo apt update
sudo apt install openjdk-17-jre maven
```

Em seguida, dentro da pasta `/eurekaserver`, execute os seguintes comandos para gerar o arquivo .jar e inicializar o servidor:
```shell
# Criar dentro target o eurekaserver-0.0.1-SNAPSHOT.jar
mvn clean install

# Executa o .jar
java -jar target/eurekaserver-0.0.1-SNAPSHOT.jar
```

## Start docker
Configure o arquivo compose.yaml de acordo com suas preferências e, em seguida, execute o seguinte comando:
```shell
docker-compose up --build
```

