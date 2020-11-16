# Cambio API
Cambio Api é uma aplicação que faz conversões entre moedas 

- [Cambio API](#cambio-api)
    - [Requisitos](#requisitos)
    - [Documentação](#documentao)
    - [Instalação](#instalao)
    - [Deploy](#deploy)

### Aquitetura
![alt text](https://github.com/eduardosanson/cambio/blob/feature/conversao-moeda/doc/image/arquitetura.png?raw=true)


### Requisitos
É necessário ter instalado a openjdk 11 e docker instalados na máquina.

### Documentação
Algumas informação sobre como a aplicação está documentada 

    .
    ├── ...
    ├── README.md     # Informações básicas da aplicação.
    ├── CHANGELOG.md  # Aqui ficam informações de feature criadas e de futuras features que viram para as próximas entregas.
    ├── HELP.md       # Ficam algumas referências bibliograficas para futuras consultas e auxilio de entendimento do projeto                
    └── ...

### Instalação
Use o mavem para realizar o build, testes e geração do pacote.

```shell script
mvn clean install
```
Use o o docker para gerar a nova imagem com o novo pacote.
```shell script
make build-image && make push-image
```
Obs: Você precisa alterar o arquivo Makefile com o nome do repositório docker i com o nome da imagem

### Deploy
O sistema roda em um container ecs existente é necessário que o ambiente tenha acesso a um elasticache redis para que a aplicação rode e que exista um ecs.
Para maiores informações veja o repositorio com [terraform-projects-infra](https://github.com/eduardosanson/terraform-projects-infra).
Para subir a aplicação basta ir em deploy/terraform e executar o seguint comando:

```shell script
make deploy-prod
```
    .
    ├── ...
    ├── deploy                        # Onde fica o código para deploy da aplicação
    │   ├── Terraform                 # Onde fica o código da infra
    │        ├── modules              # Ficam os modolos terraform da aplicação
    |        ├── file                 # Fica a task definition para o ecs subir a aplicação, mas pode ser utilizado para arquivos em geral
    │        ├── backend.tf           # Configuração de inicialização do terraform
    │        ├── backend-prod.tfvars  # Variáveis de inicialização, como profile, região, bucket s3, chave para o tfstate 
    │        ├── variable-prod.json   # Variáveis com informações para iniciar o deploy da aplicações
    |        └── ...                  # etc.
    ├── Dockerfile                    # Imagem com o docker da aplicação
    ├── Makefile                      # Alguns comandos ficam aqui, como a maneira de se criar a imagem docker
    ├── traget
    |   ├── cambio-<versão>.jar       # Aqui fica o artefato para a criação da imagem docker                 
    └── ...