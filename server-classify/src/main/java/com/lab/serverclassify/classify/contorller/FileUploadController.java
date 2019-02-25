package com.lab.serverclassify.classify.contorller;

import com.lab.serverclassify.utils.HdfsFileSystem;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.util.List;


@Controller
public class FileUploadController {

    @GetMapping("/")
    public String index() {
        System.out.println("come");
        return "/index";
    }

    @PostMapping("/upload")
    @ResponseBody
    public String batchUpload(HttpServletRequest request) {
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        MultipartFile file = null;
        String username = "bob";
        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);
            if (!file.isEmpty()) {
                try {
                    String filename = file.getOriginalFilename();
                    byte[] bytes = file.getBytes();
                    ByteArrayInputStream in = new ByteArrayInputStream(bytes);
                    HdfsFileSystem.createFile(in, username, filename);
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