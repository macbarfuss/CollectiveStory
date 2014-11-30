package de.macbarfuss.collectivestory.interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AddCurrentUserNameToModelInterceptor extends HandlerInterceptorAdapter {

    public AddCurrentUserNameToModelInterceptor() {
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
            final ModelAndView modelAndView) throws Exception {
        modelAndView.addObject("userName", request.getRemoteUser());
    }
}
