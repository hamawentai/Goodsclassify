package com.lab.serverclassify;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JavaShellUtil1 {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void useShell() {
        List<String> list = new ArrayList<>();
        ProcessBuilder pb = new ProcessBuilder("./1.sh");
        pb.directory(new File("/home/weixun/IdeaProjects/data-analysis/out/artifacts/wx"));
        int runningStatus = 0;
        String s = null;
        try {
            Process p = pb.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((s = stdInput.readLine()) != null) {
                logger.error(s);
            }
            while ((s = stdError.readLine()) != null) {
                logger.error(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}