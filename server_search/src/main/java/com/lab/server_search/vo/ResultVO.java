package com.lab.server_search.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResultVO<T> implements Serializable {
    private List<T> result;
}
