package com.demo.HospitalStaffManagement.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwtToken;

    public AuthenticationResponse(String jwtToken) {

        this.jwtToken = jwtToken;
    }
}
