package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class SiddhuSimpleProducer {

	Logger logger = LoggerFactory.getLogger(SiddhuSimpleProducer.class.getName());

	public SiddhuSimpleProducer(){}

	public static void main(String[] args) {
		new SiddhuSimpleProducer().run();
	}

	public void run(){

		logger.info("Setup");
		// create a kafka producer
		KafkaProducer<Integer, String> producer = createKafkaProducer();

		// add a shutdown hook
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			logger.info("stopping application...");
			// flush data
			producer.flush();
			producer.close();
			logger.info("done!");
		}));

		// loop to send tweets to kafka
		logger.info("Sending messages...");
		for (int i = 1; i <= AppConfigs.numEvents; i++) {
			producer.send(new ProducerRecord<Integer, String>(AppConfigs.topicName, i, "Siddhu Simple Producer Message-" + i));
		}
		logger.info("End of application");
	}



	public KafkaProducer<Integer, String> createKafkaProducer(){
		//String bootstrapServers = "127.0.0.1:9092";

		// create Producer properties
		Properties properties = new Properties();
		properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,  AppConfigs.bootstrapServers);
		properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
		properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

		// create safe Producer
		properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
		properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
		properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
		properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5"); // kafka 2.0 >= 1.1 so we can keep this as 5. Use 1 otherwise.

		// high throughput producer (at the expense of a bit of latency and CPU usage)
		properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
		properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "20");
		properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32*1024)); // 32 KB batch size

		// create the producer
		KafkaProducer<Integer, String> producer = new KafkaProducer<Integer, String>(properties);
		return producer;
	}
}