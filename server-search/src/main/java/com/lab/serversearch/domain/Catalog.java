package com.lab.serversearch.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 标签实体类
 */
@Data
@Entity
public class Catalog implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 标签名
     */
    private String labelName;
}
