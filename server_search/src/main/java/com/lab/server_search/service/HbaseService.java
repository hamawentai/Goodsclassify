package com.lab.server_search.service;

import com.lab.server_search.domain.GoodsLabel;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * hbase的goodslabel表操作类接口
 */
public interface HbaseService {

    /**
     * 根据起始列 查询表中内容
     * @param tableName
     * @param startRow
     * @param stopRow
     * @return
     */
    List<Map<String,Object>> find(String tableName , String startRow, String stopRow);

    Boolean insert(List<GoodsLabel> docList, String tableName);

    List<GoodsLabel> getDocs() throws IOException;

    /**
     * 根据rowkey查找数据
     * @param rowKeyList
     * @param tableName
     * @return
     */
    List<GoodsLabel> findByRowKeyList(List<String> rowKeyList,String tableName);
}
