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

    public String tostring(UserVO userVO) {
        return "LoginUserVO{" +
                "id=" + userVO.getId() +
                ", username='" + userVO.getUsername() + '\'' +
                ", useraccount='" + userVO.getUseraccount() + '\'' +
                ", avatar='" + userVO.getAvatar() + '\'' +
                ", gender=" + userVO.getGender() +
                ", email='" + userVO.getEmail() + '\'' +
                ", userstatus=" + userVO.getUserstatus() +
                ", phone='" + userVO.getPhone() + '\'' +
                '}';
    }
}