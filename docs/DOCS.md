# Estórias do usuário

## [US001] - Fazer sign in

Eu como usuário gostaria de me logar no sistema

### Critérios de Aceitação
```
- O usuário deve informar login e password ativos e cadastrados na base de dados.
- O sistema deve validar a existência e a validade do login e da senha.
- O sistema deve retornar um token de acesso com informações do usuário logado.
- O sistema deve permitir que o usuário faça requisições em todos os endpoints que requerem autenticação usando o token de acesso.
- O token de acesso deve ter um período razoável de validade.

Cenário 1: Login e senha válidos
DADO QUE o login e a senha informados estão válidos e corretos  
ENTÃO o sistema deve retornar um token de acesso com as informações do usuário.

Cenário 2: Login ou senha inválidos
DADO QUE o login e/ou a senha informados estão inválidos e/ou incorretos  
ENTÃO o sistema deve retornar uma mensagem de exceção “Invalid login or password”.
```
---
## [US002] - Listar todos os usuários

Eu como usuário gostaria de ver todos os usuários do sistema

### Critérios de Aceitação
```
- O usuário deve ter acesso à lista de todos os usuários do sistema.

Cenário 1: Listar todos os usuários
DADO QUE o usuário requisitou todos os usuários do sistema 
ENTÃO o sistema deve retornar uma lista com todos os usuários do sistema. Caso não haja usuários cadastrados no sistema, a lista deve ser retornada vazia.
```

## [US003] - Cadastrar um novo usuário

Eu como usuário gostaria de cadastrar um novo usuário no sistema

### Critérios de Aceitação
```
- O usuário deve informar o primeiro e último nome, email, data de nascimento, login, senha e telefone do novo usuário.
- O sistema deve validar se já existe cadastro na base para o email e o login informados.
- O sistema deve retornar uma exceção caso haja alguma campo com formato inválido como, por exemplo, data de nascimento e telefone.

Cenário 1: Usuário cadastrado
DADO QUE os dados informados na criação do usuário estão preenchidos corretamente e não há conflito com cadastros anteriores do sistema
ENTÃO o sistema deve confirmar que a criação do usuário foi bem sucedida e o id do novo usuário.

Cenário 2: Email já existe
DADO QUE o email informado já foi cadastrado anteriormente no sistema
ENTÃO o sistema deve retornar um erro com a mensagem “Email already exists”.

Cenário 3: Login já existe
DADO QUE o login informado já foi cadastrado anteriormente no sistema
ENTÃO o sistema deve retornar um erro com a mensagem “Login already exists”.

Cenário 4: Campos inválidos
DADO QUE algum dos campos informados está inválido
ENTÃO o sistema deve retornar um erro com a mensagem “Invalid fields”.

Cenário 5: Campos não preenchidos
DADO QUE algum dos campos não foi preenchido
ENTÃO o sistema deve retornar um erro com com código 5 e mensagem “Missing fields”.
```

## [US004] - Buscar um usuário pelo id

Eu como usuário gostaria de ver os dados de um usuário cadastrado no sistema pelo ID

### Critérios de Aceitação
```
- O usuário deve informar ID de um usuário cadastrado na base de dados do sistema.
- O sistema não deve informar a senha do usuário na resposta.

Cenário 1: Usuário encontrado pelo ID
DADO QUE foi possível encontrar um usuário pelo ID informado 
ENTÃO o sistema deve retornar os dados do usuário associado ao ID.

Cenário 2: Usuário NÃO encontrado pelo ID
DADO QUE não foi possível encontrar um usuário pelo ID informado 
ENTÃO o sistema não deve retornar uma mensagem de exceção mas deve indicar na resposta que nenhum usuário foi encontrado.
```

## [US005] - Remover um usuário pelo id

Eu como usuário gostaria de remover um usuário do sistema pelo ID

### Critérios de Aceitação
```
- O usuário deve informar o ID de um usuário cadastrado na base de dados do sistema.

Cenário 1: Usuário removido
DADO QUE foi possível encontrar e excluir um usuário pelo ID informado
ENTÃO o sistema deve excluir também os registros de carros do usuário e indicar na resposta que a exclusão foi feita com sucesso.

Cenário 2: Usuário NÃO removido
DADO QUE não foi possível encontrar e excluir um usuário pelo ID informado 
ENTÃO o sistema não deve retornar uma mensagem de exceção mas deve indicar na resposta que nenhum usuário foi encontrado.
```

## [US006] - Atualizar um usuário pelo id

Eu como usuário gostaria de atualizar um usuário no sistema pelo ID

### Critérios de Aceitação
```
- O usuário deve informar o primeiro e último nome, email, data de nascimento, login e telefone do usuário.
- O administrador do sistema deve informar a senha do usuário somente quando desejar alterá-la.
- O administrador do sistema deve informar o ID do usuário a ser atualizado.

Cenário 1: Usuário atualizado
DADO QUE os dados informados na criação do usuário estão preenchidos corretamente, não há conflito com cadastros anteriores do sistema e o usuário foi encontrado pelo ID
ENTÃO o sistema deve confirmar que a atualização do usuário foi bem sucedida e retornar os dados atualizados, exceto dados sensíveis (login e senha).

Cenário 2: Email já existe
DADO QUE o email informado já foi cadastrado anteriormente no sistema para outro usuário
ENTÃO o sistema deve retornar um erro com a mensagem “Email already exists”.

Cenário 3: Login já existe
DADO QUE o login informado já foi cadastrado anteriormente no sistema para outro usuário
ENTÃO o sistema deve retornar um erro com a mensagem “Login already exists”.

Cenário 4: Campos inválidos
DADO QUE algum dos campos informados está inválido
ENTÃO o sistema deve retornar um erro com a mensagem “Invalid fields”.

Cenário 5: Campos não preenchidos
DADO QUE algum dos campos não foi preenchido
ENTÃO o sistema deve retornar um erro com a mensagem “Missing fields”.
```

## [US007] - Retornar informações do usuário logado

Eu como usuário gostaria de ver os dados do usuário logado no sistema

### Critérios de Aceitação
```
- O usuário deve estar logado no sistema.
- O usuário deve informar o token de acesso não expirado.
- O sistema deve retornar o primeiro e o último nome, email, data de nascimento, login, telefone, carros, data de criação e data de último login do usuário.

Cenário 1: Usuário logado.
DADO QUE o usuário está logado no sistema
ENTÃO o sistema deve retornar os dados do usuário logado.

Cenário 2: Token não informado
DADO QUE o token de acesso não foi enviado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized”.

Cenário 3: Token expirado
DADO QUE o token de acesso informado está incorreto ou expirado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized- invalid session”.
```

## [US008] - Listar todos os carros do usuário logado

Eu como usuário gostaria de ver os carros do usuário logado no sistema

### Critérios de Aceitação`
```
- O usuário deve estar logado no sistema.
- O sistema deve retornar uma lista com todos os carros do usuário logado.

Cenário 1: Login e senha válidos
DADO QUE o login e a senha informados estão válidos e corretos  
ENTÃO o sistema deve retornar a lista de carros do usuário logados.

Cenário 2: Token não informado
DADO QUE o token de acesso não foi enviado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized”.

Cenário 3: Token expirado
DADO QUE o token de acesso informado está incorreto ou expirado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized- invalid session”.
```

## [US009] - Cadastrar um novo carro para o usuário logado

Eu como usuário gostaria de cadastrar um carro para o usuário logado

### Critérios de Aceitação
```
- O usuário deve estar logado no sistema.
- O usuário deve informar ano, placa, modelo e cor do carro.

Cenário 1: Carro cadastrado com sucesso
DADO QUE as informações do carro estão válidas, não há conflitos com registros anteriores e o usuário está devidamente autenticado
ENTÃO o sistema deve retornar a lista com todos os carros do usuário logado.

Cenário 2: Placa já existe
DADO QUE a placa informada já foi cadastrada anteriormente no sistema
ENTÃO o sistema deve retornar um erro com a mensagem “License plate already exists”.

Cenário 3: Campos inválidos
DADO QUE algum dos campos informados está inválido
ENTÃO o sistema deve retornar um erro com a mensagem “Invalid fields”.

Cenário 4: Campos não preenchidos
DADO QUE algum dos campos não foi preenchido
ENTÃO o sistema deve retornar um erro com a mensagem “Missing fields”.

Cenário 5: Token não informado
DADO QUE o token de acesso não foi enviado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized”.

Cenário 6: Token expirado
DADO QUE o token de acesso informado está incorreto ou expirado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized- invalid session”.
```

## [US010] - Buscar um carro do usuário logado pelo id

Eu como usuário gostaria de buscar um carro cadastrado para o usuário logado pelo ID.

### Critérios de Aceitação
```
- O usuário deve estar logado no sistema.
- O sistema deve retornar o primeiro e o último nome, email, data de nascimento, login, telefone, carros, data de criação e data de último login do usuário.

Cenário 1: Carro encontrado.
DADO QUE o ID do carro informado existe para o usuário logado
ENTÃO o sistema deve retornar os dados do carro.

Cenário 1: Carro não encontrado.
DADO QUE o ID do carro informado não existe para o usuário logado
ENTÃO o sistema não deve retornar mensagem de erro mas deve indicar na resposta que o carro não foi encontrado.

Cenário 2: Token não informado
DADO QUE o token de acesso não foi enviado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized”.

Cenário 3: Token expirado
DADO QUE o token de acesso informado está incorreto ou expirado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized- invalid session”.
```

## [US011] - Remover um carro do usuário logado pelo id

Eu como usuário gostaria de remover um carro do usuário logado pelo ID.

### Critérios de Aceitação
```
- O usuário deve estar autenticado no sistema.
- O sistema deve validar se o carro informado pelo ID está cadastrado para o usuário logado.
- O sistema deve excluir o carro informado.

Cenário 1: Carro removido.
DADO QUE o ID do carro informado existe para o usuário logado
ENTÃO o sistema deve remover o carro da lista do usuário.

Cenário 1: Carro não encontrado.
DADO QUE o ID do carro informado não existe para o usuário logado
ENTÃO o sistema não deve retornar erro porém deve indicar que o carro não foi encontrado.

Cenário 2: Token não informado
DADO QUE o token de acesso não foi enviado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized”.

Cenário 3: Token expirado
DADO QUE o token de acesso informado está incorreto ou expirado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized- invalid session”.
```

## [US012] - Atualizar um carro do usuário logado pelo id

Eu como usuário gostaria de atualizar um carro do usuário logado pelo ID.

### Critérios de Aceitação
```
- O usuário deve estar logado no sistema.
- O usuário deve informar ano, placa, modelo e cor do carro a ser atualizado.
- O usuário deve informar o ID do carro a ser atualizado.

Cenário 1: Carro atualizado
DADO QUE os dados informados na atualização do usuário estão preenchidos corretamente, não há conflito com cadastros anteriores do sistema e o carro foi encontrado pelo ID para o usuário logado.
ENTÃO o sistema deve confirmar que a atualização do carro foi bem sucedida e retornar os dados atualizados.

Cenário 2: Placa já existe
DADO QUE o email informado já foi cadastrado anteriormente no sistema para outro carro
ENTÃO o sistema deve retornar um erro com a mensagem “License plate already exists”.

Cenário 3: Campos inválidos
DADO QUE algum dos campos informados está inválido
ENTÃO o sistema deve retornar um erro com a mensagem “Invalid fields”.

Cenário 4: Campos não preenchidos
DADO QUE algum dos campos não foi preenchido
ENTÃO o sistema deve retornar um erro com com a mensagem “Missing fields”.

Cenário 5: Token não informado
DADO QUE o token de acesso não foi enviado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized”.

Cenário 6: Token expirado
DADO QUE o token de acesso informado está incorreto ou expirado
ENTÃO o sistema deve retornar um erro com a mensagem de exceção “Unauthorized- invalid session”.
```

# Solução

  Foi desenvolvida uma aplicação que permita ao usuário criar, ler, editar, e deletar registros de carros do usuário com facilidade e segurança.

  A aplicação consiste em uma API Rest no servidor desenvolvida na linguagem Java que conta com uma grande comunidade de desenvolvedores e farta documentação, e a facilidade de aplicar conceitos importantes na programação como Design Patterns e Orientação à Objetos. Além disso, a linguagem fornece compatibilidade com os mais diversos sistemas operacionais.
  
  Toda a configuração do servidor WEB, segurança, testes e conexão com o banco de dados é feito utilizando o Spring, mais especificamente o Spring Boot, Spring Data JPA e Spring Security. Tais ferramentas fornecem a facilidade e a rapidez na configuração do servidor, bem como a segurança e a confiabilidade de uma ferramenta consolidada e largamente adotada no mercado.
  
  Outras tecnologias utilizadas são o banco de dados em memória H2 Database para persistência de dados, o JSON Web Tokens como estratégia de autenticação no sistema, o Swagger como documentação da API, JUnit para os testes unitários e de integração, Apache Maven para gerenciar as dependências e automatizar tasks, Git para versionamento de código fonte, TravisCI para garantir a integração contínua e Heroku para deploy da aplicação. Todas estas tecnologias são amplamente adotadas na comunidade de desenvolvimento WEB satisfatoriamente.

  ## Desenvolvedor
  
   **Ricardo de Lima Rocha**
  *  [Linkedin](https://www.linkedin.com/in/ricardo-de-lima-rocha/)
  *  [GitHub](https://github.com/devricardorocha)
