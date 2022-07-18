package com.piyoro.personalweb.dto.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class CommonReqDto {
    //페이지번호
    int page;
    //페이징사이즈(게시글목록)
    int pageSize;
    //페이징 블럭 사이즈(페이징 숫자)
    int pageBlockSize;

    public int getPageSize() {
        if(pageSize == 0) pageSize = 10;
        return pageSize;
    }

    public int getPageBlockSize() {
        if(pageBlockSize == 0) pageBlockSize = 10;
        return pageBlockSize;
    }
}
