package com.lab.serversearch.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 一级分类实体类
 */
@Data
@Entity
public class Label implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private String label;
}
