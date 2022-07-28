package com.piyoro.personalweb.exception.controller;

import com.piyoro.personalweb.exception.dto.UserExceptionExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Controller
@RequestMapping("/exception")
@Validated
@Slf4j
public class ExceptionExampleController {

    @GetMapping("/user")
    public UserExceptionExample get(
            @Size(min=2)
            @RequestParam String name,
            @NotNull // 없어도 스프링 자체에서 MissingServletRequestParameterException 바인딩 익셉션 발생
            @Min(1)
            @RequestParam Integer age) {
        UserExceptionExample userExceptionExample = new UserExceptionExample();
        userExceptionExample.setName(name);
        userExceptionExample.setAge(age);

        int a = 10 + age;
        return userExceptionExample;
    }


    @GetMapping("/user/req2")
    public UserExceptionExample get(@Valid UserExceptionExample userExceptionExample) {
//        userExceptionExample.setName(name);
//        userExceptionExample.setAge(age);
//
//        int a = 10 + age;
        return userExceptionExample;
    }
}
