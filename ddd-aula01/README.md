# 🎲Aprendendo a integrar um ***DataBase*** no projeto ***Java***☕

## 👣Primeiros passos:

- Faça o *download* do *driver* tradutor com o banco de dados;
- Abra seu ***IntelliJ*** e projeto;
- Vá nas configurações do projeto e procure ***"Project Structure";***
- Abra a aba ***"Modules";***
- Dentro dela, abra a opção ***"Dependencies";***
- Adicione à ela, através do **"+"**, o *driver* do *database;*
- Clique em ***"Apply"*** e PRONTO.

## 🗂️Mantendo as "Boas normas":

Na ***"src"***, crie as packges:

- ***dao*** 
- ***modules***
- ***tests***

Não necessariamente com esses nomes, mas que cumpram os mesmos objetivos

## 🔨Crie uma classe Java para iniciar a conexão

vá para o exemplo **ConnectionFactory**

# ☕ Aprendendo a integrar uma ***API*** em ***Java***

## 🤨O que é uma *API*?

É a forma em que as aplicações se comunicam, em português **Interface de
Programação em Aplicação**. Ele retorna para nós, através de uma url, um
arquivo ***.json*** ou ***.xml***

Aqui, usaremos o .json por ser mais simples, sendo tipado, velocidade
abundante e mais leve

***Rest-API:*** Será a forma de como iremos nos comunicar com esse elemento
*front*, usando protocólos HTTP para buscar um elemento no *back*

## 🗂️Mantendo as "Boas normas":

Na ***"src"***, crie as packges:

- ***controller***
- ***dao***
- ***modules***
- ***tests***

A Controller vai só receber as requisições, encaminhá-las ao *back*, 
pegar a resposta e encaminhá-las ao *front*

## ⬇️Como mandamos uma requisição (Front)

- Get
- Post
- Put
- Delete

Você usa uma delas, com adição de um verbo HTTP

Ex: get https//localhost/filtro

## Spring

Vamos para o site [Spring Initializerz](start.spring.io) e crie um novo projeto

# Abra o projeto **AGENDA**