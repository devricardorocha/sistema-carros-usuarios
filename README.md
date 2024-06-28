# Sistema de gestão de Carros e Usuários API v1.0.0
[![Build Status](https://app.travis-ci.com/devricardorocha/sistema-carros-usuarios.svg?token=rd6y1xpgonxq1yDc1EPx&branch=develop)](https://app.travis-ci.com/devricardorocha/sistema-carros-usuarios)

Sistema para gerenciar o registro de usuários e carros.

---
### Ferramentas

*  [Java](https://www.java.com/pt_BR/)
*  [Spring Boot](https://spring.io/projects/spring-boot)
*  [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
*  [Spring Security](https://spring.io/projects/spring-security)
*  [H2 Database](https://www.h2database.com/html/main.html)
*  [Apache Maven](https://maven.apache.org/)
*  [JUnit](https://junit.org/junit5/)
*  [Travis CI](https://travis-ci.com/)
*  [Heroku](https://dashboard.heroku.com/)
*  [Swagger](https://swagger.io/)
---

#### API

  A API e sua documentação estão disponíveis no Heroku:
  
  * [Link](https://sistema-carros-usuarios-36f7a5a2f523.herokuapp.com/swagger-ui/index.html) para a documentação do Swagger.
  * [Link](https://sistema-carros-usuarios-36f7a5a2f523.herokuapp.com) para a documentação do Javadocs das principais classes e interfaces.

---
#### UI

  Disponibilizamos uma User Interface integrada com a API onde é possível ver o funcionamento da API em tempo real. Clique no [link](https://github.com/devricardorocha/sistema-carros-usuarios-ui) para o repositório e siga as instruções.

---
### Estórias do usuário e Solução

As estórias do usuário e a defesa da solução estão compilados no arquivo [DOCS.md](https://github.com/devricardorocha/sistema-carros-usuarios/blob/develop/docs/DOCS.md)

---
### Configuração do ambiente

Siga as instruções para configurar o ambiente de desenvolvimento.

#### Pré requisitos: 
* [Java 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Apache Maven 3.6.3+](https://maven.apache.org/download.cgi)

#### Clone do projeto:

  ```
  git clone https://github.com/devricardorocha/sistema-carros-usuarios.git
  ``` 

#### Instalação do projeto:

  Na raiz do projeto, execute o comando:
  ```
  ./mvnw install -DskipTests=true
  ```

#### Executar os testes:

  Na IDE de sua preferência ou nas variáveis de sistema do seu servidor, crie as seguintes variáves:

  ```
TEST_DATABASE_URL= [URL para a base de dados de teste]
  ```

  Na raiz do projeto, execute o comando:
  ```
  ./mvn test
  ```

#### Executar a aplicação no ambiente local:

  Na IDE de sua preferência ou nas variáveis de sistema do seu servidor, crie as seguintes variáves:

  ```
  DATABASE_USER= [Usuário do banco de dados]
  DATABASE_PASSWORD= [Senha do usuário do banco de dados]
  DATABASE_URL= [URL do banco de dados H2 Database]
  API_JWT_SECRET= [Um hash SHA-256 de uma string codificada em Base64]
  API_JWT_EXPIRATION= [Templo em milissegundos para a expiração do JWT Token]
  ```

  Na raiz do projeto, execute o comando:
  ```
  ./mvnw spring-boot:run
  ```

  Após o fim da execução do comando, acesse a API em [http://localhost:8080/api](http://localhost:8080/api).

  ## Desenvolvedor
  
   **Ricardo de Lima Rocha**
  *  [Linkedin](https://www.linkedin.com/in/ricardo-de-lima-rocha/)
  *  [GitHub](https://github.com/devricardorocha)
