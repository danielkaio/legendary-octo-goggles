# API de Usuários

[![Java 21](https://img.shields.io/badge/Java-21-green)](https://www.java.com/)
[![Spring Boot 3.3.2](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-v8+-blue)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow)](LICENSE)

API REST desenvolvida com **Spring Boot** para gerenciamento completo de usuários.

## 📋 Sumário

- [Tecnologias](#-tecnologias-utilizadas)
- [Características](#-características)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Instalação](#-instalação)
- [Como Executar](#-como-executar)
- [Documentação da API](#-documentação-da-api)
- [Exemplos de Uso](#-exemplos-de-uso)
- [Testes](#-testes)
- [Estrutura de Camadas](#-estrutura-de-camadas)

## 🛠️ Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| Java | 21 | Linguagem de programação |
| Spring Boot | 3.3.2 | Framework web |
| Spring Data JPA | 3.3.2 | Acesso a dados |
| MySQL | 8.0+ | Banco de dados relacional |
| Lombok | Latest | Redução de boilerplate |
| Springdoc OpenAPI | 2.5.0 | Documentação Swagger |
| JUnit 5 | Latest | Framework de testes |
| Mockito | Latest | Mock de objetos |
| H2 Database | Latest | Banco em memória para testes |

## ✨ Características

- ✅ Listar todos os usuários
- ✅ Buscar usuário por ID
- ✅ Criar novo usuário
- ✅ Atualizar usuário existente
- ✅ Remover usuário por ID
- ✅ Remover todos os usuários
- ✅ Documentação interativa com Swagger
- ✅ Testes unitários completos
- ✅ Transações gerenciadas com `@Transactional`

## 📁 Estrutura do Projeto

```
legendary-octo-goggles/
├── src/
│   ├── main/
│   │   ├── java/com/api/usuarios/
│   │   │   ├── controller/
│   │   │   │   └── UsuarioController.java      # Endpoints HTTP
│   │   │   ├── service/
│   │   │   │   └── UserService.java            # Lógica de negócio
│   │   │   ├── repository/
│   │   │   │   └── UserRepository.java         # Acesso a dados
│   │   │   ├── entity/
│   │   │   │   └── User.java                   # Modelo de dados
│   │   │   ├── dto/
│   │   │   │   └── UserDto.java                # Transfer object
│   │   │   └── UsuariosApplication.java        # Classe principal
│   │   └── resources/
│   │       └── application.properties           # Configurações
│   └── test/
│       └── java/com/api/usuarios/
│           ├── controller/
│           ├── service/
│           ├── entity/
│           └── dto/
├── pom.xml                                      # Dependências Maven
└── README.md                                    # Este arquivo
```

## 📦 Instalação

### Pré-requisitos

- **Java 21** ([Download](https://www.oracle.com/java/technologies/downloads/#java21))
- **Maven 3.8+** ([Download](https://maven.apache.org/download.cgi))
- **MySQL 8.0+** ([Download](https://www.mysql.com/downloads/))

### Clonar o repositório

```bash
git clone https://github.com/seu-usuario/legendary-octo-goggles.git
cd legendary-octo-goggles
```

### Configurar banco de dados

Crie o banco de dados `dev` no MySQL:

```sql
CREATE DATABASE dev;
```

Se necessário, ajuste as credenciais no arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dev?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
spring.datasource.username=devuser
spring.datasource.password=12345
```

## ▶️ Como Executar

### Via Maven Wrapper (Windows/Linux/macOS)

```bash
./mvnw spring-boot:run
```

### Via Maven instalado

```bash
mvn spring-boot:run
```

A aplicação iniciará em `http://localhost:8080`

## 📚 Documentação da API

### Swagger UI

Após iniciar a aplicação, acesse a documentação interativa:

```
http://localhost:8080/swagger-ui/index.html
```

### Base URL

```
http://localhost:8080/usuarios
```

## 🧪 Exemplos de Uso

### 1️⃣ Listar todos os usuários

```bash
curl -X GET http://localhost:8080/usuarios \
  -H "Content-Type: application/json"
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao@example.com"
  },
  {
    "id": 2,
    "nome": "Maria Santos",
    "email": "maria@example.com"
  }
]
```

---

### 2️⃣ Buscar usuário por ID

```bash
curl -X GET http://localhost:8080/usuarios/1 \
  -H "Content-Type: application/json"
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@example.com"
}
```

**Possível resposta (404 Not Found):**
```json
{
  "timestamp": "2026-05-04T10:30:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found with id: 999"
}
```

---

### 3️⃣ Criar novo usuário

```bash
curl -X POST http://localhost:8080/usuarios \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "João Silva",
    "email": "joao@example.com"
  }'
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@example.com"
}
```

---

### 4️⃣ Atualizar usuário

```bash
curl -X PUT http://localhost:8080/usuarios/1 \
  -H "Content-Type: application/json" \
  -d '{
    "id": 1,
    "nome": "João Atualizado",
    "email": "joao.novo@example.com"
  }'
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "nome": "João Atualizado",
  "email": "joao.novo@example.com"
}
```

**Possível resposta (404 Not Found):**
```json
{
  "timestamp": "2026-05-04T10:30:00.000+00:00",
  "status": 404,
  "error": "Not Found",
  "message": "User not found with id: 999"
}
```

---

### 5️⃣ Remover usuário por ID

```bash
curl -X DELETE http://localhost:8080/usuarios/1
```

**Resposta (204 No Content)**

---

### 6️⃣ Remover todos os usuários

```bash
curl -X DELETE http://localhost:8080/usuarios
```

**Resposta (200 OK)**

## 🧪 Testes

### Executar todos os testes

```bash
./mvnw test
```

ou

```bash
mvn test
```

### Testes disponíveis

- **UsuarioControllerTests** - Testes dos endpoints HTTP
- **UserServiceTests** - Testes da lógica de negócio
- **UserTests** - Testes da entidade User
- **UserDtoTests** - Testes do DTO

### Relatório de cobertura

```bash
./mvnw clean test
```

Os resultados estarão em `target/surefire-reports/`

## 🏗️ Estrutura de Camadas

### Controller
Responsável por expor os endpoints HTTP e mapear requisições/respostas.
- **Arquivo:** `src/main/java/com/api/usuarios/controller/UsuarioController.java`
- **Anotações:** `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`

### Service
Contém toda a lógica de negócio e processamento de dados.
- **Arquivo:** `src/main/java/com/api/usuarios/service/UserService.java`
- **Responsabilidades:** CRUD, transformação de entidades em DTOs, transações

### Repository
Fornece abstração para acesso ao banco de dados via JPA.
- **Arquivo:** `src/main/java/com/api/usuarios/repository/UserRepository.java`
- **Herança:** `JpaRepository<User, Long>`

### Entity
Representa a tabela `users` no banco de dados.
- **Arquivo:** `src/main/java/com/api/usuarios/entity/User.java`
- **Campos:** `id`, `nome`, `email`

### DTO
Objeto de transferência de dados para requisições/respostas HTTP.
- **Arquivo:** `src/main/java/com/api/usuarios/dto/UserDto.java`
- **Uso:** Listar, buscar e atualizar usuários

## 🔄 Fluxo de Requisição

```
Cliente (HTTP)
    ↓
UsuarioController (Mapeia requisição)
    ↓
UserService (Lógica de negócio)
    ↓
UserRepository (Acesso a dados)
    ↓
MySQL (Persistência)
```

## 📝 Notas Importantes

- A aplicação usa `@Transactional` para gerenciar transações automáticas
- O Hibernate está configurado com `ddl-auto=update` para criar/atualizar tabelas automaticamente
- Lombok reduz boilerplate com `@Getter` e `@Setter`
- A documentação é gerada automaticamente via Springdoc OpenAPI

## 🚀 Melhorias Futuras

- [ ] Implementar validação de dados com `@Valid`
- [ ] Adicionar paginação nos resultados
- [ ] Implementar busca por email
- [ ] Adicionar autenticação JWT
- [ ] Implementar tratamento de exceções customizado
- [ ] Adicionar logs estruturados
- [ ] Implementar cache com Redis
- [ ] Adicionar testes de integração

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

Desenvolvido para estudo de desenvolvimento de APIs REST com Spring Boot.

---

**Última atualização:** 4 de maio de 2026

