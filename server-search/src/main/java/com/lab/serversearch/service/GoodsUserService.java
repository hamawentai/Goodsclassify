package com.lab.serversearch.service;

import com.lab.serversearch.domain.GoodsUser;
import com.lab.serversearch.vo.CatalogResultVO;

import java.util.List;

/**
 * 用户分类结果服务
 */
public interface GoodsUserService {

    /**
     * 获取用户所有分类结果(Es中GoodsUser表中的数据)
     * @param userName 用户名
     * @param version 版本
     * @return
     */
    List<GoodsUser> getAllUserGoods(String userName, String version);

    /**
     * 根据分页条件获取分类结果(Es中GoodsUser表中的RowKey)
     * @param userName
     * @param version
     * @param page
     * @param size
     * @return
     */
    List<String> getUserGoodsPage(String userName, String version, Integer page, Integer size);

    /**
     * 分页
     * 获取用户分类结果的完整数据(即存在Hbase中goodslabel的完整数据)
     * @param userName
     * @param page
     * @param size
     * @return
     */
    List<CatalogResultVO> getGoodsLabel(String userName, Integer page, Integer size);
}
