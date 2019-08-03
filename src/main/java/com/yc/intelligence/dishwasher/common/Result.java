package com.yc.intelligence.dishwasher.common;

import lombok.Data;

@Data
public class Result<T> {
    /** 错误码. */
    private Integer code;

    /** 提示信息. */
    private String msg;

    /** 具体的内容. */
    private T data;
}
