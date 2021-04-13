package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.RegisterMapper;
import com.dejie.hospital.registration.mapper.ScheduleMapper;
import com.dejie.hospital.registration.model.Register;
import com.dejie.hospital.registration.model.Schedule;
import com.dejie.hospital.registration.service.RegisterService;
import com.dejie.hospital.registration.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class insertregisterController {
    @Autowired
    RegisterService registerService;
    @Autowired
    RegisterMapper registerMapper;
    @Autowired
    ScheduleMapper scheduleMapper;
    @GetMapping("/deleteregister/{Rid}")
    public String delete(@PathVariable(name = "Rid") Integer Rid,
                         Model model){
        Register register = registerMapper.selectByPrimaryKey(Rid);
        registerService.delete(register);
        return "redirect:/";
    }
    @GetMapping("/deleteMregister/{Rid}")
    public String deleteMregister(@PathVariable(name = "Rid") Integer Rid,
                                  Model model){
        Register register = registerMapper.selectByPrimaryKey(Rid);
        registerService.delete(register);
        return "redirect:/registermanage";
    }
    @GetMapping("/deleteDregister/{Rid}")
    public String deleteDregister(@PathVariable(name = "Rid") Integer Rid,
                                  Model model){
        Register register = registerMapper.selectByPrimaryKey(Rid);
        registerService.delete(register);
        return "redirect:/Dindex";
    }
    @PostMapping("/insertregister")
    public String doEditRegister(@RequestParam(value = "scid",required = false) Integer Scid,
                                 @RequestParam(value = "cardnumber",required = false) Integer cardnumber,
                                 @RequestParam(value = "visitDate",required = false) long visitDate,
                                 Model model
                                 ){
        Register register = new Register();
        Schedule schedule;
        schedule=scheduleMapper.selectByPrimaryKey(Scid);
        register.setCardnumber(cardnumber);
        register.setScid(Scid);
        register.setVisitdate(visitDate);
        register.setDmajor(schedule.getDmajor());
        register.setDid(schedule.getDid());
        register.setHospitalId(schedule.getHospitalId());
        register.setTime(schedule.getTime());
        register.setRstatus(0);
        register.setRegisterdate(System.currentTimeMillis());
        registerService.CreateRegister(register);
        return "redirect:/";
    }
}
