package com.piyoro.personalweb.exception.controller;

import com.piyoro.personalweb.exception.dto.UserExceptionExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/api/exception")
@Validated
@Slf4j
public class ExceptionExampleApiController {

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


    @GetMapping("/user/path/{name}")
    public UserExceptionExample getUserName(
            @PathVariable @Size(min = 2) String name) {
        UserExceptionExample userExceptionExample = new UserExceptionExample();
        userExceptionExample.setName(name);

        return userExceptionExample;
    }

    @PostMapping("/user")
    public UserExceptionExample post(@Valid @RequestBody UserExceptionExample userExceptionExample) {
        log.debug("post start");
        return userExceptionExample;
    }

//  특정 컨트롤러 안의 예외만 별도 처리 가능
//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<String> methodArgumentNotValidException(MethodArgumentNotValidException e) {
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//    }
}
