package com.atgugu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @ClassName TestDo
 * @Author jiawei.Zhang
 * @Description //TODO
 * @Date 2020/10/22、14:39
 * @Version 1.0
 */
@AllArgsConstructor
@Builder
@Data
public class TestDo {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;


    public class Builder{

    }
}
