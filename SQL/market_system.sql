/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 30/06/2022 19:30:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for market_system
-- ----------------------------
DROP TABLE IF EXISTS `market_system`;
CREATE TABLE `market_system`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统配置名',
  `key_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '系统配置值',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统配置表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of market_system
-- ----------------------------
INSERT INTO `market_system` VALUES (1, 'market_order_unpaid', '30', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (2, 'market_wx_index_new', '66', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (3, 'market_mall_latitude', '31.201900', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (4, 'market_order_unconfirm', '777', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (5, 'market_wx_share', 'true', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (6, 'market_express_freight_min', '99', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (7, 'market_mall_name', 'market', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (8, 'market_express_freight_value', '8', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (9, 'market_mall_qq', '139', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (10, 'market_wx_index_hot', '7', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (11, 'market_order_comment', '7', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (12, 'market_wx_catlog_goods', '4', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (13, 'market_mall_longitude', '121.587839', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (14, 'market_mall_phone', '13800000000', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (15, 'market_wx_catlog_list', '8', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (16, 'market_mall_address', '武汉', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (17, 'market_wx_index_brand', '3', NULL, NULL, 0);
INSERT INTO `market_system` VALUES (18, 'market_wx_index_topic', '4', NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
