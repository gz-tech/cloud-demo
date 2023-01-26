package com.upuphone.cloudplatform.demo.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Classname SimpleFilter
 * @Description
 * @Date 2022/3/19 10:13 下午
 * @Created by gz-d
 */
@Component
public class SimpleFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Remote Host: " + servletRequest.getRemoteHost() );
        System.out.println("Remote Address:" + servletRequest.getRemoteAddr());
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        System.out.println(request.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
