package com.lab.serverclassify.classify.serviceImpl;

import com.lab.serverclassify.classify.service.RunShellService;
import com.lab.serverclassify.pojo.dto.ShellCommandsDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author weixun
 */
@Slf4j
@Service
public class RunShellServiceImpl implements RunShellService {


    @Override
    public void runDataAnalysisShell(ShellCommandsDTO commandsDTO) {
        List<String> list = new ArrayList<>();
        list.add(commandsDTO.getShellName());
        list.add(commandsDTO.getSuccessUrl());
        list.add(commandsDTO.getFailUrl());
        if (commandsDTO.getCommands() != null) {
            log.info(commandsDTO.getCommands().size() + "");
            list.addAll(commandsDTO.getCommands());
        }
        ProcessBuilder pb = new ProcessBuilder(list);
        pb.directory(new File(commandsDTO.getShellPath()));
        String s = null;
        try {
            log.info(commandsDTO.getShellName() + " is start");
            Process p = pb.start();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
            while ((s = stdInput.readLine()) != null) {
                log.info(s);
            }
            while ((s = stdError.readLine()) != null) {
                log.error(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
