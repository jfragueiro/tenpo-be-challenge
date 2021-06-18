package com.tenpo.interceptor;

import com.tenpo.model.HistoryResource;
import com.tenpo.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserHistoryInterceptor implements HandlerInterceptor {

    private final HistoryService historyService;

    @Autowired
    public UserHistoryInterceptor(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    public boolean preHandle
            (HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion
            (HttpServletRequest request, HttpServletResponse response, Object
                    handler, Exception exception) throws Exception {
        if (!request.getRequestURL().toString().contains("/error")) {
            historyService.save(new HistoryResource(request.getMethod() + ":" + request.getRequestURL().toString()));
        }
    }
}