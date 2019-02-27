//package com.lab.server_search.service;
//
//import com.lab.server_search.document.Doc;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
///**
// * hbase操作类接口
// */
//public interface HbaseService {
//
//    /**
//     * 根据起始列 查询表中内容
//     * @param tableName
//     * @param startRow
//     * @param stopRow
//     * @return
//     */
//    List<Map<String,Object>> find(String tableName , String startRow, String stopRow);
//
//    Boolean insert(List<Doc> docList,String tableName);
//
//    List<Doc> getDocs() throws IOException;
//}
