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
4. [Fazendo Backup e Restore do Mongo](#backup-e-restore)
5. [Como consumir as rotas do projeto](#chamadas-via-insomniapostman)

## Sobre o projeto
https://www.figma.com/board/eFixy0yt61WAYY21CPrwiq/Untitled?node-id=0-1&t=hrcMU3oJyB3mzfLF-1
#### Doc Técnica
http://localhost:8080/parquimetro-api/swagger-ui/index.html#/

## Instalação do mongo para usar(docker)
### Baixar e rodar ou apenas rodar
Instalar: docker run --name peter-parking -d -p 27017:27017 mongo  
Iniciar: docker start peter_parking  
Stopar: docker stop peter_parking  
Validar: docker ps

OBS: Pode-se utilizar o MongoCompass para melhor visualização dos dados  
https://www.mongodb.com/products/tools/compass

### Uso do shell
Caso esteja utilizando com docker, ele já vem com o shell na imagem.       
Utilizar o shell: docker exec -it peter-parking mongosh  
Acessar console do container: docker exec -it peter-parking bash  
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

### Backup e Restore
#### Criando backup
Roda comando criando conexão entre pasta local e container  
-- docker run -d --name peter-parking -v C:\Users\{SEU_USER}\mongodb\backup:/parquimetro -p 27017:27017 mongo  

#### Entrar no mongo shell para executar comandos
-- docker exec -it peter-parking mongosh

##TO BE CONTINUED


### Chamadas via Insomnia/Postman
```json
//Trazer todos os carros cadastrados
curl --request GET \
--url http://localhost:8080/carros \
--header 'User-Agent: insomnia/10.3.0'
```