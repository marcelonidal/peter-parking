# peter-parking
Projeto FIAP parquimetro

TÍTULO DO PROJETO: Peter Parking

Autores:

●	Marcelo Nidal
●	Evaldo Fires
●	Fernando Monin
●	Fabio Aquino
●	Vinícius Campos

## Sumário
1. [Sobre o Projeto](#sobre-o-projeto)
2. [Instalação com Docker](#instalação-do-mongo-para-usardocker)
3. [Uso do Shell e Comandos Úteis](#uso-do-shell)
4. [Como consumir as rotas do projeto](#chamadas-via-insomniapostman)
5. [Como consumir as rotas do projeto](#subindo-a-aplicação-e-o-mongo-com-docker)

## Sobre o projeto
https://www.figma.com/board/eFixy0yt61WAYY21CPrwiq/Untitled?node-id=0-1&t=hrcMU3oJyB3mzfLF-1
#### Doc Técnica
http://localhost:8080/parquimetro-api/swagger-ui/index.html#/

## Instalação do mongo para usar(docker)
### Baixar e rodar ou apenas rodar
Instalar: docker run --name mongo-peter-parking -d -p 27017:27017 mongo  
Iniciar: docker start mongo-peter-parking  
Stopar: docker stop mongo-peter-parking  
Validar: docker ps

OBS: Pode-se utilizar o MongoCompass para melhor visualização dos dados  
https://www.mongodb.com/products/tools/compass

### Uso do shell
Caso esteja utilizando com docker, ele já vem com o shell na imagem.       
Utilizar o shell: docker exec -it mongo-peter-parking mongosh  
Acessar console do container: docker exec -it mongo-peter-parking bash  
Listar DBs: show dbs  
Trocar para Parquimetro: use parquimetro  
Listar carros estacionados: db.parquimetro.find()  
Listar carros cadastrados: db.carros.find()  
Inserir um carro: 
```json
db.carro.insertOne({placaDoCarro:"EXW6772", modelo:"Celta"})
```
Estacionando um carro:   
```json
db.parquimetro.insertOne({  
carro: ObjectId('677c920f34b71e91e9c1c18c'),  
estacionamentoEntrada: ISODate('2025-01-07T02:37:40.490Z'),
permanencia: ISODate('2025-01-07T04:37:40.490Z')  
});
```

### Chamadas via Insomnia/Postman
```textmate
//Utilizar a doc técnica
http://localhost:8080/parquimetro-api/swagger-ui/index.html#/
```

### Subindo a aplicação e o mongo com docker
```textmate
---------CRIANDO A IMAGEM--------------
1 - Gerar o .jar
mvn clean package

2 - Constrói Imagem da aplicação
cd {Path_Projeto}/peter-parking
docker build -t peter-parking:latest .

3 - Criar uma rede para comunicação entre a aplicação e o mongo
docker network create parkingrede

4 - Subir container do mongo com a rede criada
docker run --name mongo-peter-parking --network parkingrede -d -p 27017:27017 mongo

5 - Executar container com imagem da aplicação
docker run --name app-peter-parking --network parkingrede -e MONGO_NAME=mongo-peter-parking -d -p 8080:8080 peter-parking

------------REUTILIZANDO IMGEM-----------------------
Após subir a imagem no dockerhub de maneira pública, basta utilizar a imagem pública para subir a aplicação
docker run --name app-peter-parking --network parkingrede -e MONGO_NAME=mongo-peter-parking -d -p 8080:8080 majorv22/peter-parking:1.0

```