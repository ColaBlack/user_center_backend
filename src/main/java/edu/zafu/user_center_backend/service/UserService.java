package edu.zafu.user_center_backend.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.zafu.user_center_backend.model.DTO.user.UserQueryRequest;
import edu.zafu.user_center_backend.model.PO.User;
import edu.zafu.user_center_backend.model.VO.UserVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author ColaBlack
 * @description 针对表【user】的数据库操作Service
 * @createDate 2024-09-05 19:57:52
 */
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 校验密码
     * @return 新用户 id
     */
    UserVO userRegister(String userAccount, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request      请求对象
     * @return 脱敏后的用户信息
     */
    UserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * 用户注销
     *
     * @param request 请求对象
     * @return 是否成功注销
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request 请求对象
     * @return 当前登录用户
     */
    UserVO getLoginUser(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request 请求对象
     * @return 是否为管理员
     */
    boolean isAdmin(HttpServletRequest request);

    /**
     * 获取脱敏的用户信息
     *
     * @param user 用户对象
     * @return 脱敏后的用户信息
     */
    UserVO getUserVO(User user);

    /**
     * 获取脱敏的用户信息
     *
     * @param userList 用户对象列表
     * @return 脱敏后的用户信息列表
     */
    List<UserVO> getUserVO(List<User> userList);

    /**
     * 获取查询queryWrapper
     *
     * @param userQueryRequest 用户查询请求对象
     * @return queryWrapper queryWrapper对象
     */
    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);
}
