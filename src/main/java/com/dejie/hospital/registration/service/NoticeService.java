package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.dto.HospitalDepartmentDTO;
import com.dejie.hospital.registration.dto.departmentDTO;
import com.dejie.hospital.registration.dto.noticeDTO;
import com.dejie.hospital.registration.mapper.DepartmentMapper;
import com.dejie.hospital.registration.mapper.Hospital_departmentMapper;
import com.dejie.hospital.registration.mapper.NoticeMapper;
import com.dejie.hospital.registration.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;
    public  void createOrUpdate(Notice notice) {
        if (notice.getNoticeid()==null){
            //创建
            notice.setGmtCreate(System.currentTimeMillis());
            notice.setGmtModified(notice.getGmtCreate());
            noticeMapper.insert(notice);
        }else {
            //更新
            Notice updateNotice=new Notice();
            updateNotice.setCreator(notice.getCreator());
            updateNotice.setTitle(notice.getTitle());
            updateNotice.setDescription(notice.getDescription());
            updateNotice.setGmtModified(System.currentTimeMillis());
           // updateDepartment.setDpmtname(department.getDpmtname());
          //  updateDepartment.setDpmtinfo(department.getDpmtinfo());
            NoticeExample example = new NoticeExample();
            example.createCriteria()
                    .andNoticeidEqualTo(notice.getNoticeid());
            noticeMapper.updateByExampleSelective(updateNotice,example);
        }
    }
    public void delect(noticeDTO notice){
        noticeMapper.deleteByPrimaryKey(notice.getNoticeid());
    }
    public noticeDTO getById(Integer id){
        Notice notice = noticeMapper.selectByPrimaryKey(id);
        if (notice==null){
            //throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        noticeDTO noticeDTO = new noticeDTO();
        BeanUtils.copyProperties(notice,noticeDTO);
//        User user = userMapper.selectByPrimaryKey(question.getCreator());
//        questionDTO.setUser(user);
        return  noticeDTO;
    }
}
