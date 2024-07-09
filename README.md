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

![arquitetura.png](src%2Fmain%2Fresources%2Fimg%2Farquitetura.png)

A camada de infraestrutura contém implementações para serviços externos. O que se enquadraria nesta categoria?

Databases - SqlServer, PostgreSQL, MongoDB
Identity providers - Auth0, Keycloak
Emails providers
Storage services - AWS, Azure Blob Storage
Message queues - RabbitMQ, Kafka
Controllers

### Cobertura de Testes de Mutação Utilozando o Pitest
#### Canmada Application Layer
![pitest-usecase.png](src%2Fmain%2Fresources%2Fimg%2Fpitest-usecase.png)
#### Canmada Infrastructure Layer
![pitest-controller.png](src%2Fmain%2Fresources%2Fimg%2Fpitest-controller.png)

### DUVIDAS: 
#### Na camada de UseCase eu crio um Request e Response para manipular os dados ou eu passo o objeto direto para camada de Infraestrutura para manipular esses dados?

Na Arquitetura Limpa, a camada de UseCase (ou Application Layer) serve como um intermediário entre a interface do usuário (ou qualquer outra camada externa) e a lógica de negócios (Domain Layer). O objetivo é isolar a lógica de negócios de detalhes externos, como a interface do usuário ou o acesso a dados. Isso é feito para manter o código do domínio limpo e focado nas regras de negócio, facilitando a manutenção e testabilidade.  Utilizar Request e Response na camada de UseCase:  
Vantagens:  
Desacoplamento: Ao usar objetos Request e Response específicos para cada caso de uso, você desacopla a lógica de negócios das camadas externas. Isso permite que mudanças na interface do usuário ou na infraestrutura não afetem diretamente a lógica de negócios.
Flexibilidade: Facilita a adaptação da entrada e saída de dados conforme necessário para cada caso de uso, sem impactar outras partes do sistema.
Testabilidade: Torna mais fácil testar os casos de uso isoladamente, pois você pode mockar os objetos Request e Response.
Desvantagens:  
Verbosidade: Pode aumentar a quantidade de código necessária, já que você precisa definir classes Request e Response para cada caso de uso.
Complexidade: Adiciona uma camada extra de abstração, o que pode aumentar a complexidade do sistema.
Passar o objeto direto para a camada de Infraestrutura:  
Vantagens:  
Simplicidade: Reduz a quantidade de código e abstração necessária, tornando o sistema potencialmente mais simples de entender e manter.
Eficiência: Pode ser mais eficiente em termos de desempenho, pois há menos indireções e transformações de dados.
Desvantagens:  
Acoplamento: Aumenta o acoplamento entre as camadas, fazendo com que mudanças na infraestrutura ou na interface do usuário possam afetar diretamente a lógica de negócios.
Flexibilidade e Testabilidade Reduzidas: Torna mais difícil adaptar a entrada e saída de dados para diferentes necessidades e pode complicar os testes isolados da lógica de negócios.
Conclusão:  Na Arquitetura Limpa, é recomendado utilizar objetos Request e Response na camada de UseCase para manipular os dados. Isso promove o desacoplamento, a flexibilidade e a testabilidade do código, alinhando-se com os princípios da arquitetura. Passar objetos diretamente para a camada de infraestrutura pode ser tentador pela simplicidade, mas geralmente resulta em um acoplamento mais forte e menor flexibilidade.

#### Utilizando os conceitos de Arquitetura Limpa Na camada de Infraestrutura eu crio um Request e Response Para manipular os dados ou apresento o objeto passado pela camada de UseCase?

Na Arquitetura Limpa, a camada de Infraestrutura é responsável por implementar os detalhes técnicos necessários para que a aplicação possa operar com o mundo externo, como bancos de dados, sistemas de arquivos, redes, etc. Ela deve ser capaz de comunicar-se com a camada de Aplicação (UseCases) de forma a não violar os princípios de inversão de dependência, mantendo o núcleo do domínio isolado de detalhes externos.  Opção 1: Apresentar o objeto passado pela camada de UseCase  
Vantagens: Mantém a simplicidade e coesão do código, evitando a duplicação de estruturas de dados. Facilita a manutenção, pois qualquer alteração nos objetos de domínio ou nos DTOs de UseCase reflete diretamente na camada de Infraestrutura sem a necessidade de mapeamentos adicionais.
Como fazer: A camada de Infraestrutura recebe e utiliza diretamente os objetos Request e Response definidos na camada de UseCase para operações de entrada e saída de dados.
Opção 2: Criar Request e Response específicos para a camada de Infraestrutura  
Vantagens: Permite uma adaptação mais fina aos requisitos técnicos específicos da infraestrutura, como formatos de dados esperados por um serviço web externo ou esquemas de banco de dados. Isso pode ser útil quando a representação dos dados na camada de Aplicação não se alinha diretamente com esses requisitos.
Como fazer: Definir novas classes de Request e Response na camada de Infraestrutura e implementar mapeadores para converter entre os objetos de domínio ou DTOs de UseCase e essas novas classes.
Conclusão: A escolha entre essas opções depende das necessidades específicas do projeto e dos trade-offs entre simplicidade e flexibilidade. Em muitos casos, utilizar diretamente os objetos passados pela camada de UseCase é suficiente e alinha-se com os princípios de Arquitetura Limpa, promovendo a simplicidade e a manutenção do código. A criação de Request e Response específicos para a camada de Infraestrutura pode ser considerada quando houver requisitos técnicos específicos que justifiquem essa abordagem.

### Utilizando os conceitos de Arquitetura Limpa Na camada de application devo criar um mapper com metodos estaticos ou crio um mapper com injeção de dependencia ?

Na Arquitetura Limpa, a escolha entre criar um mapper com métodos estáticos ou um mapper com injeção de dependência na camada de Application depende de vários fatores, incluindo a complexidade do projeto, a necessidade de testabilidade e a preferência por desacoplamento.  Mapper com Métodos Estáticos:  
Vantagens: Simplicidade e facilidade de uso, sem a necessidade de gerenciar instâncias.
Desvantagens: Menor flexibilidade e testabilidade, pois métodos estáticos não podem ser sobrescritos ou mockados facilmente em testes.
Mapper com Injeção de Dependência:  
Vantagens: Maior flexibilidade e facilita a testabilidade, permitindo a substituição de implementações em testes. Promove o desacoplamento e adere aos princípios de inversão de dependência.
Desvantagens: Maior complexidade na gestão de dependências e na configuração inicial do projeto.
Para um projeto que valoriza a testabilidade e a flexibilidade, especialmente em um ambiente que já faz uso intensivo da injeção de dependência (como projetos Spring Boot), a abordagem com injeção de dependência é recomendada.


#### TODO: Adicionar exemplos de código
#### TODO: Adicionar exemplos de CI/CD
#### TODO: Adicionar exemplos de Docker