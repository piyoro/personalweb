package com.piyoro.personalweb.common.advice.json;

import com.piyoro.personalweb.common.dto.CommonResDto;
import com.piyoro.personalweb.common.util.ThreadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
@RequiredArgsConstructor
@Slf4j
public class GlobalRestControllerAdvice {

    private final MessageSource validationMessageSource;

    private static final String APPLICATION_ERROR = "9009";
    private static final String INTERNAL_SERVER_ERROR = "9999";

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<CommonResDto> exception(Exception e) {
        log.error("시스템 에러 발생 [{}]", ThreadUtil.getRequestContext(), e);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(CommonResDto.getResponseVO(INTERNAL_SERVER_ERROR, "시스템 에러가 발생했습니다.", null));
    }
}
