package com.three.contentsecuritybackend.controller;

import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.three.contentsecuritybackend.annotation.AuthCheck;
import com.three.contentsecuritybackend.common.BaseResponse;
import com.three.contentsecuritybackend.common.ResultUtils;
import com.three.contentsecuritybackend.constant.UserConstant;
import com.three.contentsecuritybackend.exception.BusinessException;
import com.three.contentsecuritybackend.exception.ErrorCode;
import com.three.contentsecuritybackend.exception.ThrowUtils;
import com.three.contentsecuritybackend.model.dto.signRecord.SignRecordAddRequest;
import com.three.contentsecuritybackend.model.dto.signRecord.SignRecordDeleteRequest;
import com.three.contentsecuritybackend.model.dto.signRecord.SignRecordQueryRequest;
import com.three.contentsecuritybackend.model.dto.signRecord.SignRecordUpdateRequest;
import com.three.contentsecuritybackend.model.entity.SignRecord;
import com.three.contentsecuritybackend.service.SignRecordService;
import com.three.contentsecuritybackend.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/signRecord")
public class SignRecordController {

    @Resource
    private UserService userService;

    @Resource
    private SignRecordService signRecordService;

    /**
     * 签到
     *
     * @param request
     * @param file
     * @return
     */
    @PostMapping("/sign")
    public BaseResponse<Long> sign(HttpServletRequest request, @ModelAttribute MultipartFile file) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR, "用户未登录");
        ThrowUtils.throwIf(file == null, ErrorCode.PARAMS_ERROR, "照片为空");
        Long userId = userService.getLoginUser(request).getId();
        Boolean admin = userService.getLoginUser(request).getUserRole() == UserConstant.ADMIN_ROLE ? true : false;
        SignRecord signRecord = new SignRecord();
        // 调用用校验逻辑 如果校验通过则签到 否则抛出异常

        return ResultUtils.success(signRecord.getId());
    }

    /**
     * 添加签到记录（管理员）
     *
     * @param request
     * @param signRecordAddRequest
     * @return
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Long> signRecordAdd(HttpServletRequest request, @RequestBody SignRecordAddRequest signRecordAddRequest) {
        ThrowUtils.throwIf(request == null, ErrorCode.PARAMS_ERROR, "用户未登录");
        Long userId = signRecordAddRequest.getUserId();
        SignRecord signRecord = new SignRecord();
        signRecord.setUserId(userId);
        signRecordService.save(signRecord);
        return ResultUtils.success(signRecord.getId());
    }

    /**
     * 删除签到记录（管理员）
     *
     * @param signRecordDeleteRequest
     * @return
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteSignRecord(@RequestBody SignRecordDeleteRequest signRecordDeleteRequest) {
        Long signRecordId = signRecordDeleteRequest.getId();
        if (signRecordId == null || signRecordId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        boolean b = signRecordService.removeById(signRecordId);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateSignRecord(@RequestBody SignRecordUpdateRequest signRecordUpdateRequest) {
        if (signRecordUpdateRequest == null || signRecordUpdateRequest.getId() == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        SignRecord signRecord = new SignRecord();
        BeanUtils.copyProperties(signRecordUpdateRequest, signRecord);
        boolean result = signRecordService.updateById(signRecord);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        return ResultUtils.success(true);
    }

    /**
     * 分页获取签到列表（仅管理员）
     *
     * @param signRecordQueryRequest 查询请求参数
     */
    @PostMapping("/list/page")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<SignRecord>> listSignRecordByPage(@RequestBody SignRecordQueryRequest signRecordQueryRequest) {
        ThrowUtils.throwIf(signRecordQueryRequest == null, ErrorCode.PARAMS_ERROR);
        long current = signRecordQueryRequest.getCurrent();
        long pageSize = signRecordQueryRequest.getPageSize();
        QueryWrapper<SignRecord> queryWrapper = new QueryWrapper<>();
        Long id = signRecordQueryRequest.getId();
        Long userId = signRecordQueryRequest.getUserId();
        Date minSignTime = signRecordQueryRequest.getMinSignTime();
        Date maxSignTime = signRecordQueryRequest.getMaxSignTime();
        queryWrapper.eq(ObjUtil.isNotNull(id), "id", id);
        queryWrapper.eq(ObjUtil.isNotNull(userId), "userId", userId);
        queryWrapper.between(ObjUtil.isNotNull(minSignTime) && ObjUtil.isNotNull(maxSignTime), "signTime", minSignTime, maxSignTime);
        Page<SignRecord> signRecordPage = signRecordService.page(new Page<>(current, pageSize), queryWrapper);
        return ResultUtils.success(signRecordPage);
    }

}
