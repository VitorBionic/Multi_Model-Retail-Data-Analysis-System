# 🛍️ Sistema de Análise de Dados de Varejo Multimodelo

Este projeto é um sistema de análise de dados voltado para o domínio de uma **locadora de filmes**, utilizando uma abordagem **multimodelo de banco de dados** (ObjectDB, PostgreSQL e MongoDB). O sistema permite análises históricas, KPIs de vendas e consultas OLAP.

---

## 📁 Estrutura do Projeto

| Item                     | Caminho                                 | Descrição |
|--------------------------|------------------------------------------|-----------|
| 📄 Relatório Técnico     | [`/artifacts/relatório-técnico.docx`](./artifacts/relatório-técnico.docx) | Documentação completa do projeto, arquitetura e decisões técnicas. |
| 📘 Tutorial da API       | [`/artifacts/como-usar-api.docx`](./artifacts/como-usar-api.docx) | Guia para consumo dos endpoints da API REST. |
| 🌐 Interface Frontend    | [`/retail-analysis-api`](./retail-analysis-api) | Aplicação frontend para visualização dos KPIs e análises. |
| 🛠️ API (Backend Spring Boot) | [`/retail-analysis-api`](./retail-analysis-api) | Código-fonte da API REST, com integração multimodelo. |
| 🧩 Schemas dos Bancos    | [`/schemas-dos-bancos-de-dados`](./schemas-dos-bancos-de-dados) | Schemas e inserts dos bancos: ObjectDB, PostgreSQL (DW), MongoDB. |

---

## 🔧 Tecnologias Utilizadas

- **Java 21 + Spring Boot 3**
- **ObjectDB** – Banco Objeto-Relacional para gerenciamento de produtos
- **PostgreSQL** – Data Warehouse (Esquema Estrela)
- **MongoDB** – Armazenamento de dados não estruturados (comentários, imagens)
- **HTML/CSS/JavaScript** – Interface Web (consumo via Fetch API)

---

## 🔍 Funcionalidades Principais

- 📊 Dashboard com KPIs de locações e desempenho
- 📈 Análises OLAP com 4 dimensões (tempo, loja, gênero, filme)
- 🕒 Consultas temporais (estoque e preços ao longo do tempo)
- 🔗 API RESTful com integração entre múltiplos bancos

---

## 🚀 Como Executar

> Consulte o arquivo [📘 Como Usar a API](./artifacts/como-usar-api.docx) para um passo a passo detalhado.

---
