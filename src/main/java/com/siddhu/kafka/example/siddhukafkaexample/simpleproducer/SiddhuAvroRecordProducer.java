package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class SiddhuAvroRecordProducer {

    public static void main(String[] args) throws Exception{
    	Logger logger = LoggerFactory.getLogger(SiddhuAvroRecordProducer.class.getName());

        String msg;

        
        Properties props = new Properties();
        props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.avro_applicationID);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.avro_bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");        
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "io.confluent.kafka.serializers.KafkaAvroSerializer");
        props.put("schema.registry.url", AppConfigs.avro_registry_url);
        

        Producer<String, SiddhuUserRecord> producer = new KafkaProducer <>(props);
        SiddhuUserRecord cr = new SiddhuUserRecord();
        try{
        	cr.setId("1".toString());
            cr.setLanguage("English");
            cr.setUseraddress("Address".toString());
            cr.setUsername("SiddhuName".toString());
            producer.send(new ProducerRecord<String, SiddhuUserRecord>(AppConfigs.avro_topicName,cr.getId().toString(),cr)).get();
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