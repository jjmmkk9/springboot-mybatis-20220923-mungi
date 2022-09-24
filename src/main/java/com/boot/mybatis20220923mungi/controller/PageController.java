package com.boot.mybatis20220923mungi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    //난 바보다
    @GetMapping("/news/newpost")
    public String loadWriteNews(){
        return "news/write";
    }
}
