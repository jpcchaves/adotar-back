package com.jpcchaves.adotar.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest req,
                       HttpServletResponse res,
                       org.springframework.security.access.AccessDeniedException accessDeniedException) throws ServletException, IOException {
        res.setContentType("application/json;charset=UTF-8");
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);

        JSONObject obj = new JSONObject();

        try {
            obj.accumulate("timestamp", new Date());
            obj.accumulate("status", HttpServletResponse.SC_FORBIDDEN);
            obj.accumulate("title", "Acesso Negado!");
            obj.accumulate("message", "Você não possui autorização para acessar esse recurso!");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        res.getWriter().write(obj.toString());
    }
}
