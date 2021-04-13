package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.registerDTO;
import com.dejie.hospital.registration.mapper.DepartmentMapper;
import com.dejie.hospital.registration.mapper.DoctorMapper;
import com.dejie.hospital.registration.mapper.RegisterMapper;
import com.dejie.hospital.registration.mapper.UserMapper;
import com.dejie.hospital.registration.model.Register;
import com.dejie.hospital.registration.model.RegisterExample;
import com.dejie.hospital.registration.model.User;
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
public class DindexController {
    @Autowired
    DoctorService doctorService;
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    RegisterMapper registerMapper;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    UserMapper userMapper;
    @GetMapping("/Dindex")
    public String Dindex(@RequestParam(defaultValue = "1") int pageNum,
                         @RequestParam(defaultValue = "10") int pageSize,
                         HttpServletRequest request, Model model){
        int Did = new Integer(0);
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getSession().getAttribute("Did")!=null){
            Did=(int)request.getSession().getAttribute("Did");}
        PageHelper.startPage(pageNum,pageSize);
        RegisterExample example = new RegisterExample();
        example.createCriteria().andDidEqualTo(Did);
        List<Register> registers= registerMapper.selectByExample(example);
        List<registerDTO> registerDTOS = new ArrayList<>();
        for(int i=0;i<registers.size();i++){
            registerDTO registerDTO = new registerDTO();
            registerDTO.setRid(registers.get(i).getRid());
            registerDTO.setCardnumber(registers.get(i).getCardnumber());
            registerDTO.setTruename(userMapper.selectByPrimaryKey(registers.get(i).getCardnumber()).getTruename());
            registerDTO.setDpmtname(departmentMapper.selectByPrimaryKey(registers.get(i).getDmajor()).getDpmtname());
            registerDTO.setVisitDate(registers.get(i).getVisitdate());
            registerDTO.setTime(registers.get(i).getTime());
            registerDTO.setRstatus(registers.get(i).getRstatus());
            registerDTO.setCurrentDate(System.currentTimeMillis());
            registerDTOS.add(registerDTO);
        }
        PageInfo pageInfo = new PageInfo(registerDTOS);
        model.addAttribute("pageInfo",pageInfo);
        return "/Dindex";
    }
}
