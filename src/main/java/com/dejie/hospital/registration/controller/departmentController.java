package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.HospitalDepartmentDTO;
import com.dejie.hospital.registration.dto.departmentDTO;
import com.dejie.hospital.registration.entity.department;
import com.dejie.hospital.registration.mapper.DepartmentMapper;
import com.dejie.hospital.registration.mapper.Hospital_departmentMapper;
import com.dejie.hospital.registration.mapper.UserMapper;
import com.dejie.hospital.registration.mapper.UserinfoMapper;
import com.dejie.hospital.registration.model.Department;
import com.dejie.hospital.registration.model.Hospital_department;
import com.dejie.hospital.registration.repositories.DepartmentRepository;
import com.dejie.hospital.registration.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class departmentController {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    UserinfoMapper userinfoMapper;

    @GetMapping("/department/{Hid}")
    public String department(@PathVariable(name = "Hid") Integer Hid,
                             HttpServletRequest request, Model model){
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        List<HospitalDepartmentDTO> hospitalDepartments = new ArrayList<>();
        hospitalDepartments=departmentService.getByHid(Hid);
//        HospitalDepartmentDTO hospital_department = new HospitalDepartmentDTO();
        List<departmentDTO> departmentDTOS = new ArrayList<>();
        List<department> TJdepartments = new ArrayList<>();
        if (request.getSession().getAttribute("user")!=null)
        {TJdepartments = departmentService.search(userinfoMapper.selectByPrimaryKey((int)request.getSession().getAttribute("cardnumber")).getBriefinfo());}
        for (HospitalDepartmentDTO hospital_department:hospitalDepartments){
            departmentDTO HD = new departmentDTO();
            HD=departmentService.getById(hospital_department.getDpmtid());
            departmentDTOS.add(HD);
        }
        model.addAttribute("Hid",Hid);
        model.addAttribute("departmentlist",departmentDTOS);
        model.addAttribute("TJdepartments",TJdepartments);
        model.addAttribute("hospitaldepartments",hospitalDepartments);
        return "department";
    }
}
