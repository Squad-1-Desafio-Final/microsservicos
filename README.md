# Microsserviços - Acabou Mony
Este repositório contém a implementação de microsserviços do projeto Acabou Mony.

🗃️ Banco de Dados
O banco de dados utilizado é o PostgreSQL.
Para iniciar o projeto, siga os passos abaixo:

✅ Link para instalar o PostgreSQL
https://sbp.enterprisedb.com/getfile.jsp?fileid=125956

Crie o banco de dados:
No PostgreSQL, execute o seguinte comando para criar o banco:

CREATE DATABASE acabou_mony;

📦 Kafka
Utilizamos o Apache Kafka como sistema de mensageria entre os microsserviços.

🔗 Instalação
Baixe o Kafka:
👉 https://dlcdn.apache.org/kafka/3.9.1/kafka_2.13-3.9.1.tgz

Descompacte o arquivo:

Extraia o arquivo .tgz para obter a pasta do Kafka.

⚙️ Inicializando o Zookeeper
O Zookeeper é necessário para o funcionamento do Kafka.
No PowerShell (ou terminal equivalente), execute na pasta do Kafka:
```
java -cp ".\libs\*;.\config" org.apache.zookeeper.server.quorum.QuorumPeerMain .\config\zookeeper.properties
```
⚙️ Inicializando o Kafka
Em outro terminal na mesma pasta do Kafka, execute:
```
java -cp ".\libs\*;.\config" kafka.Kafka .\config\server.properties
```

🚀 Iniciando os Microsserviços

Com o banco de dados PostgreSQL e o Kafka rodando, inicie cada um dos microsserviços do projeto.

📌 Observações
Antes de iniciar os microsserviços, verifique se o banco de dados e o Kafka estão devidamente configurados e em execução.

Em caso de problemas, consulte a documentação oficial do PostgreSQL e do Kafka para obter detalhes sobre configuração e troubleshooting.

📞 Contato:
Em caso de dúvidas ou sugestões, fique à vontade para enviar um email para:
turmadamonycasquad@gmail.com


## Link para endpoints Postman

```
https://eduardomiyasaki.postman.co/workspace/Eduardo-Miyasaki's-Workspace~5a8a36eb-49de-44a5-8d99-d1dd8145f590/collection/44816139-0852211a-7508-466c-a92d-0f3a2f0113f1?action=share&source=copy-link&creator=44816139
```

## 🧪 Testando a API com Insomnia/Postman

Siga os passos abaixo para testar o sistema utilizando o **Insomnia/Postman**. 

> ⚠️ **Importante:** Todas as requisições após o login exigem o token de autenticação (Bearer Token) no **Header**.

---

### 👤 Criação de Usuário

**URL:** `POST http://localhost:8084/usuario`  
**Body (JSON):**  

```json
{  
  "nome": "tiago",  
  "login": "tiago.elastic@gmail.com",  
  "password": "senhaSegura123",  
  "cpf": "221.536.578-33",  
  "telefone": "(11) 91234-5655",  
  "dtNasc": "1990-05-20T00:00:00.000+00:00",  
  "role": "admin"  
}  
``` 

### 🔐 Login do Usuário

**URL:** `POST http://localhost:8084/usuario/login`  
**Body (JSON):**
``` json
{
  "login": "tiago.elastic@gmail.com",
  "password": "senhaSegura123"
}
``` 

### 🏦 Criação de Conta

**URL:** `POST http://localhost:8080/api/conta`  
**Body (JSON):**
``` json
{
"dataVencimento": "2025-10-12", 
"limite": 1000.00,
"agencia": 100,
"numero": 123421,
"banco": 1,
"idUsuario": "2db7e9bb-171b-4147-9df8-ebb739267099"
}
``` 

### 📦 Criação de Produto

**URL:** `POST http://localhost:8080/api/produto/criar`  
**Body (JSON):**
``` json
{
"nome": "Fone de Ouvido Bluetooth",
"preco": 199.99,
"descricao": "Fone de ouvido com cancelamento de ruído e conexão Bluetooth 5.0",
"disponivel": 1,
"quantidade": 10
}
``` 

### 🛒 Criação de Pedido

**URL:** `POST http://localhost:8080/api/pedido/criar`  
**Body (JSON):**
``` json
{
  "usuario": "2db7e9bb-171b-4147-9df8-ebb739267099",
	"tipo": "CREDITO",
  "produtos": [
    "0f12645f-1907-43d0-acc8-2283cb50bf0e"
  ]
}
```

### ✅ Confirmação do Pedido

**URL:** `PATCH http://localhost:8080/api/pedido/concluir-transacao`  
**Body (JSON):**
``` json
{
  "idUsuario": "2db7e9bb-171b-4147-9df8-ebb739267099",
  "idPedido": "d5966c0f-8b53-49fa-a63f-cfedad0356d0"
}
``` 

### 💸 Criação Manual da Transação

**URL:** `POST http://localhost:8080/api/transacao`  
**Body (JSON):**
``` json
{
  "tipo": "CREDITO",
  "cartao": "280e40c1-f775-4c0b-8047-8e46a5c6d525",
  "destinatario": "9ffce95b-d383-405e-9432-577534af3825",
  "pedido": "d5966c0f-8b53-49fa-a63f-cfedad0356d0"
}
``` 
OBS: O destinatário não precisa alterar.


### 🛠️ Observações Técnicas
📬 Envio de E-mails: Durante a primeira execução, o sistema pode demorar mais de 1 segundo, pois envolve a inicialização do produtor Kafka e conexões SMTP para envio de e-mails.

🚀 Nas execuções subsequentes, o tempo de resposta será menor, já que os componentes já estarão carregados em memória.

### 🤝 Contribuidores
Este projeto foi desenvolvido com a colaboração de um time dedicado e comprometido. Agradecimentos especiais aos integrantes do squad:

* Eduardo Kendi De Sousa Miyasaki:
	- Microsserviço de conta
 	- teste de carga conta
	- Colaboração no microsserviço de transação
* João Lázaro Neto:
  	- Microsserviço de usuario
  	- teste de carga usuario
  	- Arquitetura dos microsservico
  	- Swagger
* Mônica Jiuliani Leamari:
  	- Parte de produto no microsservico de pedido
  	- teste de carga de transação
  	- Colaboração no microsserviço de transação
* Maikon Douglas Da Silva Gomes:
  	- Parte de pedido no microsservico de pedido
  	- Colaboração no microsserviço de transação
  	- teste de carga pedido

Desenvolvido em conjunto:
- JWT
- API Gateway
- Prints de comprovação
- DER
- Envio de email
- Mensageria Kafka

Cada um contribuiu ativamente para o desenvolvimento, testes, arquitetura e melhorias dessa aplicação. O trabalho em equipe foi essencial para transformar a ideia em um projeto funcional e robusto. 💪🚀
