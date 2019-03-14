package com.lab.serversearch.util;

public interface HBaseDoc {
    /**
     * HBASE 表名称
     */
    public  final String TABLE_NAME = "goodslabel";
    /**
     * 列簇1 文章信息
     */
    public  final String COLUMNFAMILY_1 = "goods";
    /**
     * 列簇1中的列
     */
    public  final String COLUMNFAMILY_1_LABEL = "label";
    public  final String COLUMNFAMILY_1_DESCRIBE = "describe";
}
