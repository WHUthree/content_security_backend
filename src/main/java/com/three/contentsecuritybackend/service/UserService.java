package com.three.contentsecuritybackend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.three.contentsecuritybackend.model.dto.user.UserQueryRequest;
import com.three.contentsecuritybackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.three.contentsecuritybackend.model.vo.LoginUserVO;
import com.three.contentsecuritybackend.model.vo.UserVO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author lenovo
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-04-23 23:01:00
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param schoolId      学号
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    long userRegister(String schoolId, String userName, String userPassword, String checkPassword, MultipartFile file);

    /**
     * 用户登录
     *
     * @param schoolId  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    LoginUserVO userLogin(String schoolId, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);

    /**
     * 获取脱敏的已登录用户信息
     *
     * @return
     */
    LoginUserVO getLoginUserVO(User user);

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    UserVO getUserVO(User user);

    List<UserVO> getUserVOList(List<User> userList);

    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    String getEncryptPassword(String userPassword);
}
