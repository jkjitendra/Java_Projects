package com.demo.HospitalStaffManagement.service;

import com.demo.HospitalStaffManagement.constant.enums.Designation;
import com.demo.HospitalStaffManagement.dto.AuthenticationRequest;
import com.demo.HospitalStaffManagement.dto.AuthenticationResponse;
import com.demo.HospitalStaffManagement.dto.RegisterRequestBody;
import com.demo.HospitalStaffManagement.entity.Staff;
import com.demo.HospitalStaffManagement.exception.StaffNotExistsException;
import com.demo.HospitalStaffManagement.repository.StaffRepository;
import com.demo.HospitalStaffManagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthenticationResponse createAuthenticationToken(AuthenticationRequest authenticationRequest) {

        String email = authenticationRequest.getEmail();
        String password = authenticationRequest.getPassword();

        // Retrieve the staff entity by email
        Optional<Staff> staffOptional = staffRepository.findByStaffEmail(email);
        // Check if the staff exists and compare the passwords
        if (staffOptional.isPresent() && passwordEncoder.matches(password, staffOptional.get().getStaffPassword())) {
            Staff staff = staffOptional.get();
            System.out.println(passwordEncoder.matches(password , staff.getStaffPassword()));
                final String jwt = jwtUtil.generateToken(authenticationRequest.getEmail(), new LinkedList<String>());
                System.out.println("jwtToken: "+ jwt);
                return new AuthenticationResponse(jwt);
        }
        throw new StaffNotExistsException("Staff does Not Exists");
    }

    public Staff createStaff(RegisterRequestBody registerRequestBody) {

        if (staffRepository.findByStaffEmail(registerRequestBody.getEmail()).isEmpty()) {
            Staff staff = new Staff();
            staff.setStaffEmail(registerRequestBody.getEmail());
            staff.setStaffPassword(passwordEncoder.encode(registerRequestBody.getPassword()));
            staff.setStaffName(registerRequestBody.getName());
            staff.setStaffDesignation(Designation.valueOf(registerRequestBody.getDesignation().toString()));
            return staffRepository.save(staff);
        }
        return null;
    }
}
