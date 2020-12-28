package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiddhuTransectionProducer {
    private static Logger logger = LoggerFactory.getLogger(SiddhuTransectionProducer.class.getName());

    public static void main(String[] args) {

        logger.info("Creating Kafka Producer...");
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.transection_applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.transection_bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, AppConfigs.transection_transaction_id);

        KafkaProducer<Integer, String> producer = new KafkaProducer<>(props);
        producer.initTransactions();

        logger.info("Starting First Transaction...");
        producer.beginTransaction();
        try {
            for (int i = 1; i <= AppConfigs.transection_numEvents; i++) {
                producer.send(new ProducerRecord<>(AppConfigs.transection_topicName1, i, "Simple Message-T11-" + i));
                producer.send(new ProducerRecord<>(AppConfigs.transection_topicName2, i, "Simple Message-T11-" + i));
            }
            logger.info("Committing First Transaction.");
            producer.commitTransaction();
        }catch (Exception e){
            logger.error("Exception in First Transaction. Aborting...");
            producer.abortTransaction();
            producer.close();
            throw new RuntimeException(e);
        }

        logger.info("Starting Second Transaction...");
        producer.beginTransaction();
        try {
            for (int i = 1; i <= AppConfigs.transection_numEvents; i++) {
                producer.send(new ProducerRecord<>(AppConfigs.transection_topicName1, i, "Simple Message-T22-" + i));
                producer.send(new ProducerRecord<>(AppConfigs.transection_topicName2, i, "Simple Message-T22-" + i));
                throw new Exception();
            }
            logger.info("Aborting Second Transaction.");
            //producer.abortTransaction();
            //producer.commitTransaction();
        }catch (Exception e){
            logger.error("Exception in Second Transaction. Aborting...");
            producer.abortTransaction();
            producer.close();
            throw new RuntimeException(e);
        }

        logger.info("Finished - Closing Kafka Producer.");
        producer.close();

    }
}