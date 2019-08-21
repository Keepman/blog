/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 20/08/2019 17:28:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID ',
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文章标题',
  `article_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章正文',
  `article_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文章作者',
  `article_date` datetime(0) NOT NULL COMMENT '文章创作日期',
  `article_tabloid` mediumtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章摘要',
  `article_star` int(11) NULL DEFAULT NULL COMMENT '文章点赞',
  `article_categories` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文章分类',
  `last_article_id` bigint(20) NULL DEFAULT NULL COMMENT '上一篇文章ID',
  `next_article_id` bigint(20) NULL DEFAULT NULL COMMENT '下一篇文章ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_author`(`article_author`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  CONSTRAINT `t_article_ibfk_1` FOREIGN KEY (`article_author`) REFERENCES `t_user` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (16, 1566289317464, NULL, '<p>ververver</p>\n', '俞民涛', '2019-08-20 16:21:57', '<p>ververver</p>', 0, NULL, 0, 1566289343212);
INSERT INTO `t_article` VALUES (17, 1566289343212, NULL, '<p>fqefvwerver</p>\n', '俞民涛', '2019-08-20 16:22:23', '<p>fqefvwerver</p>', 0, NULL, 1566289317464, NULL);

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `message_commenter_id` int(11) NOT NULL COMMENT '留言者ID',
  `message_date` datetime(0) NULL DEFAULT NULL COMMENT '留言日期',
  `message_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言内容',
  `article_id` bigint(20) NOT NULL COMMENT '留言文章ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `message_commenter_id`(`message_commenter_id`) USING BTREE,
  CONSTRAINT `t_message_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_message_ibfk_2` FOREIGN KEY (`message_commenter_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户权限',
  PRIMARY KEY (`role_id`) USING BTREE,
  INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (2, 'ROLE_ADMIN');
INSERT INTO `t_role` VALUES (1, 'ROLE_USER');
INSERT INTO `t_role` VALUES (3, 'ROLE_VISITOR');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_admin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_psw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号昵称',
  `user_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户权限',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `role_name`(`user_name`) USING BTREE,
  INDEX `user_role`(`user_role`) USING BTREE,
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`user_role`) REFERENCES `t_role` (`role_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'root', '64aaf1eb7cba81517a6c659f86491946', '俞民涛', 'ROLE_ADMIN');
INSERT INTO `t_user` VALUES (2, 'admin', '2224302a7b58a6a8448a4b04b8120c4', '周昱君', 'ROLE_USER');
INSERT INTO `t_user` VALUES (3, 'youke', '8aaa1185f9ada1a1f474ec3420dfcf45', '游客', 'ROLE_VISITOR');

SET FOREIGN_KEY_CHECKS = 1;
