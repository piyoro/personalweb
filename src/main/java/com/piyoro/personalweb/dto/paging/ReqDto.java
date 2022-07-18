package com.piyoro.personalweb.dto.paging;

import com.piyoro.personalweb.dto.common.CommonReqDto;
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
