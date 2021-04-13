package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.registerDTO;
import com.dejie.hospital.registration.mapper.*;
import com.dejie.hospital.registration.model.Register;
import com.dejie.hospital.registration.service.RegisterService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class registermanageController {
    @Autowired
    RegisterExtMapper registerExtMapper;
    @Autowired
    RegisterMapper registerMapper;
    @Autowired
    RegisterService registerService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    HospitalMapper hospitalMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    DoctorMapper doctorMapper;
    @GetMapping("/registermanage")
    public String registermanage(@RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam(defaultValue = "10") int pageSize,
                                 HttpServletRequest request, Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<Register> registers = registerExtMapper.selectAll();
        List<registerDTO> registerDTOS = new ArrayList<>();
        for(int i=0;i<registers.size();i++){
            registerDTO registerDTO = new registerDTO();
            registerDTO.setRid(registers.get(i).getRid());
            registerDTO.setCardnumber(registers.get(i).getCardnumber());
            registerDTO.setTruename(userMapper.selectByPrimaryKey(registers.get(i).getCardnumber()).getTruename());
            registerDTO.setHospitalName(hospitalMapper.selectByPrimaryKey(registers.get(i).getHospitalId()).getHospitalName());
            registerDTO.setDpmtname(departmentMapper.selectByPrimaryKey(registers.get(i).getDmajor()).getDpmtname());
            registerDTO.setDname(doctorMapper.selectByPrimaryKey(registers.get(i).getDid()).getDname());
            registerDTO.setRegisterDate(registers.get(i).getRegisterdate());
            registerDTO.setVisitDate(registers.get(i).getVisitdate());
            registerDTO.setRstatus(registers.get(i).getRstatus());
            registerDTO.setCurrentDate(System.currentTimeMillis());
            registerDTOS.add(registerDTO);
        }
        PageInfo pageInfo = new PageInfo(registerDTOS);
        model.addAttribute("pageInfo",pageInfo);
        return "registermanage";
    }

}
