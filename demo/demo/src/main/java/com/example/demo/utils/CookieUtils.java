package com.example.demo.utils;

import jakarta.servlet.http.Cookie;

public class CookieUtils {
    static public Cookie getCookies(String token) {
        Cookie cookie = new Cookie("DemoCookie", token);
        cookie.setHttpOnly(true); // HttpOnly 속성 설정
        cookie.setPath("/"); // 쿠키의 경로 설정
        cookie.setSecure(false);
        cookie.setAttribute("SameSite", "strict");
        cookie.setMaxAge(3600);

        return cookie;
    }

    static public Cookie getDieCookie() {
        Cookie dieCookie = getCookies(null);
        dieCookie.setMaxAge(0);
        return dieCookie;
    }

    static public Cookie getByNameCookie(String name, Cookie[] cookies) {
        Cookie getCookie = new Cookie("notFound", null);

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                getCookie = cookie;
            }
        }
        return getCookie;
    }
}
