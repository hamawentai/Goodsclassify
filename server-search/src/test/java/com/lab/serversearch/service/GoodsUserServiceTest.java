package com.lab.serversearch.service;

import com.lab.serversearch.dao.GoodsUserDao;
import com.lab.serversearch.domain.GoodsUser;
import com.lab.serversearch.vo.CatalogResultVO;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsUserServiceTest {

    @Autowired
    private GoodsUserService goodsUserService;

    @Autowired
    private GoodsUserDao goodsUserDao;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private TransportClient transportClient;

    @Test
    public void search(){

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(boolQuery()
                        .filter(termQuery("version", "1"))
                        .filter(termQuery("user", "bob"))
                ).build();

        List<GoodsUser> goodsUserList = elasticsearchTemplate.queryForList(searchQuery, GoodsUser.class);
        System.out.println(goodsUserList.size());
        goodsUserList.forEach(
                res -> {
                    System.out.println(res);
                }
        );
    }

    @Test
    public void getAll() {
        List<GoodsUser> allUserGoods = goodsUserService.getAllUserGoods("bob", "1");
        allUserGoods.forEach(
                res -> {
                    System.out.println(res);
                }
        );
    }

    @Test
    public void getPage() {
        List<String> userGoodsPage = goodsUserService.getUserGoodsPage("bob", "1", 1, 10);
        userGoodsPage.forEach(
                res -> {
                    System.out.println(res);
                }
        );
    }

    @Test
    public void getAllData() {

        //多条件设置
        MatchPhraseQueryBuilder userQuery = QueryBuilders
                .matchPhraseQuery("user","bob");
        MatchPhraseQueryBuilder versionQuery = QueryBuilders
                .matchPhraseQuery("version","1");
        QueryBuilder userVersionQuery = QueryBuilders.boolQuery()
                .must(userQuery)
                .must(versionQuery);

                   //指定一个index和type
            SearchRequestBuilder search = transportClient.prepareSearch("goods").setTypes("goods-user");
            //使用原生排序优化性能
//            search.addSort("user", SortOrder.ASC);
            //设置每批读取的数据量
            search.setSize(100);
            //默认是查询所有
            search.setQuery(userVersionQuery);
            //设置 search context 维护1分钟的有效期
            search.setScroll(TimeValue.timeValueMinutes(1));

            //获得首次的查询结果
            SearchResponse scrollResp=search.get();
            //打印命中数量
            System.out.println("命中总数量："+scrollResp.getHits().getTotalHits());
            //打印计数
            int count=1;
            do {
                //读取结果集数据
                for (SearchHit hit : scrollResp.getHits().getHits()) {
                    String id = hit.getId();
                    String user = (String) hit.getSource().get("user");
                    String version = (String) hit.getSource().get("version");
                    System.out.println(id+" "+user);
                }
                count++;
                //将scorllId循环传递
                scrollResp = transportClient.prepareSearchScroll(scrollResp.getScrollId()).setScroll(TimeValue.timeValueMinutes(1)).execute().actionGet();

                //当searchHits的数组为空的时候结束循环，至此数据全部读取完毕
            } while(scrollResp.getHits().getHits().length != 0);
    }

    @Test
    public void test() {
        List<CatalogResultVO> bob = goodsUserService.getGoodsLabel("bob", 1, 10);
        bob.forEach(
                res -> {
                    System.out.println(res);
                }
        );
    }
}