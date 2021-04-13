package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.departmentDTO;
import com.dejie.hospital.registration.model.Department;
import com.dejie.hospital.registration.model.Userinfo;
import com.dejie.hospital.registration.service.DepartmentService;
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
public class insertdepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/insertdepartment")
    public String insertdepartment(){
        return "insertdepartment";
    }
    @GetMapping("/insertdepartment/{id}")
    public  String edit(@PathVariable(name = "id") Integer id,
                        Model model){
        //QuestionDTO question = questionService.getById(id);
        departmentDTO department = departmentService.getById(id);
        model.addAttribute("dpmtid",department.getDpmtid());
        model.addAttribute("dpmtname",department.getDpmtname());
        model.addAttribute("dpmtinfo",department.getDpmtinfo());
        return  "insertdepartment";
    }
    @GetMapping("/delectdepartment/{id}")
    public String delect(@PathVariable(name = "id") Integer id,
                         Model model){
        departmentDTO department = departmentService.getById(id);
        departmentService.delect(department);
        return "redirect:/departmentmanage";
    }
    @PostMapping("/insertdepartment")
    public String doEditDepartment(
            @RequestParam(value = "Dpmtname",required = false) String Dpmtname,
            @RequestParam(value = "Dpmtinfo",required = false) String Dpmtinfo,
            @RequestParam(value = "Dpmtid",required = false) Integer Dpmtid,
            HttpServletRequest request, Model model
    ){
        model.addAttribute("Dpmtname",Dpmtname);
        model.addAttribute("Dpmtinfo",Dpmtinfo);
//        User user = new User();
//        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        user = request.getSession().getAttribute("user");

        Department department = new Department();
//        userinfo.setCardnumber((int)request.getSession().getAttribute("cardnumber"));
        department.setDpmtid(Dpmtid);
        department.setDpmtname(Dpmtname);
        department.setDpmtinfo(Dpmtinfo);
        departmentService.createOrUpdate(department);
        return  "redirect:/departmentmanage";
    }
}
