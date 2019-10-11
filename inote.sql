/*
 Navicat Premium Data Transfer

 Source Server         : mrl-connect
 Source Server Type    : MySQL
 Source Server Version : 50720
 Source Host           : localhost:3306
 Source Schema         : inote

 Target Server Type    : MySQL
 Target Server Version : 50720
 File Encoding         : 65001

 Date: 06/06/2018 23:50:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for knowledge
-- ----------------------------
DROP TABLE IF EXISTS `knowledge`;
CREATE TABLE `knowledge` (
  `id` varchar(32) NOT NULL,
  `title` varchar(64) DEFAULT NULL,
  `content` varchar(256) DEFAULT NULL,
  `view_time` int(11) NOT NULL DEFAULT '0',
  `important` int(11) NOT NULL DEFAULT '0',
  `difficult` int(11) NOT NULL DEFAULT '0',
  `parent_id` varchar(32) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `user_id` varchar(32) NOT NULL,
  `imgUrl` varchar(64) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5fngw12qyqcm1fr6ty1sf6krw` (`user_id`),
  CONSTRAINT `FK_5fngw12qyqcm1fr6ty1sf6krw` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `password` varchar(64) NOT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `fans_count` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
