package com.atgugu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    //404_not_found
    private Integer code;
    private String massage;
    private T data;

    public CommonResult(Integer code, String massage) {
        this(code, massage, null);
    }
}
