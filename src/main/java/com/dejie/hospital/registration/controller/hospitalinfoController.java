package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.Client.FastDfsClient;
import com.dejie.hospital.registration.dto.hospitalDTO;
import com.dejie.hospital.registration.model.Hospital;
import com.dejie.hospital.registration.repositories.HospitalRepository;
import com.dejie.hospital.registration.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class hospitalinfoController {
    @Autowired
    HospitalService hospitalService;
    @Autowired
    ElasticsearchRepository template;
    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    FastDfsClient fastDfsClient;
    @Value("${picture.url}")
    private String fileServerUrl;
    @GetMapping("/hospitalinfo")
    public String hospitalinfo(){
        return "hospitalinfo";
    }
    @GetMapping("/hospitalinfo/{id}")
    public  String edit(@PathVariable(name = "id") Integer id,
                        Model model){
        //QuestionDTO question = questionService.getById(id);
        hospitalDTO hospital = hospitalService.getById(id);
        model.addAttribute("Hid",hospital.getHospitalId());
        model.addAttribute("Hname",hospital.getHospitalName());
        model.addAttribute("Htel",hospital.getHospitalTel());
        model.addAttribute("Haddress",hospital.getHospitalAddr());
        model.addAttribute("Hlevel",hospital.getHospitalLevel());
        model.addAttribute("Hmajor",hospital.getHospitalMajor());
        model.addAttribute("Hinfo",hospital.getHospitalInfo());
        model.addAttribute("hospitalPhoto",hospital.getHospitalPhoto());
        model.addAttribute("password",hospital.getPassword());
        return  "hospitalinfo";
    }
    @PostMapping("/updatehospitalinfo")
    public String doEditHospitalinfo(
            @RequestParam(value = "Hname",required = false) String Hname,
            @RequestParam(value = "Htel",required = false) String Htel,
            @RequestParam(value = "Haddress",required = false) String Haddr,
            @RequestParam(value = "Hlevel",required = false) String Hlevel,
            @RequestParam(value = "Hmajor",required = false) String Hmajor,
            @RequestParam(value = "Hinfo",required = false) String Hinfo,
            @RequestParam(value = "Hid",required = false) Integer Hid,
            @RequestParam(value = "password",required = false) String password,
            HttpServletRequest request, Model model
    ){

        Hospital hospital = new Hospital();

        hospital.setHospitalId(Hid);
        hospital.setHospitalName(Hname);
        hospital.setHospitalAddr(Haddr);
        hospital.setHospitalTel(Htel);
        hospital.setHospitalLevel(Hlevel);
        hospital.setHospitalMajor(Hmajor);
        hospital.setHospitalInfo(Hinfo);
        hospital.setPassword(password);
        hospitalService.Update(hospital);
        return  "redirect:/Hindex";
    }
    @PostMapping("/HuploadHphoto")
    public String uploadHphoto( @RequestParam(value = "Hid",required = false) Integer Hid,
                                MultipartFile photo,
                                HttpServletRequest request)throws Exception{
        //获取文件名
        String originalFilename = photo.getOriginalFilename();
        //获取文件后缀名（图片格式）
        String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String photoname=null;
        photoname = fastDfsClient.uploadFile(photo.getBytes(), extName);
        Hospital hospital=new Hospital();
        hospital.setHospitalId(Hid);
        hospital.setHospitalPhoto(fileServerUrl+photoname);
        hospitalService.uploadphoto(hospital);
        return "redirect:/hospitalinfo/"+Hid;
    }
}
