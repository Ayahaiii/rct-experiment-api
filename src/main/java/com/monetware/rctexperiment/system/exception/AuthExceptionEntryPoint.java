package com.monetware.rctexperiment.system.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Token失效异常信息
 * Token 过期
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response, AuthenticationException authException) throws ServletException {
        Map map = new HashMap();
        map.put("code", "10001");
        map.put("message", "凭证认证失败");
        map.put("data", null);
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(response.getOutputStream(), map);
//            throw new ServiceException(ErrorCode.CUSTOM_MSG, "凭证认证失败");
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
