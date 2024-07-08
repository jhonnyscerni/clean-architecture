# Estrutura do Clean Architecture

### Modulos

* Domain
* Application
* Infrastructure

### Domain Layer
A camada Domínio fica no centro da Arquitetura Limpa. É o coração de seu aplicativo e responsável por seus modelos principais. Aqui definimos coisas como: entities, value objects, aggregates, domain events, exceptions, repository interfaces, etc.

### Application Layer

A camada Application fica logo acima da camada Domain. Ele atua como um orquestrador para a camada de Domain, contendo os casos de uso mais importantes em sua aplicação.

Você pode estruturar seus casos de uso usando services ou commands e queries. Seguindo o padrão CQRS.

### Infrastructure Layer

A camada de infraestrutura contém implementações para serviços externos. O que se enquadraria nesta categoria?

Databases - SqlServer, PostgreSQL, MongoDB
Identity providers - Auth0, Keycloak
Emails providers
Storage services - AWS, Azure Blob Storage
Message queues - RabbitMQ, Kafka
Controllers

TODO: Adicionar diagrama de arquitetura limpa
TODO: Adicionar diagrama de arquitetura limpa com módulos
TODO: Adicionar diagrama de arquitetura limpa com módulos e camadas
TODO: Adicionar exemplos de código
TODO: Adicionar exemplos de testes
TODO: Adicionar exemplos de CI/CD
TODO: Adicionar exemplos de Docker
TODO: Verificar os mappers se estão corretos