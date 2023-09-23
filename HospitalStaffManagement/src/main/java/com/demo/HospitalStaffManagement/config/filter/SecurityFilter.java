package com.demo.HospitalStaffManagement.config.filter;

import com.demo.HospitalStaffManagement.entity.Staff;
import com.demo.HospitalStaffManagement.service.UserDetailsServiceImpl;
import com.demo.HospitalStaffManagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtutil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String AuthHeader = request.getHeader("Authorization");
        String jwt = null;
        String username = null;
        if (AuthHeader != null && AuthHeader.startsWith("Bearer")) {
            jwt = jwtutil.resolveToken(request);
            System.out.println("jwt testing : "+ jwt);
            username = jwtutil.extractUsername(jwt);
            System.out.println("username testing : "+ username);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Staff staff = (Staff) this.userDetailsService.loadUserByUsername(username);

            System.out.println("staff validateToken "+ jwtutil.validateToken(jwt, staff.getStaffEmail()));
            if (jwtutil.validateToken(jwt, staff.getStaffEmail())) {
                UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(username, null,
                        staff.getAuthorities());
                userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(userToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
