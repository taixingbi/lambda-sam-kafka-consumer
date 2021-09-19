package helloworld;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class KafkaSubscribe {
    final String bootstrapServers = "b-1.test-cluster-1.qvtxwq.c7.kafka.us-east-2.amazonaws.com:9094";
    final String topic= "mp.inventory.v1";
    final String group= "java-group";
    Properties properties = new Properties();

    public void receive(LambdaLogger logger) {
        System.out.println("\n\n---------------------------------------receive---------------------------------------");
        logger.log("\n\n---------------------------------------receive---------------------------------------");

        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, group);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
        properties.setProperty("security.protocol","SSL");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe( Arrays.asList(topic) );
            ConsumerRecords<String, String> records = consumer.poll( Duration.ofMillis(1000) );
            for(ConsumerRecord record : records){
                logger.log("\n********************* record*****************************");
                logger.log("key: " + record.key() );
                logger.log("value: " + record.value() );

                System.out.println("\n********************* record*****************************");
                System.out.println("key: " + record.key() );
                System.out.println("value: " + record.value() );
                System.out.println("topic:" + record.topic() );
                System.out.println("partition: " + record.partition() );
                System.out.println("offset: " + record.offset() );
            }
        }
}

// https://www.youtube.com/watch?v=NpzwlWYLOdE

//public class KafkaDelivery {
//    final String bootstrapServers = "b-1.test-cluster-1.qvtxwq.c7.kafka.us-east-2.amazonaws.com:9094";
//    final String topic= "mp.inventory.v1";
//    Properties properties = new Properties();
//    public void send() {
//        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
//        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
//        properties.setProperty("security.protocol","SSL");
//        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
//        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic,
//                "key1", "value1" );
//
//        producer.send(record, new Callback() {
//            @Override
//            public void onCompletion(RecordMetadata metadata, Exception e) {
//                if (e != null) {
//                    System.out.println("successfully posted to Kafka");
//                } else {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//
//
//        producer.flush();
//        producer.close();
//    }
//}
