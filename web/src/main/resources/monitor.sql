/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : clustermonitor

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2017-11-16 23:56:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_client_info
-- ----------------------------
DROP TABLE IF EXISTS `t_client_info`;
CREATE TABLE `t_client_info` (
  `id` varchar(20) DEFAULT NULL COMMENT '客户机id',
  `client_system` varchar(10) DEFAULT NULL COMMENT '客户机操作系统名称',
  `client_name` varchar(20) DEFAULT NULL COMMENT '客户机名称',
  `client_ip` varchar(20) DEFAULT NULL COMMENT '客户机ip',
  `client_cpu` varchar(20) DEFAULT NULL COMMENT '客户机CPU型号',
  `client_storage` float DEFAULT NULL COMMENT '客户机硬盘容量（GB）',
  `client_ram` float DEFAULT NULL COMMENT '客户机运行内存（GB）',
  `client_log_path` varchar(20) DEFAULT NULL COMMENT '客户机日志在服务器上的存储路径',
  `client_create_time` date DEFAULT NULL COMMENT '客户机连接服务器的日期',
  `client_update_time` date DEFAULT NULL COMMENT '客户机最近一次更新日期',
  `client_status` int(11) DEFAULT NULL COMMENT '客户机状态 0断线 1在线'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_client_info
-- ----------------------------

-- ----------------------------
-- Table structure for t_conf_info
-- ----------------------------
DROP TABLE IF EXISTS `t_conf_info`;
CREATE TABLE `t_conf_info` (
  `conf_name` varchar(20) DEFAULT NULL COMMENT '配置文件名称',
  `conf_path` varchar(20) DEFAULT NULL COMMENT '配置文件路径',
  `conf_detail` varchar(100) DEFAULT NULL COMMENT '配置文件说明'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_conf_info
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员用户唯一标识',
  `user_name` varchar(20) DEFAULT NULL COMMENT '管理员名称',
  `password` varchar(20) DEFAULT NULL COMMENT '管理员密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
