# Adote.Me

<a href="https://ibb.co/rvbXbmZ"><img src="https://i.ibb.co/9GwdwNq/01.png" alt="01" border="0"></a>
<a href="https://ibb.co/KFpXHXK"><img src="https://i.ibb.co/6g7m5mX/02.png" alt="02" border="0"></a>
<a href="https://ibb.co/dMd14xK"><img src="https://i.ibb.co/h9PhFw8/03.png" alt="03" border="0"></a>
<a href="https://ibb.co/XsfyHdR"><img src="https://i.ibb.co/Yk4DgKs/04.png" alt="04" border="0"></a>
<a href="https://ibb.co/QY0YnNP"><img src="https://i.ibb.co/r595M6x/5.png" alt="5" border="0"></a>

## Adote.Me: Facilitando a adoção de animais através de uma API em Java e Spring Boot

O Adote.Me é um projeto inovador que nasceu da necessidade de encontrar uma solução para o problema persistente da adoção de animais, especialmente gatos e cachorros, em várias cidades onde a falta de uma política pública eficiente dificulta a resolução desse desafio. Essa iniciativa visa criar uma rede que facilite o processo de adoção, conectando de forma eficaz pessoas que desejam adotar um animal de estimação com aqueles que procuram um novo lar para seus animais.

## Descrição do Projeto
A API Adote.Me foi desenvolvida em Java e Spring Boot, aproveitando a robustez dessas tecnologias para criar uma plataforma poderosa e fácil de usar. Através dessa API, proprietários de animais que desejam doá-los e indivíduos interessados em adotar podem se cadastrar e interagir de maneira simples e intuitiva.

## Stack utilizada

**Java (JDK 17), SpringBoot ˆ3.0.0**

**Database** PostgreSQL

## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/jpcchaves/adote.me.git
```

Entre no diretório do projeto

```bash
  cd adotar.me
```
Instalando as dependencias

```bash
  mvn dependency:resolve
```
Rodando o projeto

```bash
  mvn spring-boot:run
```

## Aprendizados

Aprendi a utilizar o framework Spring Boot para desenvolver uma API Rest com os principais endpoints: GET, PUT, PATCH, POST, DELETE.

Também foi de grande valia para aprender mais sobre tratamento de exceções em Java e validação dos dados enviados pelo usuário por meio da validação disponibilizada pelo Spring Boot (Bean Validation with Hibernate validator).

Além disso, aprendi a desenvolver uma feature de resetar a senha do usuário utilizando a lib Java Mailsender para enviar o código de recuperação de senha para o e-mail do usuário que realizou a solicitação.
