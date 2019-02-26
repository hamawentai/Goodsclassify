package com.lab.server_search.service;

import com.lab.server_search.vo.LabelVO;

import java.util.List;

/**
 * 分类表相关操作
 */
public interface LabelService {

    List<LabelVO> findAllLabels();
}
