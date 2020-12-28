package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiddhuConsumerRebalance{
    
    
    public static void main(String[] args) throws Exception{
    	Logger logger = LoggerFactory.getLogger(SiddhuConsumerRebalance.class.getName());

            
            KafkaConsumer<String, String> ObjConsumer = null;
            
            String groupName = "Siddhu-Consumer-Rebalance";
            Properties props = new Properties();
            props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.simple_rebalance_applicationID);
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.simple_rebalance_bootstrapServers);
            props.put("group.id", AppConfigs.simple_rebalance_groupName);
            props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
            //to do the offset commit manually
            props.put("enable.auto.commit", "false");

            ObjConsumer = new KafkaConsumer<>(props);
            //creating instance of our listener that will help us to call the inbuild method that will be executing before and immediately rebalance happens. Providing consumer instance as an input.
            SiddhuRebalanceListner objRebalanceListner = new SiddhuRebalanceListner(ObjConsumer);
         
            ObjConsumer.subscribe(Arrays.asList(AppConfigs.simple_rebalance_topicName),objRebalanceListner);
            try{
                while (true){
                    ConsumerRecords<String, String> records = ObjConsumer.poll(100);
                    for (ConsumerRecord<String, String> record : records){
                        //System.out.println("record:-"+ record.toString());	
                    	//Perform business action here
                          objRebalanceListner.addOffset(record.topic(), record.partition(),record.offset());
                    }
                       
                }
            }catch(Exception ex){
            	logger.info(ex.toString());
                ex.printStackTrace();
            }
            finally{
            	logger.info("Closing the consumer");
                    ObjConsumer.close();
            }
    }
    
}