# API Veterinária (Spring Boot)

## Visão Geral

Esta aplicação é uma API RESTful em Spring Boot para gerenciar um sistema veterinário, com autenticação via JWT e operações CRUD para as entidades principais. Todos os endpoints (exceto login) exigem token JWT válido.

### Funcionalidades

- **Autenticação**

  - Endpoint de login (`/api/logar`) para gerar token JWT com validade de 24 horas
  - Validação de usuário e senha através de credenciais configuradas em `application.properties`

- **Cadastro de Usuários**

  - Endpoint de login (`/api/cadastrar`) para cadastrar um usuário ao banco

- **CRUD de Animais** (`/api/animais`)

  - Listar, buscar por ID, buscar animais por pessoaId, criar, atualizar e excluir registros de animais, relacionando-os a pessoas

- **CRUD de Pessoas** (`/api/pessoas`)

  - Listar, buscar por ID, criar, atualizar e excluir registros de pessoas

- **CRUD de Serviços** (`/api/servicos`)

  - Listar, buscar por ID, buscar por animalId, criar, atualizar e excluir registros de serviços prestados, relacionando-os a animais

- **Documentação Swagger/OpenAPI**
  - Interface Swagger disponível para explorar e testar todos os endpoints

---

## Estrutura de Pastas

---

## Índice

- [Descrição](#descrição)
- [Pré-requisitos](#pré-requisitos)
- [Configuração e Execução](#configuração-e-execução)
- [Fluxo de Autenticação](#fluxo-de-autenticação)
- [Endpoints](#endpoints)
- [Swagger / OpenAPI](#swagger--openapi)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Autor](#autor)

---

## Descrição

A aplicação foi desenvolvida como estudos em JWT para uma API de gerenciamento veterinário:

1. **Auth** (`/api/logar`):

   - Endpoint para login de usuário.
   - Geração de token JWT válido por 24 horas.
   - Configurado via `application.properties`.

   (`/api/cadastrar`):

   - Endpoint para cadastro do usuário.

2. **Services** (`/api/******`):
   - Endpoints CRUD para entidades: `Animal`, `Pessoa`, e `Servico`.
   - Protegidos por JWT; somente usuários autenticados (via API Auth) podem acessá-los.
   - Persistência em banco de dados MySQL.
   - Documentação Swagger disponível.

---

## Pré-requisitos

- Java 17 ou superior
- Maven 3.6+
- MySQL (Para armazenamento dos usuários)
- MongoDB Para armazenamento dos controllers(`Animais, Pessoas, Serviços`)

---

## Configuração e Execução

1. Acesse o diretório:

   ```bash
   cd api/src/main
   ```

2. Configure, o token de acesso em `src/main/resources/application.properties`:

   ```properties
   jwt.secret=<chave-secreta>
   jwt.expiration=86400000
   ```

3. Configure a conexão com o MySQL em `src/main/resources/application.properties`:

   ```properties
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.url=jdbc:mysql://localhost:3306/NOME_BD
    spring.datasource.username=USER_BD
    spring.datasource.password=SENHA_BD
    spring.jpa.hibernate.ddl-auto=update

   ```

4. Configure a conexão do MongoDB em `src/main/resources/application.properties`:

   ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/NOME_BD
   ```

5. Execute a aplicação:

   A API ficará disponível em `http://localhost:8080`.

---

## Fluxo de Autenticação

1. Faça o cadastro de um usuário:

- **URL**: `POST http://localhost:8080/api/cadastrar`
- **Payload**:
  ```json
  {
    "user": "adm",
    "senha": "123"
  }
  ```

2. Faça login com o usuário criado:

   - **URL**: `POST http://localhost:8080/api/logar`
   - **Payload**:
     ```json
     {
       "user": "adm",
       "senha": "123"
     }
     ```
   - **Resposta**:
     ```json
     {
       "message": "Authenticated",
       "token": "<JWT_TOKEN>"
     }
     ```

3. Para acessar qualquer endpoint protegido na API, inclua o token que foi retornado no login em Authorization:
   ```
   Authorization: Bearer <JWT_TOKEN>
   ```

---

## Endpoints

#### Autenticação

- `POST /api/cadastrar`

  - Body (JSON): `{ "user": "<username>", "password": "<password>" }`
  - Retorno: `{ "user": "<username>", "password": "<password>" }`

- `POST /api/login`
  - Descrição: Gera token JWT para usuário válido.
  - Body (JSON): `{ "user": "<username>", "password": "<password>" }`
  - Retorno: `{ "message": "Authenticated", "token": "<JWT_TOKEN>" }`

> **Observação**: Todos os endpoints abaixo exigem o header `Authorization: Bearer <JWT_TOKEN>`.

#### Animais

- `GET /api/animais`
  - Lista todos os animais.
- `GET /api/animais/{id}`
  - Busca animal por ID.
- `GET /api/animais/pessoa/{pessoaId}`
  - Lista todos os animais associados a uma pessoa.
- `POST /api/animais`
  - Cria um novo animal.
  - Body: `AnimalModel` (JSON com campos `nome`, `especie`, `raca`, `idade`, `pessoaId`, etc.)
- `PUT /api/animais/{id}`
  - Atualiza um animal existente.
  - Body: `AnimalModel`.
- `DELETE /api/animais/{id}`
  - Exclui um animal por ID.

#### Pessoa

- `GET /api/pessoas`
  - Lista todas as pessoas.
- `GET /api/pessoas/{id}`
  - Busca pessoa por ID.
- `POST /api/pessoas`
  - Cria uma nova pessoa.
  - Body: `PessoaModel` (JSON com `nome`, `cpf`, `telefone`, `endereco`.)
- `PUT /api/pessoas/{id}`
  - Atualiza os dados de uma pessoa.
  - Body: `PessoaModel`.
- `DELETE /api/pessoas/{id}`
  - Exclui uma pessoa.

#### Servico

- `GET /api/servicos`
  - Lista todos os serviços.
- `GET /api/servicos/{id}`
  - Busca serviço por ID.
- `GET /api/servicos/animal/{animalId}`
  - Busca serviços por animalId.
- `POST /api/servicos`
  - Cria um novo serviço.
  - Body: `ServicoModel`.
- `PUT /api/servicos/{id}`
  - Atualiza um serviço existente.
  - Body: `ServicoModel`.
- `DELETE /api/servicos/{id}`
  - Exclui um serviço.

> As models (ex.: `AnimalModel`, `PessoaModel`, `ServicoModel`, `UsuarioModel`) podem ser encontradas em `src/main/java/com/example/api_vet/models`.

---

## Swagger / OpenAPI

A documentação Swagger da API está disponível em:

```
http://localhost:8080/swagger-ui.html
```

para visualizar e testar todos os endpoints.

---

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Spring Security (JWT)**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **MongoDB**
- **Docker**
- **Swagger / Springdoc OpenAPI**
- **Maven**

---

## Autor Caio Feiria

Este projeto foi desenvolvido para fins de estudos e demonstração de APIs RESTful com autenticação JWT utilizando Spring Boot.
