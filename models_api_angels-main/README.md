# API de Predição de Risco Gestacional

Esta API foi desenvolvida para realizar predições de risco gestacional com base em dados fornecidos pelo usuário. Este README fornecerá instruções sobre como configurar e executar a API em seu ambiente local.

## Configuração do Ambiente

1. Clone este repositório em seu ambiente local:<br>
```
git clone https://github.com/Erundule/models_api_angels
```

3. Antes de instalar as dependências, é recomendável configurar um ambiente virtual para isolar as dependências do projeto. Você pode criar um ambiente virtual usando o comando `venv`:<br>
```
python -m venv venv
```
5. Ative o ambiente virtual:

No Windows: <br>
```
venv\Scripts\activate
```
No macOS e Linux: <br>
```
source venv/bin/activate
```

5. Instale as dependências do Python listadas no arquivo `requirements.txt`:<br>
```
pip install -r requirements.txt
```

## Executando a API localmente

1. Após instalar as dependências, execute o arquivo `main.py` para iniciar o servidor da API: <br>
```
python main.py
```
2. Após iniciar o servidor, você poderá acessar a API em `http://localhost:5000`.
## Executando a API no docker
```
docker build -t models_api_angels .
```
```
docker run -p 5000:5000 models_api_angels
```

## Uso da API

A API possui as seguintes rotas:

- `/api/`: Rota de teste para verificar se a API está online.
- `/api/predict`: Rota para realizar uma predição de risco gestacional.
- `/api/parameters`: Rota para obter a lista de parâmetros do modelo.

Para fazer uma predição de risco gestacional, envie uma requisição POST para a rota `/api/predict`, fornecendo os parâmetros necessários no corpo da requisição no formato JSON. Consulte a documentação da API para obter mais detalhes sobre os parâmetros esperados e a estrutura das respostas.

## Documentação
https://docs.google.com/document/d/1eQn5PRzQKO3X7t1_WXOm6lRJYP010GqXkthFo1BX2KE/edit?usp=sharing
