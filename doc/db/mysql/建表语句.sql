-- 创建custom_table数据库
drop database if exists custom_table;
create database if not exists custom_table default charset = utf8mb4 collate utf8mb4_unicode_ci;
use custom_table;

-- custom_table，自定义表
drop table if exists custom_table;
create table if not exists custom_table
(
    id           int unsigned auto_increment primary key comment '主键，无符号，自增',
    name         varchar(64) not null comment '名称',
    remark       varchar(511) comment '描述',
    gmt_create   datetime    not null default current_timestamp comment '创建时间，必备字段。默认为当前时间，不允许更改。',
    gmt_modified datetime    not null default current_timestamp comment '修改时间，必备字段。ON UPDATE CURRENT_TIMESTAMP',
    deleted      int         not null default 0 comment '逻辑删除。0，否；非0，是。类型与该表主键保持一致'
);
alter table custom_table
    add unique (name, deleted);

-- column_type，字段类型
drop table if exists column_type;
create table if not exists column_type
(
    id           int unsigned auto_increment primary key comment '主键，无符号，自增',
    name         varchar(64) not null comment '名称',
    remark       varchar(511) comment '备注',
    gmt_create   datetime    not null default current_timestamp comment '创建时间，必备字段。默认为当前时间，不允许更改。',
    gmt_modified datetime    not null default current_timestamp comment '修改时间，必备字段。ON UPDATE CURRENT_TIMESTAMP',
    deleted      int         not null default 0 comment '逻辑删除。0，否；非0，是。类型与该表主键保持一致'
);
alter table column_type
    add unique (name, deleted);

-- column，字段表
drop table if exists column_info;
create table if not exists column_info
(
    id           int unsigned auto_increment primary key comment '主键，无符号，自增',
    custom_table int         not null comment '所属的表。关联custom_table表',
    column_type  int         not null comment '该列的类型',
    name         varchar(64) not null comment '列名ZH',
    value        varchar(64) not null comment '列名',
    remark       varchar(511) comment '备注',
    sort         int                  default 0 comment '',
    gmt_create   datetime    not null default current_timestamp comment '创建时间，必备字段。默认为当前时间，不允许更改。',
    gmt_modified datetime    not null default current_timestamp comment '修改时间，必备字段。ON UPDATE CURRENT_TIMESTAMP',
    deleted      int         not null default 0 comment '逻辑删除。0，否；非0，是。类型与该表主键保持一致'
);
alter table column_info
    add unique (custom_table, name, deleted);
alter table column_info
    add unique (custom_table, value, deleted);

-- custom_table_data，自定义数据表
drop table if exists custom_table_data;
create table if not exists custom_table_data
(
    id           int unsigned auto_increment primary key comment '主键，无符号，自增',
    column_info  int          not null comment '该值所属的字段',
    value        varchar(511) not null comment '该字段的值',
    line         varchar(64) comment '行号，该值用于表示哪些数据为同一行数据',
    gmt_create   datetime     not null default current_timestamp comment '创建时间，必备字段。默认为当前时间，不允许更改。',
    gmt_modified datetime     not null default current_timestamp comment '修改时间，必备字段。ON UPDATE CURRENT_TIMESTAMP',
    deleted      int          not null default 0 comment '逻辑删除。0，否；非0，是。类型与该表主键保持一致'
);