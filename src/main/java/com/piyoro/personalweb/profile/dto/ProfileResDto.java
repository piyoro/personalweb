package com.piyoro.personalweb.profile.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class ProfileResDto {
    private String activeProfile;
    private String profileValue;
}
