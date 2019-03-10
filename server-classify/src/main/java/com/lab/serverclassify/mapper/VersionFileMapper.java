package com.lab.serverclassify.mapper;

import com.lab.serverclassify.pojo.domain.VersionFile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author weixun
 */
@Mapper
@Component
public interface VersionFileMapper {

    /**
     * 获得结果文件路径
     *
     * @param username 用户名
     * @param version  版本号
     * @return 结果文件路径
     */
    @Select("select file_path from version_file where user_name=#{username} and version=#{version} limit 1")
    String getFilePathByUsernameAndVersion(String username, String version);

    /**
     * 获得结果文件对象
     *
     * @param username 用户名
     * @param version  版本号
     * @return 结果文件路径
     */
    @Select("select * from version_file where user_name=#{username} and version=#{version} limit 1")
    VersionFile getVersionFileByUsername(String username, String version);

    /**
     * 获得同一个人的所有记录
     * @param username 用户名
     * @return 所有的记录
     */
    @Select("select * from version_file where user_name=#{username}")
    List<VersionFile> getAllVersionFilesByUserName(String username);

    /**
     * 插入一行数据
     *
     * @param versionFile 实体对象
     * @return 被影响的列
     */
    @Insert("insert into version_file(user_name, version, file_path, kind) " +
            "values(#{userName}, #{version}, #{filePath}, #{kind})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    Integer insert(VersionFile versionFile);

    /**
     * 更新filepath
     *
     * @param username 用户名
     * @param version  版本好
     * @param filePath 路径
     * @return 被影响的列
     */
    @Update("update version_file set file_path=#{filePath} where user_name = #{username} and version=#{version}")
    Integer update(String username, String version, String filePath);

    /**
     * 删除一列
     *
     * @param username 用户名
     * @param version  版本好
     * @return 被影响的列
     */
    @Delete("delete from version_file where user_name = #{username} and version=#{version}")
    Integer delete(String username, String version);
}
