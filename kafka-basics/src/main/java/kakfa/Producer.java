package com.example.kakfa;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Properties;


public class Producer {

    public static void main(String[] args) {

        Logger LOGGER = LoggerFactory.getLogger(Producer.class);

        // producer properties
        Properties prop = new Properties();
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create Kafka producer
        KafkaProducer<String,String> kafkaProducer = new KafkaProducer<String, String>(prop);
        String topic="kafka_helidon";

        ProducerRecord<String,String> record= new ProducerRecord<>(topic,"Hello how are you?");
        //send data
        kafkaProducer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if(exception==null){
                    LOGGER.info("Topic : {}",metadata.topic());
                }else{
                    LOGGER.error("Error while producing : {}",exception.getMessage());
                }
            }
        });
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
