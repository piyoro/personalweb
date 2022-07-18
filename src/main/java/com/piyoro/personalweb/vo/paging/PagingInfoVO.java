package com.piyoro.personalweb.vo.paging;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class PagingInfoVO {
    
    //페이지 번호
    int page;
    //현재 페이지 여부
    boolean current;

}
