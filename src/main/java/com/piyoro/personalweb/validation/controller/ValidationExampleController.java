package com.piyoro.personalweb.validation.controller;

import com.piyoro.personalweb.common.dto.CommonResDto;
import com.piyoro.personalweb.validation.dto.UserValidationExample;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/validation")
public class ValidationExampleController {

    @PostMapping("/user")
    public ResponseEntity<CommonResDto> user(@Valid @RequestBody UserValidationExample userValidationExample) {
//        System.out.println(userValidationExample);
//        if(bindingResult.hasErrors()) {
//            StringBuilder sb = new StringBuilder();
//            bindingResult.getAllErrors().forEach(objectError -> {
//                FieldError field = (FieldError) objectError;
//                String message = objectError.getDefaultMessage();
//                System.out.printf("field : %s message : %s%n", field.getField(), message);
//
//                sb.append(String.format("field : [%s] ", field.getField()));
//                sb.append(String.format("message : [%s]", message));
//            });
//            //400 error 는 발생하는데 0000 으로 정상응답되는건 수정해야겠네
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResDto.defaultResponseVO(sb.toString()));
//        }

        return ResponseEntity.ok(CommonResDto.defaultResponseVO(userValidationExample));
    }


    /**
     * BindingResult bindingResult 파라미터 넣으면
     * 공통 벨리데이션 체크가 안된다.
     * @param userValidationExample
     * @param bindingResult
     * @return
     */
    @PostMapping("/user/api2")
    public ResponseEntity<CommonResDto> userApi2(@Valid @RequestBody UserValidationExample userValidationExample, BindingResult bindingResult) {
        System.out.println(userValidationExample);
        if(bindingResult.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            bindingResult.getAllErrors().forEach(objectError -> {
                FieldError field = (FieldError) objectError;
                String message = objectError.getDefaultMessage();
                System.out.printf("field : %s message : %s%n", field.getField(), message);

                sb.append(String.format("field : [%s] ", field.getField()));
                sb.append(String.format("message : [%s]", message));
            });
            //400 error 는 발생하는데 0000 으로 정상응답되는건 수정해야겠네
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonResDto.defaultResponseVO(sb.toString()));
        }

        return ResponseEntity.ok(CommonResDto.defaultResponseVO(userValidationExample));
    }
}
