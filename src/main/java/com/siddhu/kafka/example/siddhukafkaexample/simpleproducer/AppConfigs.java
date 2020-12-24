package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
public class AppConfigs {
    final static String applicationID = "SiddhuSimpleProducer";
    final static String bootstrapServers = "localhost:9092,localhost:9093";
    final static String topicName = "simple-java-producer";
    final static int numEvents = 100;
    
    //for threading example
    final static String thread_applicationID = "Siddhu-Multi-Threaded-Producer";
    final static String thread_topicName = "siddhu-multithread-topic";
    final static String thread_kafkaConfigFileLocation = "config/kafka.properties";
    final static String[] thread_eventFiles = {"data/Input_File.txt","data/Input_File1.txt"};
    
    //for Transection example
    final static String transection_applicationID = "SiddhuTransectionProducer";
    final static String transection_bootstrapServers = "localhost:9092,localhost:9093";
    final static String transection_topicName1 = "SiddhuTransectionProducer-producer-1";
    final static String transection_topicName2 = "SiddhuTransectionProducer-producer-2";
    final static int transection_numEvents = 3;
    final static String transection_transaction_id = "SiddhuTransectionProducer-Trans";
    
    //for Serialize and deserialize example
    final static String ser_der_applicationID = "SerializeDeserilizeProducerConsumer";
    final static String ser_der_bootstrapServers = "localhost:9092,localhost:9093";
    final static String ser_der_topicName = "AddressTopic";

    //Producer for simple consumer
    final static String simple_consumer_applicationID = "SiddhuSimpleProducerforSimpleConsumer";
    final static String simple_consumer_bootstrapServers = "localhost:9092,localhost:9093";
    final static String simple_consumer_topicName = "SiddhuTopic-simple-producer-simple-consumer";
    final static String simple_consumer_groupName = "SiddhuTopic-simple-producer-simple-consumer-groupname";
    final static int simple_consumer_numEvents = 100;
    
  //Producer for simple Rebalance consumer
    final static String simple_rebalance_applicationID = "SiddhuSimpleRebalanceApplication";
    final static String simple_rebalance_bootstrapServers = "localhost:9092,localhost:9093";
    final static String simple_rebalance_topicName = "SiddhuTopic-simple-rebalance";
    final static String simple_rebalance_groupName = "SiddhuTopic-simple-rebalance-groupname";
    final static int simple_rebalance_numEvents = 100;
    
    //Producer for Avro
    final static String avro_applicationID = "SiddhuAvroApplication";
    final static String avro_bootstrapServers = "localhost:9092";
    final static String avro_topicName = "SiddhuAvroTopic";
    final static String avro_groupName = "SiddhuAvroTopic-groupname";
    final static String avro_registry_url = "http://localhost:8081";
    
    final static int avro_numEvents = 100;
    
    
}