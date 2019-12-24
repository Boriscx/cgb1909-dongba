package com.tedu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PageController {

    @RequestMapping("/")
    public String index() {
        return "starter";
    }

//    @RequestMapping("{module}/{moduleUI}")
//    public String doLoad(@PathVariable String module, @PathVariable String moduleUI) {
//        return "sys" + "/" + moduleUI;
//    }

    @RequestMapping("doPageUI")
    public String doPageUI() {
        return "common/page";
    }

}
