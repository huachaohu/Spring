package com.spring.security.interceptor;

import com.spring.security.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @description：
 * @author: huachao
 * @date: 2020/3/17
 */
@Component
@Slf4j
public class SimpleAuthenticationInterceptor implements HandlerInterceptor {
    /**
    * @decription: 请求拦截方法
    * @param
    * @return
    * @author huachao
    * @date 2020/3/17 7:59
    */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object object = request.getSession().getAttribute(UserDto.SESSION_USER_KEY);
        if(object == null){
            writeContent(response,"Please login!");
            return false;
        }
        UserDto userDto = (UserDto) object;
        //请求路径
//        log.info("url:"+request.getRequestURL());
//        log.info("uri:"+request.getRequestURI());
        String uri = request.getRequestURI();
        if(uri.contains("/r1") && userDto.getAuthorities().contains("p1")){
            return true;
        }
        if(uri.contains("/r2") && userDto.getAuthorities().contains("p2")){
            return true;
        }
        writeContent(response,"Insufficient permissions, access denied!");
        return false;
    }

    //响应输出
    private void writeContent(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf‐8");
        PrintWriter writer = response.getWriter();
        writer.print(msg);
        writer.close();
//        response.resetBuffer();
    }
}
