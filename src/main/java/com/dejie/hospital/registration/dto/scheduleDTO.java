package com.dejie.hospital.registration.dto;

import lombok.Data;

@Data
public class scheduleDTO {
    private int Scid;
    private int week;
    private int hospitalId;
    private int Did;
    private int Dmajor;
    private int registerLimited;
    private String fee;
}
