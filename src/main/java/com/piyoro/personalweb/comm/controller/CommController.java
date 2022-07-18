package com.piyoro.personalweb.comm.controller;

import com.piyoro.personalweb.comm.dto.CommResDto;
import com.piyoro.personalweb.common.dto.CommonResDto;
import com.piyoro.personalweb.common.util.PagingUtil;
import com.piyoro.personalweb.common.vo.PagingVO;
import com.piyoro.personalweb.paging.dto.ReqDto;
import com.piyoro.personalweb.paging.dto.ResDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/comm")
public class CommController {

    @GetMapping("/example")
    public ResponseEntity<CommonResDto> paging(ReqDto reqDto, HttpServletRequest req, HttpServletResponse res) {
        List<Integer> pageList = PagingUtil.getPagingExampleData(20);

        CommResDto commResDto = CommResDto.builder()
                .response("OK")
                .list(pageList)
                .build();
        CommonResDto response = CommonResDto.defaultResponseVO(commResDto);
        return ResponseEntity.ok(response);
    }

}
