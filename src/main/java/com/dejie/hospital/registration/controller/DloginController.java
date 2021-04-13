package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.DoctorMapper;
import com.dejie.hospital.registration.model.Doctor;
import com.dejie.hospital.registration.model.Hospital;
import com.dejie.hospital.registration.service.DoctorService;
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
public class DloginController {
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    DoctorService doctorService;
    @GetMapping("/Dlogin")
    public String login(){
        return "Dlogin";
    }
    @PostMapping("/Dlogin")
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
        Doctor doctor = new Doctor();
        boolean canLogin;
        doctor.setDid(username);
        doctor.setPassword(password);
        canLogin = doctorService.doLogin(doctor);
        if (canLogin==true){
            //登录
            String token= UUID.randomUUID().toString();
            doctor.setToken(token);
            doctorService.addToken(doctor);
            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60 * 24 * 30 * 6);
            response.addCookie(cookie);
            return "redirect:/Dindex";
        }else {
            //登陆失败
        }
        return  "redirect:/Dindex";
    }
    @GetMapping("/Dlogout")
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {
        request.getSession().removeAttribute("doctor");
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/Dindex";
    }
}
