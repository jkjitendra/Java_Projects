package com.demo.HospitalStaffManagement.exception;

public class StaffNotExistsException  extends RuntimeException{

    public StaffNotExistsException(String message) {
        super(message);
    }
}
