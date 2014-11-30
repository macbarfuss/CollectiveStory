package de.macbarfuss.collectivestory.interceptors;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AddControllerNameToModelInterceptor extends HandlerInterceptorAdapter {

    public AddControllerNameToModelInterceptor() {
        super();
    }

    @Override
    public void postHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler,
            final ModelAndView modelAndView) throws Exception {
        final HandlerMethod handlerMethod = (HandlerMethod) handler;

        modelAndView.addObject("controllerName", handlerMethod.getBean().getClass().getSimpleName());
        modelAndView.addObject("controllerAction", handlerMethod.getMethod().getName());
    }
}
