package edu.zafu.user_center_backend.model.DTO.user;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户创建请求
 *
 * @author ColaBlack
 */
@Data
public class UserAddRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String userNickname;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 状态 0-正常 1-vip 2-ban 3-管理员
     */
    private Integer userRole;

    /**
     * 用户简介
     */
    private String userprofile;
}