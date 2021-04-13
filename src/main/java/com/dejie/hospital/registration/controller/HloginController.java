package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.model.Hospital;
import com.dejie.hospital.registration.model.Manager;
import com.dejie.hospital.registration.service.HospitalService;
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
public class HloginController {
    @Autowired
    HospitalService hospitalService;
    @GetMapping("/Hlogin")
    public String login(){
        return "Hlogin";
    }
    @PostMapping("/Hlogin")
    public String doLogin(
            @RequestParam(value = "name",required = false) int username,
            @RequestParam(value = "password",required = false) String password,
            HttpServletResponse response, Model model
    ){
        model.addAttribute("username",username);
        model.addAttribute("password",password);
        if(username==0){
            model.addAttribute("error","医院号不能为空");
            return "register";
        }
        if(password==null||password==""){
            model.addAttribute("error","密码不能为空");
            return "register";
        }
        Hospital hospital = new Hospital();
        boolean canLogin;
        hospital.setHospitalId(username);
        hospital.setPassword(password);
        canLogin = hospitalService.doLogin(hospital);
        if (canLogin==true){
            //登录
            String token= UUID.randomUUID().toString();
            hospital.setToken(token);
            hospitalService.addToken(hospital);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            response.addCookie(cookie);
            return "redirect:/Hindex";
        }else {
            //登陆失败
        }
        return  "redirect:/Hindex";
    }
    @GetMapping("/Hlogout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("hospital");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/Hindex";
    }

}
