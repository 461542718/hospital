package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.dto.doctorDTO;
import com.dejie.hospital.registration.mapper.DepartmentMapper;
import com.dejie.hospital.registration.mapper.DoctorMapper;
import com.dejie.hospital.registration.model.Department;
import com.dejie.hospital.registration.model.DepartmentExample;
import com.dejie.hospital.registration.model.Doctor;
import com.dejie.hospital.registration.model.DoctorExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;
    public  void createOrUpdate(Doctor doctor) {
        if (doctor.getDid()==null){
            //创建

            doctorMapper.insert(doctor);
        }else {
            //更新
            Doctor updateDoctor=new Doctor();
            updateDoctor.setDname(doctor.getDname());
            updateDoctor.setDsex(doctor.getDsex());
            updateDoctor.setDtel(doctor.getDtel());
            updateDoctor.setDlevel(doctor.getDlevel());
            updateDoctor.setHospitalId(doctor.getHospitalId());
            updateDoctor.setDmajor(doctor.getDmajor());
            updateDoctor.setDinfo(doctor.getDinfo());
            DoctorExample example = new DoctorExample();
            example.createCriteria()
                    .andDidEqualTo(doctor.getDid());
            doctorMapper.updateByExampleSelective(updateDoctor,example);
        }
    }
    public  void addToken(Doctor doctor){
        Doctor updateDoctor=new Doctor();
        updateDoctor.setToken(doctor.getToken());
        DoctorExample example = new DoctorExample();
        example.createCriteria()
                .andDidEqualTo(doctor.getDid());
        doctorMapper.updateByExampleSelective(updateDoctor,example);
    }
    public void delect(doctorDTO doctor){
        doctorMapper.deleteByPrimaryKey(doctor.getDid());
    }
    public doctorDTO getById(Integer id){
        Doctor doctor = doctorMapper.selectByPrimaryKey(id);
        if (doctor==null){
            //throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }

        doctorDTO doctorDTO = new doctorDTO();
        BeanUtils.copyProperties(doctor,doctorDTO);
//        User user = userMapper.selectByPrimaryKey(question.getCreator());
//        questionDTO.setUser(user);
        return  doctorDTO;
    }
    public List<doctorDTO> getByKey(Integer hospitalId, Integer dpmtid){
        DoctorExample example = new DoctorExample();
        example.createCriteria()
                .andHospitalIdEqualTo(hospitalId)
                .andDmajorEqualTo(dpmtid);
        List<doctorDTO> doctorDTOS = new ArrayList<>();
        List<Doctor> doctors= doctorMapper.selectByExample(example);
        doctors.forEach(item->{
            doctorDTO data = new doctorDTO();
            BeanUtils.copyProperties(item,data);
            doctorDTOS.add(data);
        });
        return doctorDTOS;
    }
    public boolean doLogin(Doctor doctor){
        DoctorExample doctorExample = new DoctorExample();

        doctorExample.createCriteria()
                .andDidEqualTo(doctor.getDid())
                .andPasswordEqualTo(doctor.getPassword());
        List<Doctor> doctors = doctorMapper.selectByExample(doctorExample);
        if (doctors.size()==0){
            //登录失败
            return false;
        }else{
            //登录
            return true;
        }
    }
    public  void Update(Doctor doctor) {
            //更新
            Doctor updateDoctor=new Doctor();
            updateDoctor.setDname(doctor.getDname());
            updateDoctor.setDsex(doctor.getDsex());
            updateDoctor.setDtel(doctor.getDtel());
            updateDoctor.setDlevel(doctor.getDlevel());
            updateDoctor.setHospitalId(doctor.getHospitalId());
            updateDoctor.setDmajor(doctor.getDmajor());
            updateDoctor.setDinfo(doctor.getDinfo());
            DoctorExample example = new DoctorExample();
            example.createCriteria()
                    .andDidEqualTo(doctor.getDid());
            doctorMapper.updateByExampleSelective(updateDoctor,example);

    }
    public void uploadphoto(Doctor doctor){
        Doctor updateDoctor=new Doctor();
        updateDoctor.setDphoto(doctor.getDphoto());
        DoctorExample example = new DoctorExample();
        example.createCriteria()
                .andDidEqualTo(doctor.getDid());
        doctorMapper.updateByExampleSelective(updateDoctor,example);
    }
    public List<Doctor> getByHid(Integer hospitalId){
        DoctorExample example = new DoctorExample();
        example.createCriteria().andHospitalIdEqualTo(hospitalId);
        List<Doctor> List = doctorMapper.selectByExample(example);
        return List;
    }
}
