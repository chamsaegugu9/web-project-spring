package com.example.demo.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.entity.DemoEntity;
import com.example.demo.utils.CookieUtils;
import com.example.demo.utils.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoLoginFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    AuthenticationManager authenticationManager;

    public DemoLoginFilter() {
        setFilterProcessesUrl("/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        System.out.println("DemoLoginFilter");

        try {
            ObjectMapper om = new ObjectMapper();
            DemoEntity demoEntity = om.readValue(request.getInputStream(), DemoEntity.class);

            System.out.println(demoEntity.getPassword());

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    demoEntity.getId(), demoEntity.getPassword());

            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

            User user = ((User) authentication.getPrincipal());

            System.out.println(user.getPassword());

            return authentication;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
        UserDetails userDetails = (User) authResult.getPrincipal();

        String jwt = JwtUtils.generateToken(userDetails.getUsername());
        response.addCookie(CookieUtils.getCookies(jwt));
    }
}
