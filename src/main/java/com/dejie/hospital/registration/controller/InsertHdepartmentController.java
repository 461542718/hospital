package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.model.Hospital_department;
import com.dejie.hospital.registration.service.HospitaldepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InsertHdepartmentController {
    @Autowired
    HospitaldepartmentService hospitaldepartmentService;
    @GetMapping("/insertHdepartment/{hospitalId}")
    public  String insertHdepartment(){return "/insertHdepartment";}
    @PostMapping("/insertHdepartment")
    public String doEditHdepartment(
            @RequestParam(value = "hospitalId",required = false) Integer hospitalId,
            @RequestParam(value = "Dpmtid",required = false) Integer Dpmtid,
            HttpServletRequest request, Model model){
        Hospital_department hospital_department = new Hospital_department();
//        userinfo.setCardnumber((int)request.getSession().getAttribute("cardnumber"));
        hospital_department.setDpmtid(Dpmtid);
        hospital_department.setHospitalId(hospitalId);
        hospitaldepartmentService.createOrUpdate(hospital_department);
        return  "redirect:/Hindex";
    }
}
