package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.dto.userinfoDTO;
import com.dejie.hospital.registration.mapper.UserMapper;
import com.dejie.hospital.registration.mapper.UserinfoMapper;
import com.dejie.hospital.registration.model.User;
import com.dejie.hospital.registration.model.UserExample;
import com.dejie.hospital.registration.model.Userinfo;
import com.dejie.hospital.registration.model.UserinfoExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserinfoMapper userinfoMapper;
    public  void createOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andUsernameEqualTo(user.getUsername());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            //创建

            userMapper.insert(user);
        }else {
            //更新
            User dbUser = users.get(0);
            User updateUser = new User();
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria()
                    .andCardnumberEqualTo(dbUser.getCardnumber());
            userMapper.updateByExampleSelective(updateUser, example);
            }
        }
    public  void createOrUpdateInfo(Userinfo userinfo) {
        UserinfoExample userinfoExample = new UserinfoExample();
        userinfoExample.createCriteria()
                .andCardnumberEqualTo(userinfo.getCardnumber());
        List<Userinfo> userinfos = userinfoMapper.selectByExample(userinfoExample);
        if (userinfos.size()==0){
            //创建

            userinfoMapper.insert(userinfo);
        }else {
            //更新
            Userinfo dbUserinfo = userinfos.get(0);
            Userinfo updateUserinfo = new Userinfo();
            updateUserinfo.setCardnumber(userinfo.getCardnumber());
            updateUserinfo.setTel(userinfo.getTel());
            updateUserinfo.setAddress(userinfo.getAddress());
            updateUserinfo.setSex(userinfo.getSex());
            updateUserinfo.setBriefinfo(userinfo.getBriefinfo());
            UserinfoExample example = new UserinfoExample();
            example.createCriteria()
                    .andCardnumberEqualTo(dbUserinfo.getCardnumber());
            userinfoMapper.updateByExampleSelective(updateUserinfo,example);
        }
    }
    public boolean doLogin(User user){
        UserExample userExample=new UserExample();

        userExample.createCriteria()
                .andUsernameEqualTo(user.getUsername())
                .andPasswordEqualTo(user.getPassword());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size()==0){
            //登录失败
            return false;
        }else{
            //登录
            return true;
        }
    }

    public userinfoDTO getByCardnumber(Integer cardnumber) {
        Userinfo userinfo = userinfoMapper.selectByPrimaryKey(cardnumber);
        userinfoDTO userinfoDTO = new userinfoDTO();
        if (userinfo==null){
            //抛异常
            //throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            return userinfoDTO;
        }
        //QuestionDTO questionDTO = new QuestionDTO();

        BeanUtils.copyProperties(userinfo,userinfoDTO);
        User user = userMapper.selectByPrimaryKey(userinfo.getCardnumber());
        userinfoDTO.setUser(user);
        return  userinfoDTO;
    }
    public void uploadphoto(Userinfo userinfo){
        UserinfoExample userinfoExample = new UserinfoExample();
        userinfoExample.createCriteria()
                .andCardnumberEqualTo(userinfo.getCardnumber());
        List<Userinfo> userinfos = userinfoMapper.selectByExample(userinfoExample);
        Userinfo dbUserinfo = userinfos.get(0);
        Userinfo updateUserinfo = new Userinfo();
        updateUserinfo.setCardnumber(userinfo.getCardnumber());
        updateUserinfo.setUphoto(userinfo.getUphoto());
        UserinfoExample example = new UserinfoExample();
        example.createCriteria()
                .andCardnumberEqualTo(dbUserinfo.getCardnumber());
        userinfoMapper.updateByExampleSelective(updateUserinfo,example);
    }
    }

