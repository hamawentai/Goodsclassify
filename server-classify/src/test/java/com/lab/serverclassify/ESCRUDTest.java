package com.lab.serverclassify;


import com.lab.serverclassify.pojo.document.GoodsUser;
import com.lab.serverclassify.utils.CreateResultFileUtil;
import com.lab.serverclassify.utils.HbaseUtil;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.data.elasticsearch.core.ScrolledPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServerClassifyApplication.class)
@Slf4j
public class ESCRUDTest {


    @Autowired
    private ElasticsearchTemplate esTemplate;

    @Autowired
    private CreateResultFileUtil createResultFileUtil;

    @Autowired
    private HbaseUtil hbaseUtil;

    @Autowired
    private TransportClient transportClient;

    @Test
    public void aTest() {
        Instant t1 = Instant.now();
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(termQuery("user", "bob")).withQuery(termQuery("version", "1")).build();
        ScrolledPage<GoodsUser> scroll = (ScrolledPage<GoodsUser>) esTemplate.startScroll(1000, searchQuery, GoodsUser.class);
        List<String> rowkeys = new ArrayList<>();
        while (scroll.hasContent()) {
            List<GoodsUser> list = scroll.getContent();
            for (GoodsUser user : list) {
                rowkeys.add(user.getId());
            }
            scroll = (ScrolledPage<GoodsUser>) esTemplate.continueScroll(scroll.getScrollId(), 1000, GoodsUser.class);
        }
//        ResultFileBo resultFileBo = hbaseUtil.findByRowKeyList(rowkeys, "goodslabel");
//        createResultFileUtil.mppCreateFile(resultFileBo, "1.txt");
        Instant t2 = Instant.now();
        System.out.println(Duration.between(t1, t2).toMillis());
    }

    @Test
    public void spendTime() {
        getAllUserGoods("bob", "1");
        getAllUserGoods("bob", "1");
        aTest();
        aTest();
    }

    public void getAllUserGoods(String userName, String version) {
        long start = System.currentTimeMillis();
        List<String> rowkeys = new ArrayList<>();
        SearchRequestBuilder search = transportClient.prepareSearch("goods").setTypes("goods-user");
        MatchPhraseQueryBuilder versionQuery = QueryBuilders
                .matchPhraseQuery("version", version);
        MatchPhraseQueryBuilder userQuery = QueryBuilders
                .matchPhraseQuery("user", userName);
        QueryBuilder userVersionQuery = QueryBuilders.boolQuery()
                .must(userQuery)
                .must(versionQuery);
        search.setSize(100);
        search.setQuery(userVersionQuery);
        search.setScroll(TimeValue.timeValueMinutes(2));
        SearchResponse scrollResp = search.get();
        do {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                String id = hit.getId();
                rowkeys.add(id);
            }
            scrollResp = transportClient.prepareSearchScroll(scrollResp.getScrollId()).setScroll(TimeValue.timeValueMinutes(2)).execute().actionGet();

        } while (scrollResp.getHits().getHits().length != 0);
        log.info("rowkey size {} spend time {} ", rowkeys.size(), System.currentTimeMillis() - start);
    }


}
