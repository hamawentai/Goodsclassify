package com.lab.serversearch.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


/**
 * 用户版本类
 * 记录用户上传的历史版本
 */
@Data
@Entity
public class UserVersion {

    @Id
    @GeneratedValue
    private Integer id;

    private String userName;

    private String version;
}
