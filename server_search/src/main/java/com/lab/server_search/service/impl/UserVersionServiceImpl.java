package com.lab.server_search.service.impl;

import com.lab.server_search.dao.UserVersionDao;
import com.lab.server_search.domain.UserVersion;
import com.lab.server_search.service.UserVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserVersionServiceImpl implements UserVersionService {

    @Autowired
    private UserVersionDao userVersionDao;

    @Override
    public UserVersion getUserVersion(String userName) {
        return userVersionDao.findByUserName(userName);
    }

    @Override
    public void updateUserVersion(UserVersion userVersion) {
        userVersionDao.save(userVersion);
    }
}
