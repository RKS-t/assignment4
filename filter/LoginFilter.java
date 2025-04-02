package com.example.scheduleproject.filter;

import com.example.scheduleproject.common.Const;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;



public class LoginFilter implements Filter {

    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if(!isWhiteList(requestURI)){

            HttpSession session = httpRequest.getSession(false);

            if (session==null||session.getAttribute(Const.LOGIN_USER)==null){
                throw new RuntimeException("로그인 해주세요");
            }

        }
        filterChain.doFilter(httpRequest, httpResponse);
    }

    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(Const.WHITE_LIST, requestURI);
    }


}
