package com.three.contentsecuritybackend.model.dto.signRecord;

import com.three.contentsecuritybackend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class SignRecordQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 起始签到时间
     */
    private Date minSignTime;

    /**
     * 结束签到时间
     */
    private Date maxSignTime;

    private static final long serialVersionUID = 1L;
}
