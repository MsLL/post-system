package com.upup.demo.postsystem.util;

import com.upup.demo.postsystem.dictionary.Constants;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author tao.li
 * @Date 2021/1/28 上午2:25
 */
public class WebUtil {
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setDomain(Constants.COOKIE_DOMIN);
        cookie.setPath("/");
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void addCookie(HttpServletResponse response, String name, String value) {
        addCookie(response, name, value, Constants.DEFAULT_COOKIE_MAX_AGE);
    }

    public static void deleteCookie(HttpServletResponse response, String name) {
        addCookie(response, name, "", 0);
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (name.equals(cookies[i].getName())) {
                    return cookies[i];
                }
            }
        }
        return null;
    }


    /**
     * 获取cookie值
     * @param request http请求
     * @param name cookie名字
     * @return 值
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * 获取指定域名的cookie值
     * @param request http请求
     * @param domain 域
     * @param name cookie名字
     * @return String
     */
    public static String getCookieValue(HttpServletRequest request, String domain, String name) {
        Cookie cookie = getCookie(request, domain,name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * 获取指定域名的cookie对象
     * @param request http请求
     * @param domain 域
     * @param name cookie名字
     * @return Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String domain,String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) return null;
        if(domain==null || domain.equalsIgnoreCase("localhost")){
            for (Cookie cookie:cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        for (Cookie cookie:cookies) {
            if (domain.equals(cookie.getDomain()) && name.equals(cookie.getName())) {
                return cookie;
            }
        }
        return null;
    }

    /**
     * 获取登录用户远程主机ip地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
