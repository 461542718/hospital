package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.*;
import com.dejie.hospital.registration.dto.registerDTO;
import com.dejie.hospital.registration.model.Register;
import com.dejie.hospital.registration.model.RegisterExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class myregisterController {
    @Autowired
    RegisterMapper registerMapper;
    @Autowired
    HospitalMapper hospitalMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    ScheduleMapper scheduleMapper;
    @GetMapping("/myregister/{cardnumber}")
    public String myregister(@PathVariable(name = "cardnumber") Integer cardnumber,
                             @RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "5") int pageSize,
                             Model model){
        RegisterExample example = new RegisterExample();
        example.createCriteria().andCardnumberEqualTo(cardnumber);
        List<Register> myregister= registerMapper.selectByExample(example);
        List<registerDTO> preregister=new ArrayList();
        List<registerDTO> newregister=new ArrayList();


        for (int i=0;i<myregister.size();i++){
            if (myregister.get(i).getVisitdate()<System.currentTimeMillis()||myregister.get(i).getRstatus()==1){
                registerDTO preregisterDTO = new registerDTO();
                preregisterDTO.setRid(myregister.get(i).getRid());
                preregisterDTO.setHospitalName(hospitalMapper.selectByPrimaryKey(myregister.get(i).getHospitalId()).getHospitalName());
                preregisterDTO.setDname(doctorMapper.selectByPrimaryKey(myregister.get(i).getDid()).getDname());
                preregisterDTO.setDpmtname(departmentMapper.selectByPrimaryKey(myregister.get(i).getDmajor()).getDpmtname());
                preregisterDTO.setVisitDate(myregister.get(i).getVisitdate());
                preregisterDTO.setTime(scheduleMapper.selectByPrimaryKey(myregister.get(i).getScid()).getTime());
                preregisterDTO.setRstatus(myregister.get(i).getRstatus());
                preregister.add(preregisterDTO);
            }else {
                registerDTO registerDTO = new registerDTO();
                registerDTO.setRid(myregister.get(i).getRid());
                registerDTO.setHospitalName(hospitalMapper.selectByPrimaryKey(myregister.get(i).getHospitalId()).getHospitalName());
                registerDTO.setDname(doctorMapper.selectByPrimaryKey(myregister.get(i).getDid()).getDname());
                registerDTO.setDpmtname(departmentMapper.selectByPrimaryKey(myregister.get(i).getDmajor()).getDpmtname());
                registerDTO.setVisitDate(myregister.get(i).getVisitdate());
                registerDTO.setTime(scheduleMapper.selectByPrimaryKey(myregister.get(i).getScid()).getTime());
                registerDTO.setRstatus(myregister.get(i).getRstatus());
                newregister.add(registerDTO);
            }
            PageHelper.startPage(pageNum,pageSize);
            PageInfo prepageInfo = new PageInfo(preregister);
            PageInfo newpageInfo = new PageInfo(newregister);
            model.addAttribute("prepageInfo",prepageInfo);
            model.addAttribute("newpageInfo",newpageInfo);

        }
        return "/myregister";
     }
}
