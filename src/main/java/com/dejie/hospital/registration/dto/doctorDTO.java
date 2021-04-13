package com.dejie.hospital.registration.dto;

import com.dejie.hospital.registration.model.Doctor;
import lombok.Data;

@Data
public class doctorDTO {
    private Integer Did;
    private String Dname;
    private Integer Dsex;
    private String Dtel;
    private Integer Dlevel;
    private String Lname;
    private Integer Dmajor;
    private String dpmtname;
    private String Dphoto;
    private String Dinfo;
    private String Password;
    private Integer hospitalId;
    private Doctor doctor;
}
