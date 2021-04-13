package com.dejie.hospital.registration.service;

import com.dejie.hospital.registration.entity.hospital;
import com.dejie.hospital.registration.mapper.HospitalMapper;
import com.dejie.hospital.registration.model.Hospital;
import com.dejie.hospital.registration.repositories.HospitalRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchHospitalService {
    @Autowired
    ElasticsearchRepository template;
    @Autowired
    ElasticsearchRestTemplate restTemplate;
    @Autowired
    HospitalRepository hospitalRepository;
    @Autowired
    HospitalMapper hospitalMapper;


    public List search(String Hname){
        NativeSearchQuery query = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.queryStringQuery(Hname).defaultField("hname"))
                .withPageable(PageRequest.of(0,5))
                .build();
        IndexCoordinates index = restTemplate.getIndexCoordinatesFor(hospital.class);
        //Pageable pageable = PageRequest.of(0,5);
       // List<hospital> hospitalList = hospitalRepository.findByHname(Hname,pageable);
        List<hospital> hospitalList = restTemplate.queryForList(query,hospital.class,index);
        List<Hospital> List = new ArrayList<>();
        for(int i=0;i<hospitalList.size();i++){
            List.add(hospitalMapper.selectByPrimaryKey(hospitalList.get(i).getId()));
        }
        return List;
    }

}
