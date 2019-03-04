package com.lab.serverclassify.classify.service;

import com.lab.serverclassify.pojo.dto.ShellCommandsDTO;

/**
 * @author weixun
 */
public interface RunShellService {

    /**
     * 用来调用脚本
     *
     * @param commandsDTO shell脚本所需要的参数
     */
    void runDataAnalysisShell(ShellCommandsDTO commandsDTO);
}
