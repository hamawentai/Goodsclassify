package com.lab.serverclassify.classify.service;

/**
 * @author weixun
 */
public interface ResultFileService {


    /**
     * 生产结果文件
     *
     * @param username  用户名
     * @param operation 最新的一次操作
     * @return 文件路径
     */
    String  generateFile(String username, String operation);

    /**
     * 获得结果文件的路径
     *
     * @param username  用户名
     * @param operation 最新的一次操作
     * @return 结果文件路径
     */
    String getResultFilePath(String username, String operation);

}
