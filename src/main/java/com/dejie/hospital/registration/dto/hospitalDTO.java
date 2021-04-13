package com.dejie.hospital.registration.dto;

import com.dejie.hospital.registration.model.Hospital;
import lombok.Data;

@Data
public class hospitalDTO {
    private Integer hospitalId;
    private String hospitalName;
    private String hospitalAddr;
    private String hospitalTel;
    private String hospitalLevel;
    private String hospitalMajor;
    private String hospitalPhoto;
    private String hospitalInfo;
    private String password;
    private Hospital hospital;
}
