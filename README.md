# Sistema de gestão de Carros e Usuários API v1.0.0
[![Build Status](https://app.travis-ci.com/devricardorocha/sistema-carros-usuarios.svg?token=rd6y1xpgonxq1yDc1EPx&branch=develop)](https://app.travis-ci.com/devricardorocha/sistema-carros-usuarios)

Sistema para gerenciar o registro de usuários e carros.

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

  A API e a documentação do Swagger está disponível no Heroku através do [link](http://smartphone-api.herokuapp.com/api/browser/index.html).

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

  Na raiz do projeto, execute o comando:
  ```
  ./mvn test
  ```

#### Executar a aplicação no ambiente local:

  Na raiz do projeto, execute o comando:
  ```
  ./mvnw spring-boot:run
  ```

  Após o fim da execução do comando, acesse a API em [http://localhost:8080/api](http://localhost:8080/api).

  ## Desenvolvedor
  
   **Ricardo de Lima Rocha**
  *  [Linkedin](https://www.linkedin.com/in/ricardo-de-lima-rocha/)
  *  [GitHub](https://github.com/devricardorocha)
