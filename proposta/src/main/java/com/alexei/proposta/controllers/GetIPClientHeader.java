package com.alexei.proposta.controllers;

import javax.servlet.http.HttpServletRequest;

public class GetIPClientHeader {

    public static String getIpClientRequest(HttpServletRequest request) {
        String ip = request.getHeader("X-Forward-For");
        if (ip == null) {
           return ip = request.getRemoteAddr();
        }
        return ip;
    }
    
}
