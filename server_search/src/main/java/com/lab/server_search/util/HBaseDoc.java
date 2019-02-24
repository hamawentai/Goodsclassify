package com.lab.server_search.util;

public interface HBaseDoc {
    /**
     * HBASE 表名称
     */
    public  final String TABLE_NAME = "doc";
    /**
     * 列簇1 文章信息
     */
    public  final String COLUMNFAMILY_1 = "cf";
    /**
     * 列簇1中的列
     */
    public  final String COLUMNFAMILY_1_TITLE = "title";
    public  final String COLUMNFAMILY_1_AUTHOR = "author";
    public  final String COLUMNFAMILY_1_CONTENT = "content";
    public  final String COLUMNFAMILY_1_DESCRIBE = "describe";
}
