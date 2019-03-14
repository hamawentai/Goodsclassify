package com.lab.serversearch.service.impl;

import com.lab.serversearch.dao.GoodsUserDao;
import com.lab.serversearch.domain.GoodsLabel;
import com.lab.serversearch.domain.GoodsUser;
import com.lab.serversearch.domain.UserVersion;
import com.lab.serversearch.service.GoodsUserService;
import com.lab.serversearch.service.HbaseService;
import com.lab.serversearch.service.UserVersionService;
import com.lab.serversearch.util.HBaseDoc;
import com.lab.serversearch.vo.CatalogResultVO;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
public class GoodsUserServiceImpl implements GoodsUserService {

    @Autowired
    private GoodsUserDao goodsUserDao;

    @Autowired
    private TransportClient transportClient;

    @Autowired
    private HbaseService hbaseService;

    @Autowired
    private UserVersionService userVersionService;

    private static final AtomicLong currentPos = new AtomicLong();

    @Override
    public List<GoodsUser> getAllUserGoods(String userName, String version) {
        List<GoodsUser> goodsUserList = new ArrayList<>();

        //多条件设置
        MatchPhraseQueryBuilder userQuery = QueryBuilders
                .matchPhraseQuery("user",userName);
        MatchPhraseQueryBuilder versionQuery = QueryBuilders
                .matchPhraseQuery("version",version);
        QueryBuilder userVersionQuery = QueryBuilders.boolQuery()
                .must(userQuery)
                .must(versionQuery);

        //指定一个index和type
        SearchRequestBuilder search = transportClient.prepareSearch("goods").setTypes("goods-user");
        //设置每批读取的数据量
        search.setSize(100);
        //默认是查询所有
        search.setQuery(userVersionQuery);
        //设置 search context 维护1分钟的有效期
        search.setScroll(TimeValue.timeValueMinutes(1));

        //获得首次的查询结果
        SearchResponse scrollResp=search.get();
        //打印命中数量
//        System.out.println("命中总数量："+scrollResp.getHits().getTotalHits());
        //打印计数
        int count=1;
        do {
            //读取结果集数据
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                GoodsUser goodsUser = new GoodsUser();
                String id = hit.getId();
                String user = (String) hit.getSource().get("user");
                String currtVersion = (String) hit.getSource().get("version");
                goodsUser.setId(id);
                goodsUser.setUser(user);
                goodsUser.setVersion(currtVersion);
                goodsUserList.add(goodsUser);
            }
            count++;
            //将scorllId循环传递
            scrollResp = transportClient.prepareSearchScroll(scrollResp.getScrollId()).setScroll(TimeValue.timeValueMinutes(1)).execute().actionGet();

            //当searchHits的数组为空的时候结束循环，至此数据全部读取完毕
        } while(scrollResp.getHits().getHits().length != 0);

        log.info("Es总条数:{}",goodsUserList.size());
        return goodsUserList;
    }

    @Override
    public List<String> getUserGoodsPage(String userName, String version, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page-1,size);
        Page<GoodsUser> userGoodsPage = goodsUserDao.findByUserAndVersion(userName, version,pageRequest);
        List<String> rowKeyList = new ArrayList<>();

        for (GoodsUser goodsUser : userGoodsPage) {
            rowKeyList.add(goodsUser.getId());
        }
        return rowKeyList;
    }

    @Override
    public List<CatalogResultVO> getGoodsLabel(String userName, Integer page, Integer size) {

        //获取用户最新上传历史版本
        UserVersion userVersion = userVersionService.getUserVersion(userName);

        //根据分页条件,从ElasticSearch中查询对应的Rowkey
        List<String> rowKeyList = getUserGoodsPage(userName,userVersion.getId().toString(),page,size);
//        log.info("userName:{},version:{},page:{},size:{}",userName,userVersion.toString(),page,size);
        log.info("rowKeyList:{}",rowKeyList);

        //根据Rowkey从Hbase中查询出数据
        List<GoodsLabel> goodsLabelList = hbaseService.findByRowKeyList(rowKeyList, HBaseDoc.TABLE_NAME);

        List<CatalogResultVO> catalogResultVOList = new ArrayList<>();

        for (GoodsLabel goodsLabel: goodsLabelList) {

            currentPos.getAndIncrement();

            CatalogResultVO catalogResultVO = new CatalogResultVO();
            String label = goodsLabel.getLabel();

            catalogResultVO.setLabelA(label.split("--")[0]);
            catalogResultVO.setLabelB(label.split("--")[1]);
            catalogResultVO.setLabelC(label.split("--")[2]);
            catalogResultVO.setPos(currentPos.longValue());
            catalogResultVO.setName(goodsLabel.getDescribe());
            catalogResultVOList.add(catalogResultVO);
        }

        return catalogResultVOList;
    }
}
