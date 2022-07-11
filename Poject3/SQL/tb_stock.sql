/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : csmall

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 11/07/2022 09:13:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_stock
-- ----------------------------
DROP TABLE IF EXISTS `tb_stock`;
CREATE TABLE `tb_stock`  (
  `item_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '商品id',
  `stock_count` bigint(20) NOT NULL DEFAULT 0 COMMENT '库存数量',
  `lock_count` int(11) NOT NULL DEFAULT 0 COMMENT '冻结库存数量',
  `restrict_count` int(3) NULL DEFAULT 5 COMMENT '限购数量',
  `sell_id` int(6) NULL DEFAULT NULL COMMENT '售卖id',
  PRIMARY KEY (`item_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tb_stock
-- ----------------------------
INSERT INTO `tb_stock` VALUES (100023501, 1000, 0, 5, NULL);
INSERT INTO `tb_stock` VALUES (100026701, 1000, 0, 2, NULL);
INSERT INTO `tb_stock` VALUES (100026801, 988, 12, 3, NULL);
INSERT INTO `tb_stock` VALUES (100036501, 1000, 0, 4, NULL);
INSERT INTO `tb_stock` VALUES (100039702, 994, 6, 5, NULL);
INSERT INTO `tb_stock` VALUES (100040501, 1000, 0, 2, NULL);
INSERT INTO `tb_stock` VALUES (100040607, 988, 12, 3, NULL);
INSERT INTO `tb_stock` VALUES (100042203, 1000, 0, 4, NULL);
INSERT INTO `tb_stock` VALUES (100042801, 1000, 0, 5, NULL);
INSERT INTO `tb_stock` VALUES (100046401, 971, 29, 2, NULL);
INSERT INTO `tb_stock` VALUES (100047001, 1000, 0, 3, NULL);
INSERT INTO `tb_stock` VALUES (100047101, 1000, 0, 4, NULL);
INSERT INTO `tb_stock` VALUES (100051701, 971, 29, 5, NULL);
INSERT INTO `tb_stock` VALUES (100052801, 1000, 0, 2, NULL);
INSERT INTO `tb_stock` VALUES (100053001, 1000, 0, 3, NULL);
INSERT INTO `tb_stock` VALUES (100053202, 1000, 0, 4, NULL);
INSERT INTO `tb_stock` VALUES (100053312, 994, 6, 5, NULL);
INSERT INTO `tb_stock` VALUES (100055301, 1000, 0, 5, NULL);
INSERT INTO `tb_stock` VALUES (100055601, 1000, 0, 5, NULL);
INSERT INTO `tb_stock` VALUES (100057401, 993, 7, 5, NULL);
INSERT INTO `tb_stock` VALUES (100057501, 1000, 0, 2, NULL);
INSERT INTO `tb_stock` VALUES (100057601, 1000, 0, 3, NULL);
INSERT INTO `tb_stock` VALUES (100057701, 1000, 0, 4, NULL);

SET FOREIGN_KEY_CHECKS = 1;
