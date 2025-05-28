
# 📦 Retail API em Java – Como Usar

API multimodelo com suporte a múltiplos bancos: ObjectDB, PostgreSQL (Data Warehouse), MongoDB e dados temporais.

## 🌍 API Base URL

```
https://quetzal-novel-man.ngrok-free.app
```

## 📋 Headers Obrigatórios

Todas as requisições devem conter:

```
ngrok-skip-browser-warning: skip
```

Se a requisição usar `body`, também incluir:

```
Content-Type: application/json
```

---

## 🗃️ PostgreSQL – Data Warehouse

### 📁 Client – `/api/dw/dim/client`

- **GET** `/api/dw/dim/client` – Buscar todos os clientes
- **GET** `/api/dw/dim/client/{id}` – Buscar cliente por ID
- **POST** `/api/dw/dim/client` – Criar cliente  
  **Body:**
  ```json
  {
    "name": "João Silva",
    "email": "joao@email.com",
    "city": "São Paulo",
    "state": "SP"
  }
  ```
- **PUT** `/api/dw/dim/client` – Atualizar cliente (mesmo body acima com `id`)
- **DELETE** `/api/dw/dim/client/{id}` – Deletar cliente

---

### 🎬 Film – `/api/dw/dim/film`

- **Campos:** `title`, `genre`, `duration`

---

### 🏬 Store – `/api/dw/dim/store`

- **Campos:** `name`, `city`, `state`

---

### 📅 Time – `/api/dw/dim/time`

- **Campos:** `year`, `month`, `day`, `date` (formato: `yyyy-MM-dd`)

---

### 📊 Rental – `/api/dw/fact/rental`

- **GET** `/api/dw/fact/rental` – Buscar todos os aluguéis
- **GET** `/api/dw/fact/rental/{id}` – Buscar aluguel por ID
- **POST/PUT** `/api/dw/fact/rental`  
  **Body:**
  ```json
  {
    "timeId": 1,
    "filmId": 2,
    "clientId": 3,
    "storeId": 4,
    "paidValue": 50.0,
    "quantity": 2
  }
  ```
- **DELETE** `/api/dw/fact/rental/{id}` – Deletar aluguel

---

## ⏱️ Dados Temporais

### 💲 Price – `/api/temporal/price`

- **Campos:** `filmId`, `price`, `startValidity`, `endValidity`

```json
{
  "filmId": 1,
  "price": 29.99,
  "startValidity": "2024-01-01",
  "endValidity": "2024-06-30"
}
```

---

### 📦 Stock – `/api/temporal/stock`

- **Campos:** `filmId`, `quantity`, `startValidity`, `endValidity`

```json
{
  "filmId": 1,
  "quantity": 150,
  "startValidity": "2024-01-01",
  "endValidity": "2024-06-30"
}
```

---

## 🍃 MongoDB

### 🖼️ FilmImage – `/api/mongo/filmImage`

- **Campos:** `filmId`, `imageUrl`, `description`

```json
{
  "filmId": 2,
  "imageUrl": "https://example.com/image.jpg",
  "description": "Capa promocional"
}
```

---

### 💬 Comment – `/api/mongo/comment`

- **Campos:** `filmId`, `clientId`, `comment`, `evaluation`, `commentInstant`

```json
{
  "filmId": 2,
  "clientId": 1,
  "comment": "Filme excelente!",
  "evaluation": 4.5,
  "commentInstant": "2024-05-01T10:15:30Z"
}
```

---

## 📈 OLAP – Análises Multidimensionais

A API permite realizar operações OLAP com as dimensões **tempo** e **categoria** dos filmes (gênero), para apoiar a análise de desempenho de locações ao longo do tempo.

### 🔁 Roll-Up por Gênero – `/api/olap/rollupByGenre`

- **GET** `/api/olap/rollupByGenre`
- **Retorno:**
```json
[
  { "genre": "Ação", "total": 1520.50 },
  { "genre": "Comédia", "total": 870.00 }
]
```

---

### 🔍 Drill-Down para Filmes – `/api/olap/drilldownToFilm`

- **GET** `/api/olap/drilldownToFilm`
- **Retorno:**
```json
[
  { "genre": "Ação", "title": "Velozes e Furiosos", "total": 850.00 },
  { "genre": "Ação", "title": "Missão Impossível", "total": 670.50 }
]
```

---

### 🔁 Roll-Up por Ano – `/api/olap/rollupByYear`

- **GET** `/api/olap/rollupByYear`
- **Retorno:**
```json
[
  { "year": 2023, "total": 3200.00 },
  { "year": 2024, "total": 2780.00 }
]
```

---

### 🔍 Drill-Down para Mês – `/api/olap/drilldownToMonth`

- **GET** `/api/olap/drilldownToMonth`
- **Retorno:**
```json
[
  { "year": 2024, "month": 1, "total": 850.00 },
  { "year": 2024, "month": 2, "total": 690.00 }
]
```

---

## 📎 Exemplo de Headers

```http
Headers:
ngrok-skip-browser-warning: skip
Content-Type: application/json
```

---

## 📬 Contato

Para dúvidas ou contribuições, entre em contato com o responsável pelo projeto.
