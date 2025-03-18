package com.example.demo.filter;

import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entity.DemoEntity;
import com.example.demo.service.DemoServiceImpl;
import com.example.demo.utils.CookieUtils;
import com.example.demo.utils.JwtUtils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DemoJwtAuthenticationFilter extends BasicAuthenticationFilter {

    // @Autowired
    // AuthenticationManager authenticationManager;

    @Autowired
    DemoServiceImpl demoService;

    public DemoJwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("JwtAuthenticationFilter");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Cookie[] cookies = httpRequest.getCookies();
        try {
            Cookie cookie = CookieUtils.getByNameCookie("DemoCookie", cookies);
            String id = JwtUtils.getId(cookie.getValue());
            if (id != null) {
                DemoEntity demoEntity = demoService.findByName(id);

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        demoEntity.getId(), demoEntity.getPassword());

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                filterChain.doFilter(request, response);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
