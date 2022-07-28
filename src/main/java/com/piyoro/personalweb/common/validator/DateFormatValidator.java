package com.piyoro.personalweb.common.validator;

import com.piyoro.personalweb.common.annotation.DateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {

    private String pattern;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        //yyyyMM
        try {
            String pattern = this.pattern;
            switch (pattern) {
                case "yyyy" :
                    pattern = pattern + "MMdd";
                    value = value + "0101";
                    break;
                case "yyyyMM" :
                    pattern = pattern + "dd";
                    value = value + "01";
                    break;
            }
            LocalDate localDate = LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void initialize(DateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }
}
