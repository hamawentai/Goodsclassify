package com.lab.serverclassify.kafka.service;


/**
 * @author weixun
 */
public interface KafkaSenderService<T> {


    /**
     * 发送消息至kafka某个topic
     *
     * @param topic topic
     * @param msg   消息
     */
    void sendMsg(String topic, T msg);
}
