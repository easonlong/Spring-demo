DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`(
`uid` VARCHAR(32) COMMENT '唯一id',
`username` VARCHAR(32)COMMENT '用户名',
`password` VARCHAR(32)COMMENT '密码',
PRIMARY KEY (`uid`),
UNIQUE KEY `username` (`username`) USING BTREE
) COMMENT '用户表';
INSERT INTO t_user VALUES('1', 'eason', '213213');
INSERT INTO t_user VALUES('2', 'jack', '213213');
INSERT INTO t_user VALUES('3', 'lucy', '213213');
INSERT INTO t_user VALUES('4', 'pal', '213213');