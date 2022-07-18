package com.piyoro.personalweb.paging.dto;

import com.piyoro.personalweb.common.dto.CommonReqDto;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ReqDto extends CommonReqDto {
    private String test;
}
