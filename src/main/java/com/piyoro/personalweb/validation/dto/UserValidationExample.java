package com.piyoro.personalweb.validation.dto;

import com.piyoro.personalweb.common.annotation.DateFormat;
import com.piyoro.personalweb.common.consts.CommonValidationCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.List;

/**
 * [TODO] message-interpolation 도 찾아봐야할일이 있겠다.
 * https://www.baeldung.com/spring-validation-message-interpolation
 */
@Getter
@Setter
@ToString
public class UserValidationExample {

    @NotBlank(message = CommonValidationCode.NOTNULL)
    private String name;
    @Max(value = 90)
    private int age;
    @Email
    private String email;
    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "핸드폰 번호의 양식과 일치하지 않습니다. 01x-xxx(x)-xxxx")
    private String phoneNumber;

//    @Size(min = 6, max = 6)
    @DateFormat()
    private String reqYearMonth; //yyyyMM

//    @Valid List안의 Car 객체의 검증을 하기 위해선 @Valid 어노테이션 붙여줘야한다.
    private List<CarValidationExample> carValidationExamples;

//    Custom Annotation 생성해서 이렇게 사용할 필요 없음
//    @AssertTrue(message = "yyyyMM의 형식에 맞지 않습니다.")
//    public boolean isReqYearMonthValidation() {
//        try {
//            LocalDate localDate = LocalDate.parse(this.getReqYearMonth() + "01", DateTimeFormatter.ofPattern(CommonConst.DATE_PATERRN_YYYYMMDD));
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }
}
