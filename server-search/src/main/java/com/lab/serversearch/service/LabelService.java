package com.lab.serversearch.service;

import com.lab.serversearch.vo.LabelVO;

import java.util.List;

/**
 * 分类表相关操作
 */
public interface LabelService {

    List<LabelVO> findAllLabels();
}
