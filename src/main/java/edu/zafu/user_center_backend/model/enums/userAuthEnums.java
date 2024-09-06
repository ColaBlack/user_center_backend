package edu.zafu.user_center_backend.model.enums;

import lombok.Getter;

/**
 * 用户权限类型枚举类
 *
 * @author ColaBlack
 */
@Getter
public enum userAuthEnums {
    /**
     * 0: 普通用户
     * 1: VIP
     * 2: ban
     * 3: 管理员
     */
    USER_AUTH_NORMAL(0, "普通用户"),
    USER_AUTH_VIP(1, "VIP"),
    USER_AUTH_BAN(2, "ban"),
    USER_AUTH_ADMIN(3, "管理员");

    private final Integer val;
    private final String desc;

    userAuthEnums(Integer val, String desc) {
        this.val = val;
        this.desc = desc;
    }
}
