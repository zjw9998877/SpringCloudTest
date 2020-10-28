package com.atgugu.springcloud.entities;

/**
 * @ClassName Demo
 * @Author jiawei.Zhang
 * @Description //TODO
 * @Date 2020/10/22、14:40
 * @Version 1.0
 */
public class Demo {
    public static void main(String[] args) {
        TestDo testDo = TestDo.builder()
                .a("a")
                .b("b")
                .c("c")
                .d("d")
                .e("e")
                .f("f")
                .build();
    }
}
