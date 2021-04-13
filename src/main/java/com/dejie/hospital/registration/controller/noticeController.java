package com.dejie.hospital.registration.controller;

import com.dejie.hospital.registration.dto.noticeDTO;
import com.dejie.hospital.registration.mapper.NoticeMapper;
import com.dejie.hospital.registration.model.Notice;
import com.dejie.hospital.registration.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class noticeController {
    @Autowired
    NoticeService noticeService;
    @GetMapping("/notice/{id}")
    public String notice(@PathVariable(name = "id") Integer id,
                           Model model){
        noticeDTO noticeDTO = noticeService.getById(id);
        model.addAttribute("notice",noticeDTO);
        return "notice";
    }
}
