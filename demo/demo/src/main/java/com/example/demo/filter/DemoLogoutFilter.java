package com.example.demo.filter;

import java.io.IOException;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.example.demo.utils.CookieUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DemoLogoutFilter extends LogoutFilter {
    private LogoutSuccessHandler logoutSuccessHandler;
    private LogoutHandler[] handlers;

    public DemoLogoutFilter(LogoutSuccessHandler logoutSuccessHandler, LogoutHandler[] handlers) {
        super(logoutSuccessHandler, handlers);
        setFilterProcessesUrl("/logout");
        this.logoutSuccessHandler = logoutSuccessHandler;
        this.handlers = handlers;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        httpResponse.addCookie(CookieUtils.getDieCookie());

        super.doFilter(request, response, chain);
    }
}
