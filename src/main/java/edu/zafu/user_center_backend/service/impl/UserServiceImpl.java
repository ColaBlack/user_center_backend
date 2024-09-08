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

    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
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

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request      请求对象
     * @return 脱敏后的用户信息
     */
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
        ThrowUtils.throwIf(user.getUserstatus().equals(userAuthEnums.USER_AUTH_BAN.getVal()), ErrorCode.NO_AUTH_ERROR, "用户已被禁用");
        // 登录成功，记录用户信息到 session
        UserVO userVO = this.getUserVO(user);
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, userVO);
        return this.getUserVO(user);
    }

    /**
     * 用户注销
     *
     * @param request 请求对象
     * @return 是否成功注销
     */
    @Override
    public boolean userLogout(HttpServletRequest request) {
        ThrowUtils.throwIf(request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE) == null, ErrorCode.NOT_LOGIN_ERROR, "未登录");
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
        return true;
    }

    /**
     * 获取当前登录用户
     *
     * @param request 请求对象
     * @return 当前登录用户
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        UserVO userVO = (UserVO) attribute;
        if (userVO == null) {
            return null;
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userVO.getId());
        return this.baseMapper.selectOne(queryWrapper);
    }

    /**
     * 是否为管理员
     *
     * @param request 请求对象
     * @return 是否为管理员
     */
    @Override
    public boolean isAdmin(HttpServletRequest request) {
        Object attribute = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        UserVO userVO = (UserVO) attribute;
        return userVO != null && userVO.getUserstatus().equals(userAuthEnums.USER_AUTH_ADMIN.getVal());
    }

    /**
     * 是否为管理员
     *
     * @param user 用户对象
     * @return 是否为管理员
     */
    @Override
    public boolean isAdmin(User user) {
        return user != null && user.getUserstatus().equals(userAuthEnums.USER_AUTH_ADMIN.getVal());
    }

    /**
     * 获取脱敏的用户信息
     *
     * @param user 用户对象
     * @return 脱敏后的用户信息
     */
    @Override
    public UserVO getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return userVO;
    }

    /**
     * 获取脱敏的用户信息
     *
     * @param userList 用户对象列表
     * @return 脱敏后的用户信息列表
     */
    @Override
    public List<UserVO> getUserVO(List<User> userList) {
        if (CollectionUtil.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }
}




