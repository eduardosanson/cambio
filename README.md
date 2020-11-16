# Aquitetura
![alt text](https://github.com/eduardosanson/cambio/blob/feature/conversao-moeda/doc/image/arquitetura.png?raw=true)

#Cambio API
Cambio Api é uma aplicação que faz conversões entre moedas 

#Reuqisitos
É necessário ter instalado a openjdk 11 e docker instalados na máquina.

#Instalação
Use o mavem para realizar o build, testes e geração do pacote.

```shell script
mvn clean install
```
Use o o docker para gerar a nova imagem com o novo pacote.
```shell script
make build-image && make push-image
```
Obs: Você precisa alterar o arquivo Makefile com o nome do repositório docker i com o nome da imagem

#Deploy
O sistema roda em um container ecs existente é necessário que o ambiente tenha acesso a um elasticache redis para que a aplicação rode e que exista um ecs.
Para maiores informações veja o repositorio com [terraform-projects-infra](https://github.com/eduardosanson/terraform-projects-infra)