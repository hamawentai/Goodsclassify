package com.lab.server_search.service.impl;

import com.lab.server_search.domain.GoodsLabel;
import com.lab.server_search.service.HbaseService;
import com.lab.server_search.util.HBaseDoc;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class HbaseServiceImpl implements HbaseService {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    /**
     * GoodsLabel
     * 通过表名，开始行键和结束行键获取数据
     * @param tableName
     * @param startRow
     * @param stopRow
     * @return
     */
    public List<Map<String,Object>> find(String tableName , String startRow, String stopRow) {
        Scan scan = new Scan();
        if(startRow==null){
            startRow="";
        }
        if(stopRow==null){
            stopRow="";
        }
        scan.withStartRow(Bytes.toBytes(startRow));
        scan.withStopRow(Bytes.toBytes(stopRow));

        return 	hbaseTemplate.find(tableName, scan,new RowMapper<Map<String,Object>>(){
            public Map<String,Object> mapRow(Result result, int rowNum) throws Exception {

                List<Cell> ceList =  result.listCells();
                Map<String,Object> map = new HashMap<String,Object>();
                if(ceList!=null&&ceList.size()>0){
                    GoodsLabel goodsLabel = getGoodsLabel(ceList);
                    map.put(goodsLabel.getRowKey(),goodsLabel);
                }
                return map;
            }
        });
    }

    @Override
    public Boolean insert(List<GoodsLabel> goodsLabels, String tableName) {
        hbaseTemplate.execute(tableName, new TableCallback<String>() {

            @Override
            public String doInTable(HTableInterface hTableInterface) throws Throwable {
                List<Put> puts = new ArrayList<>();
                for (GoodsLabel goodsLabel : goodsLabels) {

                    Put put = new Put(Bytes.toBytes(goodsLabel.getRowKey()));
                    put.addColumn(Bytes.toBytes(HBaseDoc.COLUMNFAMILY_1), Bytes.toBytes(HBaseDoc.COLUMNFAMILY_1_LABEL), Bytes.toBytes(goodsLabel.getLabel()));
                    put.addColumn(Bytes.toBytes(HBaseDoc.COLUMNFAMILY_1), Bytes.toBytes(HBaseDoc.COLUMNFAMILY_1_DESCRIBE), Bytes.toBytes(goodsLabel.getDescribe()));
                    puts.add(put);
                }
                hTableInterface.put(puts);
                return null;
            }
        });
        return null;
    }

    public List<GoodsLabel> findByRowKeyList(List<String> rowKeyList,String tableName) {
        log.info("rowkeyList:{},tableName:{}",rowKeyList,tableName);
        return hbaseTemplate.execute(tableName, new TableCallback<List<GoodsLabel>>() {

            @Override
            public List<GoodsLabel> doInTable(HTableInterface hTableInterface) throws Throwable {
                List<GoodsLabel> goodsLabelList = new ArrayList<>();
                List<Get> getList = new ArrayList<>();
                for (String rowkey : rowKeyList){//把rowkey加到get里，再把get装到list中
                    Get get = new Get(Bytes.toBytes(rowkey));
                    getList.add(get);
                }
                Result[] results = hTableInterface.get(getList);//重点在这，直接查getList<Get>
                for (Result result : results){//对返回的结果集进行操作

                    List<Cell> ceList =  result.listCells();
                    log.info("ceList:{}",ceList);
                    if(ceList!=null&&ceList.size()>0){
                        GoodsLabel goodsLabel = getGoodsLabel(ceList);
                        goodsLabelList.add(goodsLabel);
                    }
                }

                return goodsLabelList;
            }
        });
    }

    private GoodsLabel getGoodsLabel(List<Cell> ceList) {
        GoodsLabel goodsLabel = new GoodsLabel();
        for(Cell cell: ceList){
            String row =Bytes.toString( cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
            String value =Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
            String quali = Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());
            if (quali.equals(HBaseDoc.COLUMNFAMILY_1_DESCRIBE)) {
                goodsLabel.setDescribe(value);
            }
            if (quali.equals(HBaseDoc.COLUMNFAMILY_1_LABEL)) {
                goodsLabel.setLabel(value);
            }
            goodsLabel.setRowKey(row);
        }
        return goodsLabel;
    }

    public List<GoodsLabel> getDocs() throws IOException {
//        List<Doc> arrayList = new ArrayList<Doc>();
//        File file = new File("/home/twl/Desktop/book/doc.txt");
//        List<String> list = FileUtils.readLines(file,"UTF8");
//        for(String line : list){
//            Doc Doc = new Doc();
//            String[] split = line.split("\t");
//            System.out.print(split[0]);
//            int parseInt = Integer.parseInt(split[0].trim());
//            Doc.setId(parseInt);
//            Doc.setTitle(split[1]);
//            Doc.setAuthor(split[2]);
//            Doc.setDescribe(split[3]);
//            arrayList.add(Doc);
//        }
//        return arrayList;
        return null;
    }
}
