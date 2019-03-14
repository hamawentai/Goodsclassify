package com.lab.serversearch.service;

import com.lab.serversearch.domain.UserVersion;

public interface UserVersionService {

    /**
     * 根据用户名获取用户最新的上传记录
     * @param userName
     * @return
     */
    UserVersion getUserVersion(String userName);

    /**
     * 更新用户上传记录
     * @param userVersion
     */
    void updateUserVersion(UserVersion userVersion);
}
