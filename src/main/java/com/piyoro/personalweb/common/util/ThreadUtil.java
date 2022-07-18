package com.piyoro.personalweb.common.util;

import com.piyoro.personalweb.common.context.RequestContext;

public class ThreadUtil {
    private static ThreadLocal<String> tlReqTrcId = new ThreadLocal<>();

    private static ThreadLocal<RequestContext> tlRequestContext = new ThreadLocal<>();

    public static void setReqTrcId(String reqTrcId) {
        tlReqTrcId.set(reqTrcId);
    }

    public static String getReqTrcId() {
        return tlReqTrcId.get();
    }

    public static RequestContext getRequestContext() {
        return tlRequestContext.get();
    }

    public static void setRequestContext(RequestContext requestContext) {
        tlRequestContext.set(requestContext);
    }
}
