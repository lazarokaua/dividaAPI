

### 1. Criar Dívida (POST)
Endpoint: `http://localhost:8080/dividas`
Method: `POST`
Body (JSON):
```json
{
    "cpfDevedor": "123.456.789-00",
    "valorPego": 1000.00,
    "valorComJuros": 1200.00,
    "valorComDesconto": 1050.00
}
```

### 2. Listar Todas as Dívidas (GET)
Endpoint: `http://localhost:8080/dividas`
Method: `GET`

### 3. Buscar Dívida por ID (GET)
Endpoint: `http://localhost:8080/dividas/{id}`
Method: `GET`
*(Substitua `{id}` pelo ID gerado na criação)*

### 4. Atualizar Dívida (PUT)
Endpoint: `http://localhost:8080/dividas/{id}`
Method: `PUT`
Body (JSON):
```json
{
    "cpfDevedor": "123.456.789-00",
    "valorPego": 1000.00,
    "valorComJuros": 1150.00,
    "valorComDesconto": 1020.00
}
```

### 5. Deletar Dívida (DELETE)
Endpoint: `http://localhost:8080/dividas/{id}`
Method: `DELETE`