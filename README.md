# Sea Tecnologia - Backend Challenge

API REST para sistema de Solicitações de Atendimento.

## Tecnologias

- Java 21
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- JPA/Hibernate
- Bean Validation
- ViaCEP
- Docker Compose

## Como rodar

No Windows:

```bash
.\mvnw clean package
docker compose up --build

## A API ficará disponível em:

http://localhost:8080

## Autenticação:

POST /auth/register
POST /auth/login
GET /auth/me

## Solicitações:

POST /solicitations
GET /solicitations/my
PUT /solicitations/{id}/step1
PUT /solicitations/{id}/step2
PUT /solicitations/{id}/step3
POST /solicitations/{id}/submit

## Análise:

GET /solicitations/submitted
POST /solicitations/{id}/start-analysis
POST /solicitations/{id}/approve
POST /solicitations/{id}/reject

## Fluxo principal
Cliente se cadastra.
Cliente faz login e recebe token JWT.
Cliente cria solicitação em rascunho.
Cliente preenche Step 1, Step 2 e Step 3.
Cliente envia para análise.
Analista inicia análise.
Analista aprova ou rejeita.

## Cadastro público cria apenas usuários CLIENT.
Senhas são criptografadas com BCrypt.
Step 2 consulta ViaCEP pelo CEP informado.
Docker Compose sobe a API e o PostgreSQL.