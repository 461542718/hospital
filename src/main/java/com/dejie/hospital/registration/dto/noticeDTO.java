package com.dejie.hospital.registration.dto;

import com.dejie.hospital.registration.model.Notice;
import lombok.Data;

@Data
public class noticeDTO {
    private int noticeid;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private int creator;
    private Notice notice;
}
