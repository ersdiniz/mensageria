# Mensageria com ActiveMQ e Kafka

**Kafka**

Download do broker na versão:

`kafka_2.12-2.1.0`


Iniciar o ZooKeeper:

[Diretório do Kafka]/bin/`zookeeper-server-start.sh config/zookeeper.properties`


Iniciar o Kafka:

[Diretório do Kafka]/bin/`kafka-server-start.sh config/server.properties`


Criar os tópicos no Kafka:

[Diretório do Kafka]/bin/`sh kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic-kafka-v1`
[Diretório do Kafka]/bin/`sh kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic-kafka-v2`


O tópico topic-kafka-v1 será utilizado no teste de mensagem JMS consumida diretamente ao ser recebida.
O tópico topic-kafka-v2 será utilizado no teste de mensagem consumida mais de uma vez conforme é requisitada.


Rodando o projeto:

Executar o `run.bat` do projeto mensageria-api


Enviando e consumindo mensagens:

Acessar o endereço `localhost:10004`, em seguida:

Para o topic-kafka-v1, informar uma mensagem no campo Kafka V1 e clicar em Enviar. Esta mensagem será consumida e retornada para a tela via websocket assim que o disponibilizada pelo broker.

Para o topic-kafka-v2, informar uma mensagem no campo Kafka V2 e clicar em Enviar. Esta mensagem será consumida no momento em que o botão Receber for pressionado. Neste cenário, a mesma mensagem pode ser consumida várias vezes.



**ActiveMQ**


Download do broker na versão:

`apache-activemq-5.15.8`


Iniciar o ActiveMQ:

[Diretório do ActiveMQ]/bin/`activemq start`


Rodando o projeto:

Executar o `run.bat` do projeto mensageria-api


Enviando e consumindo mensagens:

Acessar o endereço `localhost:10004`, informar uma mensagem no campo ActiveMQ e clicar em Enviar. Esta mensagem será consumida e retornada para a tela via websocket assim que o disponibilizada pelo broker.