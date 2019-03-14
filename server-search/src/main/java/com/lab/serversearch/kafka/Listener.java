package com.lab.serversearch.kafka;

//public class Listener {
//
//    @KafkaListener(topics = {"test-topic"})
//    public void listen(ConsumerRecord<?, ?> record) {
//        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
//        if (kafkaMessage.isPresent()) {
//            Object message = kafkaMessage.get();
//            System.out.println("listen1 " + message);
//        }
//    }
//}
