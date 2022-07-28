package com.piyoro.personalweb.common.advice.view;

import com.piyoro.personalweb.common.dto.CommonResDto;
import com.piyoro.personalweb.common.util.ThreadUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ResponseBody 형태의 응답이 아닌
 * 페이지 반환 컨트롤러의 에러 처리.
 * 화면단까지는 아직 생각하지 않아서 사용시 응답처리에 대한 작업 필요.
 * Model 에 결과를 담아서 화면에서 해당 값을 표시해주면 되겠다.
 */
@ControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
@RequiredArgsConstructor
@Slf4j
public class GlobalControllerAdvice {

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
