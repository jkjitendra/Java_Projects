package com.demo.HospitalStaffManagement.dto;

import com.demo.HospitalStaffManagement.constant.enums.Designation;
import lombok.Data;

@Data
public class RegisterRequestBody {

	private String name;

	private String email;

	private String password;

	private Designation designation;

	private String department;

}
