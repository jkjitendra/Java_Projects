package com.demo.HospitalStaffManagement.repository;

import com.demo.HospitalStaffManagement.constant.enums.PatientStatus;
import com.demo.HospitalStaffManagement.entity.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Integer> {

    List<Patient> findAllByPatientStatus(PatientStatus status);
    Optional<Patient> findByPatientEmail(String email);
}
