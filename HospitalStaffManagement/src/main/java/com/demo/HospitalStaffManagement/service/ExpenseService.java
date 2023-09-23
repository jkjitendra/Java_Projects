package com.demo.HospitalStaffManagement.service;


import com.demo.HospitalStaffManagement.entity.Expense;
import com.demo.HospitalStaffManagement.entity.Patient;
import com.demo.HospitalStaffManagement.exception.PatientNotExistsException;
import com.demo.HospitalStaffManagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExpenseService {

    @Autowired
    private PatientRepository patientRepository;

    public int getTotalAmountPending(String email) {
        Optional<Patient> patientOptional = patientRepository.findByPatientEmail(email);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            List<Expense> expenses = patient.getPatientExpenses();
            int totalExpenseAmount = expenses.stream().mapToInt(Expense::getItem_Price).sum();
            return totalExpenseAmount - patient.getPatientTotalAmountPaid();
        } else {
            throw new PatientNotExistsException("Patient Not Exists");
        }
    }

    public Map<String, Integer> getAllBills(String email) {
        Optional<Patient> patientOptional = patientRepository.findByPatientEmail(email);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            List<Expense> expenses = patient.getPatientExpenses();
            Map<String, Integer> bills = new HashMap<>();
            for (Expense expense : expenses) {
                bills.put(expense.getItem_Name(), expense.getItem_Price());
            }
            return bills;
        } else {
            throw new PatientNotExistsException("Patient Not Exists");
        }
    }


}

