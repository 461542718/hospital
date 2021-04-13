package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.noticeDTO;
import com.dejie.hospital.registration.model.Manager;
import com.dejie.hospital.registration.model.Notice;
import com.dejie.hospital.registration.service.NoticeService;
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
public class insertnoticeController {
    @Autowired
    NoticeService noticeService;
    @GetMapping("/insertnotice")
    public String insertdepartment(){
        return "insertnotice";
    }
    @GetMapping("/insertnotice/{id}")
    public  String edit(@PathVariable(name = "id") Integer id,
                        Model model){
        //QuestionDTO question = questionService.getById(id);
        noticeDTO notice= noticeService.getById(id);
        model.addAttribute("noticeid",notice.getNoticeid());
        model.addAttribute("title",notice.getTitle());
        model.addAttribute("description",notice.getDescription());
        return  "insertnotice";
    }
    @GetMapping("/delectnotice/{id}")
    public String delect(@PathVariable(name = "id") Integer id,
                         Model model){
        noticeDTO notice = noticeService.getById(id);
        noticeService.delect(notice);
        return "redirect:/noticemanage";
    }
    @PostMapping("/insertnotice")
    public String doEditNotice(
            @RequestParam(value = "Title",required = false) String Title,
            @RequestParam(value = "Description",required = false) String Description,
            @RequestParam(value = "Noticeid",required = false) Integer id,
            HttpServletRequest request, Model model
    ){
        model.addAttribute("title",Title);
        model.addAttribute("description",Description);
//        User user = new User();
//        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        user = request.getSession().getAttribute("user");
        Manager manager = (Manager) request.getSession().getAttribute("manager");
        Notice notice = new Notice();
//        userinfo.setCardnumber((int)request.getSession().getAttribute("cardnumber"));
        notice.setCreator(manager.getMnumber());
        notice.setNoticeid(id);
        notice.setTitle(Title);
        notice.setDescription(Description);
        noticeService.createOrUpdate(notice);
        return  "redirect:/shiftapplymanage";
    }
}
