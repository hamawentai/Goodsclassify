package com.lab.serversearch.service.impl;

import com.lab.serversearch.dao.UserVersionDao;
import com.lab.serversearch.domain.UserVersion;
import com.lab.serversearch.service.UserVersionService;
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
