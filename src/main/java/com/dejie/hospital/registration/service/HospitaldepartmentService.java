package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.dto.HospitalDepartmentDTO;
import com.dejie.hospital.registration.mapper.Hospital_departmentMapper;
import com.dejie.hospital.registration.model.Hospital_department;
import com.dejie.hospital.registration.model.Hospital_departmentExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitaldepartmentService {
    @Autowired
    private Hospital_departmentMapper hospital_departmentMapper;
    public  void createOrUpdate(Hospital_department hospital_department) {
        if (hospital_department.getId()==null){
            //创建
            hospital_departmentMapper.insert(hospital_department);
        }else {
            //更新
            Hospital_department updateHospital_department= new Hospital_department();
            updateHospital_department.setHospitalId(hospital_department.getHospitalId());
            updateHospital_department.setDpmtid(hospital_department.getDpmtid());
            Hospital_departmentExample example = new Hospital_departmentExample();
            example.createCriteria()
                    .andIdEqualTo(hospital_department.getId());
            hospital_departmentMapper.updateByExampleSelective(updateHospital_department,example);
        }
    }
    public void delect(HospitalDepartmentDTO hospitalDepartmentDTO){
        hospital_departmentMapper.deleteByPrimaryKey(hospitalDepartmentDTO.getId());
    }
    public HospitalDepartmentDTO getById(Integer id){
        Hospital_department hospital_department = hospital_departmentMapper.selectByPrimaryKey(id);
        if (hospital_department==null){
            //throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        HospitalDepartmentDTO hospitalDepartmentDTO = new HospitalDepartmentDTO();
        BeanUtils.copyProperties(hospital_department,hospitalDepartmentDTO);
//        User user = userMapper.selectByPrimaryKey(question.getCreator());
//        questionDTO.setUser(user);
        return  hospitalDepartmentDTO;
    }
}
