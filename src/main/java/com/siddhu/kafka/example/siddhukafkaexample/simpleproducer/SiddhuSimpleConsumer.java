package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.*;
import java.io.*;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;

public class SiddhuSimpleConsumer{

	public static void main(String[] args) throws Exception{

//		String topicName = "SiddhuTopic";
//		String groupName = "SiddhuTopicGroup";

		Properties props = new Properties();
		props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.simple_consumer_applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.simple_consumer_bootstrapServers);
		props.put("group.id", AppConfigs.simple_consumer_groupName);
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");  

		KafkaConsumer<String, String> consumer = null;

		try {
			consumer = new KafkaConsumer<>(props);
			consumer.subscribe(Arrays.asList(AppConfigs.simple_consumer_topicName));

			while (true){
				ConsumerRecords<String, String> records = consumer.poll(100);
				for (ConsumerRecord<String, String> record : records){
					System.out.println("Record Value = " + record.toString());
				}

			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{

			consumer.close();
		}
	}
}