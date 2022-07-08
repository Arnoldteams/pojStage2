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

 Date: 01/07/2022 10:06:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for market_order
-- ----------------------------
DROP TABLE IF EXISTS `market_order`;
CREATE TABLE `market_order`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户表的用户ID',
  `order_sn` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '订单编号',
  `order_status` smallint(6) NOT NULL COMMENT '订单状态',
  `aftersale_status` smallint(6) NULL DEFAULT 0 COMMENT '售后状态，0是可申请，1是用户已申请，2是管理员审核通过，3是管理员退款成功，4是管理员审核拒绝，5是用户已取消',
  `consignee` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人名称',
  `mobile` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货人手机号',
  `address` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '收货具体地址',
  `message` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户订单留言',
  `goods_price` decimal(10, 2) NOT NULL COMMENT '商品总费用',
  `freight_price` decimal(10, 2) NOT NULL COMMENT '配送费用',
  `coupon_price` decimal(10, 2) NOT NULL COMMENT '优惠券减免',
  `integral_price` decimal(10, 2) NOT NULL COMMENT '用户积分减免',
  `groupon_price` decimal(10, 2) NOT NULL COMMENT '团购优惠价减免',
  `order_price` decimal(10, 2) NOT NULL COMMENT '订单费用， = goods_price + freight_price - coupon_price',
  `actual_price` decimal(10, 2) NOT NULL COMMENT '实付费用， = order_price - integral_price',
  `pay_id` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '微信付款编号',
  `pay_time` datetime(0) NULL DEFAULT NULL COMMENT '微信付款时间',
  `ship_sn` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发货编号',
  `ship_channel` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发货快递公司',
  `ship_time` datetime(0) NULL DEFAULT NULL COMMENT '发货开始时间',
  `refund_amount` decimal(10, 2) NULL DEFAULT NULL COMMENT '实际退款金额，（有可能退款金额小于实际支付金额）',
  `refund_type` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款方式',
  `refund_content` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '退款备注',
  `refund_time` datetime(0) NULL DEFAULT NULL COMMENT '退款时间',
  `confirm_time` datetime(0) NULL DEFAULT NULL COMMENT '用户确认收货时间',
  `comments` smallint(6) NULL DEFAULT 0 COMMENT '待评价订单商品数量',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '订单关闭时间',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of market_order
-- ----------------------------
INSERT INTO `market_order` VALUES (1, 2, '20220626056297\r\n', 301, 0, 'user2', '13811111111', '北京市市辖区东城区 101\r\n', 'nice', 200.00, 10.00, 0.00, 0.00, 0.00, 210.00, 210.00, NULL, '2022-06-27 09:05:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-06-29 02:06:55', 0, NULL, '2022-06-27 02:07:14', '2022-06-27 02:07:35', 0);
INSERT INTO `market_order` VALUES (2, 2, '20220626056298\r\n', 301, 0, 'user2', '13811111111', '北京市市辖区东城区 101\r\n', 'nice', 200.00, 10.00, 0.00, 0.00, 0.00, 210.00, 210.00, NULL, '2022-06-27 09:05:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-06-29 02:06:55', 0, NULL, '2022-06-27 02:07:14', '2022-06-27 02:07:35', 0);
INSERT INTO `market_order` VALUES (3, 3, '20220626054298\r\n', 301, 0, 'user3', '13811111111', '北京市市辖区东城区 101\r\n', 'nice', 200.00, 10.00, 0.00, 0.00, 0.00, 210.00, 210.00, NULL, '2022-05-27 09:05:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-05-29 02:06:55', 0, NULL, '2022-05-27 02:07:14', '2022-05-27 02:07:35', 0);
INSERT INTO `market_order` VALUES (4, 3, '20220627054298\r\n', 301, 0, 'user3', '13811111111', '北京市市辖区东城区 101\r\n', 'nice', 200.00, 10.00, 0.00, 0.00, 0.00, 210.00, 210.00, NULL, '2022-05-27 09:05:40', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '2022-05-29 02:06:55', 0, NULL, '2022-05-24 02:07:14', '2022-05-27 02:07:35', 0);

SET FOREIGN_KEY_CHECKS = 1;
