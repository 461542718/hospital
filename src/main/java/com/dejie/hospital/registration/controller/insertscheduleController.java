package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.doctorDTO;
import com.dejie.hospital.registration.mapper.ScheduleMapper;
import com.dejie.hospital.registration.model.Doctor;
import com.dejie.hospital.registration.model.Schedule;
import com.dejie.hospital.registration.service.DoctorService;
import com.dejie.hospital.registration.service.ScheduleService;
import com.dejie.hospital.registration.dto.scheduleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class insertscheduleController {
    @Autowired
    ScheduleService scheduleService;
    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    DoctorService doctorService;
//    @GetMapping("/insertschedule")
//    public String insertschedule(){
//        return "insertschedule";
//    }
    @GetMapping("/insertschedule/{scid}/{week}/{did}/{time}")
    public String edit(@PathVariable(name = "scid") Integer scid,
                       @PathVariable(name = "week") Integer week,
                       @PathVariable(name = "did") Integer did,
                       @PathVariable(name = "time") Integer time,
                               Model model){
        scheduleDTO schedule = scheduleService.getById(scid);
        model.addAttribute("scid",scid);
        model.addAttribute("week",week);
        model.addAttribute("did",did);
        model.addAttribute("time",time);
        model.addAttribute("registerLimited",schedule.getRegisterLimited());
        return "insertschedule";
    }
    @PostMapping("/insertschedule")
    public String doEditSchedule(@RequestParam(value = "registerLimited",required = false) Integer registerLimited,
                                 @RequestParam(value = "time",required = false) Integer time,
                                 @RequestParam(value = "week",required = false) Integer week,
                                 @RequestParam(value = "did",required = false) Integer did,
                                 @RequestParam(value = "scid",required = false) Integer scid,
                                 HttpServletRequest request, Model model
                                 ){
        model.addAttribute("registerLimited",registerLimited);
        model.addAttribute("time",time);
        Schedule schedule = new Schedule();
        doctorDTO doctor = doctorService.getById(did);
        schedule.setScid(scid);
        schedule.setWeek(week);
        schedule.setDid(did);
        schedule.setTime(time);
        schedule.setHospitalId(doctor.getHospitalId());
        schedule.setDmajor(doctor.getDmajor());
        schedule.setRegisterLimited(registerLimited);
        scheduleService.createOrUpdate(schedule);
        return  "redirect:/schedulemanage/"+did;
    }
    @GetMapping("/delectschedule/{scid}/{did}")
    public String delectSchedule(@PathVariable(name = "scid") Integer scid,
                                 @PathVariable(name = "did") Integer did){
        scheduleDTO schedule = scheduleService.getById(scid);
        scheduleService.delect(schedule);
        return  "redirect:/schedulemanage/"+did;
    }
}
