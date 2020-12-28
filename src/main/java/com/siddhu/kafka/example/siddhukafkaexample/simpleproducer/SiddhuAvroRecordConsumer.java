package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.SerializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SiddhuAvroRecordConsumer{    

	public static void main(String[] args) throws Exception{


		Logger logger = LoggerFactory.getLogger(SiddhuAvroRecordConsumer.class.getName());

		Properties props = new Properties();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.avro_bootstrapServers);
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfigs.avro_applicationID);        
		props.put(ConsumerConfig.GROUP_ID_CONFIG, AppConfigs.avro_groupName);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroDeserializer");
		props.put("schema.registry.url", AppConfigs.avro_registry_url);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put("specific.avro.reader", "true");

		//		props.put("bootstrap.servers", "localhost:9092,localhost:9093");
		//        props.put("group.id", "SiddhuAvroTopic-groupname");
		//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		//        props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
		//        props.put("schema.registry.url", "http://localhost:8081");
		//        props.put("specific.avro.reader", "true");
		KafkaConsumer<String, SiddhuUserRecord> consumer = new KafkaConsumer<String, SiddhuUserRecord>(props);
		consumer.subscribe(Arrays.asList(AppConfigs.avro_topicName));
		try {	
			while (true){

				try {
					ConsumerRecords<String, SiddhuUserRecord> records = consumer.poll(100);
					for (ConsumerRecord<String, SiddhuUserRecord> record : records){
						logger.info("client Id="+ record.value().getId()
								+ " Langaugae=" + record.value().getLanguage()
								+ " User Name=" + record.value().getUsername()
								+ " User Address=" + record.value().getUseraddress());
					}
				} /*catch (SerializationException e) {
					String s = e.getMessage().split("Error deserializing key/value for partition ")[1].split(". If needed, please seek past the record to continue consumption.")[0];
					String topics = s.split("-")[0];
					int offset = Integer.valueOf(s.split("offset ")[1]);
					int partition = Integer.valueOf(s.split("-")[1].split(" at")[0]);

					TopicPartition topicPartition = new TopicPartition(topics, partition);
					logger.info("Skipping " + AppConfigs.avro_topicName + "-" + partition + " offset " + offset);
					consumer.seek(topicPartition, offset + 1);
				}*/
				catch(Exception ex){
					ex.printStackTrace();
				}
//				for (ConsumerRecord<String, SiddhuUserRecord> record : records){
//					logger.info("client Id="+ record.value().getId()
//							+ " Langaugae=" + record.value().getLanguage()
//							+ " User Name=" + record.value().getUsername()
//							+ " User Address=" + record.value().getUseraddress());
//				}
			}
			


		//}

	}catch(Exception ex){
		ex.printStackTrace();
	}
	finally{
		consumer.close();
	}
}
}
