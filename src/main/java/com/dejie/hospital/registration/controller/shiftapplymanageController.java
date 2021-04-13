package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.ShiftapplyMapper;

import com.dejie.hospital.registration.model.Shiftapply;
import com.dejie.hospital.registration.model.ShiftapplyExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class shiftapplymanageController {
    @Autowired
    ShiftapplyMapper shiftapplyMapper;
    @GetMapping("/shiftapplymanage")
    public String shiftapplymanage(@RequestParam(defaultValue = "1") int pageNum,
                                   @RequestParam(defaultValue = "10") int pageSize,
                                   HttpServletRequest request, Model model){
        int Did = new Integer(0);
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getSession().getAttribute("Did")!=null){
            Did=(int)request.getSession().getAttribute("Did");
        }
        PageHelper.startPage(pageNum,pageSize);
        ShiftapplyExample example = new ShiftapplyExample();
        example.createCriteria().andDidEqualTo(Did);
        List<Shiftapply> shiftapplies = shiftapplyMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(shiftapplies);
        model.addAttribute("pageInfo",pageInfo);
        return "shiftapplymanage";
    }
}
