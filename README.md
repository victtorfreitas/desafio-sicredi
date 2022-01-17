# Desafio Sicredi
[![Maven Package](https://github.com/victtorfreitas/desafio-sicredi/actions/workflows/maven-publish.yml/badge.svg)](https://github.com/victtorfreitas/desafio-sicredi/actions/workflows/maven-publish.yml)

Este projeto contém a resolução do desafio da instituição Sicredi.

## O Desafio

O desafio é composto por três etapas, são elas:

- Cenário
- Requisito
- Funcionalidade

### Cenário

Todo dia útil por volta das 6 horas da manhã um colaborador da retaguarda do Sicredi recebe e
organiza as informações de contas para enviar ao Banco Central. Todas agencias e cooperativas enviam
arquivos Excel à Retaguarda. Hoje o Sicredi já possui mais de 4 milhões de contas ativas. Esse
usuário da retaguarda exporta manualmente os dados em um arquivo CSV para ser enviada para a Receita
Federal, antes as 10:00 da manhã na abertura das agências.

### Requisito

Usar o "serviço da receita" (fake) para processamento automático do arquivo.

### Funcionalidade

1. Criar uma aplicação SprintBoot standalone.
2. Processa um arquivo CSV de entrada com o formato abaixo.
3. Envia a atualização para a Receita através do serviço (SIMULADO pela classe ReceitaService).
4. Retorna um arquivo com o resultado do envio da atualização da Receita. Mesmo formato adicionando
   o resultado em uma nova coluna.

## Como Utilizar

Para executar o projeto, abra o terminal e execute o comando:

```
   mvn install
```

Feito isso execute o comando abaixo:

```java
  java-jar target/sicredi-0.0.1-SNAPSHOT.jar contas.csv
```

O arquivo passado no comando acima está dentro da raiz do projeto, pode-se utilizar outro, desde que
siga o corpo padrão abaixo:

```
   agencia;conta;saldo;status  
   0101;12225-6;100,00;A
   0101;12226-8;3200,50;A
   3202;40011-1;-35,12;I
   3202;54001-2;0,00;P
   3202;00321-2;34500,00;B
```

Obs: Caso queira utilizar outro arquivo deve-se alterar o nome do arquivo passado no comando
anterior.

Se a comunicação com o servidor da receita funcionar, deverá obter um retorno contendo o caminho do
arquivo gerado.

## Autor

- [@Victtor Freitas](https://www.linkedin.com/in/victtor-freitas-programador/)

