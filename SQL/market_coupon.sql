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

 Date: 01/07/2022 11:41:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for market_coupon
-- ----------------------------
DROP TABLE IF EXISTS `market_coupon`;
CREATE TABLE `market_coupon`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '优惠券名称',
  `desc` varchar(127) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '优惠券介绍，通常是显示优惠券使用限制文字',
  `tag` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '优惠券标签，例如新人专用',
  `total` int(11) NOT NULL DEFAULT 0 COMMENT '优惠券数量，如果是0，则是无限量',
  `discount` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '优惠金额，',
  `min` decimal(10, 2) NULL DEFAULT 0.00 COMMENT '最少消费金额才能使用优惠券。',
  `limit` smallint(6) NULL DEFAULT 1 COMMENT '用户领券限制数量，如果是0，则是不限制；默认是1，限领一张.',
  `type` smallint(6) NULL DEFAULT 0 COMMENT '优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换；',
  `status` smallint(6) NULL DEFAULT 0 COMMENT '优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架。',
  `goods_type` smallint(6) NULL DEFAULT 0 COMMENT '商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制。',
  `goods_value` varchar(1023) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '[]' COMMENT '商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合。',
  `code` varchar(63) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '优惠券兑换码',
  `time_type` smallint(6) NULL DEFAULT 0 COMMENT '有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期；',
  `days` smallint(6) NULL DEFAULT 0 COMMENT '基于领取时间的有效天数days。',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '使用券开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '使用券截至时间',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `deleted` tinyint(1) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `code`(`code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '优惠券信息及规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of market_coupon
-- ----------------------------
INSERT INTO `market_coupon` VALUES (1, '限时满减券', '全场通用', '无限制', 1, 5.00, 99.00, 2, 0, 0, 0, '[]', 'DC6FF8SF', 0, 10, NULL, NULL, '2018-02-01 00:00:00', '2022-06-26 19:38:13', 0);
INSERT INTO `market_coupon` VALUES (2, '限时满减券', '全场通用', '无限制', 2, 10.00, 99.00, 1, 0, 0, 0, '[]', 'DC6FF8SR', 0, 10, NULL, NULL, '2018-02-01 00:00:00', '2018-02-01 00:00:00', 0);
INSERT INTO `market_coupon` VALUES (3, '新用户优惠券', '全场通用', '无限制', 2, 10.00, 99.00, 1, 1, 0, 0, '[]', 'DC6FF8SA', 0, 10, NULL, NULL, '2018-02-01 00:00:00', '2018-02-01 00:00:00', 0);
INSERT INTO `market_coupon` VALUES (8, '可兑换优惠券', '全场通用', '仅兑换领券', 13, 16.00, 99.00, 1, 2, 0, 0, '[]', 'DC6FF8SE', 0, 7, NULL, NULL, '2018-12-23 09:29:57', '2022-07-01 11:38:52', 0);
INSERT INTO `market_coupon` VALUES (9, '老用户返场优惠', '全场通用', '无限制', 43, 200.00, 998.00, 1, 0, 0, 0, '[]', 'DC6FF8SA', 0, 15, NULL, NULL, NULL, '2022-06-29 20:48:29', 0);
INSERT INTO `market_coupon` VALUES (10, '老用户返场优惠', '全场通用', '无限制', 48, 200.00, 998.00, 2, 0, 0, 0, '[]', NULL, 0, 15, NULL, NULL, NULL, '2022-06-30 17:49:55', 0);
INSERT INTO `market_coupon` VALUES (12, '新老用户共享优惠', '全场可用', '无限制', 15, 300.00, 668.00, 1, 0, 0, 0, '[]', NULL, 0, 15, NULL, NULL, NULL, NULL, 0);
INSERT INTO `market_coupon` VALUES (14, '黄金会员优惠券', '全场可用', '无限制', 16, 30.00, 88.00, 1, 0, 0, 0, '[]', NULL, 1, 0, '2022-06-30 00:00:00', '2022-07-26 00:00:00', '2022-06-26 22:06:53', '2022-06-30 20:03:01', 0);
INSERT INTO `market_coupon` VALUES (15, '白银会员优惠券', '全场通用', '无限制', 13, 300.00, 1000.00, 2, 0, 0, 0, '[]', NULL, 0, 15, NULL, NULL, '2022-06-27 16:21:15', '2022-06-30 21:26:41', 0);
INSERT INTO `market_coupon` VALUES (18, '至尊会员优惠券', '全场通用', '无限制', 9, 50000.00, 100000.00, 1, 0, 0, 0, '[]', '7EQHQ6HE', 0, 50, NULL, NULL, '2022-06-30 16:41:46', '2022-06-30 19:27:25', 0);

SET FOREIGN_KEY_CHECKS = 1;
