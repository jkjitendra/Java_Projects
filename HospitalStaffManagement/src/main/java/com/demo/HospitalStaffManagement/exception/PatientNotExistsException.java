package com.demo.HospitalStaffManagement.exception;

public class PatientNotExistsException extends RuntimeException{

    public PatientNotExistsException(String message) {
        super(message);
    }
}
