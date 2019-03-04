package com.lab.serverclassify.mongodb.repository;


import com.lab.serverclassify.pojo.dos.FolderDO;
import com.lab.serverclassify.pojo.dos.UserFilesDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @author weixun
 */
@Repository
public class UserFilesAdvancedRepository {

    @Autowired
    private MongoOperations mongoOperations;

    /**
     * 在嵌套数组中进行插入一个元素
     */
    public void inc() {
        FolderDO folderDO = new FolderDO("fiiii", 2, null);
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("bob"));
        mongoOperations.updateFirst(query, new Update().push("result.0.list", folderDO), "file");
    }

    /**
     * 对嵌套数组中的某个元素进行修改
     */
    public void update() {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("bob"));
        mongoOperations.updateFirst(query, new Update().set("result.0.list.0.fileName", "file010.0"), "file");
    }

    /**
     * 对嵌套数组中的某个元素进行删除
     */
    public void delete() {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is("bob"));
        Update update = new Update();
        mongoOperations.updateFirst(query, update.unset("result.0.list.0"), "file");
        mongoOperations.updateFirst(query, new Update().pull("result.0.list", null), "file");
    }

    public UserFilesDO findByName(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(username));
        return mongoOperations.findOne(query, UserFilesDO.class, "file");
    }

    public void create() {
        FolderDO folderDO = new FolderDO("fiiii", 2, null);
        UserFilesDO userFilesDO = new UserFilesDO("0002", "jack", folderDO);
        mongoOperations.insert(userFilesDO, "file");
    }
}
