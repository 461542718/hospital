package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.HdepartmentDTO;
import com.dejie.hospital.registration.dto.HospitalDepartmentDTO;
import com.dejie.hospital.registration.mapper.DepartmentMapper;
import com.dejie.hospital.registration.mapper.Hospital_departmentMapper;
import com.dejie.hospital.registration.model.Hospital_department;
import com.dejie.hospital.registration.model.Hospital_departmentExample;
import com.dejie.hospital.registration.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HindexController {
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    Hospital_departmentMapper hospital_departmentMapper;
    @GetMapping("/Hindex")
    public String Hindex(@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize,
                         HttpServletRequest request, Model model){
        int hospitalId = new Integer(0);
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getSession().getAttribute("hospitalId")!=null){
        hospitalId=(int)request.getSession().getAttribute("hospitalId");}
        PageHelper.startPage(pageNum,pageSize);
        Hospital_departmentExample example = new Hospital_departmentExample();
        example.createCriteria().andHospitalIdEqualTo(hospitalId);
        List<Hospital_department> Hdpmts=hospital_departmentMapper.selectByExample(example);
        List<HdepartmentDTO> hospitalDpmt = new ArrayList<>();
        for (int i=0;i<Hdpmts.size();i++){
            HdepartmentDTO hospitalDepartmentDTO = new HdepartmentDTO();
            hospitalDepartmentDTO.setId(Hdpmts.get(i).getId());
            hospitalDepartmentDTO.setHospitalId(Hdpmts.get(i).getHospitalId());
            hospitalDepartmentDTO.setDpmtid(Hdpmts.get(i).getDpmtid());
            hospitalDepartmentDTO.setDpmtname(departmentMapper.selectByPrimaryKey(Hdpmts.get(i).getDpmtid()).getDpmtname());
            hospitalDpmt.add(hospitalDepartmentDTO);
        }
        PageInfo pageInfo = new PageInfo(hospitalDpmt);
        model.addAttribute("pageInfo",pageInfo);
        return "/Hindex";
    }
}
