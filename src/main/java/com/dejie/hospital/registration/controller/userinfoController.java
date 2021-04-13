package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.Client.FastDfsClient;
import com.dejie.hospital.registration.FastDfsConfig;
import com.dejie.hospital.registration.dto.userinfoDTO;
import com.dejie.hospital.registration.model.User;
import com.dejie.hospital.registration.model.Userinfo;
import com.dejie.hospital.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
public class userinfoController {
    @Autowired
    private UserService userService;
    @Autowired
    FastDfsClient fastDfsClient;
    @Value("${picture.url}")
    private String fileServerUrl;
    @GetMapping("/userinfo/{Cardnumber}")
    public  String edit(@PathVariable(name = "Cardnumber") Integer cardnumber,
                        Model model){
        userinfoDTO userinfo = userService.getByCardnumber(cardnumber);
        if (userinfo.getCardnumber()!=0){
        model.addAttribute("tel",userinfo.getTel());
        model.addAttribute("address",userinfo.getAddress());
        model.addAttribute("sex",userinfo.getSex());
        model.addAttribute("briefinfo",userinfo.getBriefinfo());
        model.addAttribute("Uphoto",userinfo.getUphoto());
        }
        //model.addAttribute("tags", TagCache.get());
        return  "userinfo";
    }
    @GetMapping("/userinfo")
    public String register(){
        return "userinfo";
    }
    @PostMapping("/userinfo")
    public String doEditUserinfo(
            @RequestParam(value = "tel",required = false) String tel,
            @RequestParam(value = "address",required = false) String address,
            @RequestParam(value = "sex",required = false) int sex,
            @RequestParam(value = "briefinfo",required = false) String briefinfo,
            HttpServletRequest request, Model model
    ){
        model.addAttribute("tel",tel);
        model.addAttribute("address",address);
        model.addAttribute("sex",sex);
        model.addAttribute("briefinfo",briefinfo);
//        User user = new User();
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        user = request.getSession().getAttribute("user");

        Userinfo userinfo = new Userinfo();
        userinfo.setCardnumber((int)request.getSession().getAttribute("cardnumber"));
        userinfo.setTel(tel);
        userinfo.setAddress(address);
        userinfo.setSex(sex);
        userinfo.setBriefinfo(briefinfo);
        userService.createOrUpdateInfo(userinfo);
        return  "redirect:/";
    }
    @PostMapping("/upload")
    public String uploadpicture(MultipartFile photo,HttpServletRequest request) throws Exception {
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取文件名
        String originalFilename = photo.getOriginalFilename();
        //获取文件后缀名（图片格式）
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String photoname=null;
        int cardnumber=(int)request.getSession().getAttribute("cardnumber");
        photoname = fastDfsClient.uploadFile(photo.getBytes(), extName);
        Userinfo userinfo = new Userinfo();
        userinfo.setCardnumber(cardnumber);
        userinfo.setUphoto(fileServerUrl+photoname);
        userService.uploadphoto(userinfo);
        return "redirect:/userinfo/"+cardnumber;
    }
}

