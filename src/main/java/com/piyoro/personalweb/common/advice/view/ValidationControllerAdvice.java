package com.piyoro.personalweb.common.advice.view;

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
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Locale;
import java.util.Optional;

/**
 * ResponseBody 형태의 응답이 아닌
 * 페이지 반환 컨트롤러의 에러 처리.
 * 화면단까지는 아직 생각하지 않아서 사용시 응답처리에 대한 작업 필요.
 * Model 에 결과를 담아서 화면에서 해당 값을 표시해주면 되겠다.
 * [TODO] org.springframework.validation.BindException 처리도 해야한다
 * - controller 에서 @Valid UserExceptionExample userExceptionExample 이렇게만 사용할 경우
 */
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
@Slf4j
public class ValidationControllerAdvice {

    private final MessageSource validationMessageSource;

    private static final String BAD_REQUEST = "9004";
    private static final String APPLICATION_ERROR = "9009";

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<CommonResDto> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletResponse res) {
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder();
        if(bindingResult.hasErrors()) {
            ObjectError objectError = bindingResult.getAllErrors().get(0);
            FieldError field = (FieldError) objectError;
            String message = objectError.getDefaultMessage();
            sb.append(String.format("field : [%s] ", field.getField()));
            sb.append(String.format("message : [%s]", message));
        }
        log.error("MethodArgumentNotValidException [{}]", sb);
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResDto.getResponseVO(BAD_REQUEST, sb.toString(), null));
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<CommonResDto> constraintViolationException(ConstraintViolationException e, HttpServletResponse res) {
        StringBuilder sb = new StringBuilder();
        Optional<ConstraintViolation<?>> optConstraintViolation = e.getConstraintViolations().stream().findFirst();
        if(optConstraintViolation.isPresent()) {
            ConstraintViolation<?> error = optConstraintViolation.get();
            String strPropertyPath = error.getPropertyPath().toString();
            String fieldName = strPropertyPath.substring(strPropertyPath.lastIndexOf(".")+1);
            String message = error.getMessage();
            sb.append(String.format("field : [%s] ", fieldName));
            sb.append(String.format("message : [%s]", message));
        }
        log.error("ConstraintViolationException [{}]", sb);
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResDto.getResponseVO(BAD_REQUEST, sb.toString(), null));
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public String missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletResponse res) {
        String fieldName = e.getParameterName();
        String fieldType = e.getParameterType();
        String invalidMessage = validationMessageSource.getMessage(CommonValidationCode.NOTNULL.substring(1, CommonValidationCode.NOTNULL.length()-1), null, Locale.getDefault());;
        String message = String.format("field : [%s] message : [%s]", fieldName, invalidMessage);
        log.error("MissingServletRequestParameterException [{}]", message);
        res.setStatus(HttpStatus.BAD_REQUEST.value());
        return "error";
    }
}
