package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.Date;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
public class MySerializerProducer {

   public static void main(String[] args) throws Exception{

      String topicName = "AddressTopic";

      Properties props = new Properties();
      props.put(ProducerConfig.CLIENT_ID_CONFIG, AppConfigs.ser_der_applicationID);
      props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.ser_der_bootstrapServers);
      props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
      props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "com.siddhu.kafka.example.siddhukafkaexample.simpleproducer.AddressSerializer");
     
      Producer<String, Address> producer = new KafkaProducer <>(props);


          Address sp1 = new Address(101,"Society 1.",new Date());
          Address sp2 = new Address(102,"Society 2.",new Date());

         producer.send(new ProducerRecord<String,Address>(topicName,"MySup",sp1)).get();
         producer.send(new ProducerRecord<String,Address>(topicName,"MySup",sp2)).get();
          
         producer.close();

   }
}