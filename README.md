# Raízes do Nordeste API

API REST desenvolvida como projeto acadêmico para atender necessidade do cliente "Raízes do Nordeste", empresa ficticia em crescente expansão. Na API proposta, é apresentado toda a estrutura do restaurante, gerenciamento de pedidos, clientes, produtos e pagamentos, simulando o funcionamento de um sistema de delivery.

---

## Tecnologias Utilizadas

-> Java 21
-> Spring Boot
-> Spring Data JPA
-> Spring Security (JWT)
-> Maven
-> Swagger (OpenAPI)
-> Postman (testes)

---

## Funcionalidades

-> Cadastro de clientes
-> Cadastro de produtos, categorias e unidades
-> Criação de pedidos com múltiplos itens
-> Aplicação de promoções
-> Processamento de pagamento (mock)
-> Atualização de status do pedido
-> Autenticação com JWT (Token)
-> Documentação com Swagger

---

## Autenticação

A API utiliza autenticação via JWT.

### Login

```http
POST /auth/login
```

### Body:

```json
{
  "username": "admin",
  "password": "123"
}
```

### Resposta:

```json
{
  "token": "seu_token_aqui"
}
```

* Utilize o token nas requisições:

```http
Authorization: Bearer seu_token_aqui
```

---

## Fluxo principal da aplicação

1. Criar produtos
2. Cadastrar clientes
3. Criar pedido
4. Processar pagamento
5. Atualizar status do pedido

### Fluxo de status:

```
RECEIVED → PAID → PREPARING → READY → DELIVERED
```

---

## Exemplo de criação de pedido

```http
POST /orders
```

```json
{
  "channel": "APP",
  "customerId": 1,
  "items": [
    {
      "productId": 2,
      "quantity": 2
    }
  ]
}
```

---

## Processamento de pagamento (Mock)

```http
POST /payments/process/{orderId}
```

* O pagamento é simulado (mock), retornando sucesso automaticamente.

---

## Atualização de status do pedido

```http
PATCH /orders/{id}/status?status=PREPARING
PATCH /orders/{id}/status?status=READY
PATCH /orders/{id}/status?status=DELIVERED
```

---

## Tratamento de erros

A API possui tratamento global de exceções.

### Exemplo:

```http
GET /orders/999
```

Resposta:

```json
{
  "status": 404,
  "error": "Not Found",
  "message": "Order not found"
}
```

---

## Documentação da API

Disponível via Swagger:

```text
http://localhost:8080/swagger-ui/index.html
```

---

## Testes

Os testes foram realizados utilizando o Postman, incluindo:

-> Fluxo completo de pedidos
-> Autenticação com JWT
-> Processamento de pagamento
-> Atualização de status
-> Cenários de erro (404, 403)

A collection com os testes da API está disponível em:

/postman/OpenAPI definition_Raizes_do_Nordeste_collection.json

Importe no Postman para executar os testes.

---

## Estrutura do Projeto

```
config     → configuração da aplicação (Spring Security, filtros, etc.)
controller → definição dos endpoints REST
service    → regras de negócio da aplicação
repository → acesso ao banco de dados (JPA)
entity     → entidades JPA
dto        → objetos de transferência de dados
enums      → tipos e estados da aplicação (status, métodos, etc.)
security   → autenticação e autorização (JWT)
exception  → tratamento de erros e exceções
```

---

## Observações

* O processamento de pagamento foi implementado como **mock**, simulando integração com gateway externo.
* O projeto segue boas práticas de arquitetura em camadas.
* A API está preparada para futura integração com frontend.

---

## Autor

Victor Gabriel Moreira
RU 4655363

---

## Status do Projeto

-> Concluído para fins acadêmicos
-> Pronto para evolução futura
