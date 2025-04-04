package com.example.scheduleproject.filter;

import com.example.scheduleproject.common.Const;
import com.example.scheduleproject.exception.LoginAuthException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;



public class LoginFilter implements Filter {

    //화이트리스트 제외한 URI 접속 시도시 로그인 세션 필요
    //로그인한 상태에서는 블락리스트 접속 시도시 예외
    @Override
    public void doFilter(
            ServletRequest servletRequest,
            ServletResponse servletResponse,
            FilterChain filterChain
    ) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpRequest.getSession(false);

        //특정URI에 비로그인 사용자 접근 금지
        if(!isWhiteList(requestURI)){

            if (session==null||session.getAttribute(Const.LOGIN_USER)==null){
                throw new LoginAuthException("로그인 해주세요");
            }

        }

        //특정URI에 로그인 사용자 상태는 접근 금지
        if(isBlockList(requestURI)){

            if (session!=null&&session.getAttribute(Const.LOGIN_USER)!=null){
                throw new LoginAuthException("로그인된 사용자는 접근 할 수 없습니다.");
            }

        }

        filterChain.doFilter(httpRequest, httpResponse);
    }

    private boolean isWhiteList(String requestURI){
        return PatternMatchUtils.simpleMatch(Const.WHITE_LIST, requestURI);
    }

    private boolean isBlockList(String requestURI){
        return PatternMatchUtils.simpleMatch(Const.BLOCK_LIST, requestURI);
    }




}
