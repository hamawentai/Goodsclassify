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

    /**
     * 根据省份名返回各个省的各种分类的销量排行
     * @param province 省份名
     * @return 各个标签的销量排行
     *
     * 	"province" : "河北省",
     * 	"detail" : [
     *                {
     * 			"index" : "0",
     * 			"label" : "图书杂志",
     * 			"value" : "658807104"
     *        },....]
     */
    public List<LabelValueDo> findLabelRankByProvince(String province) {
        Document queryDoc = new Document("province", province);
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

    /**
     * 获得省份的销量排行
     * @return 省份-销量
     *
     * {
     * 	"_id" : ObjectId("5c8a28341021ec191365e244"),
     * 	"province" : "湖北省",
     * 	"value" : "1938062390"
     * }...
     */
    public List<ProvinceValueDO> findAllProvinceSalesRank() {
        return mongoOperations.findAll(ProvinceValueDO.class, "province_rank");
    }

    /**
     *  获得全国商品类型的销量排行
     * @return 销量排行
     *
     * {
     * 	"_id" : ObjectId("5c8a1a611021ec14d16c2b7e"),
     * 	"label" : "图书杂志",
     * 	"value" : "8869582303"
     * }...
     */
    public List<LabelValueDo> findAllLabelSalesRank() {
        return mongoOperations.findAll(LabelValueDo.class, "label_rank");
    }

}
