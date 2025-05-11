package com.three.contentsecuritybackend.model.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 学号
     */
    private String schoolId;

    /**
     * 密码
     */
    private String userPassword;
}
