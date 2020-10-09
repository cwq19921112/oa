/*
 Navicat Premium Data Transfer

 Source Server         : myServer
 Source Server Type    : MySQL
 Source Server Version : 50549
 Source Host           : localhost:3306
 Source Schema         : mashibing

 Target Server Type    : MySQL
 Target Server Version : 50549
 File Encoding         : 65001

 Date: 09/10/2020 15:12:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'user',
  `head_img_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NULL DEFAULT '2020-09-29 00:00:00',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`user_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, '陈武强', 'chenwuqiang', '123', 20, '陈武强', '广东省深圳市宝安区西乡街道固戍社区沙边新村二巷七号', 'user', '/198767f3-f89a-4e7d-941b-0451dafd556ccwq.png', '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (2, '陈武强', 'chenwuqiang1', '123', 18, '陈武强', '', 'user', NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (3, '陈武强', 'chenwuqiang2', '123', 18, '陈武强', NULL, 'user', NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (4, '陈武强', 'chenwuqiang3', '123', 18, '陈武强', NULL, 'user', NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (5, '陈武强', 'chenwuqiang4', '123', 18, '陈武强', NULL, 'user', NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (6, '陈武强', 'chenwuqiang5', '123', 18, '陈武强', NULL, 'user', NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (7, '陈武强', 'chenwuqiang6', '123', 18, '陈武强', NULL, 'user', NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (8, '陈武强', 'chenwuqiang7', '123', 18, '陈武强', NULL, 'user', NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (11, NULL, 'chenwuqiang666', '123456', NULL, NULL, NULL, NULL, NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (13, NULL, 'chenwuqiang521', '521', 28, '陈武强', '深圳市宝安区西乡街道固戍社区沙边新村二巷七号', 'admin', '/468d2831-8fb5-43c8-93a5-6c9c559321e6test.jpg', '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (14, NULL, 'zhangsan', 'zhangsan', 22, '张三', '广东省深圳市宝安区西乡街道固戍社区沙边新村二巷七号', 'user', NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (17, NULL, 'lisi', 'lisi', NULL, NULL, NULL, NULL, NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');
INSERT INTO `account` VALUES (18, '王五', 'wangwu', 'wangwu', 23, '王五', NULL, 'user', NULL, '2020-09-29 10:06:14', '2020-09-29 00:00:00');

-- ----------------------------
-- Table structure for account_role
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of account_role
-- ----------------------------
INSERT INTO `account_role` VALUES (1, 1, 1);
INSERT INTO `account_role` VALUES (2, 1, 2);

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uri` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `c` tinyint(1) NULL DEFAULT 0,
  `r` tinyint(1) NULL DEFAULT 0,
  `u` tinyint(1) NULL DEFAULT 0,
  `d` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_uri`(`uri`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '/account/list', '用户列表', 1, 0, 1, 0);
INSERT INTO `permission` VALUES (2, '/role/list1', '角色列表', 0, 1, 0, 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_name`(`name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2, '普通用户');
INSERT INTO `role` VALUES (1, '管理员');

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `permission_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, 1, 1);
INSERT INTO `role_permission` VALUES (2, 1, 2);

SET FOREIGN_KEY_CHECKS = 1;
