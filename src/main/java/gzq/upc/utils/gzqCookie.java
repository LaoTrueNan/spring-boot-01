package gzq.upc.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class gzqCookie {
    public static String getMyCookie(HttpServletRequest request,String cookieName){
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
            for(Cookie cookie: cookies){
                if(cookie.getName().equals(cookieName)){
                    return cookie.getValue();
                }
            }
        }
    return null;
    }

    public static String setCookie(HttpServletResponse response,String cookieName,String newValue){

        Cookie cookie = new Cookie(cookieName,newValue);
        cookie.setDomain("");
        cookie.setPath("/");
        response.addCookie(cookie);
        return newValue;
    }
}
