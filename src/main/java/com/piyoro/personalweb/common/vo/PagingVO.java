package com.piyoro.personalweb.common.vo;

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
    private int totalCnt;
    //현재 페이지
    private int page;
    //요청한 페이징 사이즈
    private int pageSize;
    //페이징 블럭 사이즈
    private int pageBlockSize;
    //
    private int totalPageBlockCnt;
    //이전 페이지 여부
    private boolean prevPage;
    //다음 페이지 여부
    private boolean nextPage;
    //이전 페이지 블럭 여부
    private boolean prevPageBlock;
    //다음 페이지 블럭 여부
    private boolean nextPageBlock;
    //
    private String uri;
    //get 쿼리스트링
    private String queryString;

    List<PagingInfoVO> list;

}
