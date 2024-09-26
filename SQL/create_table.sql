CREATE DATABASE IF NOT EXISTS user_center;

USE user_center;

# 用户表
CREATE TABLE IF NOT EXISTS user
(
    user_id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_nickname VARCHAR(256) DEFAULT '快来设置昵称吧'  NULL COMMENT '用户昵称',
    user_account  VARCHAR(256)                           NULL COMMENT '用户账号',
    user_avatar   VARCHAR(1024)                          NULL COMMENT '用户头像',
    user_password VARCHAR(512)                           NOT NULL COMMENT '密码',
    user_role     INT          DEFAULT 0                 NULL COMMENT '状态 0-正常 1-vip 2-ban 3-管理员',
    create_time   DATETIME     DEFAULT CURRENT_TIMESTAMP NULL COMMENT '记录创建时间',
    update_time   DATETIME     DEFAULT CURRENT_TIMESTAMP NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
    is_delete     TINYINT      DEFAULT 0                 NOT NULL COMMENT '是否删除',
    user_profile  TEXT                                   NULL COMMENT '用户简介'
);

CREATE INDEX user_user_account_index
    ON user (user_account);
