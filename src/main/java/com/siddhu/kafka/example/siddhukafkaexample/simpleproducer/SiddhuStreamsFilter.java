package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import com.google.gson.JsonParser;

public class SiddhuStreamsFilter {

    public static void main(String[] args) {
        // create properties
        Properties properties = new Properties();
        properties.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, AppConfigs.stream_bootstrapServers);
        properties.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, AppConfigs.stream_applicationID);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());        
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
		

        // create a topology
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        // input topic
        KStream<String, String> inputTopic = streamsBuilder.stream(AppConfigs.stream_topicName);
        KStream<String, String> filteredStream = inputTopic.filter(
                // filter for tweets which has a user of over 10000 followers
                (k, data) ->  extractUserFollowersInTweet(data) > 100
        );
        filteredStream.to(AppConfigs.stream_new_topicName);

        // build the topology
        KafkaStreams kafkaStreams = new KafkaStreams(
                streamsBuilder.build(),
                properties
        );

        // start our streams application
        kafkaStreams.start();
    }

    private static JsonParser jsonParser = new JsonParser();

    @SuppressWarnings("deprecation")
	private static Integer extractUserFollowersInTweet(String data){
        // gson library
        try {
            return jsonParser.parse(data)
                    .getAsJsonObject()
                    .get("pinnumber")
                    .getAsInt();
        }
        catch (NullPointerException e){
            return 0;
        }
    }
}