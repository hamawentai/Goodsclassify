package com.lab.serverclassify.mongodb.repository;

import com.lab.serverclassify.pojo.domain.LabelValueDo;
import com.lab.serverclassify.pojo.domain.ProvinceValueDO;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weixun
 * @data 19-3-10 下午7:31
 */
@Repository
public class RedistributionRepository {

    @Autowired
    private MongoOperations mongoOperations;

    public List<LabelValueDo> findByLabel(String lableName) {
        Document queryDoc = new Document("province", lableName);
        List<LabelValueDo> list = new ArrayList<>();
        MongoCollection<Document> collection = mongoOperations.getCollection("redistribution");
        FindIterable<Document> documents = collection.find(queryDoc);
        MongoCursor<Document> iterator = documents.iterator();
        if (iterator.hasNext()) {
            List<Document> detail = (List<Document>) iterator.next().get("detail");
            detail.forEach(ele -> {
                LabelValueDo labelValueDo = new LabelValueDo((String) ele.get("label"), (String) ele.get("value"));
                list.add(labelValueDo);
            });
        }
        return list;
    }

    public List<ProvinceValueDO> findAllProvinceSalesRank() {
        return mongoOperations.findAll(ProvinceValueDO.class, "province_rank");
    }

}
