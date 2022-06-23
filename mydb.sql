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

 Date: 23/06/2022 22:38:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'admin', 'admin', 'admin', 'admin');
INSERT INTO `admin` VALUES (2, '124324@qq.com', 'admin2', 'admin', 'admin');
INSERT INTO `admin` VALUES (17, 'ccc', 'xxx', 'xxx', 'xx');
INSERT INTO `admin` VALUES (18, 'ccc', 'xxx', 'xxx', 'xx');

-- ----------------------------
-- Table structure for cskaoyan_account
-- ----------------------------
DROP TABLE IF EXISTS `cskaoyan_account`;
CREATE TABLE `cskaoyan_account`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `money` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cskaoyan_account
-- ----------------------------
INSERT INTO `cskaoyan_account` VALUES (1, '1210');
INSERT INTO `cskaoyan_account` VALUES (2, '1830');

-- ----------------------------
-- Table structure for cskaoyan_user
-- ----------------------------
DROP TABLE IF EXISTS `cskaoyan_user`;
CREATE TABLE `cskaoyan_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cskaoyan_user
-- ----------------------------
INSERT INTO `cskaoyan_user` VALUES (3, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (4, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (5, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (6, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (7, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (8, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (9, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (10, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (11, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (12, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (13, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (14, '北海', '长风', 25);
INSERT INTO `cskaoyan_user` VALUES (15, '北海', '长风', 25);

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `image` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `typeId` int(11) NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `stockNum` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, 'apple', 'image/4/d/d/9/5/c/4/0/e684ba8b-e3fb-44bc-b962-7fa4353078f2-2.jpg', 192, '34', 213.00, 32);
INSERT INTO `goods` VALUES (2, 'banana', 'image/4/d/d/9/5/c/4/0/e684ba8b-e3fb-44bc-b962-7fa4353078f2-2.jpg', 192, '34', 34.00, 34);
INSERT INTO `goods` VALUES (3, 'fish', 'image/4/d/d/9/5/c/4/0/e684ba8b-e3fb-44bc-b962-7fa4353078f2-2.jpg', 192, '234', 3.00, 345);
INSERT INTO `goods` VALUES (4, '男裤', 'image/4/d/d/9/5/c/4/0/e684ba8b-e3fb-44bc-b962-7fa4353078f2-2.jpg', 190, '45', 43.00, 234);
INSERT INTO `goods` VALUES (6, '音响', 'image/4/d/d/9/5/c/4/0/e684ba8b-e3fb-44bc-b962-7fa4353078f2-2.jpg', 191, '34', 43.00, 23);
INSERT INTO `goods` VALUES (7, '床头柜', '192.168.13.114:8084/static/image/2.jpg', 193, '666', 2342.00, 34);
INSERT INTO `goods` VALUES (8, 'cat', 'image/4/d/d/9/5/c/4/0/e684ba8b-e3fb-44bc-b962-7fa4353078f2-2.jpg', 192, '营养高', 2.00, 4);
INSERT INTO `goods` VALUES (12, 'DF-26', 'image/f/0/e/b/0/0/5/f/5eae3bbf-2935-4b54-bcec-3fb33b97cafb-Snipaste_2022-06-10_09-41-40.jpg', 888, '', 1.00, 35);
INSERT INTO `goods` VALUES (17, '呢绒大衣', 'image/6/e/3/2/0/7/c/6/d246b500-d99a-4f04-b37b-cc762aa1b2d0-11.jpg', 1, '女式服装', 299.00, 33);
INSERT INTO `goods` VALUES (22, '111', 'image/7/1/a/d/5/c/7/a/1d508a3b-fe35-4a51-a9aa-a1ca589d386f-12.jpg', 1, NULL, 199.00, 22);
INSERT INTO `goods` VALUES (24, 'xxxx', 'xxx', 1, NULL, 0.00, 0);
INSERT INTO `goods` VALUES (25, 'xxxx', 'xxx', 1, NULL, 0.00, 0);

-- ----------------------------
-- Table structure for goods_specs
-- ----------------------------
DROP TABLE IF EXISTS `goods_specs`;
CREATE TABLE `goods_specs`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `stockNum` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `price` decimal(10, 2) NULL DEFAULT NULL,
  `goodsId` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_specs
-- ----------------------------
INSERT INTO `goods_specs` VALUES (1, '规格1', '100', 100.00, 1);
INSERT INTO `goods_specs` VALUES (2, '规格2', '200', 20.00, 1);
INSERT INTO `goods_specs` VALUES (3, '规格6', '1', 1.00, 2);
INSERT INTO `goods_specs` VALUES (4, '默认', '66', 88.00, 3);
INSERT INTO `goods_specs` VALUES (5, '1', '6', 88.00, 4);
INSERT INTO `goods_specs` VALUES (7, '1', '54', 9.00, 6);
INSERT INTO `goods_specs` VALUES (8, '1', '34', 28.00, 6);
INSERT INTO `goods_specs` VALUES (9, '6', '6', 6.00, 7);
INSERT INTO `goods_specs` VALUES (14, '6', '6', 6.00, 7);
INSERT INTO `goods_specs` VALUES (19, 'fff', '454', 5.00, 7);
INSERT INTO `goods_specs` VALUES (24, '默认', '1', 1.00, 8);
INSERT INTO `goods_specs` VALUES (38, '2', '2', 2.00, 8);
INSERT INTO `goods_specs` VALUES (47, '大', '1', 10000.00, 12);
INSERT INTO `goods_specs` VALUES (48, '小', '33', 12.00, 12);
INSERT INTO `goods_specs` VALUES (49, '超级大', '1', 1.00, 12);
INSERT INTO `goods_specs` VALUES (50, '黑', '10', 299.00, NULL);
INSERT INTO `goods_specs` VALUES (51, '灰', '23', 399.00, NULL);
INSERT INTO `goods_specs` VALUES (52, '默认', '1', 1.00, NULL);
INSERT INTO `goods_specs` VALUES (54, '棕色', '66', 99.00, 6);
INSERT INTO `goods_specs` VALUES (55, '黑色', '43', 88.00, NULL);
INSERT INTO `goods_specs` VALUES (56, '1', '22', 199.00, NULL);
INSERT INTO `goods_specs` VALUES (57, '1', '22', 199.00, NULL);

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 889 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type` VALUES (1, '女装');
INSERT INTO `goods_type` VALUES (190, '男装');
INSERT INTO `goods_type` VALUES (191, '电子产品');
INSERT INTO `goods_type` VALUES (192, '食品');
INSERT INTO `goods_type` VALUES (888, '东风导弹');

-- ----------------------------
-- Table structure for order_comment
-- ----------------------------
DROP TABLE IF EXISTS `order_comment`;
CREATE TABLE `order_comment`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `goodsName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `goodsId` int(11) NULL DEFAULT NULL,
  `orderId` int(11) NULL DEFAULT NULL,
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `replyContent` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `state` int(11) NULL DEFAULT NULL,
  `createTime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_comment
-- ----------------------------
INSERT INTO `order_comment` VALUES (1, 'admin', 1, 'fish', 1, 2, '可以', '非常非常号', 0, '2022-06-13 14:47:42');
INSERT INTO `order_comment` VALUES (2, 'admin', 1, 'fish', 2, 4, '牛鞭', '牛哇', 3, '2022-06-13 13:58:22');
INSERT INTO `order_comment` VALUES (3, 'admin', 1, 'banana', 2, 2, '一个字强！', NULL, 3, '2022-06-13 13:57:50');
INSERT INTO `order_comment` VALUES (4, 'admin', 1, 'banana', 2, 19, '很香', NULL, 3, '2022-06-13 14:31:13');
INSERT INTO `order_comment` VALUES (5, 'admin', 1, 'DF-26', 12, 17, '厉害劲大', NULL, 3, '2022-06-13 14:42:37');

-- ----------------------------
-- Table structure for order_msg
-- ----------------------------
DROP TABLE IF EXISTS `order_msg`;
CREATE TABLE `order_msg`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `userId` int(11) NULL DEFAULT NULL,
  `goodsName` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `goodsId` int(11) NULL DEFAULT NULL,
  `orderId` int(11) NULL DEFAULT NULL,
  `content` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `replyContent` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `asker` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `state` int(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_msg
-- ----------------------------
INSERT INTO `order_msg` VALUES (1, 'zs', 1, 'fish', 1, 2, '这个好嘛', '非常不错6766', 'abc', '2022-06-13 14:49:50', 0);
INSERT INTO `order_msg` VALUES (2, 'admin', 1, 'DF-26', 12, NULL, '666', '888', 'admin', '2022-06-13 15:06:22', 0);
INSERT INTO `order_msg` VALUES (3, 'admin', 1, 'DF-26', 12, NULL, '1', '323', 'admin', '2022-06-13 15:29:55', 0);
INSERT INTO `order_msg` VALUES (4, 'admin', 1, 'DF-26', 12, NULL, '2', '343', 'admin', '2022-06-13 15:30:03', 0);
INSERT INTO `order_msg` VALUES (5, 'admin', 1, 'DF-26', 12, NULL, '3', NULL, 'admin', '2022-06-13 15:11:47', 1);

-- ----------------------------
-- Table structure for orderlist
-- ----------------------------
DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `receiver` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `goods` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `goodsId` int(11) NULL DEFAULT NULL,
  `spec` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `specId` int(11) NULL DEFAULT NULL,
  `number` int(11) NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `stateId` int(11) NULL DEFAULT NULL,
  `createtime` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `hasComment` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `score` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderlist
-- ----------------------------
INSERT INTO `orderlist` VALUES (2, 1, 'as', 'ls', 'TJ', '13888888888', 'fish', 2, '2', 3, 100, 100, 3, '2022-06-13 14:28:27', 'false', 60);
INSERT INTO `orderlist` VALUES (4, 1, 'as', 'ls', 'TJ', '13888888888', 'fish', 3, '3', 2, 2, 176, 3, '2022-06-13 14:28:23', 'false', 80);
INSERT INTO `orderlist` VALUES (5, 1, 'as', 'ls', 'TJ', '13888888888', 'fish', 4, '4', 3, 6, 10, 2, '2022-06-13 14:41:46', 'false', 100);
INSERT INTO `orderlist` VALUES (6, 1, 'as', 'ls', 'TJ', '13888888888', 'fish', 5, '5', 4, 3, 234, 1, '2022-06-13 14:16:59', 'false', 100);
INSERT INTO `orderlist` VALUES (7, 1, 'as', 'ls', 'TJ', '13888888888', 'fish', 6, '7', 4, 10, 10, 3, '2022-06-13 14:17:01', 'false', 100);
INSERT INTO `orderlist` VALUES (8, 1, 'as', 'ls', 'SH', '13888888888', 'fish', 7, '1', 4, 4, 10, 2, '2022-06-13 14:41:57', 'false', 100);
INSERT INTO `orderlist` VALUES (9, 1, 'as', 'ls', 'SH', '13888888888', 'fish', 8, '1', 4, 5, 5, 1, '2022-06-13 14:29:50', 'true', 100);
INSERT INTO `orderlist` VALUES (13, 1, 'admin', '1', 'TJ', '13888888888', 'apple', 1, '规格1', 1, 1, 100, 1, '2022-06-13 14:29:49', 'false', 100);
INSERT INTO `orderlist` VALUES (14, 1, 'admin', '1', 'TJ', '13888888888', 'apple', 1, '规格1', 1, 1, 100, 1, '2022-06-13 14:29:49', 'false', 100);
INSERT INTO `orderlist` VALUES (15, 1, 'admin', '1', 'TJ', '13888888888', '音响', 6, '1', 7, 1, 9, 1, '2022-06-13 14:17:16', 'false', 100);
INSERT INTO `orderlist` VALUES (16, 1, 'admin', '1', 'TJ', '13888888888', 'cat', 8, '2', 38, 2, 2, 1, '2022-06-13 14:29:50', '0', 0);
INSERT INTO `orderlist` VALUES (17, 1, 'admin', '1', 'TJ', '13888888888', 'DF-26', 12, '小', 48, 1, 10000, 3, '2022-06-13 14:42:37', '0', 100);
INSERT INTO `orderlist` VALUES (18, 1, 'admin', '1', 'TJ', '13888888888', 'banana', 2, '规格6', 3, 1, 1, 1, '2022-06-13 14:29:49', '0', 0);
INSERT INTO `orderlist` VALUES (19, 1, 'admin', '1', 'TJ', '13888888888', 'banana', 2, '规格6', 3, 1, 1, 3, '2022-06-13 14:39:16', '0', 100);
INSERT INTO `orderlist` VALUES (21, 1, 'admin', '1', 'TJ', '13888888888', 'apple', 1, '规格1', 1, 66, 88, 0, '2022-06-14 16:41:34', '0', 0);
INSERT INTO `orderlist` VALUES (22, 1, 'admin', '1', 'TJ', '13888888888', 'apple', 1, '规格1', 1, 66, 88, 0, '2022-06-14 16:42:05', '0', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `recipient` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `birthday` date NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 70 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 'admin', 'admin', 'TJ', '13888888888', '1', NULL);
INSERT INTO `user` VALUES (2, 'aws', '张三', '222', '13110727472@qq.com', '火星', '13888888889', '张三', NULL);
INSERT INTO `user` VALUES (64, '666888@qq.com', 'ShiQiang2', '@Abcd123', '666888@qq.com', '联合国大厦103', '15600000000', '9', '2022-06-02');
INSERT INTO `user` VALUES (65, 'zs', NULL, '111', '111@qq.com', NULL, NULL, NULL, '2016-01-22');
INSERT INTO `user` VALUES (66, 'zss', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (67, 'zss', NULL, NULL, NULL, NULL, NULL, NULL, '2022-06-21');
INSERT INTO `user` VALUES (68, 'zsss', NULL, '666', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user` VALUES (69, 'zsss', NULL, '666', NULL, NULL, NULL, NULL, '2008-10-02');

SET FOREIGN_KEY_CHECKS = 1;
