package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.mapper.RegisterMapper;
import com.dejie.hospital.registration.mapper.ScheduleExtMapper;
import com.dejie.hospital.registration.mapper.ScheduleMapper;
import com.dejie.hospital.registration.model.Register;
import com.dejie.hospital.registration.model.RegisterExample;
import com.dejie.hospital.registration.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    RegisterMapper registerMapper;
    @Autowired
    ScheduleMapper scheduleMapper;
    @Autowired
    ScheduleExtMapper scheduleExtMapper;
    public void CreateRegister(Register register){
        RegisterExample example = new RegisterExample();
        example.createCriteria()
                .andCardnumberEqualTo(register.getCardnumber())
                .andVisitdateGreaterThan(System.currentTimeMillis())
                .andRstatusEqualTo(0);
        if(registerMapper.selectByExample(example).isEmpty()){
        scheduleExtMapper.decRegisterLimited(scheduleMapper.selectByPrimaryKey(register.getScid()).getScid());
        registerMapper.insert(register);}
        else {
            //警告并返回首页
        }
    }
    public void delete(Register register){
        Register updateregister = new Register();
        updateregister.setRstatus(1);
        RegisterExample example = new RegisterExample();
        example.createCriteria()
                .andRidEqualTo(register.getRid());
        registerMapper.updateByExampleSelective(updateregister,example);
    }
}
