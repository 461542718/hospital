package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.model.User;
import com.dejie.hospital.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class registerController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/register")
    public String doLogin(
            @RequestParam(value = "name",required = false) String username,
            @RequestParam(value = "password",required = false) String password,
            @RequestParam(value = "idcard",required = false) String idcard,
            @RequestParam(value = "truename",required = false) String truename,
            HttpServletRequest request, Model model
    ){
        model.addAttribute("name",username);
        model.addAttribute("password",password);
        model.addAttribute("idcard",idcard);
        model.addAttribute("truename",truename);
        if(username==null||username==""){
            model.addAttribute("error","用户名不能为空");
            return "register";
        }
        if(password==null||password==""){
            model.addAttribute("error","密码不能为空");
            return "register";
        }
        if(idcard==null||idcard==""){
            model.addAttribute("error","身份证号不能为空");
            return "register";
        }
        if(truename==null||truename==""){
            model.addAttribute("error","姓名不能为空");
            return "register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setIdcard(idcard);
        user.setTruename(truename);
        userService.createOrUpdate(user);
        return  "redirect:/";
    }
}
