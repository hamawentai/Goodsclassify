package com.lab.serverclassify.utils;

import com.lab.serverclassify.pojo.bo.ResultFileBo;
import com.lab.serverclassify.pojo.bo.ResultFileRowBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weixun
 */
@Slf4j
@Service
public class HbaseUtil {

    @Autowired
    private HbaseTemplate hbaseTemplate;

    public ResultFileBo findByRowkeys(List<String> rowkeys, String tableName) {
        long start = System.currentTimeMillis();
        System.setProperty("HADOOP_HOME", "/home/hadoop/apps/hadoop/");
        ResultFileBo resultFile = hbaseTemplate.execute(tableName, hTableInterface -> {
            List<ResultFileRowBO> rowBOS = new ArrayList<>();
            List<Get> getList = new ArrayList<>();
            Long len = 0L;
            ResultFileBo resultFileBo = new ResultFileBo();
            rowkeys.forEach(rowkey -> getList.add(new Get(Bytes.toBytes(rowkey))));
            Result[] results = hTableInterface.get(getList);
            for (Result result : results) {
                List<Cell> ceList = result.listCells();
                if (ceList != null && ceList.size() > 0) {
                    ResultFileRowBO resultFileRowBO = getGoodsLabel(ceList);
                    len += resultFileRowBO.getLen();
                    rowBOS.add(resultFileRowBO);
                }
            }
            resultFileBo.setRows(rowBOS);
            resultFileBo.setLen(len);
            return resultFileBo;
        });
        log.info("hbase find msg by rowkeys spend time: {}", System.currentTimeMillis() - start);
        return resultFile;
    }

    private ResultFileRowBO getGoodsLabel(List<Cell> ceList) {
        ResultFileRowBO resultFileRowBO = new ResultFileRowBO();
        int len = 0;
        for (Cell cell : ceList) {
            byte[] value = CellUtil.cloneValue(cell);
            String quali = Bytes.toString(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength());
            if ("describe".equals(quali)) {
                resultFileRowBO.setDescribe(value);
            }
            if ("label".equals(quali)) {
                resultFileRowBO.setLabel(value);
            }
            len += value.length;
        }
        resultFileRowBO.setLen(len);
        return resultFileRowBO;
    }
}
