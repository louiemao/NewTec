package com.sws.newtec.modules.module1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by george on 15-6-29.
 */
@Controller
@RequestMapping("/")
public class IndexController {
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
//        model.addAttribute("message", "Hello world!");
//        model.addAttribute("message2","hello,world");
        //return "index2";
        return "redirect:/static/demo/index.html";
    }
}
