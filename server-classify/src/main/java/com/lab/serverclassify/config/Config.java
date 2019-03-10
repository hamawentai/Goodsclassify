package com.lab.serverclassify.config;

import com.lab.serverclassify.kafka.MyKafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.hbase.HbaseTemplate;

/**
 * @author weixun
 */
@Configuration
@Slf4j
public class Config {

    @Bean
    public MyKafkaProducer strStrKafkaProducer() {
        return new MyKafkaProducer<String, String>(StringSerializer.class, StringSerializer.class);
    }

    @Bean
    public HbaseTemplate hbaseTemplate(@Value("${hbase.zookeeper.quorum}") String quorum, @Value("${hbase.zookeeper.port}") String port, @Value("${hbase.rootdir}") String rootDir) {
        log.info("hbase properties"+quorum+" "+port+" "+rootDir);
        HbaseTemplate hbaseTemplate = new HbaseTemplate();
        org.apache.hadoop.conf.Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", quorum);
        conf.set("hbase.rootdir", rootDir);
        conf.set("hbase.zookeeper.port", port);
        hbaseTemplate.setConfiguration(conf);
        hbaseTemplate.setAutoFlush(true);
        return hbaseTemplate;
    }

}
