package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.*;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SiddhuRebalanceListner implements ConsumerRebalanceListener {
	Logger logger = LoggerFactory.getLogger(SiddhuRebalanceListner.class.getName());
	private KafkaConsumer objConsumer;
	private Map<TopicPartition, OffsetAndMetadata> objCurrentOffsets = new HashMap();

	public SiddhuRebalanceListner(KafkaConsumer con){
		this.objConsumer=con;
	}

	public void addOffset(String topic, int partition, long offset){
		objCurrentOffsets.put(new TopicPartition(topic, partition),new OffsetAndMetadata(offset,"Commit"));
	}

	public Map<TopicPartition, OffsetAndMetadata> getCurrentOffsets(){
		return objCurrentOffsets;
	}

	public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
		logger.info("Inside onPartitionsAssigned");        
		for(TopicPartition partition: partitions)   
		{
			logger.info("iteratig partition onPartitionsAssigned" + partition.partition()+",");
		}

	}

	public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
		logger.info("Inside onPartitionsRevoked"); 
		for(TopicPartition partition: partitions)   
		{
			logger.info("iteratig partition onPartitionsRevoked" + partition.partition()+",");    
		}


		for(TopicPartition tp: objCurrentOffsets.keySet())
		{
			logger.info("iteratig partition commited" + tp.partition());
		}

		objConsumer.commitSync(objCurrentOffsets);
		objCurrentOffsets.clear();
	}
}