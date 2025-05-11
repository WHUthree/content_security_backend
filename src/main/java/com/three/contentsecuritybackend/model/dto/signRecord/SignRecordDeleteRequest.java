package com.three.contentsecuritybackend.model.dto.signRecord;

import lombok.Data;

import java.io.Serializable;

@Data
public class SignRecordDeleteRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    private static final long serialVersionUID = 1L;
}
