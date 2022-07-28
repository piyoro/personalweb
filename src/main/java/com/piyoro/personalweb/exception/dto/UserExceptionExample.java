package com.piyoro.personalweb.exception.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class UserExceptionExample {

    @NotEmpty
    @Size(min=1,max=10)
    private String name;
    @Min(1)
    @NotNull
    private Integer age;

}
