package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.mapper.ShiftapplyExtMapper;
import com.dejie.hospital.registration.mapper.ShiftapplyMapper;
import com.dejie.hospital.registration.model.Doctor;
import com.dejie.hospital.registration.model.Shiftapply;
import com.dejie.hospital.registration.model.ShiftapplyExample;
import com.dejie.hospital.registration.service.DoctorService;
import com.dejie.hospital.registration.service.ShiftapplyService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.list.SynchronizedList;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class HapplyController {
    @Autowired
    ShiftapplyMapper shiftapplyMapper;
    @Autowired
    ShiftapplyExtMapper shiftapplyExtMapper;
    @Autowired
    ShiftapplyService shiftapplyService;
    @Autowired
    DoctorService doctorService;
    @GetMapping("/Happlymanage")
    public String Happlymanage(@RequestParam(defaultValue = "1") int pageNum,
                               @RequestParam(defaultValue = "10") int pageSize,
                               HttpServletRequest request,Model model){
        int hospitalId = new Integer(0);
        request=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if(request.getSession().getAttribute("hospitalId")!=null){
            hospitalId=(int)request.getSession().getAttribute("hospitalId");}
        List<Doctor> doctorList = doctorService.getByHid(hospitalId);
        List<Shiftapply> shiftapplyList = new ArrayList<>();
        for(int i=0;i<doctorList.size();i++){
            ShiftapplyExample example = new ShiftapplyExample();
            example.createCriteria().andDidEqualTo(doctorList.get(i).getDid());
            List<Shiftapply> List = shiftapplyMapper.selectByExample(example);
            for(int j=0;j<List.size();j++){
                shiftapplyList.add(List.get(j));
            }
        }
        PageHelper.startPage(pageNum,pageSize);
        PageInfo pageInfo = new PageInfo(shiftapplyList);
        model.addAttribute("pageInfo",pageInfo);
        return "Happlymanage";
    }
    @GetMapping("/Happrove/{id}")
    private String Happrove(@PathVariable(name = "id") Integer id,
                           Model model){
        Shiftapply shiftapply = new Shiftapply();
        shiftapply=shiftapplyMapper.selectByPrimaryKey(id);
        shiftapplyService.approve(shiftapply);
        return  "redirect:/Happlymanage";
    }
    @PostMapping("/Hreject")
    private String Hreject(@RequestParam(value = "note",required = false) String note,
                          @RequestParam(value = "id",required = false) Integer id,
                          HttpServletRequest request, Model model){
        Shiftapply shiftapply = new Shiftapply();
        shiftapply=shiftapplyMapper.selectByPrimaryKey(id);
        shiftapply.setNote(note);
        shiftapplyService.reject(shiftapply);
        return  "redirect:/Happlymanage";
    }
}
