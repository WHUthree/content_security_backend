-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    schoolId     varchar(256)                           not null comment '学号',
    userPassword varchar(512)                           not null comment '密码',
    userName     varchar(256)                           not null comment '姓名',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin',
    editTime     datetime     default CURRENT_TIMESTAMP not null comment '编辑时间',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    UNIQUE KEY uk_schoolId (schoolId),
    INDEX idx_userName (userName)
    ) comment '用户' collate = utf8mb4_unicode_ci;

create table if not exists sign_record
(
    id          bigint auto_increment comment '签到记录ID' primary key,
    user_id     bigint                             not null comment '用户ID',
    sign_time   datetime default current_timestamp not null comment '签到时间',
    sign_type   int                                not null comment '签到类型（0 - 正常、1 - 迟到、2 - 成功、3 - 失败）',
    remark      text                               null comment '备注（如异常原因）',
    create_time datetime default current_timestamp not null comment '创建时间',
    update_time datetime default current_timestamp not null on update current_timestamp comment '更新时间',
    index idx_user_id (user_id),
    index idx_sign_time (sign_time)
    ) comment '签到记录表' collate = utf8mb4_unicode_ci;
