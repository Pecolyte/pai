package me.pecolyte.pai.web.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {
 
    @RequestMapping
    @ResponseBody
    public String sayHello(ModelMap model) {
        return "welcome1";
    }
 
}
