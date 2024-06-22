---
name: Estória do usuário
about: Criar estória do usuário
title: "[US001] - "
labels: documentation, enhancement
assignees: devricardorocha

---

## Estória do Usuário
**COMO** usuário  
**QUERO** fazer sign in no sistema  
**PARA** poder acessar os recursos do sistema que requerem autenticação

## Critérios de Aceitação
- O usuário deve informar login e password ativos e cadastrados na base de dados.

## Cenários

### Cenário 1: Login e senha válidos
**DADO QUE** o login e a senha informados estão válidos e corretos  
**ENTÃO** o sistema deve retornar um token de acesso com as informações do usuário.

### Cenário 2: Login ou senha inválidos
**DADO QUE** o login e/ou a senha informados estão inválidos e/ou incorretos  
**ENTÃO** o sistema deve retornar uma mensagem de exceção “Invalid login or password”.
