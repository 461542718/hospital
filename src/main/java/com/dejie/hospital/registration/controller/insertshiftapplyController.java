package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.DoctorMapper;
import com.dejie.hospital.registration.mapper.ShiftapplyMapper;
import com.dejie.hospital.registration.model.Shiftapply;
import com.dejie.hospital.registration.service.ShiftapplyService;
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
public class insertshiftapplyController {
    @Autowired
    ShiftapplyService shiftapplyService;
    @Autowired
    ShiftapplyMapper shiftapplyMapper;
    @Autowired
    DoctorMapper doctorMapper;
    @GetMapping("/insertshiftapply")
    public String insertdepartment(){
        return "insertshiftapply";
    }
    @GetMapping("/insertshiftapply/{id}")
    public  String edit(@PathVariable(name = "id") Integer id,
                        Model model){
        //QuestionDTO question = questionService.getById(id);
        Shiftapply shiftapply = shiftapplyMapper.selectByPrimaryKey(id);
        model.addAttribute("id",shiftapply.getId());
        model.addAttribute("content",shiftapply.getContent());
        return  "insertshiftapply";
    }
    @GetMapping("/deleteshiftapply/{id}")
    public String deleteshiftapply(@PathVariable(name = "id") Integer id){
        Shiftapply shiftapply = new Shiftapply();
        shiftapply=shiftapplyMapper.selectByPrimaryKey(id);
        shiftapplyService.delete(shiftapply);
        return  "redirect:/shiftapplymanage";
    }
    @PostMapping("/insertshiftapply")
    public String doEditShiftapply(
            @RequestParam(value = "content",required = false) String content,
            @RequestParam(value = "id",required = false) Integer id,
            HttpServletRequest request, Model model){
        int Did = new Integer(0);
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getSession().getAttribute("Did")!=null){
            Did=(int)request.getSession().getAttribute("Did");
        }
        Shiftapply shiftapply = new Shiftapply();
        shiftapply.setId(id);
        shiftapply.setDid(Did);
        shiftapply.setContent(content);
        shiftapply.setStatus(0);
        shiftapplyService.createOrUpdate(shiftapply);
        return  "redirect:/shiftapplymanage";
    }

}
