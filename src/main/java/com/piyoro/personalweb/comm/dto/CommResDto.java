package com.piyoro.personalweb.comm.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class CommResDto {
    private String response;
    private List<Integer> list;
}
