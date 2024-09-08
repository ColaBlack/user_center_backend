package edu.zafu.user_center_backend.model.DTO.user;


import edu.zafu.user_center_backend.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 用户查询请求
 *
 * @author ColaBlack
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQueryRequest extends PageRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String username;

    /**
     * 用户账号
     */
    private String useraccount;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0-正常 1-vip 2-ban 3-管理员
     */
    private Integer userstatus;

    /**
     * 电话
     */
    private String phone;

    /**
     * 用户简介
     */
    private String userprofile;
}