package com.msz.product.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@Order(1)
public class RequestResponseLoggers implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        log.info("request uri: "+httpServletRequest.getRequestURI());
        log.info("request method: "+httpServletRequest.getMethod());
        log.info("request body: "+httpServletRequest.getInputStream().toString());
        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

    }
}
