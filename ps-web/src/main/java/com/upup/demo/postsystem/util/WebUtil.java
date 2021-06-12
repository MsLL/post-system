package com.upup.demo.postsystem.util;

import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DESede;
import com.upup.demo.postsystem.dictionary.Constants;
import java.util.Base64;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

/**
 * 
 * @Date 2021/1/28 上午2:25
 */
public class WebUtil {

    /**
     * 1.通过api生成一个秘钥（字节数组）。(秘钥是用一定的算法生成的，不是我们随便给的一个值)。我们要保存这个值，就把它base64编码一下，以字符串的形式保存，我们需要用的时候再解码一下字符串，就得到原秘钥字节数组了。
     * byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DESede.getValue()).getEncoded();
     * String base64Str= Base64.getEncoder().encodeToString(key);
     */
    private static final String tripleDesKeyBase64ed = "uRODDrn9LFekKqSXbTcTIFfvQJ15rSmM";

    @SneakyThrows
    public static String generatePserId() {
        String uuid = Constants.PSER_KEY + UUID.randomUUID().toString();
        byte[] key = Base64.getDecoder().decode(tripleDesKeyBase64ed);//秘钥

        DESede deSede = SecureUtil.desede(key);
        byte[] encryptData = deSede.encrypt(uuid.getBytes("utf-8"));
        return HexUtil.encodeHexStr(encryptData);// new String()可能是乱码的，编码为16进制字符串
    }

    //just a friendly method name
    public static String getPserId(String encryptedAndHexencodedStr) {
        return decodeHexAndDecrypt(encryptedAndHexencodedStr);
    }

    private static String decodeHexAndDecrypt(String encryptedAndHexencodedStr) {
        byte[] key = Base64.getDecoder().decode(tripleDesKeyBase64ed);//秘钥
        DESede deSede = SecureUtil.desede(key);

        byte[] encryptedData = HexUtil.decodeHex(encryptedAndHexencodedStr);
        byte[] data = deSede.decrypt(encryptedData);
        return new String(data);
    }

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
     *
     * @param request http请求
     * @param name    cookie名字
     * @return 值
     */
    public static String getCookieValue(HttpServletRequest request, String name) {
        Cookie cookie = getCookie(request, name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * 获取指定域名的cookie值
     *
     * @param request http请求
     * @param domain  域
     * @param name    cookie名字
     * @return String
     */
    public static String getCookieValue(HttpServletRequest request, String domain, String name) {
        Cookie cookie = getCookie(request, domain, name);
        return cookie != null ? cookie.getValue() : null;
    }

    /**
     * 获取指定域名的cookie对象
     *
     * @param request http请求
     * @param domain  域
     * @param name    cookie名字
     * @return Cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String domain, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) return null;
        if (domain == null || domain.equalsIgnoreCase("localhost")) {
            for (Cookie cookie : cookies) {
                if (name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        for (Cookie cookie : cookies) {
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
