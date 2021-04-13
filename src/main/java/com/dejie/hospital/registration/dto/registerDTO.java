package com.dejie.hospital.registration.dto;

import lombok.Data;

@Data
public class registerDTO {
    private int Rid;
    private int Cardnumber;
    private String hospitalName;
    private String Dpmtname;
    private String Dname;
    private String truename;
    private long visitDate;
    private long registerDate;
    private long currentDate;
    private int time;
    private int Rstatus;
}
