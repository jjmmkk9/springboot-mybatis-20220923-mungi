package com.boot.mybatis20220923mungi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    //난 바보다
    @GetMapping("/news/newpost")   //news 폴더의 write html
    public String loadWriteNews() {
        return "news/write";
    }

    @GetMapping("/news/{id}")
    public String loadNews(@PathVariable int id) {
        return "news/read";
    }

    @GetMapping("/auth/signup")
    public String loadSignup() {
        return "/auth/signup";
    }
}


