# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [Unreleased] - yyyy-mm-dd
Escrever atualizações importantes feitas de maneira reduzida.

### Added
- Scale up/down para o processamento de fila
- Nova subnet privada com saida apenas pelo nat gateway
- LB na subnet pública
- Criação de um Route 53 
- Novo Roteador de borda http/https para a aplicação. 

### Fixed
- Tratamento de erro para o cliente do banco central.

## [0.0.1] - 2020-11-16
  
Aqui lançamos a primeira versão 0.0.1 com a primeira versão do código e infra-estrutura
 
### Added
- Acesso a api do banco central que retorna a cotação da moeda em relação ao real
- Ambiente com task definitions, target group, scalling up/down, alarm dos recursos do ecs e sqs.