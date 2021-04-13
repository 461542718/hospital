package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.Client.FastDfsClient;
import com.dejie.hospital.registration.dto.doctorDTO;
import com.dejie.hospital.registration.model.Department;
import com.dejie.hospital.registration.model.Doctor;
import com.dejie.hospital.registration.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class insertdoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    FastDfsClient fastDfsClient;
    @Value("${picture.url}")
    private String fileServerUrl;
    @GetMapping("/insertdoctor")
    public String insertdoctor(){
        return "insertdoctor";
    }
    @GetMapping("/insertdoctor/{id}")
    public  String edit(@PathVariable(name = "id") Integer id,
                        Model model){
        //QuestionDTO question = questionService.getById(id);
        doctorDTO doctor = doctorService.getById(id);
        model.addAttribute("Did",doctor.getDid());
        model.addAttribute("Dname",doctor.getDname());
        model.addAttribute("Dsex",doctor.getDsex());
        model.addAttribute("Dtel",doctor.getDtel());
        model.addAttribute("Dlevel",doctor.getDlevel());
        model.addAttribute("Hid",doctor.getHospitalId());
        model.addAttribute("Dmajor",doctor.getDmajor());
        model.addAttribute("Dinfo",doctor.getDinfo());
        model.addAttribute("Dphoto",doctor.getDphoto());
        model.addAttribute("password",doctor.getPassword());
        return  "insertdoctor";
    }
    @GetMapping("/delectdoctor/{id}")
    public String delect(@PathVariable(name = "id") Integer id,
                         Model model){
        doctorDTO doctor = doctorService.getById(id);
        doctorService.delect(doctor);
        return "redirect:/doctormanage";
    }
    @PostMapping("/insertdoctor")
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

        Doctor doctor = new Doctor();
        doctor.setDname(Dname);
        doctor.setDsex(Dsex);
        doctor.setDtel(Dtel);
        doctor.setDlevel(Dlevel);
        doctor.setHospitalId(Dhospital);
        doctor.setDmajor(Dmajor);
        doctor.setDinfo(Dinfo);
        doctor.setDid(Did);
        doctor.setPassword(password);
        doctorService.createOrUpdate(doctor);
        return  "redirect:/doctormanage";
    }
    @PostMapping("/uploadDphoto")
    public String uploadHphoto( @RequestParam(value = "Did",required = false) Integer Did,
                                MultipartFile photo,
                                HttpServletRequest request)throws Exception{
        //获取文件名
        String originalFilename = photo.getOriginalFilename();
        //获取文件后缀名（图片格式）
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String photoname=null;
        photoname = fastDfsClient.uploadFile(photo.getBytes(), extName);
        Doctor doctor=new Doctor();
        doctor.setDid(Did);
        doctor.setDphoto(fileServerUrl+photoname);
        doctorService.uploadphoto(doctor);
        return "redirect:/insertdoctor/"+Did;
    }
}
