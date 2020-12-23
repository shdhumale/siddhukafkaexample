package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiddhuKafkaThreadProducer  implements Runnable{  
	
	private static Logger logger = LoggerFactory.getLogger(SiddhuKafkaThreadProducer.class.getName());
    private String fileLocation;
    private String topicName;
    private KafkaProducer<Integer, String> producer;

    SiddhuKafkaThreadProducer(KafkaProducer<Integer, String> producer, String topicName, String fileLocation) {
        this.producer = producer;
        this.topicName = topicName;
        this.fileLocation = fileLocation;
    }

    @Override
    public void run() {
        logger.info("Start Processing " + fileLocation);
        File file = new File(fileLocation);
        int counter = 0;

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                producer.send(new ProducerRecord<>(topicName, null, line));
                counter++;
            }
            logger.info("Finished Sending " + counter + " messages from " + fileLocation);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

	public static void main(String args[]){  
		
        Properties props = new Properties();
        try{
            InputStream inputStream = new FileInputStream(AppConfigs.thread_kafkaConfigFileLocation);
            props.load(inputStream);
            props.put(ProducerConfig.CLIENT_ID_CONFIG,AppConfigs.thread_applicationID);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        KafkaProducer<Integer,String> producer = new KafkaProducer<>(props);
        Thread[] objThread = new Thread[AppConfigs.thread_eventFiles.length];
        logger.info("Starting Dispatcher threads...");
        for(int i=0;i<AppConfigs.thread_eventFiles.length;i++){
        	objThread[i]=new Thread(new SiddhuKafkaThreadProducer(producer,AppConfigs.thread_topicName,AppConfigs.thread_eventFiles[i]));
        	objThread[i].start();
        }

        try {
            for (Thread t : objThread) t.join();
        }catch (InterruptedException e){
            logger.error("Main Thread Interrupted");
        }finally {
            producer.close();
            logger.info("Finished Dispatcher Demo");
        }
    
	}  
}  