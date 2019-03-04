package com.lab.server_search.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class Docs implements Serializable {
    private Integer id;
    private String name;
}
