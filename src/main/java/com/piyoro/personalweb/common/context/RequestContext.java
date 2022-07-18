package com.piyoro.personalweb.common.context;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

public class RequestContext {

    private String contentType;

    private Map<String, String[]> paramMap;

    private String body;

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Map<String, String[]> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, String[]> paramMap) {
        this.paramMap = paramMap;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes(); 
        HttpServletRequest request = null;
        if(Objects.nonNull(requestAttributes)){
            request = requestAttributes.getRequest();
        }
        return request;
    }

    public static String getRequestUri() {
        HttpServletRequest request = RequestContext.getRequest();
        return request.getRequestURI();
    }
}
