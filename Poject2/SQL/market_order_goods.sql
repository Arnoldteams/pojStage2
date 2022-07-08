/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 01/07/2022 10:07:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for market_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `market_order_goods`;
CREATE TABLE `market_order_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL DEFAULT 0 COMMENT '订单表的订单ID',
  `goods_id` int(11) NOT NULL DEFAULT 0 COMMENT '商品表的商品ID',
  `goods_name` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品名称',
  `goods_sn` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品编号',
  `product_id` int(11) NOT NULL DEFAULT 0 COMMENT '商品货品表的货品ID',
  `number` smallint(5) NOT NULL DEFAULT 0 COMMENT '商品货品的购买数量',
  `price` decimal(10, 2) NOT NULL COMMENT '商品货品的售价',
  `specifications` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品货品的规格列表',
  `pic_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '商品货品图片或者商品图片',
  `comment` int(11) NULL DEFAULT 0 COMMENT '订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID。',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id`(`order_id`) USING BTREE,
  INDEX `goods_id`(`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of market_order_goods
-- ----------------------------
INSERT INTO `market_order_goods` VALUES (1, 1, 1006002, '轻奢纯棉刺绣水洗四件套', '1006002', 7, 10, 1000.00, '标准', '11111111', 0, '2022-05-01 09:40:14', NULL, 0);
INSERT INTO `market_order_goods` VALUES (2, 1, 1006002, '轻奢纯棉刺绣水洗四件套', '1006002', 7, 20, 2000.00, '标准', '11111111', 0, '2022-05-12 09:40:25', NULL, 0);
INSERT INTO `market_order_goods` VALUES (3, 1, 1006002, '轻奢纯棉刺绣水洗四件套', '1006002', 7, 30, 3000.00, '标准', '11111111', 0, '2022-06-27 09:40:37', NULL, 0);
INSERT INTO `market_order_goods` VALUES (4, 1, 1006002, '轻奢纯棉刺绣水洗四件套', '1006002', 7, 40, 4000.00, '标准', '11111111', 0, '2022-06-27 09:40:43', NULL, 0);
INSERT INTO `market_order_goods` VALUES (5, 1, 1006002, '轻奢纯棉刺绣水洗四件套', '1006002', 7, 50, 5000.00, '标准', '11111111', 0, '2022-05-12 09:40:48', NULL, 0);
INSERT INTO `market_order_goods` VALUES (6, 1, 1006002, '轻奢纯棉刺绣水洗四件套', '1006002', 7, 60, 6000.00, '标准', '11111111', 0, '2022-06-27 09:40:56', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
