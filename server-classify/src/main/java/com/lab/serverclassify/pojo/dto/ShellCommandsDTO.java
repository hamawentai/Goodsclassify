package com.lab.serverclassify.pojo.dto;


import lombok.Data;

import java.util.List;

/**
 * @author weixun
 * shellName  脚本名 “./1.sh“
 * shellPath  脚本文件夹 ”....artifacts/wx"
 * successUrl 脚本执行成功回调地址 http://localhost:8888/shell?res=suc
 * failUrl    脚本执行失败回调地址 http://localhost:8888/shell?res=error
 * commands   腳本所需要的额外參數
 */
@Data
public class ShellCommandsDTO {
    private String shellName;
    private String shellPath;
    private String successUrl;
    private String failUrl;
    private List<String> commands;

    public ShellCommandsDTO() {
    }

    public ShellCommandsDTO(String shellName, String shellPath, String successUrl, String failUrl, List<String> commands) {
        this.shellName = shellName;
        this.shellPath = shellPath;
        this.successUrl = successUrl;
        this.failUrl = failUrl;
        this.commands = commands;
    }

    @Override
    public String toString() {
        return "ShellCommandsDTO{" +
                "shellName='" + shellName + '\'' +
                ", shellPath='" + shellPath + '\'' +
                ", successUrl='" + successUrl + '\'' +
                ", failUrl='" + failUrl + '\'' +
                ", commands=" + commands +
                '}';
    }
}
