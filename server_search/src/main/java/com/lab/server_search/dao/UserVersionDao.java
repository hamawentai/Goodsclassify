package com.lab.server_search.dao;

import com.lab.server_search.domain.UserVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVersionDao extends JpaRepository<UserVersion,Integer> {

    UserVersion findByUserName(String userName);
}
