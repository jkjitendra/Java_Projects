package com.demo.HospitalStaffManagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Data
public class PatientRequestBody {

	private String patientName;

	private String patientEmail;

	private Integer patientAge;

	private String patientMobile;

	private String patientRoom;

	private String doctorName;

	private LocalDate patientAdmitDate;
}
