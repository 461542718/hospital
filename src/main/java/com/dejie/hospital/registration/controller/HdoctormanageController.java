package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.HdoctorDTO;
import com.dejie.hospital.registration.mapper.DepartmentMapper;
import com.dejie.hospital.registration.mapper.DoctorMapper;
import com.dejie.hospital.registration.mapper.LevelMapper;
import com.dejie.hospital.registration.model.Doctor;
import com.dejie.hospital.registration.model.DoctorExample;
import com.dejie.hospital.registration.service.DoctorService;
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
public class HdoctormanageController {
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    DoctorService doctorService;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    LevelMapper levelMapper;
    @GetMapping("/Hdoctormanage")
    public String Hdoctormanage(@RequestParam(defaultValue = "1") int pageNum,
                                @RequestParam(defaultValue = "10") int pageSize,
                                HttpServletRequest request, Model model){
        int hospitalId = new Integer(0);
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getSession().getAttribute("hospitalId")!=null){
            hospitalId=(int)request.getSession().getAttribute("hospitalId");}
        PageHelper.startPage(pageNum,pageSize);
        DoctorExample example = new DoctorExample();
        example.createCriteria().andHospitalIdEqualTo(hospitalId);
        List<Doctor> Hdoctors = doctorMapper.selectByExample(example);
        List<HdoctorDTO> hospitalDoctor = new ArrayList<>();
        for(int i =0;i<Hdoctors.size();i++){
            HdoctorDTO hdoctorDTO = new HdoctorDTO();
            hdoctorDTO.setDid(Hdoctors.get(i).getDid());
            hdoctorDTO.setDname(Hdoctors.get(i).getDname());
            hdoctorDTO.setDsex(Hdoctors.get(i).getDsex());
            hdoctorDTO.setDtel(Hdoctors.get(i).getDtel());
            hdoctorDTO.setDlevel(Hdoctors.get(i).getDlevel());
            hdoctorDTO.setLname(levelMapper.selectByPrimaryKey(Hdoctors.get(i).getDlevel()).getLname());
            hdoctorDTO.setHospitalId(Hdoctors.get(i).getHospitalId());
            hdoctorDTO.setDmajor(Hdoctors.get(i).getDmajor());
            hdoctorDTO.setDpmtname(departmentMapper.selectByPrimaryKey(Hdoctors.get(i).getDmajor()).getDpmtname());
            hdoctorDTO.setDphoto(Hdoctors.get(i).getDphoto());
            hdoctorDTO.setDinfo(Hdoctors.get(i).getDinfo());
            hospitalDoctor.add(hdoctorDTO);
        }
        PageInfo pageInfo = new PageInfo(hospitalDoctor);
        model.addAttribute("pageInfo",pageInfo);
        return "/Hdoctormanage";
    }
}
