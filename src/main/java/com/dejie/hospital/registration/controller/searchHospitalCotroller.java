package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.HospitalMapper;
import com.dejie.hospital.registration.service.SearchHospitalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class searchHospitalCotroller {
    @Autowired
    SearchHospitalService searchHospitalService;
    @Autowired
    HospitalMapper hospitalMapper;
    @GetMapping("/searchHospital")
    public  String search(){return "searchHospital";}
    @PostMapping("/searchHospital")
    public String searchHospital(@RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam(defaultValue = "5") int pageSize,
                                 @RequestParam(value = "search",required = false) String Hname,
                                 Model model
    ){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(searchHospitalService.search(Hname));
        model.addAttribute("pageInfo",pageInfo);
        return "searchHospital";
    }
}
