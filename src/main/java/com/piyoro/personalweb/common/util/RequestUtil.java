package com.piyoro.personalweb.common.util;

import com.piyoro.personalweb.common.context.RequestContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;

public class RequestUtil {

    /**
     * request 에서 getInputStream() 은 별도 장치 없이 꺼내면 안되기 때문에
     * 우선 parameterMap 으로만 작업한다.
     * 추후 장치를 마련하여 inputstream 으로 requestcontext body 항목을 구성하고
     * requestcontext 관리에 대한 작업 진행 필요
     * @param req HttpServletRequest
     * @return HttpServletRequest
     */
    public static RequestContext getRequestContext(HttpServletRequest req) {
        RequestContext request = new RequestContext();

        String contentType = RequestUtil.getContentType(req);
        request.setContentType(contentType);

        if(!StringUtils.startsWith(contentType, MediaType.APPLICATION_JSON_VALUE)) {
            request.setParamMap(req.getParameterMap());
        }

        return request;
    }

    private static String getContentType(HttpServletRequest req) {
        String contentType = req.getContentType();

        if(StringUtils.isBlank(contentType)){
            contentType = "";
        } else if(StringUtils.startsWith(contentType, MediaType.MULTIPART_FORM_DATA_VALUE)){
            contentType = MediaType.MULTIPART_FORM_DATA_VALUE;
        } else if(StringUtils.startsWith(contentType, MediaType.APPLICATION_FORM_URLENCODED_VALUE)) {
            contentType = MediaType.APPLICATION_FORM_URLENCODED_VALUE;
        } else if(StringUtils.startsWith(contentType, MediaType.APPLICATION_JSON_VALUE)) {
            contentType = MediaType.APPLICATION_JSON_VALUE;
        }

        return contentType;
    }
}
