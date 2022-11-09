package com.team.case6.core.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//Cấu hình lại lỗi không có quyền truy cập => trả về 403 + thông điệp là access denied!
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {

        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);//Trạng thái
//        response.setContentType("application/json;charset=UTF-8");
//        response.getWriter().write(mapper.writeValueAsString(new JSONObject("{'message': 'Access denied'}")));
        response.getWriter().write("Access Denied!");//Thông điệp
        response.sendRedirect("/error/403");
    }
}
