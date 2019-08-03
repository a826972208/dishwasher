package com.yc.intelligence.dishwasher.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 4752052698582956042L;
    private String mobilePhone;
    private String userName;
    private String password;
}
