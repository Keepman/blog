/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost
 Source Database       : blog

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : utf-8

 Date: 08/10/2019 10:52:53 AM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `article_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `article_content` varchar(255) DEFAULT NULL,
  `article_author` varchar(255) DEFAULT NULL,
  `article_date` datetime DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  KEY `article_author` (`article_author`),
  CONSTRAINT `t_article_ibfk_1` FOREIGN KEY (`article_author`) REFERENCES `t_role` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_article`
-- ----------------------------
BEGIN;
INSERT INTO `t_article` VALUES ('1', '征文', 'zyj', '2019-08-09 23:13:18'), ('2', '呃丰富企鹅飞', 'ymt', '2019-08-21 23:13:31');
COMMIT;

-- ----------------------------
--  Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `role_admin` varchar(255) DEFAULT NULL,
  `role_psw` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`),
  KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `t_role`
-- ----------------------------
BEGIN;
INSERT INTO `t_role` VALUES ('1', 'ymt', 'root', '64aaf1eb7cba81517a6c659f86491946'), ('2', 'zyj', 'admin', '2224302a7b58a6a8448a4b04b8120c4\n2224302a7b58a6a8448a4b04b8120c4\n2224302a7b58a6a8448a4b04b8120c4\n2224302a7b58a6a8448a4b04b8120c4\n2224302a7b58a6a8448a4b04b8120c4');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
