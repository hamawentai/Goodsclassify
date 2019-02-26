package com.lab.server_search.dao;

import com.lab.server_search.domain.Label;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabelDao extends JpaRepository<Label,Integer> {


}
