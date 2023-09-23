package com.demo.HospitalStaffManagement.controller;


import com.demo.HospitalStaffManagement.dto.AuthenticationRequest;
import com.demo.HospitalStaffManagement.dto.AuthenticationResponse;
import com.demo.HospitalStaffManagement.dto.RegisterRequestBody;
import com.demo.HospitalStaffManagement.entity.Staff;
import com.demo.HospitalStaffManagement.exception.StaffNotExistsException;
import com.demo.HospitalStaffManagement.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private LoginService loginService;
    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(@RequestBody AuthenticationRequest authenticationRequest) {

        try {
            String email = authenticationRequest.getEmail();
            String password = authenticationRequest.getPassword();
            System.out.println("authenticationrequest: "+email + ", " + password );
            // Generate JWT token
            AuthenticationResponse authenticationResponse = loginService.createAuthenticationToken(authenticationRequest);
            // Return the token in the response
            return ResponseEntity.ok(authenticationResponse);

        } catch (AuthenticationException e) {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
        catch (StaffNotExistsException e) {
            // Authentication failed
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Email");
        }
    }

    @PostMapping(path = "/signup", consumes = "application/Json")
    public ResponseEntity<Object> createStaff(@RequestBody RegisterRequestBody registerRequestBody) {
        Staff staff = loginService.createStaff(registerRequestBody);
        if (staff != null) {
            return new ResponseEntity<>("Staff Created Successfully", HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Staff already exists", HttpStatus.OK);
        }
    }
}