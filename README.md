Para utilização do Kafka


Download do broker:

kafka_2.12-2.1.0


Iniciar o ZooKeeper:

[Diretório do Kafka]/bin/zookeeper-server-start.sh config/zookeeper.properties


Iniciar o Kafka:

[Diretório do Kafka]/bin/kafka-server-start.sh config/server.properties


Criar os tópicos no Kafka:

[Diretório do Kafka]/bin/sh kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic-kafka-v1
[Diretório do Kafka]/bin/sh kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic topic-kafka-v2


O tópico topic-kafka-v1 será utilizado no teste de mensagem JMS consumida diretamente ao ser recebida.
O tópico topic-kafka-v2 será utilizado no teste de mensagem consumida mais de uma vez conforme é requisitada.


Rodando os projetos:

Executar o run.bat dos projetos mensageria-api e mensageria-mdb


Enviando mensagens:

Para o topic-kafka-v1, efetuar um get para localhost:10004/api/kafka/v1/send, essa mensagem será consumida pelo mensageria-mdb assim que o disponibilizada pelo broker.

Para o topic-kafka-v2, efetuar um get para localhost:10004/api/kafka/v2/send.


Consumindo mensagens:

Para o topic-kafka-v2, efetuar um get para localhost:10004/api/kafka/v2/consume. Esta mensagem pode ser consumida mais de uma vez.




Para utilização do ActiveMQ


Download do broker:

apache-activemq-5.15.8


Iniciar o ActiveMQ:

[Diretório do ActiveMQ]/bin/activemq start


Rodando os projetos:

Executar o run.bat dos projetos mensageria-api e mensageria-mdb


Enviando e consumindo mensagens:

Efetuar um get para localhost:10004/api/activemq/send, essa mensagem será consumida pelo mensageria-mdb assim que o disponibilizada pelo broker.