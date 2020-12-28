package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SiddhuJsonRecordConsumer {

	public static void main(String[] args) throws Exception{
		Logger logger = LoggerFactory.getLogger(SiddhuJsonRecordConsumer.class.getName());

		Properties props = new Properties();
		props.put(ConsumerConfig.CLIENT_ID_CONFIG, AppConfigs.json_applicationID);
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.json_bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");        
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, com.siddhu.kafka.example.siddhukafkaexample.simpleproducer.JsonDeserializer.class);
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.GROUP_ID_CONFIG, AppConfigs.json_groupName);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		props.put("specific.class.name", com.siddhu.kafka.example.siddhukafkaexample.simpleproducer.SiddhuUserJsonRecord.class);
		
		
    	KafkaConsumer<String, com.siddhu.kafka.example.siddhukafkaexample.simpleproducer.SiddhuUserJsonRecord> consumer = new KafkaConsumer<String, com.siddhu.kafka.example.siddhukafkaexample.simpleproducer.SiddhuUserJsonRecord>(props);
		consumer.subscribe(Arrays.asList(AppConfigs.json_topicName));
		try {	
			while (true){

				try {
					ConsumerRecords<String, com.siddhu.kafka.example.siddhukafkaexample.simpleproducer.SiddhuUserJsonRecord> records = consumer.poll(100);
					for (ConsumerRecord<String, com.siddhu.kafka.example.siddhukafkaexample.simpleproducer.SiddhuUserJsonRecord> record : records){
						logger.info("client Id="+ record.value().getId()
								+ " User Name=" + record.value().getName()
								+ " Society Name=" + record.value().getSocietyname()							
								+ " Pin Number" + record.value().getPinnumber().toString()
								+ " House Number=" + record.value().getHousenumber().toString());
					}
				} 
				catch(Exception ex){
					ex.printStackTrace();
				}
			}


		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally{
			consumer.close();
		}

	}
}