# Autenticador API
Esta é uma API autorizador construída com Spring Boot, que segue os padrões REST. Ela foi criada para para o teste prático de um mini autorizador de transações de cartão de benefício controlando o saldo de cada cartão e suas transações.

## Tecnologias Utilizadas
Spring Boot: Framework para simplificar o desenvolvimento de aplicações Java, com foco em produtividade e simplicidade de configuração.

Spring Security: Biblioteca para fornecer autenticação e autorização na aplicação.

Spring Data JPA: Para interagir com banco de dados de forma simples e eficiente.


Maven: Gerenciador de dependências e construção do projeto.

HTTP Basic Authentication: Método simples de autenticação para proteger a API.

## Objetivo da Aplicação
Esta é uma API autorizador construída com Spring Boot, que segue os padrões REST. Ela foi criada para para o teste prático de um mini autorizador de transações de cartão de benefício controlando o saldo de cada cartão e suas transações.

## Funcionalidades
Autenticação básica com HTTP Basic Authentication.

Endpoints RESTful que permitem criar, consulta saldo e cria transação.

Uso de Spring Data JPA para persistência de dados no banco de dados mysql.

## Endpoints da API
POST /v1/cartoes: Endpoint para criação de um cartão de benefícios.

GET /v1/cartoes/:numero/saldo: Retorna o saldo do cartão de benefícios

POST /v1/transacoes: Cria um novo recurso no banco de dados.


## Como Executar a Aplicação 
### Pré-requisitos
JDK 17 ou superior: A API foi desenvolvida com o Java 17.
Maven: O projeto usa o Maven para gerenciamento de dependências e construção.

### Instale as dependências

Se você não tiver o Maven instalado globalmente, pode usar o wrapper do Maven para instalar as dependências:

`./mvnw clean install`

### Execute a aplicação

Para executar a aplicação, use o comando abaixo:

bash
Copiar código
`./mvnw spring-boot:run`
Ou, se estiver usando o Maven globalmente:

bash
Copiar código
`mvn spring-boot:run`
Acessando a API

Após iniciar a aplicação, ela estará rodando na URL http://localhost:8080.

Para testar a autenticação HTTP Básica, você pode usar uma ferramenta como Postman ou curl. O nome de usuário e senha padrão são user e password (esses valores podem ser alterados no arquivo application.properties).

Exemplo de requisição curl com autenticação básica:

bash
Copiar código
curl -u user:password http://localhost:8080/v1/cartoes

Na pasta /docs/collections existe arquivos para importar as collections no postman e todas já estão configuradas para chamar os principais endpoints dá aplicação
