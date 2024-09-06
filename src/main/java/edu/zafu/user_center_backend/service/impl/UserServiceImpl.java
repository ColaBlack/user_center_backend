package edu.zafu.user_center_backend.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.zafu.user_center_backend.common.ErrorCode;
import edu.zafu.user_center_backend.common.exception.ThrowUtils;
import edu.zafu.user_center_backend.constant.UserConstant;
import edu.zafu.user_center_backend.mapper.UserMapper;
import edu.zafu.user_center_backend.model.PO.User;
import edu.zafu.user_center_backend.model.VO.UserVO;
import edu.zafu.user_center_backend.model.enums.userAuthEnums;
import edu.zafu.user_center_backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ColaBlack
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-09-05 19:57:52
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public UserVO userRegister(String userAccount, String userPassword, String checkPassword) {
//        校验信息
        ThrowUtils.throwIf(StringUtils.isAnyBlank(userAccount, userPassword, checkPassword), ErrorCode.PARAMS_ERROR, "参数为空");
        ThrowUtils.throwIf((userAccount.length() < UserConstant.MIN_ACCOUNT_LENGTH), ErrorCode.PARAMS_ERROR, "用户账号过短");
        ThrowUtils.throwIf((userPassword.length() < UserConstant.MIN_PASSWORD_LENGTH), ErrorCode.PARAMS_ERROR, "用户密码过短");
        ThrowUtils.throwIf((checkPassword.length() < UserConstant.MIN_PASSWORD_LENGTH), ErrorCode.PARAMS_ERROR, "确认密码过短");
        ThrowUtils.throwIf(!userPassword.equals(checkPassword), ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        // 密码和校验密码相同
        synchronized (userAccount.intern()) {
            // 账户不能重复
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("userAccount", userAccount);
            long count = this.baseMapper.selectCount(queryWrapper);
            ThrowUtils.throwIf(count > 0, ErrorCode.PARAMS_ERROR, "账号重复");
            // 用户密码加密
            String encryptPassword = DigestUtils.md5DigestAsHex((UserConstant.SALT + userPassword).getBytes());
            // 插入数据
            User user = new User();
            user.setUseraccount(userAccount);
            user.setUserpassword(encryptPassword);
            boolean saveResult = this.save(user);
            ThrowUtils.throwIf(!saveResult, ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            return this.getUserVO(user);
        }
    }

    @Override
    public UserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
//        校验信息
        ThrowUtils.throwIf(StringUtils.isAnyBlank(userAccount, userPassword), ErrorCode.PARAMS_ERROR, "参数为空");
        ThrowUtils.throwIf((userAccount.length() < UserConstant.MIN_ACCOUNT_LENGTH), ErrorCode.PARAMS_ERROR, "用户账号过短");
        ThrowUtils.throwIf((userPassword.length() < UserConstant.MIN_PASSWORD_LENGTH), ErrorCode.PARAMS_ERROR, "用户密码过短");
        // 用户密码加密
        String encryptPassword = DigestUtils.md5DigestAsHex((UserConstant.SALT + userPassword).getBytes());
        // 查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = this.baseMapper.selectOne(queryWrapper);
        ThrowUtils.throwIf(user == null, ErrorCode.SYSTEM_ERROR, "用户不存在或密码错误");
        // 用户被禁用
        ThrowUtils.throwIf(user.getUserstatus().equals(userAuthEnums.USER_AUTH_BAN.getVal()), ErrorCode.PARAMS_ERROR, "用户已被禁用");
        //记录用户的登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, user);
        return this.getUserVO(user);
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        return false;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        return null;
    }

    @Override
    public User getLoginUserPermitNull(HttpServletRequest request) {
        return null;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User) attribute;
        return isAdmin(user);
    }

    @Override
    public boolean isAdmin(User user) {
        return user != null && user.getUserstatus().equals(userAuthEnums.USER_AUTH_ADMIN.getVal());
    }

    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVO(List<User> userList) {
        if (CollectionUtil.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }
}




