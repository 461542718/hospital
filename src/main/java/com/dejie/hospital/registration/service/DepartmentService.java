package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.dto.HospitalDepartmentDTO;
import com.dejie.hospital.registration.dto.departmentDTO;
import com.dejie.hospital.registration.entity.department;
import com.dejie.hospital.registration.mapper.DepartmentMapper;
import com.dejie.hospital.registration.mapper.Hospital_departmentMapper;
import com.dejie.hospital.registration.model.*;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {
    @Autowired
    ElasticsearchRepository template;
    @Autowired
    ElasticsearchRestTemplate restTemplate;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    Hospital_departmentMapper hospital_departmentMapper;
    public  void createOrUpdate(Department department) {
        if (department.getDpmtid()==null){
            //创建

            departmentMapper.insert(department);
        }else {
            //更新
            Department updateDepartment=new Department();
            updateDepartment.setDpmtname(department.getDpmtname());
            updateDepartment.setDpmtinfo(department.getDpmtinfo());
            DepartmentExample example = new DepartmentExample();
            example.createCriteria()
                    .andDpmtidEqualTo(department.getDpmtid());
            departmentMapper.updateByExampleSelective(updateDepartment,example);
        }
    }
    public void delect(departmentDTO department){
        departmentMapper.deleteByPrimaryKey(department.getDpmtid());
    }
    public departmentDTO getById(Integer id){
        Department department = departmentMapper.selectByPrimaryKey(id);
        if (department==null){
            //throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        departmentDTO departmentDTO = new departmentDTO();
        BeanUtils.copyProperties(department,departmentDTO);
//        User user = userMapper.selectByPrimaryKey(question.getCreator());
//        questionDTO.setUser(user);
        return  departmentDTO;
    }
    public List<HospitalDepartmentDTO> getByHid(Integer Hid){
        Hospital_departmentExample example = new Hospital_departmentExample();
        example.createCriteria()
                .andHospitalIdEqualTo(Hid);
        List<Hospital_department> hospital_department = hospital_departmentMapper.selectByExample(example);
        if (hospital_department==null){
            //报错
        }
        List<HospitalDepartmentDTO> hospitalDepartmentDTOS = new ArrayList<>();
        hospital_department.forEach(item->{
            HospitalDepartmentDTO data = new HospitalDepartmentDTO();
            BeanUtils.copyProperties(item,data);
            hospitalDepartmentDTOS.add(data);
        });
        return hospitalDepartmentDTOS;
    }
    public List search(String briefinfo){
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(briefinfo).defaultField("departmentname"))
                .withPageable(PageRequest.of(0,5))
                .build();
        IndexCoordinates index = restTemplate.getIndexCoordinatesFor(department.class);
        //Pageable pageable = PageRequest.of(0,5);
        // List<hospital> hospitalList = hospitalRepository.findByHname(Hname,pageable);
        List<department> departmentList = restTemplate.queryForList(query,department.class,index);
        List<department> List = new ArrayList<>();
        if (departmentList.size()>3){
        for(int i=0;i<3;i++){
            List.add(departmentList.get(i));
        }}
        else {
            for(int i=0;i<departmentList.size();i++){
                List.add(departmentList.get(i));
            }}
        return List;
    }

}
