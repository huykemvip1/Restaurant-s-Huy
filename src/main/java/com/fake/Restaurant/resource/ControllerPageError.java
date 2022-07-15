package com.fake.Restaurant.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page_error")
@Slf4j
public class ControllerPageError {
    @GetMapping
    public String pageError(){
        return "page_error";
    }
}
