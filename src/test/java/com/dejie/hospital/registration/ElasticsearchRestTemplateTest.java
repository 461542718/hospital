package com.dejie.hospital.registration;

import com.dejie.hospital.registration.entity.department;
import com.dejie.hospital.registration.entity.hospital;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author faith.huan 2019-10-20 11:13
 */
@SpringBootTest
@RunWith(SpringRunner.class)

class ElasticsearchRestTemplateTest {

    @Autowired
    private ElasticsearchRestTemplate template;

    @Test
    public  void test02(){
     template.createIndex(department.class);
     template.putMapping(department.class);
    }
}

