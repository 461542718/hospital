package com.dejie.hospital.registration;

import com.dejie.hospital.registration.mapper.ScheduleMapper;
import com.dejie.hospital.registration.model.Schedule;
import com.dejie.hospital.registration.model.ScheduleExample;
import com.dejie.hospital.registration.service.ScheduleService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableAutoConfiguration( exclude = { SecurityAutoConfiguration.class } )
@ImportResource(locations={"classpath:mykaptcha.xml"})
@MapperScan("com.dejie.hospital.registration.mapper")
@EnableScheduling
public class RegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistrationApplication.class, args);
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }
    @Autowired
    ScheduleService scheduleService;
    @Scheduled(cron ="0 30 8 ? * mon")
    public void updateMonLimitied(){
        scheduleService.updateByWeek(1);
    }
    @Scheduled(cron ="0 30 8 ? * tue")
    public void updateTueLimitied(){
        scheduleService.updateByWeek(2);
    }
    @Scheduled(cron ="0 30 8 ? * wed")
    public void updateWedLimitied(){
        scheduleService.updateByWeek(3);
    }
    @Scheduled(cron ="0 30 8 ? * thu")
    public void updateThuLimitied(){
        scheduleService.updateByWeek(4);
    }
    @Scheduled(cron ="0 30 8 ? * fri")
    public void updateFriLimitied(){
        scheduleService.updateByWeek(5);
    }
    @Scheduled(cron ="0 30 8 ? * sat")
    public void updateSatLimitied(){
        scheduleService.updateByWeek(6);
    }
    @Scheduled(cron ="0 30 8 ? * sun")
    public void updateSunLimitied(){
        scheduleService.updateByWeek(0);
    }

}




