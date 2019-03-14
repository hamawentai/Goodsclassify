package com.lab.serversearch.dao;

import com.lab.serversearch.domain.UserVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVersionDao extends JpaRepository<UserVersion,Integer> {

    UserVersion findByUserName(String userName);
}
