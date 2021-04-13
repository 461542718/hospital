package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.HospitalDepartmentDTO;
import com.dejie.hospital.registration.model.Hospital_department;
import com.dejie.hospital.registration.service.HospitaldepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class inserthospitaldepartment {
    @Autowired
    HospitaldepartmentService hospitaldepartmentService;
    @GetMapping("/inserthospitaldepartment")
    public String insertdepartment(){
        return "inserthospitaldepartment";
    }
    @GetMapping("/inserthospitaldepartment/{id}")
    public  String edit(@PathVariable(name = "id") Integer id,
                        Model model){
        HospitalDepartmentDTO hospitalDepartment = hospitaldepartmentService.getById(id);
        model.addAttribute("id",hospitalDepartment.getId());
        model.addAttribute("Hid",hospitalDepartment.getHospitalId());
        model.addAttribute("dpmtid",hospitalDepartment.getDpmtid());
        return  "inserthospitaldepartment";
    }
    @GetMapping("/delecthospitaldepartment/{id}")
    public String delect(@PathVariable(name = "id") Integer id,
                         Model model){
        HospitalDepartmentDTO hospitalDepartment = hospitaldepartmentService.getById(id);
        hospitaldepartmentService.delect(hospitalDepartment);
        return "redirect:/hospitaldepartmentmanage";
    }
    @GetMapping("/delectHdepartment/{id}")
    public String delectHdepartment(@PathVariable(name = "id") Integer id,
                         Model model){
        HospitalDepartmentDTO hospitalDepartment = hospitaldepartmentService.getById(id);
        hospitaldepartmentService.delect(hospitalDepartment);
        return "redirect:/Hindex";
    }
    @PostMapping("/inserthospitaldepartment")
    public String doEditDepartment(
            @RequestParam(value = "Hid",required = false) Integer Hid,
            @RequestParam(value = "Dpmtid",required = false) Integer Dpmtid,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request, Model model
    ){
        model.addAttribute("Hid",Hid);
        model.addAttribute("Dpmtid",Dpmtid);
//        User user = new User();
//        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        user = request.getSession().getAttribute("user");

        Hospital_department hospital_department = new Hospital_department();
//        userinfo.setCardnumber((int)request.getSession().getAttribute("cardnumber"));
        hospital_department.setDpmtid(Dpmtid);
        hospital_department.setHospitalId(Hid);
        hospital_department.setId(id);
        hospitaldepartmentService.createOrUpdate(hospital_department);
        return  "redirect:/hospitaldepartmentmanage";
    }
}
