package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.dto.scheduleDTO;
import com.dejie.hospital.registration.mapper.ScheduleMapper;
import com.dejie.hospital.registration.model.Schedule;
import com.dejie.hospital.registration.model.ScheduleExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    ScheduleMapper scheduleMapper;
    public scheduleDTO getByWeekAndId(Integer id, Integer week,Integer time) {
        ScheduleExample example = new ScheduleExample();
        example.createCriteria()
                .andDidEqualTo(id)
                .andWeekEqualTo(week)
                .andTimeEqualTo(time);
        List<Schedule> schedules = scheduleMapper.selectByExample(example);
        if (schedules.size()!=0){
        Schedule schedule = schedules.get(0);
        scheduleDTO scheduleDTO = new scheduleDTO();
        BeanUtils.copyProperties(schedule,scheduleDTO);
        return scheduleDTO;}
        scheduleDTO scheduleDTO = new scheduleDTO();
        return scheduleDTO;
    }
    public void delect(scheduleDTO schedule){
        scheduleMapper.deleteByPrimaryKey(schedule.getScid());
    }
    public scheduleDTO getById(Integer id){
        scheduleDTO scheduleDTO = new scheduleDTO();
        if(id!=0){
        Schedule schedule = scheduleMapper.selectByPrimaryKey(id);
            if (schedule==null){
                //throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
            BeanUtils.copyProperties(schedule,scheduleDTO);}
//        User user = userMapper.selectByPrimaryKey(question.getCreator());
//        questionDTO.setUser(user);
        return  scheduleDTO;
    }
    public  void createOrUpdate(Schedule schedule) {
        if (schedule.getScid()==0){
            //创建

            scheduleMapper.insert(schedule);
        }else {
            //更新
            Schedule updateSchedule=new Schedule();
            updateSchedule.setRegisterLimited(schedule.getRegisterLimited());

            ScheduleExample example = new ScheduleExample();
            example.createCriteria()
                    .andScidEqualTo(schedule.getScid());
            scheduleMapper.updateByExampleSelective(updateSchedule,example);
        }
    }
    public void updateByWeek(int week){
        Schedule updateSchedule= new Schedule();
        updateSchedule.setRegisterLimited(30);
        ScheduleExample example = new ScheduleExample();
        example.createCriteria()
                .andWeekEqualTo(week);
        scheduleMapper.updateByExampleSelective(updateSchedule,example);
    }
}
