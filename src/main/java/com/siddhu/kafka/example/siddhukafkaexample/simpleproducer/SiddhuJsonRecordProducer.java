package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SiddhuJsonRecordProducer {

	public static void main(String[] args) throws Exception{
		Logger logger = LoggerFactory.getLogger(SiddhuJsonRecordProducer.class.getName());

		String msg;


		Properties props = new Properties();
		props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.json_applicationID);
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.json_bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");        
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, com.siddhu.kafka.example.siddhukafkaexample.simpleproducer.JsonSerializer.class);
		


		Producer<String, SiddhuUserJsonRecord> producer = new KafkaProducer <>(props);
		SiddhuUserJsonRecord cr = new SiddhuUserJsonRecord();
		try{
			cr.setId("2".toString());
			cr.setName("SiddhuName2");
			cr.setHousenumber(601.0);
			cr.setSocietyname("SiddhuSociety2");
			cr.setPinnumber(90);
			producer.send(new ProducerRecord<String, SiddhuUserJsonRecord>(AppConfigs.json_topicName,cr.getId().toString(),cr)).get();
			logger.info("Message send successfully");

		}

		catch(Exception ex){
			logger.error("error occured");
			ex.printStackTrace();
		}
		finally{
			producer.close();
		}

	}
}