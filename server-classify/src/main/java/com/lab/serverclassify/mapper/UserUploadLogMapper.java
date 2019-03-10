package com.lab.serverclassify.mapper;

import com.lab.serverclassify.pojo.domain.UserUploadLog;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weixun
 */
@Mapper
@Component
public interface UserUploadLogMapper {

    /**
     * 插入一条数据
     *
     * @param userUploadLog 实体对象
     * @return 受影响的列
     */
    @Insert("insert into user_upload_log(user_name, version, upload_file_path, time) " +
            "values(#{userName}, #{version}, #{uploadFilePath}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(UserUploadLog userUploadLog);

    /**
     * 获得当前用户的所有上传记录
     *
     * @param username 用户名
     * @return 所有记录
     */
    @Select("select * from user_upload_log where user_name=#{username}")
    List<UserUploadLog> getAllUserUploadLogByUsername(String username);

    /**
     * 通过用户名和版本获得用户的上传文件的路径
     *
     * @param username 用户名
     * @param version  版本号
     * @return 上传的文件路径
     */
    @Select("select upload_file_path from user_upload_log where user_name=#{username} and version=#{version}")
    String getUploadFilePathByUsernameAndVersion(String username, String version);

    /**
     * 删除一条记录
     *
     * @param username 用户名
     * @param version  版本号
     * @return 受影响的列
     */
    @Delete("delete from user_upload_log where user_name=#{username} and version=#{version}")
    Integer delete(String username, String version);


}
