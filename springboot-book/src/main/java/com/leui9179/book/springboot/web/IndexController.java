package com.leui9179.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index"; // 머스테치 스타터 덕분에 컨트롤러에서 문자열을 return 하면 자동으로 문자열 + .mustache
    }
}
