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
}