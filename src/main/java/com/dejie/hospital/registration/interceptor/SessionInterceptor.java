package com.dejie.hospital.registration.interceptor;

import com.dejie.hospital.registration.mapper.DoctorMapper;
import com.dejie.hospital.registration.mapper.HospitalMapper;
import com.dejie.hospital.registration.mapper.ManagerMapper;
import com.dejie.hospital.registration.mapper.UserMapper;
import com.dejie.hospital.registration.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by codedrinker on 2019/5/16.
 */
@Service
public class SessionInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private DoctorMapper doctorMapper;
    @Autowired
    private HospitalMapper hospitalMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0)
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    UserExample userExample = new UserExample();
                    userExample.createCriteria()
                            .andTokenEqualTo(token);
                    List<User> users = userMapper.selectByExample(userExample);
                    ManagerExample managerExample = new ManagerExample();
                    managerExample.createCriteria()
                            .andTokenEqualTo(token);
                    List<Manager> managers=managerMapper.selectByExample(managerExample);
                    DoctorExample doctorExample = new DoctorExample();
                    doctorExample.createCriteria()
                            .andTokenEqualTo(token);
                    List<Doctor> doctors=doctorMapper.selectByExample(doctorExample);
                    HospitalExample hospitalExample = new HospitalExample();
                    hospitalExample.createCriteria()
                            .andTokenEqualTo(token);
                    List<Hospital> hospitals=hospitalMapper.selectByExample(hospitalExample);

                    if (users.size() != 0) {
                        HttpSession session = request.getSession();
                        session.setAttribute("user", users.get(0));
                        session.setAttribute("cardnumber",users.get(0).getCardnumber());
                    }
                    if(managers.size()!= 0){
                        HttpSession session = request.getSession();
                        session.setAttribute("manager",managers.get(0));
                        session.setAttribute("Mnumber",managers.get(0).getMnumber());
                    }
                    if(doctors.size()!= 0){
                        HttpSession session = request.getSession();
                        session.setAttribute("doctor",doctors.get(0));
                        session.setAttribute("Did",doctors.get(0).getDid());
                    }
                    if(hospitals.size()!= 0){
                        HttpSession session = request.getSession();
                        session.setAttribute("hospital",hospitals.get(0));
                        session.setAttribute("hospitalId",hospitals.get(0).getHospitalId());
                    }
                    break;
                }
            }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {

    }
}
