package com.three.contentsecuritybackend.model.dto.signRecord;

import lombok.Data;

import java.io.Serializable;


@Data
public class SignRecordAddRequest implements Serializable {

    /**
     * 用户id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}
