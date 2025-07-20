# BTG Pactual Challenge

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.3-brightgreen)
![MongoDB](https://img.shields.io/badge/MongoDB-6.0-green)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3-orange)
![Docker](https://img.shields.io/badge/Docker-✓-blue)
![Maven](https://img.shields.io/badge/Maven-4.0.0-red)

Este projeto é uma solução para o [Desafio Técnico do BTG Pactual](https://brunograna.notion.site/Desafio-Backend-BTG-Pactual-Build-Run-3f48048e3e594fbea580c006eac6ff08), desenvolvido em Java com Spring Boot, seguindo os princípios da **Arquitetura Hexagonal** e **Domain Driven Design (DDD)**.

## Arquitetura

![Arquitetura do Sistema](https://raw.githubusercontent.com/DouglasVulcano/btgpactual_challenge/main/src/main/architecture-diagram.png)

O projeto implementa uma arquitetura hexagonal com as seguintes camadas:

### 🏗️ Estrutura de Camadas

#### **Domain Layer (Camada de Domínio) - 100% Limpa**
- `domain/model/` - Entidades puras do domínio (Order, OrderItem) - **SEM anotações**
- `domain/ports/` - Interfaces/Contratos (OrderServicePort, OrderRepositoryPort)
- `domain/services/` - Serviços de domínio com regras de negócio (OrderService)

#### **Application Layer (Camada de Aplicação) - Adaptadores de Entrada**
- `application/controllers/` - REST Controllers (OrderController)
- `application/listener/` - Event Listeners (OrderCreatedListener)
- `application/dtos/rabbitMq/` - DTOs para eventos RabbitMQ (OrderEventDto, OrderItemEventDto)
- `application/mappers/` - Conversores entre camadas (OrderMapper, OrderItemMapper)
- `application/entities/` - Entidades MongoDB com anotações (OrderEntity, OrderItemEntity)
- `application/repositories/` - Spring Data Repositories e Adaptadores
  - `OrderRepository` - Interface Spring Data MongoDB
  - `implementations/OrderMongoPort` - Adapter que implementa OrderRepositoryPort

#### **Infrastructure Layer (Camada de Infraestrutura) - Configurações**
- `infrastructure/config/` - Configurações do Spring
  - `BeansConfig` - Configuração de injeção de dependências
  - `RabbitMqConfig` - Configuração do RabbitMQ

## 📋 Pré-requisitos

- Docker
- Docker Compose
- Java 21 (para desenvolvimento local)
- Maven (para desenvolvimento local)

## ⚙️ Variáveis de Ambiente

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```env
# MongoDB Configuration
MONGO_ROOT_USERNAME=root
MONGO_ROOT_PASSWORD=example
MONGO_DATABASE=orders-btg
SPRING_DATA_MONGODB_URI=mongodb://root:example@mongo:27017/orders-btg?authSource=admin

# RabbitMQ Configuration
RABBITMQ_HOST=rabbitmq
RABBITMQ_PORT=5672
RABBITMQ_DEFAULT_USER=guest
RABBITMQ_DEFAULT_PASS=guest
```

## 🐳 Como Executar

### Execução com Docker (Recomendado)

2. Configure o arquivo `.env` (copie do `.env.example`):
   ```bash
   cp .env.example .env
   ```

3. Execute o projeto:
   ```bash
   docker-compose up -d --build
   ```

4. Verifique se os serviços estão rodando:
   ```bash
   docker-compose ps
   ```

### Execução Local (Desenvolvimento)

1. Inicie apenas os serviços de infraestrutura:
   ```bash
   docker-compose up -d mongo rabbitmq
   ```

2. Execute a aplicação:
   ```bash
   ./mvnw spring-boot:run
   ```

## 📡 Endpoints da API

### Pedidos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/orders` | Lista todos os pedidos |
| GET | `/orders/{id}` | Busca pedido por ID |

### Exemplos de Uso

```bash
# Listar todos os pedidos
curl -X GET http://localhost:8080/orders

# Buscar pedido específico
curl -X GET http://localhost:8080/orders/1
```

## 🐰 RabbitMQ

### Configuração

- **Queue**: `order-created-queue`
- **Exchange**: Default
- **Management UI**: http://localhost:15672 (guest/guest)

### Formato do Evento

```json
{
  "codigoPedido": 1001,
  "codigoCliente": 1,
  "itens": [
    {
      "produto": "lápis",
      "quantidade": 100,
      "preco": 1.10
    },
    {
      "produto": "caderno",
      "quantidade": 10,
      "preco": 1.00
    }
  ]
}
```

## 🗄️ MongoDB

### Configuração

- **Porta**: 27017
- **Database**: orders-btg
- **Collection**: orders

### Estrutura do Documento

```json
{
  "_id": ObjectId("..."),
  "orderId": 1001,
  "customerId": 1,
  "items": [
    {
      "product": "lápis",
      "quantity": 100,
      "price": 1.10
    }
  ],
  "total": 120.00,
  "createdAt": "2024-01-01T10:00:00Z"
}
```

## 📊 Monitoramento

### Health Checks

- **Aplicação**: http://localhost:8080/actuator/health
- **RabbitMQ Management**: http://localhost:15672
- **MongoDB**: Conecte via cliente MongoDB na porta 27017

### Logs

```bash
# Logs da aplicação
docker-compose logs -f app

# Logs do RabbitMQ
docker-compose logs -f rabbitmq

# Logs do MongoDB
docker-compose logs -f mongo
```


## 🛠️ Stack Tecnológica

- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.3** - Framework principal
- **Spring Data MongoDB** - Persistência
- **Spring AMQP** - Mensageria
- **MongoDB 6.0** - Banco de dados NoSQL
- **RabbitMQ 3** - Message Broker
- **Docker & Docker Compose** - Containerização
- **Maven** - Gerenciamento de dependências

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/br/com/vulcanodev/btgpactual_challenge/
│   │   ├── BtgpactualChallengeApplication.java  # Main Class
│   │   ├── application/                         # CAMADA DE APLICAÇÃO
│   │   │   ├── controllers/
│   │   │   │   └── OrderController.java         # REST API
│   │   │   ├── dtos/rabbitMq/
│   │   │   │   ├── OrderEventDto.java           # Event DTO
│   │   │   │   └── OrderItemEventDto.java       # Item DTO
│   │   │   ├── entities/
│   │   │   │   ├── OrderEntity.java             # MongoDB Entity
│   │   │   │   └── OrderItemEntity.java         # Item Entity
│   │   │   ├── listener/
│   │   │   │   └── OrderCreatedListener.java    # RabbitMQ Listener
│   │   │   ├── mappers/
│   │   │   │   ├── OrderMapper.java             # Order Converter
│   │   │   │   └── OrderItemMapper.java         # Item Converter
│   │   │   └── repositories/
│   │   │       ├── OrderRepository.java         # Spring Data Interface
│   │   │       └── implementations/
│   │   │           └── OrderMongoPort.java      # Repository Adapter
│   │   ├── domain/                              # CAMADA DE DOMÍNIO (PURA)
│   │   │   ├── model/
│   │   │   │   ├── Order.java                   # Domain Entity (SEM anotações)
│   │   │   │   └── OrderItem.java               # Value Object
│   │   │   ├── ports/
│   │   │   │   ├── OrderServicePort.java        # Service Port
│   │   │   │   └── OrderRepositoryPort.java     # Repository Port
│   │   │   └── services/
│   │   │       └── OrderService.java            # Domain Service
│   │   └── infrastructure/                      # CAMADA DE INFRAESTRUTURA
│   │       └── config/
│   │           ├── BeansConfig.java             # DI Configuration
│   │           └── RabbitMqConfig.java          # RabbitMQ Setup
│   └── resources/
│       └── application.yml                      # App Configuration
├── test/java/                                   # Tests
├── .env.example                                 # Environment Template
├── docker-compose.yml                           # Docker Services
├── Dockerfile                                   # App Container
└── pom.xml                                      # Maven Dependencies
```

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](https://github.com/DouglasVulcano/btgpactual_challenge/blob/main/LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Douglas Vulcano**
- GitHub: [@DouglasVulcano](https://github.com/DouglasVulcano)
- LinkedIn: [Douglas Vulcano](https://www.linkedin.com/in/douglas-da-silva-vulcano/)
