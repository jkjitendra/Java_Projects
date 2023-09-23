package com.demo.HospitalStaffManagement.dto;


import com.demo.HospitalStaffManagement.constant.enums.Designation;
import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;
    private Designation designation;
}
