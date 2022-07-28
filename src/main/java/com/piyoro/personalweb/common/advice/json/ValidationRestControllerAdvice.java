package com.piyoro.personalweb.common.advice.json;

import com.piyoro.personalweb.common.consts.CommonValidationCode;
import com.piyoro.personalweb.common.dto.CommonResDto;
import com.piyoro.personalweb.common.util.ThreadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Locale;
import java.util.Optional;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@Slf4j
public class ValidationRestControllerAdvice {

    private final MessageSource validationMessageSource;

    private static final String BAD_REQUEST = "9004";
    private static final String APPLICATION_ERROR = "9009";

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResDto> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        if(bindingResult.hasErrors()) {
            //모든 에러 내용 확인 가능
//            bindingResult.getAllErrors().forEach(objectError -> {
//                FieldError field = (FieldError) objectError;
//                String message = objectError.getDefaultMessage();
//                sb.append(String.format("field : [%s] ", field.getField()));
//                sb.append(String.format("message : [%s]", message));
//            });
            ObjectError objectError = bindingResult.getAllErrors().get(0);
            FieldError field = (FieldError) objectError;
            String message = objectError.getDefaultMessage();
            sb.append(String.format("field : [%s] ", field.getField()));
            sb.append(String.format("message : [%s]", message));
        }
        log.error("MethodArgumentNotValidException [{}]", sb);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResDto.getResponseVO(BAD_REQUEST, sb.toString(), null));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<CommonResDto> constraintViolationException(ConstraintViolationException e) {
        StringBuilder sb = new StringBuilder();
        Optional<ConstraintViolation<?>> optConstraintViolation = e.getConstraintViolations().stream().findFirst();
        //모든 에러 내용 확인 가능
//        e.getConstraintViolations().forEach(error -> {
//            String strPropertyPath = error.getPropertyPath().toString();
//            String fieldName = strPropertyPath.substring(strPropertyPath.lastIndexOf(".")+1);
//            String message = error.getMessage();
//            sb.append(String.format("field : [%s] ", fieldName));
//            sb.append(String.format("message : [%s]", message));
//        });
        if(optConstraintViolation.isPresent()) {
            ConstraintViolation<?> error = optConstraintViolation.get();
            String strPropertyPath = error.getPropertyPath().toString();
            String fieldName = strPropertyPath.substring(strPropertyPath.lastIndexOf(".")+1);
            String message = error.getMessage();
            sb.append(String.format("field : [%s] ", fieldName));
            sb.append(String.format("message : [%s]", message));
        }
        log.error("ConstraintViolationException [{}]", sb);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResDto.getResponseVO(BAD_REQUEST, sb.toString(), null));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<CommonResDto> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        String fieldName = e.getParameterName();
        String fieldType = e.getParameterType();
        String invalidMessage = validationMessageSource.getMessage(CommonValidationCode.NOTNULL.substring(1, CommonValidationCode.NOTNULL.length()-1), null, Locale.getDefault());;
        String message = String.format("field : [%s] message : [%s]", fieldName, invalidMessage);
        log.error("MissingServletRequestParameterException [{}]", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResDto.getResponseVO(BAD_REQUEST, message, null));
    }
}
