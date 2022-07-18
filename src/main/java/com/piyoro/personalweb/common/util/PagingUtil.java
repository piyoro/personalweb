package com.piyoro.personalweb.common.util;

import com.piyoro.personalweb.common.context.RequestContext;
import com.piyoro.personalweb.common.vo.PagingVO;
import com.piyoro.personalweb.common.vo.PagingInfoVO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PagingUtil {

    public static List<Integer> getPagingExampleData(int size) {
        return IntStream.range(0, size).boxed().collect(Collectors.toList());
    }

    public static List<Integer> getPageList(int page, int pageSize, int totalCnt, List<Integer> list) {
        int totalPageCnt = (int) Math.ceil((double) totalCnt / pageSize); //전체 페이지 갯수
        List<Integer> ret;
        if(page < totalPageCnt) {
            //시작 인덱스
            int start = page * pageSize;
            //종료 인덱스
            int end = Math.min(start + pageSize, totalCnt);
            ret = list.subList(start, end);
        } else {
            ret = new ArrayList<>();
        }
        return ret;
    }

    /**
     * 현재 url 을 설정해주고 싶은데, 나중엔 검색조건 설정도 필요해지겠지...
     * @param page 현재 페이지
     * @param pageBlockSize 페이징 사이즈
     * @param totalCnt 전체 건수
     * @return 페이징 정보
     */
    public static PagingVO getPaging(int page, int pageSize, int pageBlockSize, int totalCnt) {
        HttpServletRequest req = RequestContext.getRequest();
        String reqUri = req.getRequestURI();
        String queryString = StringUtils.defaultIfBlank(req.getQueryString(), "");
        if(StringUtils.isNotBlank(queryString)) {
            queryString = "?" + queryString;
        }
        PagingVO paging = PagingVO.builder()
            .page(page)
            .pageSize(pageSize)
            .pageBlockSize(pageBlockSize)
            .prevPage(page > 0)
            .totalCnt(totalCnt)
            .uri(reqUri)
            .queryString(queryString)
            .build();

        int totalPageCnt = (int) Math.ceil((double) totalCnt / pageSize); //전체 페이지 갯수
        //전체 페이징 블럭 갯수
        int totalPagingBlockCnt = (int) Math.ceil((double) totalCnt / pageSize / pageBlockSize);

        paging.setTotalPageBlockCnt(totalPagingBlockCnt);
        List<PagingInfoVO> list;
        if(page < totalPageCnt) {

            int pageBlock = page / pageBlockSize;  //요청 페이지의 페이지 블럭
            //시작 인덱스
            int start = pageBlock * pageBlockSize;
            //종료 인덱스
            int end = Math.min((pageBlock + 1) * pageBlockSize, totalPageCnt);

            list = IntStream.range(start, end).mapToObj(i -> {
                PagingInfoVO p = PagingInfoVO.builder()
                        .page(i)
                        .build();
                if(i == page) p.setCurrent(true);
                return p;
            }).collect(Collectors.toList());

            paging.setPrevPageBlock(start > 0);
            paging.setNextPageBlock(end < totalPageCnt);
        }
        else {
            list = new ArrayList<>();
        }

        paging.setNextPage(page < (totalPageCnt - 1));
        paging.setList(list);
        return paging;
    }

}
