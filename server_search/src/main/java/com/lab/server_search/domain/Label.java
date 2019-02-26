package com.lab.server_search.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * 分类实体类
 */
@Data
@Entity
public class Label {

    @Id
    @GeneratedValue
    private int id;

    private String label;
}
