-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
                               `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限值',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                         `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
                         `level` int(11) NOT NULL COMMENT '角色等级',
                         `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
                                    `role_id` int(11) NOT NULL,
                                    `permission_id` int(11) NOT NULL,
                                    PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
                              `user_id` int(11) NOT NULL COMMENT '用户ID',
                              `role_id` int(11) NOT NULL COMMENT '角色ID',
                              PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                         `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账户',
                         `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
                         `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
                         `gender` int(1) NOT NULL DEFAULT 0 COMMENT '性别（0：保密，1：女，2：男）',
                         `phone_number` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
                         `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
                         `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像地址',
                         `birthday` date NULL DEFAULT NULL COMMENT '生日',
                         `states` int(1) NOT NULL DEFAULT 1 COMMENT '状态',
                         `register_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '注册时间',
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

INSERT INTO `cat`.`user`(`id`, `username`, `password`, `nick_name`, `gender`, `phone_number`, `email`, `avatar`, `birthday`, `status`, `register_time`) VALUES (1, 'admin', '$2a$10$fx73BdgI5WzMvgYqilkkW.GgsbTvaJodWHmmh21FzAS6NhS8.NjV6', 'admin', 0, NULL, NULL, NULL, NULL, 1, '2022-10-14 17:40:06');

INSERT INTO `cat`.`permission`(`id`, `name`, `value`) VALUES (1, '权限管理', 'permission');
INSERT INTO `cat`.`permission`(`id`, `name`, `value`) VALUES (2, '用户管理', 'user');

INSERT INTO `cat`.`role`(`id`, `name`, `level`, `description`) VALUES (1, '超级管理员', 0, NULL);

INSERT INTO `cat`.`role_permission`(`role_id`, `permission_id`) VALUES (1, 1);
INSERT INTO `cat`.`role_permission`(`role_id`, `permission_id`) VALUES (1, 2);

INSERT INTO `cat`.`user_role`(`user_id`, `role_id`) VALUES (1, 1);
