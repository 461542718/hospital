package com.dejie.hospital.registration.dto;

import com.dejie.hospital.registration.model.User;
import lombok.Data;

@Data
public class userinfoDTO {
    private int cardnumber;
    private String tel;
    private String address;
    private int sex;
    private String briefinfo;
    private User user;
    private String Uphoto;
}
