/*
Navicat MySQL Data Transfer

Source Server         : longyi
Source Server Version : 50140
Source Host           : localhost:3306
Source Database       : adp

Target Server Type    : MYSQL
Target Server Version : 50140
File Encoding         : 65001

Date: 2014-05-07 10:45:30
*/

drop database if exists `adp`;
create database `adp`;
use `adp`;

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `managerId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `descrition` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`managerId`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('4', 'longyi', 'longyi', 'manager', 'NORMAL', '描述');
INSERT INTO `manager` VALUES ('14', '123456', '123456', 'manager', 'NORMAL', '你好');
INSERT INTO `manager` VALUES ('19', 'qazwsx', 'qwerty', 'manager', 'NORMAL', null);
