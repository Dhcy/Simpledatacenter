-- 2016-04-19
alter table tb_logplat_second_menu add column type int default 0 comment '0-通用;1-单机版;2-联网版';

alter table tb_logplat_game add column gametype tinyint default 0 comment '1-单机版,2-联网版';












