package com.piyoro.personalweb.common.filter;

import com.piyoro.personalweb.common.context.RequestContext;
import com.piyoro.personalweb.common.util.RequestUtil;
import com.piyoro.personalweb.common.util.ThreadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestFilter implements Filter {
    /**
     * Logger for this class
     */
    private static final Logger log = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        RequestContext requestContext = RequestUtil.getRequestContext(req);
        ThreadUtil.setRequestContext(requestContext);

        chain.doFilter(req, response);

    }

}