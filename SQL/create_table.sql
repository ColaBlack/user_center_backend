CREATE DATABASE IF NOT EXISTS user_center;

USE user_center;

# 用户表

CREATE TABLE IF NOT EXISTS user
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    username     VARCHAR(256)                       NULL COMMENT '用户昵称',
    userAccount  VARCHAR(256)                       NULL COMMENT '用户账号',
    avatar       VARCHAR(1024)                      NULL COMMENT '用户头像',
    gender       TINYINT                            NULL COMMENT '性别',
    userPassword VARCHAR(512)                       NOT NULL COMMENT '密码',
    email        VARCHAR(512)                       NULL COMMENT '邮箱',
    userStatus   INT      DEFAULT 0                 NULL COMMENT '状态 0-正常 1-vip 2-ban 3-管理员',
    phone        VARCHAR(128)                       NULL COMMENT '电话',
    createTime   DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '记录创建时间',
    updateTime   DATETIME DEFAULT CURRENT_TIMESTAMP null ON UPDATE CURRENT_TIMESTAMP COMMENT '记录更新时间',
    isDelete    TINYINT DEFAULT 0 NOT NULL COMMENT '是否删除',
    userProfile TEXT              NULL COMMENT '用户简介'
);