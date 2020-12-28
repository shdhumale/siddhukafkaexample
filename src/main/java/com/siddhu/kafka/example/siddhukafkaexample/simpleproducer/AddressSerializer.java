package com.siddhu.kafka.example.siddhukafkaexample.simpleproducer;
import java.nio.ByteBuffer;
import java.util.Map;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class AddressSerializer implements Serializer<Address> {
	private String encoding = "UTF8";

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// nothing to configure
	}

	@Override
	public byte[] serialize(String topic, Address data) {

		int sizeOfSocietyName;
		int sizeOfStartDate;
		byte[] serializedSocietyName;
		byte[] serializedStartDate;

		try {
			if (data == null)
				return null;
			serializedSocietyName = data.getSocietyName().getBytes(encoding);
			sizeOfSocietyName = serializedSocietyName.length;
			serializedStartDate = data.getStartDate().toString().getBytes(encoding);
			sizeOfStartDate = serializedStartDate.length;

			ByteBuffer buf = ByteBuffer.allocate(sizeOfSocietyName+sizeOfStartDate+50);
			buf.putInt(data.getHouseId());
			buf.putInt(sizeOfSocietyName);
			buf.put(serializedSocietyName);
			buf.putInt(sizeOfStartDate);
			buf.put(serializedStartDate);


			return buf.array();

		} catch (Exception e) {
			throw new SerializationException("Error when serializing Address to byte[]");
		}
	}

	@Override
	public void close() {
		// nothing to do
	}
}