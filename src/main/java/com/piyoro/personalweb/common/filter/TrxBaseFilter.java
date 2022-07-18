package com.piyoro.personalweb.common.filter;

import com.piyoro.personalweb.common.consts.CommonConst;
import com.piyoro.personalweb.common.util.DateUtil;
import com.piyoro.personalweb.common.util.StringUtil;
import com.piyoro.personalweb.common.util.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class TrxBaseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;


        String reqTrcId = req.getHeader("reqTrcId");
        if (StringUtils.isBlank(reqTrcId)) {
            reqTrcId = DateUtil.getCurrentDate(CommonConst.DATE_PATERRN_YYYYMMDDHHMMSSSSS) + StringUtil.getFixedRandomStringId(3, "0")
                    + StringUtil.getFixedRandomStringId(3, "0") + StringUtil.getFixedRandomStringId(3, "0");
        }
        MDC.put("reqTrcId", reqTrcId);

        log.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - start");

        ThreadUtil.setReqTrcId(reqTrcId);
        chain.doFilter(req, response);

        log.debug("doFilter(ServletRequest, ServletResponse, FilterChain) - end");
    }

}