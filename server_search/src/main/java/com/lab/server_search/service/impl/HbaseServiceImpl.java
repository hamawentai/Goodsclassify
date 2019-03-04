package com.lab.server_search.service.impl;

import com.lab.server_search.document.Doc;
import com.lab.server_search.service.DocSearchService;
import com.lab.server_search.service.HbaseService;
import com.lab.server_search.util.HBaseDoc;
import org.apache.commons.io.FileUtils;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HbaseServiceImpl implements HbaseService {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    @Autowired
    private DocSearchService docSearchService;

    /**
     * 通过表名，开始行键和结束行键获取数据
     * @param tableName
     * @param startRow
     * @param stopRow
     * @return
     */
    @Override
    public List<Map<String,Object>> find(String tableName , String startRow, String stopRow) {
        Scan scan = new Scan();
        if(startRow==null){
            startRow="";
        }
        if(stopRow==null){
            stopRow="";
        }
        scan.setStartRow(Bytes.toBytes(startRow));
        scan.setStopRow(Bytes.toBytes(stopRow));

        return 	hbaseTemplate.find(tableName, scan,new RowMapper<Map<String,Object>>(){
            @Override
            public Map<String,Object> mapRow(Result result, int rowNum) throws Exception {

                List<Cell> ceList =   result.listCells();
                Map<String,Object> map = new HashMap<String,Object>();
                Map<String,Map<String,Object>> returnMap = new HashMap<String,Map<String,Object>>();
                String  row = "";
                if(ceList!=null&&ceList.size()>0){
                    for(Cell cell:ceList){
                        row =Bytes.toString( cell.getRowArray(), cell.getRowOffset(), cell.getRowLength());
                        String value =Bytes.toString( cell.getValueArray(), cell.getValueOffset(), cell.getValueLength());
                        String family =  Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(),cell.getFamilyLength());
                        String quali = Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(),cell.getQualifierLength());
                        map.put(family+"_"+quali, value);
                    }
                    map.put("row",row );
                }
                return  map;
            }
        });
    }

    @Override
    public Boolean insert(List<Doc> docList, String tableName) {
        hbaseTemplate.execute(tableName, new TableCallback<String>() {

            @Override
            public String doInTable(HTableInterface hTableInterface) throws Throwable {
                List<Put> puts = new ArrayList<>();
                for (Doc doc : docList) {

                    Put put = new Put(Bytes.toBytes(doc.getId()));
                    put.addColumn(Bytes.toBytes(HBaseDoc.COLUMNFAMILY_1), Bytes.toBytes(HBaseDoc.COLUMNFAMILY_1_AUTHOR), Bytes.toBytes(doc.getAuthor()));
                    put.addColumn(Bytes.toBytes(HBaseDoc.COLUMNFAMILY_1), Bytes.toBytes(HBaseDoc.COLUMNFAMILY_1_DESCRIBE), Bytes.toBytes(doc.getDescribe()));
                    put.addColumn(Bytes.toBytes(HBaseDoc.COLUMNFAMILY_1), Bytes.toBytes(HBaseDoc.COLUMNFAMILY_1_TITLE), Bytes.toBytes(doc.getTitle()));
                    puts.add(put);
                    docSearchService.addDoc(doc);
                }
                hTableInterface.put(puts);
                return null;
            }
        });
        return null;
    }

    @Override
    public List<Doc> getDocs() throws IOException {
        List<Doc> arrayList = new ArrayList<Doc>();
        File file = new File("/home/twl/Desktop/book/doc.txt");
        List<String> list = FileUtils.readLines(file,"UTF8");
        for(String line : list){
            Doc Doc = new Doc();
            String[] split = line.split("\t");
            System.out.print(split[0]);
            int parseInt = Integer.parseInt(split[0].trim());
            Doc.setId(parseInt);
            Doc.setTitle(split[1]);
            Doc.setAuthor(split[2]);
            Doc.setDescribe(split[3]);
            arrayList.add(Doc);
        }
        return arrayList;
    }
}
