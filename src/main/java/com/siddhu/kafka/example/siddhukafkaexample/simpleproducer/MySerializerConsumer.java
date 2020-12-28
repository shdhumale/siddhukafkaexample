package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class MySerializerConsumer{

        public static void main(String[] args) throws Exception{

                String topicName = "AddressTopic";
                String groupName = "AddressTopicGroup";

                Properties props = new Properties();
                props.put("group.id", groupName);
                props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.ser_der_applicationID);
                props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.ser_der_bootstrapServers);
                props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
                props.put("value.deserializer", "com.siddhu.kafka.example.siddhukafkaexample.simpleproducer.AddressDeserializer");
                
                KafkaConsumer<String, Address> consumer = new KafkaConsumer<>(props);
                consumer.subscribe(Arrays.asList(topicName));

                while (true){
                        ConsumerRecords<String, Address> records = consumer.poll(100);
                        for (ConsumerRecord<String, Address> record : records){
                                System.out.println("House number= " + String.valueOf(record.value().getHouseId()) + " Society  Name = " + record.value().getSocietyName() + " Start Date = " + record.value().getStartDate().toString());
                        }
                }

        }
}