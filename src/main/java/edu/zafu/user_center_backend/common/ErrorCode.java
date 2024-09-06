package edu.zafu.user_center_backend.common;

import lombok.Getter;

/**
 * 自定义错误码
 *
 * @author ColaBlack
 */
@Getter
public enum ErrorCode {
    /**
     * code 业务状态码
     * message 状态信息
     */
    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败");

    /**
     * 业务状态码
     */
    private final int code;

    /**
     * 状态信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
