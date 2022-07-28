package com.piyoro.personalweb.common.dto;

import com.piyoro.personalweb.common.util.ThreadUtil;

public class CommonResDto {

    private CommonRestHeaderDto header;
    private Object body;

    public CommonRestHeaderDto getHeader() {
        return header;
    }

    public void setHeader(CommonRestHeaderDto header) {
        this.header = header;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public static CommonResDto defaultResponseVO() {
        CommonRestHeaderDto header = new CommonRestHeaderDto();
        header.setCode("0000");
        header.setMsg("정상 처리되었습니다.");
        header.setReqTrcId(ThreadUtil.getReqTrcId());
        CommonResDto response = new CommonResDto();
        response.setHeader(header);
        return response;
    }

    public static CommonResDto defaultResponseVO(Object body) {
        CommonResDto response = defaultResponseVO();
        response.setBody(body);
        return response;
    }

    public static CommonResDto getResponseVO(String code, String msg, Object body) {

        CommonRestHeaderDto header = new CommonRestHeaderDto();
        header.setCode(code);
        header.setMsg(msg);
        header.setReqTrcId(ThreadUtil.getReqTrcId());

        CommonResDto response = new CommonResDto();
        response.setHeader(header);

        response.setBody(body);

        return response;
    }
}
