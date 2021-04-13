package com.dejie.hospital.registration.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@Document(indexName = "hospital",shards = 5)
public class hospital {
    @Id
    @Field(type = FieldType.Integer,store = true)
    private Integer id;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_max_word")
    private String hname;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_max_word")
    private String haddress;
    @Field(type = FieldType.Text,store = true)
    private String htel;
    @Field(type = FieldType.Text,store = true)
    private String hlevel;
    @Field(type = FieldType.Text,store = true)
    private String hmajor;
    @Field(type = FieldType.Text,store = true,analyzer = "ik_max_word")
    private String hinfo;
//    public hospital(Long id, Integer Hid,String Haddress, String Hname,String Htel,
//                    String Hlevel,String Hmajor,String Hinfo){
//        this.id=id;
//        this.Hid=Hid;
//        this.Haddress=Haddress;
//        this.Hname=Hname;
//        this.Htel=Htel;
//        this.Hlevel=Hlevel;
//        this.Hmajor=Hmajor;
//        this.Hinfo=Hinfo;
//    }
}
