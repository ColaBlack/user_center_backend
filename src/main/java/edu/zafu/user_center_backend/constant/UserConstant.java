package edu.zafu.user_center_backend.constant;

/**
 * 用户常量
 *
 * @author ColaBlack
 */
public interface UserConstant {

    /**
     * 用户密码加密盐
     */
    String SALT = "ColaBlack";

    /**
     * 用户登录态键
     */
    String USER_LOGIN_STATE = "USER_LOGIN_STATE";

    /**
     * 最小账号长度
     */
    Integer MIN_ACCOUNT_LENGTH = 4;

    /**
     * 最小密码长度
     */
    Integer MIN_PASSWORD_LENGTH = 6;
}
