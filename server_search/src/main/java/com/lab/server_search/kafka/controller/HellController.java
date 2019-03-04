package com.lab.server_search.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellController {

    @GetMapping
    public String hello() {
        return "YZM";
    }
}
