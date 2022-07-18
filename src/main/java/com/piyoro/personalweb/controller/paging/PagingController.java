package com.piyoro.personalweb.controller.paging;

import com.piyoro.personalweb.dto.paging.ReqDto;
import com.piyoro.personalweb.dto.paging.ResDto;
import com.piyoro.personalweb.vo.paging.PagingVO;
import com.piyoro.personalweb.util.PagingUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class PagingController {

    @GetMapping("/paging")
    public ResponseEntity<ResDto> paging(ReqDto reqDto, HttpServletRequest req, HttpServletResponse res) {

        int reqPage = reqDto.getPage();
        int reqPageSize = reqDto.getPageSize();
        int reqPageBlockSize = reqDto.getPageBlockSize();
        List<Integer> pageList = PagingUtil.getPagingExampleData(115);
        int totalCnt = pageList.size();
        //굳이 목록도 넘겨 준다면.
        List<Integer> list = PagingUtil.getPageList(reqPage, reqPageSize, totalCnt, pageList);
        PagingVO paging = PagingUtil.getPaging(reqPage, reqPageSize, reqPageBlockSize, totalCnt, req.getRequestURI());
        ResDto response = ResDto.builder()
                .paging(paging)
                .build();
        response.setList(list);

        return ResponseEntity.ok(response);
    }

}
