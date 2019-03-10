package com.lab.serverclassify.classify.contorller;


import com.lab.serverclassify.classify.service.ResultFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author weixun
 */
@Controller
@Slf4j
public class DownloadResultFileController {

    @Autowired
    private ResultFileService resultFileService;

    @RequestMapping("/downloadFile")
    public void downloadFileAction(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "version") String operation) {
        long start = System.currentTimeMillis();
        String username = "bob";
        response.setCharacterEncoding(request.getCharacterEncoding());
        response.setContentType("application/octet-stream");
        FileInputStream in = null;
        String filename = resultFileService.getResultFilePath(username, operation);
        log.info("filename " + filename);
        try {
            File file = new File(filename);
            response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
            in = new FileInputStream(file);
            IOUtils.copy(in, response.getOutputStream());
            response.flushBuffer();
            log.info("download file spend: {}", System.currentTimeMillis() - start);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
