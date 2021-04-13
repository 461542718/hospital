package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.ShiftapplyExtMapper;
import com.dejie.hospital.registration.mapper.ShiftapplyMapper;
import com.dejie.hospital.registration.model.Shiftapply;
import com.dejie.hospital.registration.service.ShiftapplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MapplymanageController {
    @Autowired
    ShiftapplyExtMapper shiftapplyExtMapper;
    @Autowired
    ShiftapplyMapper shiftapplyMapper;
    @Autowired
    ShiftapplyService shiftapplyService;
    @GetMapping("/Mapplymanage")
    public String Mapplymanage(@RequestParam(defaultValue = "1") int pageNum,
                             @RequestParam(defaultValue = "10") int pageSize,
                             Model model){
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(shiftapplyExtMapper.selectAll());
        model.addAttribute("pageInfo",pageInfo);
        return "Mapplymanage";
    }
    @GetMapping("/approve/{id}")
    private String approve(@PathVariable(name = "id") Integer id,
                           Model model){
        Shiftapply shiftapply = new Shiftapply();
        shiftapply=shiftapplyMapper.selectByPrimaryKey(id);
        shiftapplyService.approve(shiftapply);
        return  "redirect:/Mapplymanage";
    }
    @PostMapping("/reject")
    private String reject(@RequestParam(value = "note",required = false) String note,
                          @RequestParam(value = "id",required = false) Integer id,
                          HttpServletRequest request, Model model){
        Shiftapply shiftapply = new Shiftapply();
        shiftapply=shiftapplyMapper.selectByPrimaryKey(id);
        shiftapply.setNote(note);
        shiftapplyService.reject(shiftapply);
        return  "redirect:/Mapplymanage";
    }
}
