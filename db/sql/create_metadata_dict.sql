create table metadata_dict
(
  metadata_id   int auto_increment
    primary key,
  metadata_name char(32)    null,
  metadata_desp char(24)    null,
  metadata_type int         not null,
  dbname        varchar(32) null
)
  comment '系统用户表';

