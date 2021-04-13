package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.dto.hospitalDTO;
import com.dejie.hospital.registration.entity.hospital;
import com.dejie.hospital.registration.mapper.DoctorMapper;
import com.dejie.hospital.registration.mapper.HospitalMapper;
import com.dejie.hospital.registration.model.Doctor;
import com.dejie.hospital.registration.model.DoctorExample;
import com.dejie.hospital.registration.model.Hospital;
import com.dejie.hospital.registration.model.HospitalExample;
import com.dejie.hospital.registration.repositories.HospitalRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {
    @Autowired
    private HospitalMapper hospitalMapper;
    @Autowired
    ElasticsearchRepository template;
    @Autowired
    HospitalRepository hospitalRepository;
    public  void createOrUpdate(Hospital hospital) {
        if (hospital.getHospitalId()==null){
            //创建
            hospitalMapper.insert(hospital);
            hospital eshospital= new hospital();
            eshospital.setId(hospital.getHospitalId());
            eshospital.setHaddress(hospital.getHospitalAddr());
            eshospital.setHtel(hospital.getHospitalTel());
            eshospital.setHname(hospital.getHospitalName());
            eshospital.setHlevel(hospital.getHospitalLevel());
            eshospital.setHmajor(hospital.getHospitalMajor());
            eshospital.setHinfo(hospital.getHospitalInfo());
            hospitalRepository.save(eshospital);
        }else {
            //更新
            Hospital updateHospital=new Hospital();
            updateHospital.setHospitalName(hospital.getHospitalName());
            updateHospital.setHospitalAddr(hospital.getHospitalAddr());
            updateHospital.setHospitalTel(hospital.getHospitalTel());
            updateHospital.setHospitalLevel(hospital.getHospitalLevel());
            updateHospital.setHospitalMajor(hospital.getHospitalMajor());
            updateHospital.setHospitalInfo(hospital.getHospitalInfo());
            updateHospital.setPassword(hospital.getPassword());
            HospitalExample example = new HospitalExample();
            example.createCriteria()
                    .andHospitalIdEqualTo(hospital.getHospitalId());
            hospitalMapper.updateByExampleSelective(updateHospital,example);
            hospital eshospital= new hospital();
            eshospital.setId(hospital.getHospitalId());
            eshospital.setHaddress(hospital.getHospitalAddr());
            eshospital.setHtel(hospital.getHospitalTel());
            eshospital.setHname(hospital.getHospitalName());
            eshospital.setHlevel(hospital.getHospitalLevel());
            eshospital.setHmajor(hospital.getHospitalMajor());
            eshospital.setHinfo(hospital.getHospitalInfo());
            hospitalRepository.save(eshospital);
        }
    }
    public  void addToken(Hospital hospital){
        Hospital updateHospital=new Hospital();
        updateHospital.setToken(hospital.getToken());
        HospitalExample example = new HospitalExample();
        example.createCriteria()
                .andHospitalIdEqualTo(hospital.getHospitalId());
        hospitalMapper.updateByExampleSelective(updateHospital,example);
    }
    public void delect(hospitalDTO hospital){
        hospitalMapper.deleteByPrimaryKey(hospital.getHospitalId());
        hospitalRepository.deleteById((long)hospital.getHospitalId());
    }
    public hospitalDTO getById(Integer id){
        Hospital hospital = hospitalMapper.selectByPrimaryKey(id);
        if (hospital==null){
            //throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        hospitalDTO hospitalDTO = new hospitalDTO();
        BeanUtils.copyProperties(hospital,hospitalDTO);
//        User user = userMapper.selectByPrimaryKey(question.getCreator());
//        questionDTO.setUser(user);
        return  hospitalDTO;
    }
    public boolean doLogin(Hospital hospital){
        HospitalExample hospitalExample = new HospitalExample();

        hospitalExample.createCriteria()
                .andHospitalIdEqualTo(hospital.getHospitalId())
                .andPasswordEqualTo(hospital.getPassword());
        List<Hospital> hospitals = hospitalMapper.selectByExample(hospitalExample);
        if (hospitals.size()==0){
            //登录失败
            return false;
        }else{
            //登录
            return true;
        }
    }
    public void Update(Hospital hospital){
        Hospital updateHospital=new Hospital();
        updateHospital.setHospitalName(hospital.getHospitalName());
        updateHospital.setHospitalAddr(hospital.getHospitalAddr());
        updateHospital.setHospitalTel(hospital.getHospitalTel());
        updateHospital.setHospitalLevel(hospital.getHospitalLevel());
        updateHospital.setHospitalMajor(hospital.getHospitalMajor());
        updateHospital.setHospitalInfo(hospital.getHospitalInfo());
        updateHospital.setPassword(hospital.getPassword());
        HospitalExample example = new HospitalExample();
        example.createCriteria()
                .andHospitalIdEqualTo(hospital.getHospitalId());
        hospitalMapper.updateByExampleSelective(updateHospital,example);
        hospital eshospital= new hospital();
        eshospital.setId(hospital.getHospitalId());
        eshospital.setHaddress(hospital.getHospitalAddr());
        eshospital.setHtel(hospital.getHospitalTel());
        eshospital.setHname(hospital.getHospitalName());
        eshospital.setHlevel(hospital.getHospitalLevel());
        eshospital.setHmajor(hospital.getHospitalMajor());
        eshospital.setHinfo(hospital.getHospitalInfo());
        hospitalRepository.save(eshospital);
    }
    public void uploadphoto(Hospital hospital){
        Hospital updateHospital=new Hospital();
        updateHospital.setHospitalPhoto(hospital.getHospitalPhoto());
        HospitalExample example = new HospitalExample();
        example.createCriteria()
                .andHospitalIdEqualTo(hospital.getHospitalId());
        hospitalMapper.updateByExampleSelective(updateHospital,example);
    }
}
