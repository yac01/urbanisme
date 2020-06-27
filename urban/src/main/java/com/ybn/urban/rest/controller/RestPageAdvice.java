package com.ybn.urban.rest.controller;

import com.ybn.urban.rest.technical.RestPage;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class RestPageAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return RestPage.class.isAssignableFrom(methodParameter.getParameterType());

    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        RestPage<?> rp = (RestPage<?>) o;
        serverHttpResponse.getHeaders().add("X-Total-Count", rp.getTotalElement());
        serverHttpResponse.getHeaders().add("X-Result-Count", rp.getResultCount());
        return rp.getContent();
    }
}


