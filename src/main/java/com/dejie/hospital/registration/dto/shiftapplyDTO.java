package com.dejie.hospital.registration.dto;

import lombok.Data;

@Data
public class shiftapplyDTO {
    private int id;
    private String content;
    private String note;
    private long gmtCreate;
    private long gmtModify;
    private int status;
}
