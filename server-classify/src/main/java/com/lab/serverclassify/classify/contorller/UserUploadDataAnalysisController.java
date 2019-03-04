package com.lab.serverclassify.classify.contorller;

import com.lab.serverclassify.classify.service.RunShellService;
import com.lab.serverclassify.classify.serviceImpl.HDFSFileSystem;
import com.lab.serverclassify.pojo.dto.ShellCommandsDTO;
import com.lab.serverclassify.kafka.service.KafkaSenderService;
import com.lab.serverclassify.properties.HdfsProperties;
import com.lab.serverclassify.properties.ShellCallBackProperties;
import com.lab.serverclassify.properties.ShellProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * @author weixun
 */
@Controller
@Slf4j
public class UserUploadDataAnalysisController {


    @Autowired
    private ShellProperties shellProperties;

    @Autowired
    private HdfsProperties hdfsProperties;

    @Autowired
    private HDFSFileSystem hdfsFileSystem;

    @Autowired
    private RunShellService runShellService;

    @Autowired
    private KafkaSenderService<ShellCommandsDTO> kafkaShellCommandsSender;

    @GetMapping("/h")     // 测试用
    public String index() {
        log.info(shellProperties.getUrl()[0].getShellName() + " " + shellProperties.getShellPath());
        return "/index";
    }

    @GetMapping("/shell") // 测试用 shell回调地址
    @ResponseBody
    public String shell(String res) {
        return res + "\n";
    }

    @PostMapping("/upload") // 将文件上传至hdfs
    @ResponseBody
    public String batchUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        ShellCallBackProperties callBackProperties = shellProperties.getUrl()[1];
        MultipartFile file = null;
        String username = "bob";
        String shellPath = shellProperties.getShellPath();
        String shellName = callBackProperties.getShellName();
        String successUrl = callBackProperties.getSuccessUrl();
        String failUrl = callBackProperties.getFailUrl();
        List<String> commands = new ArrayList<>();
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    String filename = file.getOriginalFilename();
                    byte[] bytes = file.getBytes();
                    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                    String hdfPath = hdfsFileSystem.createFile(in, username, filename);
                    log.info(hdfPath);
                    commands.add(hdfPath);
                    commands.add(username);
                    ShellCommandsDTO commandsDTO = new ShellCommandsDTO(shellName, shellPath, successUrl, failUrl, commands);
                    kafkaShellCommandsSender.sendMsg("shell", commandsDTO);
                } catch (Exception e) {
                    return "You failed to upload " + i + " => " + e.getMessage();
                }
            } else {
                return "You failed to upload " + i + " because the file was empty.";
            }
        }
        return "upload successful";
    }
}