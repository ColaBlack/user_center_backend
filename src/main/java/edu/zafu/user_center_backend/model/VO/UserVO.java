package edu.zafu.user_center_backend.model.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 已登录用户VO
 *
 * @author ColaBlack
 */
@Data
public class UserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long userId;
    /**
     * 用户昵称
     */
    private String userName;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 状态 0-正常 1-vip 2-ban 3-管理员
     */
    private Integer userRole;

    /**
     * 用户简介
     */
    private String userProfile;

    @Override
    public String toString() {
        return "UserVO{" + "userId=" + userId + ", userName='" + userName + '\'' + ", userAccount='" + userAccount + '\'' + ", userAvatar='" + userAvatar + '\'' + ", userRole=" + userRole + ", userProfile='" + userProfile + '\'' + '}';
    }
}