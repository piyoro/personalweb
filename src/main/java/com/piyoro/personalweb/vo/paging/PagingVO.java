package com.piyoro.personalweb.vo.paging;

import lombok.*;

import java.util.List;

@Getter
@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class PagingVO {

    //전체 카운트
    int totalCnt;
    //현재 페이지
    int page;
    //요청한 페이징 사이즈
    int pageSize;
    //페이징 블럭 사이즈
    int pageBlockSize;
    //
    int totalPageBlockCnt;
    //이전 페이지 여부
    boolean prevPage;
    //다음 페이지 여부
    boolean nextPage;
    //이전 페이지 블럭 여부
    boolean prevPageBlock;
    //다음 페이지 블럭 여부
    boolean nextPageBlock;
    //
    String uri;

    List<PagingInfoVO> list;

}
