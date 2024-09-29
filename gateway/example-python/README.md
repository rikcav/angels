# Cadastro de APIs
Para cadastrar novas APIs no servidor, é necessário atender a alguns requisitos para garantir seu funcionamento adequado.

## Rotas
A API deve incluir duas rotas: um endpoint GET em /api/parameters e um endpoint POST em /api/predict.

`/api/parameters`: Retorna uma lista dos parâmetros necessários para o modelo da API.

`/api/predict`: Recebe os parâmetros e retorna a previsão.


## registro
Para realizar o registro no servidor, a API deve utilizar uma biblioteca Cliente Eureka. Como exemplo, utilizaremos 
a biblioteca python-eureka-client em conjunto com o FastAPI.

### python-eureka-client
Para instalar a biblioteca python-eureka-client, utilize o seguinte comando:
```shell
pip install python_eureka_client
```
### Variaveis
Para que a conexão ocorra, é necessário configurar algumas variáveis:

`eureka_server`: Endereço de conexão com o servidor Eureka.

`app_name`: Nome da aplicação. Deve começar com "model" para ser identificada pelo gateway como um modelo.

`instance_host`: Endereço do host que deve ser acessado para chegar a esta instância.

`instance_port`: Número da porta desta instância.

por exemplo:
```python
from py_eureka_client.eureka_client import EurekaClient

eureka_server = "http://localhost:8761/eureka"
app_name = "model-test-service"
instance_host = "127.0.0.1"
instance_port = 8000

client = EurekaClient(eureka_server=eureka_server, app_name=app_name,
                      instance_host=instance_host, instance_port=instance_port, status_page_url="/docs")
```

### Start e stop
Para registrar a instância no servidor, basta chamar o método start() do cliente Eureka. É recomendado utilizar o método 
start() durante o processo de inicialização da API. Aqui está um exemplo de como fazer isso no FastAPI:
```python
from fastapi import FastAPI

app = FastAPI()

@app.on_event("startup")
async def startup_event():
    await client.start()
```
Também é altamente recomendado encerrar a conexão quando a API é desligada. Aqui está um exemplo de como fazer isso:

```python
@app.on_event("shutdown")
async def shutdown_event():
    await client.stop()
```
