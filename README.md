Microsserviços - Acabou Mony
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
java -cp ".\libs\*;.\config" org.apache.zookeeper.server.quorum.QuorumPeerMain .\config\zookeeper.properties

⚙️ Inicializando o Kafka
Em outro terminal na mesma pasta do Kafka, execute:

java -cp ".\libs\*;.\config" kafka.Kafka .\config\server.properties

🚀 Iniciando os Microsserviços

Com o banco de dados PostgreSQL e o Kafka rodando, inicie cada um dos microsserviços do projeto.

📌 Observações
Antes de iniciar os microsserviços, verifique se o banco de dados e o Kafka estão devidamente configurados e em execução.

Em caso de problemas, consulte a documentação oficial do PostgreSQL e do Kafka para obter detalhes sobre configuração e troubleshooting.

📞 Contato:
Em caso de dúvidas ou sugestões, fique à vontade para abrir uma issue ou enviar um pull request