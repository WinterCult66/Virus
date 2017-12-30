package com.virus.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class RequestTimeinterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestTimeinterceptor.class);

    //PRIMERO
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOGGER.info("URL : {}  ", request.getRequestURI());
        request.setAttribute("startTime  ", System.currentTimeMillis());
        return true;
    }

    //SEGUNDO
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //long startTime = (long) request.getAttribute("startTime");
        //LOGGER.info("REQUEST URL: {}, TOTAL TIME {} ms   ", request.getRequestURL().toString(), (System.currentTimeMillis() - startTime));

    }

}
