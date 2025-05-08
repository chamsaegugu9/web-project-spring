package com.example.demo.filter;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entity.DemoEntity;
import com.example.demo.service.DemoServiceImpl;
import com.example.demo.utils.CookieUtils;
import com.example.demo.utils.JwtUtils;

import io.jsonwebtoken.lang.Arrays;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// @Component
public class DemoJwtAuthenticationFilter extends BasicAuthenticationFilter {

    private DemoServiceImpl demoService;

    public DemoJwtAuthenticationFilter(AuthenticationManager authenticationManager, DemoServiceImpl demoService) {
        super(authenticationManager);
        this.demoService = demoService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        try {

            Cookie cookie = CookieUtils.getByNameCookie("DemoCookie", cookies);

            String id = JwtUtils.getId(cookie.getValue());

            DemoEntity demoEntity = demoService.findById(id);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    User.builder().username(demoEntity.getId()).password(demoEntity.getPassword())
                            .authorities(new SimpleGrantedAuthority("ROLE_USER")).build(),
                    demoEntity.getPassword(),
                    List.of(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        } catch (Exception e) {
            System.out.println("JwtAuthFilter : " + e);
        }
        filterChain.doFilter(request, response);
    }
}
