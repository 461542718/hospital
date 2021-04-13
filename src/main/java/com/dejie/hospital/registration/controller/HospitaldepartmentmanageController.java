package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.Hospital_departmentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HospitaldepartmentmanageController {
    @Autowired
    Hospital_departmentMapper hospital_departmentMapper;
    @GetMapping("/hospitaldepartmentmanage")
    public String hospitaldepartmentmanage(@RequestParam(defaultValue = "1") int pageNum,
                                   @RequestParam(defaultValue = "10") int pageSize,
                                   Model model){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(hospital_departmentMapper.selectAll());
        model.addAttribute("pageInfo",pageInfo);
        return "hospitaldepartmentmanage";
    }
}
