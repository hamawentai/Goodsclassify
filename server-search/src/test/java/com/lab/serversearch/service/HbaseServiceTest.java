package com.lab.serversearch.service;

import com.lab.serversearch.domain.GoodsLabel;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
public class HbaseServiceTest {

    @Autowired
    private HbaseService hbaseService;

    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Test
    public void getByRowKeyList() {
        List<String> rowkeyList = new ArrayList<>();
//        000107034201902282131
        rowkeyList.add("000107034201902282131");
        rowkeyList.add("000193440201902282131");
        List<GoodsLabel> goodslabel = hbaseService.findByRowKeyList(rowkeyList, "goodslabel");

        if (goodslabel!=null) {
            System.out.println(goodslabel.size());
        }
        goodslabel.forEach(
                res -> {
                    System.out.println(res);
                }
        );
    }

    @Test
    public void find() {

        List<Map<String, Object>> fileTable = hbaseService.find("goodslabel", "000107034201902282131", "000193440201902282131");
        System.out.println(fileTable.size());
        fileTable.forEach(
                map->{
                    map.forEach(
                            (k,v)->{
                                GoodsLabel goodsLabel = (GoodsLabel) v;
                                System.out.println(k+" "+goodsLabel.getDescribe()+" "+goodsLabel.getLabel());
                            }
                    );
                }
        );
    }

    @Test
    public void foreach() {
        List<Result> goodslabel = hbaseTemplate.execute("goodslabel", new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                // 过滤器的添加
                Scan scan = new Scan();
                scan.setStartRow(Bytes.toBytes("1"));
                scan.setStopRow(Bytes.toBytes("10"));
                ResultScanner rscanner = table.getScanner(scan);
                for (Result result : rscanner) {
                    list.add(result);
                }
                return list;
            }

        });

    }
}