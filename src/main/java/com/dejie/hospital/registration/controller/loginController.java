package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.UserMapper;
import com.dejie.hospital.registration.model.User;
import com.dejie.hospital.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class loginController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @PostMapping("/login")
    public String doLogin(
            @RequestParam(value = "name",required = false) String username,
            @RequestParam(value = "password",required = false) String password,
            HttpServletResponse response, Model model
            ){
        model.addAttribute("username",username);
        model.addAttribute("password",password);
        if(username==null||username==""){
            model.addAttribute("error","用户名不能为空");
            return "register";
        }
        if(password==null||password==""){
            model.addAttribute("error","密码不能为空");
            return "register";
        }
        User user = new User();
        boolean canLogin;
        user.setUsername(username);
        user.setPassword(password);
        canLogin = userService.doLogin(user);
        if (canLogin==true){
            //登录
            String token=UUID.randomUUID().toString();
            user.setToken(token);
            userService.createOrUpdate(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            response.addCookie(cookie);
            return "redirect:/";
        }else {
            //登陆失败
        }
        return  "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("user");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/";
    }

}