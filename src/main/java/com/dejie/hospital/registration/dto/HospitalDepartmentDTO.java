package com.dejie.hospital.registration.dto;

import com.dejie.hospital.registration.model.Hospital_department;
import lombok.Data;

@Data
public class HospitalDepartmentDTO {
    private Integer id;
    private Integer hospitalId;
    private Integer Dpmtid;
    private Hospital_department hospital_department;
}
