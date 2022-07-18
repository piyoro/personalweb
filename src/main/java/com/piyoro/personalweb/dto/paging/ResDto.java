package com.piyoro.personalweb.dto.paging;

import com.piyoro.personalweb.vo.paging.PagingVO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ResDto {

    List<Integer> list; //본문 목록

    PagingVO paging;

}
