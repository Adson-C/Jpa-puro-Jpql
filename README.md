# JohnFood API - Estudo de JPA e JPQL

![Java](https://img.shields.io/badge/Java-17-blue?style=for-the-badge&logo=java)
![Spring](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=spring)
![JPA](https://img.shields.io/badge/JPA-Hibernate-orange?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-3.8-red?style=for-the-badge&logo=apache-maven)

API RESTful para um sistema de delivery de comida, desenvolvida com Java e Spring Boot. O projeto serve como um estudo aprofundado sobre o uso da **Java Persistence API (JPA)** e da **Java Persistence Query Language (JPQL)** para realizar operações de persistência e consultas complexas ao banco de dados de forma eficiente.

## 🎯 Objetivo

O principal objetivo deste projeto é demonstrar na prática a aplicação dos conceitos de JPA e JPQL em uma arquitetura de software bem definida, seguindo as melhores práticas de desenvolvimento para APIs, como o uso de DTOs, separação de responsabilidades em camadas e tratamento de exceções.

## ✨ Funcionalidades Principais

*   CRUD completo para Restaurantes, Cozinhas e Formas de Pagamento.
*   Consultas dinâmicas e complexas utilizando JPQL.
*   Busca de restaurantes por nome, faixa de taxa de frete e frete grátis.
*   Mapeamento de relacionamentos entre entidades (`@OneToMany`, `@ManyToOne`, `@ManyToMany`).
*   Paginação e ordenação de resultados.

## 🛠️ Tecnologias Utilizadas

*   **Java 17**: Versão LTS da linguagem.
*   **Spring Boot**: Framework para criação de aplicações stand-alone.
*   **Spring Data JPA**: Facilita a implementação de repositórios baseados em JPA.
*   **Hibernate**: Implementação do provedor de persistência JPA.
*   **Maven**: Gerenciador de dependências e build do projeto.
*   **H2 Database**: Banco de dados em memória para ambiente de desenvolvimento e testes.
*   **Lombok**: Reduz o código boilerplate (getters, setters, construtores).

## 🏗️ Arquitetura do Projeto

O projeto segue uma arquitetura em camadas para garantir a separação de responsabilidades, manutenibilidade e testabilidade do código.

### 1. `Entity` (Entidade)

As entidades são classes Java que representam as tabelas do banco de dados. Elas são anotadas com `@Entity` e contêm os mapeamentos de colunas e relacionamentos (ex: `@OneToMany`, `@ManyToOne`). Esta é a camada de domínio principal.

*   **Exemplos**: `Restaurante.java`, `Cozinha.java`, `FormaPagamento.java`.


### 2. `Repository` (Repositório)

São interfaces que estendem `JpaRepository`. Elas abstraem o acesso aos dados, fornecendo métodos CRUD prontos (`save`, `findById`, `findAll`, `delete`) e permitindo a criação de consultas customizadas com JPQL através da anotação `@Query` ou por meio de *Query Methods*.

*   **Exemplos**: `RestauranteRepository.java`, `CozinhaRepository.java`.


### 3. `DTO` (Data Transfer Object)

São objetos simples utilizados para transferir dados entre as camadas, especialmente entre os `Controllers` e os clientes da API. O uso de DTOs evita a exposição direta das entidades de domínio (`@Entity`), permitindo moldar os dados que a API expõe e recebe, além de evitar problemas de serialização e segurança.

*   **Exemplos**: `RestauranteDTO.java` (para saída), `RestauranteInputDTO.java` (para entrada).


### 4. `Service` (Serviço)

A camada de serviço contém a lógica de negócio da aplicação. Ela orquestra as operações, utilizando os `Repositories` para acessar o banco de dados, validando regras de negócio e convertendo `Entities` em `DTOs` (e vice-versa) antes de se comunicar com os `Controllers`.

*   **Exemplos**: `CadastroRestauranteService.java`, `CadastroCozinhaService.java`.

### 5. `Controller` (Controlador)

Responsáveis por expor os endpoints da API REST. Eles recebem as requisições HTTP, delegam a execução para a camada de `Service`, tratam os parâmetros de entrada (como `@PathVariable`, `@RequestBody`) e retornam as respostas HTTP com os `DTOs` apropriados e os status codes corretos.

*   **Exemplos**: `RestauranteController.java`, `CozinhaController.java`.


## 🚀 Como Executar o Projeto

### Pré-requisitos

*   Java 17 ou superior
*   Maven 3.8 ou superior

### Passos

1.  **Clone o repositório:**


2.  **Execute a aplicação:**
    O projeto está configurado para usar o banco de dados em memória H2, então nenhuma configuração adicional é necessária.
    
git clone https://github.com/AdsonSa/johnfood-api.git
cd johnfood-api
```

```bash
./mvnw spring-boot:run
```


## 🤝 Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou enviar pull requests.

## 📄 Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo `LICENSE` para mais detalhes.

---

Desenvolvido com ❤️ por [Adson Sá](https://github.com/AdsonSa)

