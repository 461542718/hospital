package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.doctorDTO;
import com.dejie.hospital.registration.model.Doctor;
import com.dejie.hospital.registration.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class insertHdoctorController {
    @Autowired
    DoctorService doctorService;
    @GetMapping("/insertHdoctor")
    public String insertHdoctor(){
        return "insertHdoctor";
    }
    @GetMapping("/insertHdoctor/{id}")
    public String Edit(@PathVariable(name = "id") Integer id, HttpServletRequest request, Model model){
        doctorDTO doctor = doctorService.getById(id);
        model.addAttribute("Did",doctor.getDid());
        model.addAttribute("Dname",doctor.getDname());
        model.addAttribute("Dsex",doctor.getDsex());
        model.addAttribute("Dtel",doctor.getDtel());
        model.addAttribute("Dlevel",doctor.getDlevel());
        model.addAttribute("Hid",doctor.getHospitalId());
        model.addAttribute("Dmajor",doctor.getDmajor());
        model.addAttribute("Dinfo",doctor.getDinfo());
        model.addAttribute("password",doctor.getPassword());
        return  "insertHdoctor";
    }
    @GetMapping("/delectHdoctor/{id}")
    public String delect(@PathVariable(name = "id") Integer id,
                         Model model){
        doctorDTO doctor = doctorService.getById(id);
        doctorService.delect(doctor);
        return "redirect:/Hdoctormanage";
    }
    @PostMapping("/insertHdoctor")
    public String doEditDoctor(
            @RequestParam(value = "Dname",required = false) String Dname,
            @RequestParam(value = "Dsex",required = false) Integer Dsex,
            @RequestParam(value = "Dtel",required = false) String Dtel,
            @RequestParam(value = "Dlevel",required = false) Integer Dlevel,
            @RequestParam(value = "Dhospital",required = false) Integer Dhospital,
            @RequestParam(value = "Dmajor",required = false) Integer Dmajor,
            @RequestParam(value = "Dinfo",required = false) String Dinfo,
            @RequestParam(value = "Did",required = false) Integer Did,
            @RequestParam(value = "password",required = false) String password,
            HttpServletRequest request, Model model
    ){
        int hospitalId = new Integer(0);
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getSession().getAttribute("hospitalId")!=null){
            hospitalId=(int)request.getSession().getAttribute("hospitalId");}
        Doctor doctor = new Doctor();
        doctor.setDname(Dname);
        doctor.setDsex(Dsex);
        doctor.setDtel(Dtel);
        doctor.setDlevel(Dlevel);
        doctor.setHospitalId(hospitalId);
        doctor.setDmajor(Dmajor);
        doctor.setDinfo(Dinfo);
        doctor.setDid(Did);
        doctor.setPassword(password);
        doctorService.createOrUpdate(doctor);
        return  "redirect:/Hdoctormanage";
    }
}
