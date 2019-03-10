package com.lab.serverclassify.utils;

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
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weixun
 * @date 19-3-8 下午4:33
 */

@Component
@Slf4j
public class EsSearchUtil {


    @Autowired
    private TransportClient transportClient;

    public List<String> getAllRowkeysByUsernameAndVersion(String username, String version) {
        long start = System.currentTimeMillis();
        List<String> rowkeys = new ArrayList<>();
        SearchRequestBuilder search = transportClient.prepareSearch("goods").setTypes("goods-user");
        MatchPhraseQueryBuilder versionQuery = QueryBuilders
                .matchPhraseQuery("version", version);
        MatchPhraseQueryBuilder userQuery = QueryBuilders
                .matchPhraseQuery("user", username);
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
        return rowkeys;
    }
}
