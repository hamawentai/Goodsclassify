package com.lab.serverclassify.mongodb.repository;

import com.lab.serverclassify.pojo.dos.UserFilesDO;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author weixun
 */
public interface UserFilesRepository extends MongoRepository<UserFilesDO, Long> {
    /**
     * 通过用户名来查找user-file
     * @param name 用户名
     * @return UseFilesDo
     */
    UserFilesDO findUserFilesDOByName(String name);

}
