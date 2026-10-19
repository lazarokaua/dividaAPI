# 💳 DividaAPI — Gerenciamento e Controle de Dívidas

API RESTful desenvolvida em **Java** e **Spring Boot** para cadastro, consulta, atualização e exclusão de dívidas financeiras.

---

## 📌 Origem do Projeto & Contexto

Este projeto foi desenvolvido como resolução do teste técnico para a vaga de **Desenvolvedor(a) Backend Java Júnior**. 

O documento com os requisitos e especificações completas do teste técnico está disponível na raiz deste repositório:
📄 **[teste_tecnico_java_spring_boot_controle_dividas.pdf](teste_tecnico_java_spring_boot_controle_dividas.pdf)**

Além de cobrir integralmente os requisitos funcionais solicitados (CRUD de dívidas, persistência relacional, validação de CPF e integridade monetária), o projeto implementa os diferenciais técnicos recomendados pelo desafio:
- ✅ **Documentação interativa com OpenAPI / Swagger UI**
- ✅ **Conteinerização com Docker e Docker Compose**
- ✅ **Controle de versionamento de banco com Flyway Migrations**
- ✅ **Paginação nas listagens (`Pageable`)**
- ✅ **Consulta filtrada por CPF**
- ✅ **Validações declarativas com Bean Validation (`@CPF`, `@PositiveOrZero`)**
- ✅ **Tratamento global e padronizado de exceções (`@ControllerAdvice`)**

---

## 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Framework:** Spring Boot 4.x
  - **Spring Web MVC:** Construção dos endpoints REST
  - **Spring Data JPA:** Abstração da camada de persistência com Hibernate
  - **Spring Validation:** Validação de entradas e regras de integridade
- **Banco de Dados:** PostgreSQL 18
- **Database Migration:** Flyway
- **Documentação:** Springdoc OpenAPI / Swagger UI (v2.2.0)
- **Produtividade:** Lombok
- **DevOps:** Docker e Docker Compose
- **Gerenciador de Dependências:** Maven

---

## 🏛️ Arquitetura e Organização de Camadas

A aplicação adota separação clara de responsabilidades:

```text
src/main/java/com/lazarokaua/dividaAPI/
├── controller/
│   └── DividaController.java         # Exposição dos endpoints REST e status HTTP
├── domain/divida/
│   ├── Divida.java                   # Entidade JPA mapeada para a tabela 'dividas'
│   ├── DividaDTO.java                # Record de entrada com anotações de validação
│   ├── DividaResponse.java           # Record de resposta customizado
│   ├── DividaRepository.java         # Interface Spring Data JPA e queries personalizadas
│   ├── service/
│   │   └── DividaService.java        # Regras de negócio, sanitização de CPF e persistência
│   └── exception/
│       └── DividaNotFoundException.java # Exceção de negócio para registros não localizados
├── infra/exception/
│   └── GlobalExceptionHandler.java   # Interceptador global (@ControllerAdvice) para erros 400 e 404
└── util/
    └── Converter.java                # Conversão desacoplada entre Entidade e DTO
```

---

## 🚀 Como Executar o Projeto

### Pré-requisitos
- **Java 17+** (JDK) instalado
- **Docker** e **Docker Compose** instalados e em execução
- **Maven** (ou utilize o wrapper `./mvnw` incluído no projeto)

---

### Passo 1: Configurar Variáveis de Ambiente
O projeto conta com um arquivo `.env` na raiz definindo as credenciais do banco:
```env
POSTGRES_USER=postgres
POSTGRES_PASSWORD=postgres
POSTGRES_DB=divida_db
```

### Passo 2: Subir o Banco de Dados com Docker Compose
O banco PostgreSQL roda na porta externa **`5436`** (para evitar conflitos com instâncias locais padrões na 5432):

```bash
docker compose up -d
```

Para verificar o status do contêiner:
```bash
docker compose ps
```

> **Nota:** As migrações do banco de dados são executadas de forma automática pelo **Flyway** assim que a aplicação Spring Boot inicia (`V1__create_table_dividas.sql`).

### Passo 3: Executar a Aplicação Spring Boot
Execute via Maven Wrapper:

```bash
# No Linux/macOS:
./mvnw spring-boot:run

# No Windows (CMD / PowerShell):
./mvnw.cmd spring-boot:run
```

A aplicação estará disponível em `http://localhost:8080`.

---

## 📖 Documentação Interativa (Swagger / OpenAPI)

Com a aplicação rodando, acesse a interface interativa do Swagger para visualizar todos os contratos, esquemas e testar as rotas diretamente pelo navegador:

🔗 **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
🔗 **OpenAPI Docs (JSON):** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 📬 Coleção Postman

Para facilitar a validação e execução dos testes manuais, foi disponibilizada na raiz do repositório a collection oficial do Postman:

📁 **`dividaAPI.postman_collection.json`**

### Como importar no Postman:
1. Abra o Postman.
2. Clique no botão **Import** (canto superior esquerdo).
3. Selecione o arquivo `dividaAPI.postman_collection.json` localizado na raiz do projeto.
4. Execute as requisições pré-configuradas para criação, busca, listagem, atualização e exclusão.

---

## 🛣️ Endpoints da API

| Método | Endpoint | Descrição | Status Sucesso |
| :--- | :--- | :--- | :--- |
| `POST` | `/dividas` | Cadastra uma nova dívida | `201 Created` |
| `GET` | `/dividas` | Lista dívidas paginadas (`?page=0&size=10&sort=id,asc`) | `200 OK` |
| `GET` | `/dividas/{id}` | Consulta detalhes de uma dívida por ID | `200 OK` |
| `GET` | `/dividas/buscarPorCpf/{cpf}` | Consulta dívidas associadas a um CPF (paginado) | `200 OK` |
| `PUT` | `/dividas/{id}` | Atualiza uma dívida existente | `200 OK` |
| `DELETE` | `/dividas/{id}` | Exclui uma dívida existente | `204 No Content` |

---

### Exemplos de Requisições e Respostas

#### 1. Criar Dívida
- **Rota:** `POST /dividas`
- **Body:**
```json
{
  "cpfDevedor": "123.456.789-00",
  "valorPego": 1000.00,
  "valorComJuros": 1200.00,
  "valorComDesconto": 1050.00
}
```
- **Header de Resposta:** `Location: http://localhost:8080/dividas/1`
- **Resposta (`201 Created`):**
```json
{
  "id": 1,
  "cpfDevedor": "12345678900",
  "valorPego": 1000.00
}
```

#### 2. Listar Todas as Dívidas (Paginado)
- **Rota:** `GET /dividas?page=0&size=10`
- **Resposta (`200 OK`):** Retorna o objeto `Page` padrão do Spring Data, contendo array `content` e metadados de paginação (`totalPages`, `totalElements`, etc.).

#### 3. Buscar por CPF
- **Rota:** `GET /dividas/buscarPorCpf/123.456.789-00`
- **Resposta (`200 OK`):** Retorna a lista paginada das dívidas do devedor correspondente (o CPF é sanitizado no backend, aceitando tanto com pontuação quanto apenas dígitos).

#### 4. Atualizar Dívida
- **Rota:** `PUT /dividas/{id}`
- **Body:**
```json
{
  "cpfDevedor": "12345678900",
  "valorPego": 1000.00,
  "valorComJuros": 1150.00,
  "valorComDesconto": 1020.00
}
```
- **Resposta (`200 OK`):**
```json
{
  "id": 1,
  "cpfDevedor": "12345678900",
  "valorPego": 1000.00
}
```

#### 5. Excluir Dívida
- **Rota:** `DELETE /dividas/{id}`
- **Resposta:** `204 No Content`

---

## 🛡️ Tratamento de Erros e Validações

- **Validação de Entrada (`400 Bad Request`):**  
  Se qualquer campo não cumprir os requisitos (ex.: CPF inválido pelo validador `@CPF`, campos em branco ou valores monetários negativos via `@PositiveOrZero`), a API retorna um mapa com os campos com erro e suas respectivas mensagens descritivas.
- **Registro Inexistente (`404 Not Found`):**  
  Requisições de busca, atualização ou deleção para IDs que não constam na base de dados disparam `DividaNotFoundException`, interceptada pelo `GlobalExceptionHandler` retornando status `404 Not Found`.