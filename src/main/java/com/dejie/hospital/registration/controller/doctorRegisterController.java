package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.scheduleDTO;
import com.dejie.hospital.registration.model.Doctor;
import com.dejie.hospital.registration.dto.doctorDTO;
import com.dejie.hospital.registration.dto.hospitalDTO;
import com.dejie.hospital.registration.dto.departmentDTO;
import com.dejie.hospital.registration.service.DepartmentService;
import com.dejie.hospital.registration.service.DoctorService;
import com.dejie.hospital.registration.service.HospitalService;
import com.dejie.hospital.registration.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class doctorRegisterController {
    @Autowired
    HospitalService hospitalService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    DoctorService doctorService;
    @Autowired
    ScheduleService scheduleService;
    @GetMapping("/doctorRegister/{id}")
    public String doctorRegister(@PathVariable(name = "id") Integer id,
                                 Model model){
        Long currentTime = System.currentTimeMillis();
        doctorDTO doctor = doctorService.getById(id);
        hospitalDTO hospital = hospitalService.getById(doctor.getHospitalId());
        departmentDTO department = departmentService.getById(doctor.getDmajor());
        scheduleDTO mondayM = scheduleService.getByWeekAndId(id,1,0);
        scheduleDTO mondayA = scheduleService.getByWeekAndId(id,1,1);
        scheduleDTO tuesdayM = scheduleService.getByWeekAndId(id,2,0);
        scheduleDTO tuesdayA = scheduleService.getByWeekAndId(id,2,1);
        scheduleDTO wednesdayM = scheduleService.getByWeekAndId(id,3,0);
        scheduleDTO wednesdayA = scheduleService.getByWeekAndId(id,3,1);
        scheduleDTO thursdayM = scheduleService.getByWeekAndId(id,4,0);
        scheduleDTO thursdayA = scheduleService.getByWeekAndId(id,4,1);
        scheduleDTO fridayM = scheduleService.getByWeekAndId(id,5,0);
        scheduleDTO fridayA = scheduleService.getByWeekAndId(id,5,1);
        scheduleDTO saturdayM = scheduleService.getByWeekAndId(id,6,0);
        scheduleDTO saturdayA = scheduleService.getByWeekAndId(id,6,1);
        scheduleDTO sundayM = scheduleService.getByWeekAndId(id,0,0);
        scheduleDTO sundayA = scheduleService.getByWeekAndId(id,0,1);
        model.addAttribute("did",doctor.getDid());
        model.addAttribute("mondayM",mondayM);
        model.addAttribute("mondayA",mondayA);
        model.addAttribute("tuesdayA",tuesdayA);
        model.addAttribute("tuesdayM",tuesdayM);
        model.addAttribute("wednesdayM",wednesdayM);
        model.addAttribute("wednesdayA",wednesdayA);
        model.addAttribute("thursdayM",thursdayM);
        model.addAttribute("thursdayA",thursdayA);
        model.addAttribute("fridayM",fridayM);
        model.addAttribute("fridayA",fridayA);
        model.addAttribute("saturdayM",saturdayM);
        model.addAttribute("saturdayA",saturdayA);
        model.addAttribute("sundayM",sundayM);
        model.addAttribute("sundayA",sundayA);
        model.addAttribute("dname",doctor.getDname());
        model.addAttribute("hospitalName",hospital.getHospitalName());
        model.addAttribute("dpmtname",department.getDpmtname());
        model.addAttribute("Did",id);
        model.addAttribute("currentTime",currentTime);
        return "doctorRegister";
    }
}
