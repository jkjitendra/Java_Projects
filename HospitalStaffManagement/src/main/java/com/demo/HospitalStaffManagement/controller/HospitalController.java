package com.demo.HospitalStaffManagement.controller;


import com.demo.HospitalStaffManagement.dto.PatientRequestBody;
import com.demo.HospitalStaffManagement.entity.Patient;
import com.demo.HospitalStaffManagement.exception.PatientNotExistsException;
import com.demo.HospitalStaffManagement.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @PostMapping("/admit")
    public ResponseEntity<Object> addPatient(@RequestHeader("Authorization")String auth, @RequestBody PatientRequestBody patientRequestBody) {

        String response = hospitalService.admitPatient(patientRequestBody).toString();
        if (response != null) {
            return new ResponseEntity<>("Patient admitted successfully", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Patient already exists", HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/patients")
    public ResponseEntity<Object> getAllAdmittedPatients(@RequestHeader("Authorization")String auth) {

        try {
            List<HashMap<String, Object>> response = hospitalService.getAllAdmittedPatients();
            return ResponseEntity.ok(response);
        } catch (PatientNotExistsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/patient/{email}")
    public ResponseEntity<Object> getPatientByEmail(@RequestHeader("Authorization")String auth, @PathVariable("email") String email) {

        try {
            HashMap<String, Object> patient = hospitalService.getPatientByEmail(email);
            return ResponseEntity.ok(patient);
        }catch(PatientNotExistsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/update/patient")
    public ResponseEntity<String> updatePatient(@RequestHeader("Authorization")String auth , @RequestBody Patient patient){

        try {
            String response = hospitalService.updatePatient(patient);
            return ResponseEntity.ok(response);
        } catch (PatientNotExistsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }

    }

    @PutMapping("/discharge/patient/{email}")
    public ResponseEntity<Object> dischargePatient(@RequestHeader("Authorization")String auth ,@PathVariable("email") String email) {

        try {
            String response = hospitalService.dischargePatient(email);
            return ResponseEntity.ok(response);
        } catch (PatientNotExistsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
