package edu.zafu.user_center_backend.model.PO;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName user
 * @author ColaBlack
 */
@TableName(value = "user")
@Data
public class User implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
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
     * 用户头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userpassword;

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
     * 记录创建时间
     */
    private Date createtime;

    /**
     * 记录更新时间
     */
    private Date updatetime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isdelete;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}