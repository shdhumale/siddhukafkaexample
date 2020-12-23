/**
 * 
 */
package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * @author Siddhartha
 *
 */
public class SimpleProducer {
	Logger logger = LoggerFactory.getLogger(SimpleProducer.class.getName());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new SimpleProducer().run();

	}

	public void run(){
		// TODO Auto-generated method stub
		logger.info("Kafka Producer start...");
		Properties props = new Properties();
		props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.applicationID);
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
		
		KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(props);

		
        
		logger.info("Sending messages...");
		for (int i = 1; i <= AppConfigs.numEvents; i++) {
			producer.send(new ProducerRecord<Integer, String>(AppConfigs.topicName, i, "Simple Message-" + i));
		}

		logger.info("Finished message sending- Closing Kafka Producer.");
		// add a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("stopping application...");
            // flush data
            producer.flush();
            logger.info("closing producer...");
            producer.close();
            logger.info("done!");
        }));
		//producer.close();
	}

}
