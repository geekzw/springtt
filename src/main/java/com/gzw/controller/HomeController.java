package com.gzw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by gujian on 2017/6/28.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){

        return "/login";
    }
}
