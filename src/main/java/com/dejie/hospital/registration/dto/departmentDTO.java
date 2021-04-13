package com.dejie.hospital.registration.dto;

import com.dejie.hospital.registration.model.Department;
import lombok.Data;

@Data
public class departmentDTO {
    private Integer Dpmtid;
    private String Dpmtname;
    private String Dpmtinfo;
    private Department department;
}
