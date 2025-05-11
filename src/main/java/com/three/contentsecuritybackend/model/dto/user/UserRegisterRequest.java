package com.three.contentsecuritybackend.model.dto.user;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    /**
     * 学号
     */
    private String schoolId;

    /**
     * 姓名
     */
    private String userName;
    /**
     * 密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;

    /**
     * 照片
     */
    private MultipartFile file;
}
