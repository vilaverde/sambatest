## Aplicação

Esta é uma aplicação de estudo/teste desenvolvida em Java utilizando o framework Spring e gerenciador de pacotes Gradle.

O objetivo desta aplicação é disponibilizar um endpoint para envio de arquivo de Vídeo não suportado na web, armazenar no Amazon S3, converter para formato suportado utilizando o Zencoder e armazenar o resultado no Amazon S3.

O editor utilizado para o desenvolvimento deste projeto foi o VIM, sem ferramentas de autocomplete, apenas um Linter para validação sintática/semântica.

## TODO
* Front-end
* Testes unitários

## Deploy
* A aplicação está deployada no Heroku e acessível através do endpoint:

      https://sambatest-rest-api.herokuapp.com/decode

## Execução
* Os testes manuais foram feitos utilizando o Postman e arquivos Java apenas para upload do arquivo e validação das respostas.
