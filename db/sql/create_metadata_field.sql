create table metadata_field
(
  md_fieldID  int auto_increment
    primary key,
  metadata_id int      not null,
  name        char(32) null,
  desp        char(32) null,
  disp_order  int      null,
  is_show     int      null
);

