package com.piyoro.personalweb.profile.controller;

import com.piyoro.personalweb.profile.dto.ProfileReqDto;
import com.piyoro.personalweb.profile.dto.ProfileResDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Value("${spring.profiles.active:${spring.profiles.default}}")
    private String activeProfile;

    @Value("${piyoro.profile.value}")
    private String profileValue;

    @GetMapping("")
    public ResponseEntity<ProfileResDto> profile(ProfileReqDto profileReqDto, HttpServletRequest req, HttpServletResponse res) {

        ProfileResDto response = ProfileResDto.builder()
                .activeProfile(activeProfile)
                .profileValue(profileValue)
                .build();

        return ResponseEntity.ok(response);
    }
}
