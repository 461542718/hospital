package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.HospitalMapper;
import com.dejie.hospital.registration.mapper.NoticeMapper;
import com.dejie.hospital.registration.service.HospitalService;
import com.dejie.hospital.registration.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class indexController {
    @Autowired
    HospitalMapper hospitalMapper;
    @Autowired
    HospitalService hospitalService;
    @Autowired
    NoticeService noticeService;
    @Autowired
    NoticeMapper noticeMapper;
    @GetMapping("/")
    public String Mindex(@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "5") int pageSize,
                         @RequestParam(defaultValue = "1") int pageNum1,
                         @RequestParam(defaultValue = "5") int pageSize1,
                         Model model
    ){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(hospitalMapper.selectAll());
        model.addAttribute("pageInfo",pageInfo);
        PageHelper.startPage(pageNum1,pageSize1);
        PageHelper.orderBy("noticeid desc");
        PageInfo noticeInfo = new PageInfo(noticeMapper.selectAll());
        model.addAttribute("noticeInfo",noticeInfo);
        return "index";
    }

}
