package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.HdoctorDTO;
import com.dejie.hospital.registration.dto.doctorDTO;
import com.dejie.hospital.registration.mapper.DepartmentMapper;
import com.dejie.hospital.registration.mapper.LevelMapper;
import com.dejie.hospital.registration.model.Department;
import com.dejie.hospital.registration.model.Doctor;
import com.dejie.hospital.registration.service.DoctorService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class doctorController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    LevelMapper levelMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @RequestMapping("doctor")
    public String doctor(@RequestParam("hospitalId") int hospitalId,
                         @RequestParam("dpmtid") int dpmtid,
                         @RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize,
                         Model model){
        PageHelper.startPage(pageNum,pageSize);
        List<doctorDTO> doctors =doctorService.getByKey(hospitalId,dpmtid);
        List<doctorDTO> DList = new ArrayList<>();
        for (int i=0;i<doctors.size();i++){
            doctorDTO doctorDTO = new doctorDTO();
            doctorDTO.setDid(doctors.get(i).getDid());
            doctorDTO.setDname(doctors.get(i).getDname());
            doctorDTO.setDsex(doctors.get(i).getDsex());
            doctorDTO.setDtel(doctors.get(i).getDtel());
            doctorDTO.setDlevel(doctors.get(i).getDlevel());
            doctorDTO.setLname(levelMapper.selectByPrimaryKey(doctors.get(i).getDlevel()).getLname());
            doctorDTO.setHospitalId(doctors.get(i).getHospitalId());
            doctorDTO.setDmajor(doctors.get(i).getDmajor());
            doctorDTO.setDpmtname(departmentMapper.selectByPrimaryKey(doctors.get(i).getDmajor()).getDpmtname());
            doctorDTO.setDinfo(doctors.get(i).getDinfo());
            doctorDTO.setDphoto(doctors.get(i).getDphoto());
            DList.add(doctorDTO);
        }
        PageInfo pageInfo = new PageInfo(DList);
        model.addAttribute("pageInfo",pageInfo);
        return "doctor";

    }
}
