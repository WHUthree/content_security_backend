package com.three.contentsecuritybackend.model.dto.signRecord;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SignRecordUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 签到时间
     */
    private Date signTime;

    /**
     * 备注（如异常原因）
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}