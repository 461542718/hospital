package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.HospitalMapper;
import com.dejie.hospital.registration.service.HospitalService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MindexController {
    @Autowired
    HospitalMapper hospitalMapper;
    @Autowired
    HospitalService hospitalService;
    @GetMapping("/Mindex")
    public String Mindex(@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize,
                         Model model
                         ){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(hospitalMapper.selectAll());
        model.addAttribute("pageInfo",pageInfo);
        return "Mindex";
    }
}
