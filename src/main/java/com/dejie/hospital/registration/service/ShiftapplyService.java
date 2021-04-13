package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.mapper.ShiftapplyMapper;
import com.dejie.hospital.registration.model.Shiftapply;
import com.dejie.hospital.registration.model.ShiftapplyExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShiftapplyService {
    @Autowired
    ShiftapplyMapper shiftapplyMapper;
    public void createOrUpdate(Shiftapply shiftapply){
        if(shiftapply.getId()==null){
            shiftapply.setGmtCreate(System.currentTimeMillis());
            shiftapplyMapper.insert(shiftapply);
        }else{
            Shiftapply updateshiftapply=new Shiftapply();
            updateshiftapply.setGmtCreate(System.currentTimeMillis());
            updateshiftapply.setContent(shiftapply.getContent());
            ShiftapplyExample example = new ShiftapplyExample();
            example.createCriteria().andIdEqualTo(shiftapply.getId());
            shiftapplyMapper.updateByExampleSelective(updateshiftapply,example);
        }
    }
    public void delete(Shiftapply shiftapply){
        Shiftapply updateshiftapply=new Shiftapply();
        updateshiftapply.setStatus(1);
        ShiftapplyExample example = new ShiftapplyExample();
        example.createCriteria().andIdEqualTo(shiftapply.getId());
        shiftapplyMapper.updateByExampleSelective(updateshiftapply,example);
    }
    public void approve(Shiftapply shiftapply){
        Shiftapply updateshiftapply=new Shiftapply();
        updateshiftapply.setStatus(2);
        updateshiftapply.setGmtModify(System.currentTimeMillis());
        updateshiftapply.setNote("已批准并处理申请");
        ShiftapplyExample example = new ShiftapplyExample();
        example.createCriteria().andIdEqualTo(shiftapply.getId());
        shiftapplyMapper.updateByExampleSelective(updateshiftapply,example);
    }
    public void reject(Shiftapply shiftapply){
        Shiftapply updateshiftapply=new Shiftapply();
        updateshiftapply.setStatus(3);
        updateshiftapply.setGmtModify(System.currentTimeMillis());
        updateshiftapply.setNote(shiftapply.getNote());
        ShiftapplyExample example = new ShiftapplyExample();
        example.createCriteria().andIdEqualTo(shiftapply.getId());
        shiftapplyMapper.updateByExampleSelective(updateshiftapply,example);
    }
}
