package com.dejie.hospital.registration.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "department",shards = 5)
public class department {
    @Id
    @Field(type = FieldType.Integer,store = true)
    private Integer id;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_syno",searchAnalyzer = "ik_syno")
    private String departmentname;
}
