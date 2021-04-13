package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.mapper.ManagerMapper;
import com.dejie.hospital.registration.model.Manager;
import com.dejie.hospital.registration.model.ManagerExample;
import com.dejie.hospital.registration.model.User;
import com.dejie.hospital.registration.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerService {
    @Autowired
    private ManagerMapper managerMapper;
    public  void createOrUpdate(Manager manager) {
        ManagerExample managerExample = new ManagerExample();
        managerExample.createCriteria()
                .andUsernameEqualTo(manager.getUsername());
        List<Manager> managers = managerMapper.selectByExample(managerExample);
        if (managers.size()==0){
            //创建

            managerMapper.insert(manager);
        }else {
            //更新
            Manager dbManager = managers.get(0);
            Manager updateManager = new Manager();
            updateManager.setToken(manager.getToken());
            ManagerExample example = new ManagerExample();
            example.createCriteria()
                    .andMnumberEqualTo(dbManager.getMnumber());
            managerMapper.updateByExampleSelective(updateManager, example);
        }
    }


    public boolean doLogin(Manager manager){
        ManagerExample managerExample=new ManagerExample();

        managerExample.createCriteria()
                .andUsernameEqualTo(manager.getUsername())
                .andPasswordEqualTo(manager.getPassword());
        List<Manager> managers = managerMapper.selectByExample(managerExample);
        if (managers.size()==0){
            //登录失败
            return false;
        }else{
            //登录
            return true;
        }
    }


}
