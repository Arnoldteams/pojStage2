/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : mydb

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 27/06/2022 14:48:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for market_permission_table
-- ----------------------------
DROP TABLE IF EXISTS `market_permission_table`;
CREATE TABLE `market_permission_table`  (
  `key` int(255) NOT NULL AUTO_INCREMENT,
  `pid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `api` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`key`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 297 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of market_permission_table
-- ----------------------------
INSERT INTO `market_permission_table` VALUES (151, NULL, '商场管理', '商场管理', NULL, 1);
INSERT INTO `market_permission_table` VALUES (152, '151', '类目管理', '类目管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (153, '152', 'admin:category:update', '编辑', 'POST /admin/category/update', 3);
INSERT INTO `market_permission_table` VALUES (154, '152', 'admin:category:list', '查询', 'GET /admin/category/list', 3);
INSERT INTO `market_permission_table` VALUES (155, '152', 'admin:category:read', '详情', 'GET /admin/category/read', 3);
INSERT INTO `market_permission_table` VALUES (156, '152', 'admin:category:delete', '删除', 'POST /admin/category/delete', 3);
INSERT INTO `market_permission_table` VALUES (157, '152', 'admin:category:create', '添加', 'POST /admin/category/create', 3);
INSERT INTO `market_permission_table` VALUES (158, '151', '订单管理', '订单管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (159, '158', 'admin:order:read', '详情', 'GET /admin/order/detail', 3);
INSERT INTO `market_permission_table` VALUES (160, '158', 'admin:order:reply', '订单商品回复', 'POST /admin/order/reply', 3);
INSERT INTO `market_permission_table` VALUES (161, '158', 'admin:order:refund', '订单退款', 'POST /admin/order/refund', 3);
INSERT INTO `market_permission_table` VALUES (162, '158', 'admin:order:ship', '订单发货', 'POST /admin/order/ship', 3);
INSERT INTO `market_permission_table` VALUES (163, '158', 'admin:order:list', '查询', 'GET /admin/order/list', 3);
INSERT INTO `market_permission_table` VALUES (164, '158', 'admin:order:delete', '订单删除', 'POST /admin/order/delete', 3);
INSERT INTO `market_permission_table` VALUES (165, '151', '通用问题', '通用问题', NULL, 2);
INSERT INTO `market_permission_table` VALUES (166, '165', 'admin:issue:update', '编辑', 'POST /admin/issue/update', 3);
INSERT INTO `market_permission_table` VALUES (167, '165', 'admin:issue:list', '查询', 'GET /admin/issue/list', 3);
INSERT INTO `market_permission_table` VALUES (168, '165', 'admin:issue:delete', '删除', 'POST /admin/issue/delete', 3);
INSERT INTO `market_permission_table` VALUES (169, '165', 'admin:issue:create', '添加', 'POST /admin/issue/create', 3);
INSERT INTO `market_permission_table` VALUES (170, '151', '关键词', '关键词', NULL, 2);
INSERT INTO `market_permission_table` VALUES (171, '170', 'admin:keyword:update', '编辑', 'POST /admin/keyword/update', 3);
INSERT INTO `market_permission_table` VALUES (172, '170', 'admin:keyword:list', '查询', 'GET /admin/keyword/list', 3);
INSERT INTO `market_permission_table` VALUES (173, '170', 'admin:keyword:read', '详情', 'GET /admin/keyword/read', 3);
INSERT INTO `market_permission_table` VALUES (174, '170', 'admin:keyword:delete', '删除', 'POST /admin/keyword/delete', 3);
INSERT INTO `market_permission_table` VALUES (175, '170', 'admin:keyword:create', '添加', 'POST /admin/keyword/create', 3);
INSERT INTO `market_permission_table` VALUES (176, '151', '品牌管理', '品牌管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (177, '176', 'admin:brand:update', '编辑', 'POST /admin/brand/update', 3);
INSERT INTO `market_permission_table` VALUES (178, '176', 'admin:brand:list', '查询', 'GET /admin/brand/list', 3);
INSERT INTO `market_permission_table` VALUES (179, '176', 'admin:brand:read', '详情', 'GET /admin/brand/read', 3);
INSERT INTO `market_permission_table` VALUES (180, '176', 'admin:brand:delete', '删除', 'POST /admin/brand/delete', 3);
INSERT INTO `market_permission_table` VALUES (181, '176', 'admin:brand:create', '添加', 'POST /admin/brand/create', 3);
INSERT INTO `market_permission_table` VALUES (182, NULL, '商品管理', '商品管理', NULL, 1);
INSERT INTO `market_permission_table` VALUES (183, '182', '商品管理', '商品管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (184, '183', 'admin:goods:read', '详情', 'GET /admin/goods/detail', 3);
INSERT INTO `market_permission_table` VALUES (185, '183', 'admin:goods:update', '编辑', 'POST /admin/goods/update', 3);
INSERT INTO `market_permission_table` VALUES (186, '183', 'admin:goods:list', '查询', 'GET /admin/goods/list', 3);
INSERT INTO `market_permission_table` VALUES (187, '183', 'admin:goods:delete', '删除', 'POST /admin/goods/delete', 3);
INSERT INTO `market_permission_table` VALUES (188, '183', 'admin:goods:create', '上架', 'POST /admin/goods/create', 3);
INSERT INTO `market_permission_table` VALUES (189, '182', '评论管理', '评论管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (190, '189', 'admin:comment:list', '查询', 'GET /admin/comment/list', 3);
INSERT INTO `market_permission_table` VALUES (191, '189', 'admin:comment:delete', '删除', 'POST /admin/comment/delete', 3);
INSERT INTO `market_permission_table` VALUES (192, NULL, '用户管理', '用户管理', NULL, 1);
INSERT INTO `market_permission_table` VALUES (193, '192', '用户收藏', '用户收藏', NULL, 2);
INSERT INTO `market_permission_table` VALUES (194, '193', 'admin:collect:list', '查询', 'GET /admin/collect/list', 3);
INSERT INTO `market_permission_table` VALUES (195, '192', '搜索历史', '搜索历史', NULL, 2);
INSERT INTO `market_permission_table` VALUES (196, '195', 'admin:history:list', '查询', 'GET /admin/history/list', 3);
INSERT INTO `market_permission_table` VALUES (197, '192', '收货地址', '收货地址', NULL, 2);
INSERT INTO `market_permission_table` VALUES (198, '197', 'admin:address:list', '查询', 'GET /admin/address/list', 3);
INSERT INTO `market_permission_table` VALUES (199, '192', '意见反馈', '意见反馈', NULL, 2);
INSERT INTO `market_permission_table` VALUES (200, '199', 'admin:feedback:list', '查询', 'GET /admin/feedback/list', 3);
INSERT INTO `market_permission_table` VALUES (201, '192', '用户足迹', '用户足迹', NULL, 2);
INSERT INTO `market_permission_table` VALUES (202, '201', 'admin:footprint:list', '查询', 'GET /admin/footprint/list', 3);
INSERT INTO `market_permission_table` VALUES (203, '192', '会员管理', '会员管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (204, '203', 'admin:user:detail', '详情', 'GET /admin/user/detail', 3);
INSERT INTO `market_permission_table` VALUES (205, '203', 'admin:user:update', '编辑', 'POST /admin/user/update', 3);
INSERT INTO `market_permission_table` VALUES (206, '203', 'admin:user:list', '查询', 'GET /admin/user/list', 3);
INSERT INTO `market_permission_table` VALUES (207, NULL, '推广管理', '推广管理', NULL, 1);
INSERT INTO `market_permission_table` VALUES (208, '207', '团购管理', '团购管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (209, '208', 'admin:groupon:read', '详情', 'GET /admin/groupon/listRecord', 3);
INSERT INTO `market_permission_table` VALUES (210, '208', 'admin:groupon:update', '编辑', 'POST /admin/groupon/update', 3);
INSERT INTO `market_permission_table` VALUES (211, '208', 'admin:groupon:list', '查询', 'GET /admin/groupon/list', 3);
INSERT INTO `market_permission_table` VALUES (212, '208', 'admin:groupon:delete', '删除', 'POST /admin/groupon/delete', 3);
INSERT INTO `market_permission_table` VALUES (213, '208', 'admin:groupon:create', '添加', 'POST /admin/groupon/create', 3);
INSERT INTO `market_permission_table` VALUES (214, '207', '广告管理', '广告管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (215, '214', 'admin:ad:update', '编辑', 'POST /admin/ad/update', 3);
INSERT INTO `market_permission_table` VALUES (216, '214', 'admin:ad:list', '查询', 'GET /admin/ad/list', 3);
INSERT INTO `market_permission_table` VALUES (217, '214', 'admin:ad:read', '详情', 'GET /admin/ad/read', 3);
INSERT INTO `market_permission_table` VALUES (218, '214', 'admin:ad:delete', '删除', 'POST /admin/ad/delete', 3);
INSERT INTO `market_permission_table` VALUES (219, '214', 'admin:ad:create', '添加', 'POST /admin/ad/create', 3);
INSERT INTO `market_permission_table` VALUES (220, '207', '优惠券管理', '优惠券管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (221, '220', 'admin:coupon:listuser', '查询用户', 'GET /admin/coupon/listuser', 3);
INSERT INTO `market_permission_table` VALUES (222, '220', 'admin:coupon:update', '编辑', 'POST /admin/coupon/update', 3);
INSERT INTO `market_permission_table` VALUES (223, '220', 'admin:coupon:list', '查询', 'GET /admin/coupon/list', 3);
INSERT INTO `market_permission_table` VALUES (224, '220', 'admin:coupon:read', '详情', 'GET /admin/coupon/read', 3);
INSERT INTO `market_permission_table` VALUES (225, '220', 'admin:coupon:delete', '删除', 'POST /admin/coupon/delete', 3);
INSERT INTO `market_permission_table` VALUES (226, '220', 'admin:coupon:create', '添加', 'POST /admin/coupon/create', 3);
INSERT INTO `market_permission_table` VALUES (227, '207', '专题管理', '专题管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (228, '227', 'admin:topic:batch-delete', '批量删除', 'POST /admin/topic/batch-delete', 3);
INSERT INTO `market_permission_table` VALUES (229, '227', 'admin:topic:update', '编辑', 'POST /admin/topic/update', 3);
INSERT INTO `market_permission_table` VALUES (230, '227', 'admin:topic:list', '查询', 'GET /admin/topic/list', 3);
INSERT INTO `market_permission_table` VALUES (231, '227', 'admin:topic:read', '详情', 'GET /admin/topic/read', 3);
INSERT INTO `market_permission_table` VALUES (232, '227', 'admin:topic:delete', '删除', 'POST /admin/topic/delete', 3);
INSERT INTO `market_permission_table` VALUES (233, '227', 'admin:topic:create', '添加', 'POST /admin/topic/create', 3);
INSERT INTO `market_permission_table` VALUES (234, '207', '通知管理', '通知管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (235, '234', 'admin:notice:batch-delete', '批量删除', 'POST /admin/notice/batch-delete', 3);
INSERT INTO `market_permission_table` VALUES (236, '234', 'admin:notice:update', '编辑', 'POST /admin/notice/update', 3);
INSERT INTO `market_permission_table` VALUES (237, '234', 'admin:notice:read', '详情', 'GET /admin/notice/read', 3);
INSERT INTO `market_permission_table` VALUES (238, '234', 'admin:notice:delete', '删除', 'POST /admin/notice/delete', 3);
INSERT INTO `market_permission_table` VALUES (239, '234', 'admin:notice:create', '添加', 'POST /admin/notice/create', 3);
INSERT INTO `market_permission_table` VALUES (240, NULL, '配置管理', '配置管理', NULL, 1);
INSERT INTO `market_permission_table` VALUES (241, '240', '小程序配置', '小程序配置', NULL, 2);
INSERT INTO `market_permission_table` VALUES (242, '241', 'admin:config:wx:list', '详情', 'GET /admin/config/wx', 3);
INSERT INTO `market_permission_table` VALUES (243, '241', 'admin:config:wx:updateConfigs', '编辑', 'POST /admin/config/wx', 3);
INSERT INTO `market_permission_table` VALUES (244, '240', '运费配置', '运费配置', NULL, 2);
INSERT INTO `market_permission_table` VALUES (245, '244', 'admin:config:express:list', '详情', 'GET /admin/config/express', 3);
INSERT INTO `market_permission_table` VALUES (246, '244', 'admin:config:express:updateConfigs', '编辑', 'POST /admin/config/express', 3);
INSERT INTO `market_permission_table` VALUES (247, '240', '商场配置', '商场配置', NULL, 2);
INSERT INTO `market_permission_table` VALUES (248, '247', 'admin:config:mall:list', '详情', 'GET /admin/config/mall', 3);
INSERT INTO `market_permission_table` VALUES (249, '247', 'admin:config:mall:updateConfigs', '编辑', 'POST /admin/config/mall', 3);
INSERT INTO `market_permission_table` VALUES (250, '240', '订单配置', '订单配置', NULL, 2);
INSERT INTO `market_permission_table` VALUES (251, '250', 'admin:config:order:list', '详情', 'GET /admin/config/order', 3);
INSERT INTO `market_permission_table` VALUES (252, '250', 'admin:config:order:updateConfigs', '编辑', 'POST /admin/config/order', 3);
INSERT INTO `market_permission_table` VALUES (253, NULL, '其他', '其他', NULL, 1);
INSERT INTO `market_permission_table` VALUES (254, '253', '权限测试', '权限测试', NULL, 2);
INSERT INTO `market_permission_table` VALUES (255, '254', 'index:permission:write', '权限写', 'POST /admin/index/write', 3);
INSERT INTO `market_permission_table` VALUES (256, '254', 'index:permission:read', '权限读', 'GET /admin/index/read', 3);
INSERT INTO `market_permission_table` VALUES (257, NULL, '系统管理', '系统管理', NULL, 1);
INSERT INTO `market_permission_table` VALUES (258, '257', '角色管理', '角色管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (259, '258', 'admin:role:permission:update', '权限变更', 'POST /admin/role/permissions', 3);
INSERT INTO `market_permission_table` VALUES (260, '258', 'admin:role:update', '角色编辑', 'POST /admin/role/update', 3);
INSERT INTO `market_permission_table` VALUES (261, '258', 'admin:role:list', '角色查询', 'GET /admin/role/list', 3);
INSERT INTO `market_permission_table` VALUES (262, '258', 'admin:role:read', '角色详情', 'GET /admin/role/read', 3);
INSERT INTO `market_permission_table` VALUES (263, '258', 'admin:role:delete', '角色删除', 'POST /admin/role/delete', 3);
INSERT INTO `market_permission_table` VALUES (264, '258', 'admin:role:permission:get', '权限详情', 'GET /admin/role/permissions', 3);
INSERT INTO `market_permission_table` VALUES (265, '258', 'admin:role:create', '角色添加', 'POST /admin/role/create', 3);
INSERT INTO `market_permission_table` VALUES (266, '257', '管理员管理', '管理员管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (267, '266', 'admin:admin:update', '编辑', 'POST /admin/admin/update', 3);
INSERT INTO `market_permission_table` VALUES (268, '266', 'admin:admin:list', '查询', 'GET /admin/admin/list', 3);
INSERT INTO `market_permission_table` VALUES (269, '266', 'admin:admin:read', '详情', 'GET /admin/admin/read', 3);
INSERT INTO `market_permission_table` VALUES (270, '266', 'admin:admin:delete', '删除', 'POST /admin/admin/delete', 3);
INSERT INTO `market_permission_table` VALUES (271, '266', 'admin:admin:create', '添加', 'POST /admin/admin/create', 3);
INSERT INTO `market_permission_table` VALUES (272, '257', '操作日志', '操作日志', NULL, 2);
INSERT INTO `market_permission_table` VALUES (273, '272', 'admin:log:list', '查询', 'GET /admin/log/list', 3);
INSERT INTO `market_permission_table` VALUES (274, '257', '对象存储', '对象存储', NULL, 2);
INSERT INTO `market_permission_table` VALUES (275, '274', 'admin:storage:update', '编辑', 'POST /admin/storage/update', 3);
INSERT INTO `market_permission_table` VALUES (276, '274', 'admin:storage:list', '查询', 'GET /admin/storage/list', 3);
INSERT INTO `market_permission_table` VALUES (277, '274', 'admin:storage:read', '详情', 'POST /admin/storage/read', 3);
INSERT INTO `market_permission_table` VALUES (278, '274', 'admin:storage:delete', '删除', 'POST /admin/storage/delete', 3);
INSERT INTO `market_permission_table` VALUES (279, '274', 'admin:storage:create', '上传', 'POST /admin/storage/create', 3);
INSERT INTO `market_permission_table` VALUES (280, '257', '通知管理', '通知管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (281, '280', 'admin:notice:list', '查询', 'GET /admin/notice/list', 3);
INSERT INTO `market_permission_table` VALUES (282, NULL, '统计管理', '统计管理', NULL, 1);
INSERT INTO `market_permission_table` VALUES (283, '282', '用户统计', '用户统计', NULL, 2);
INSERT INTO `market_permission_table` VALUES (284, '283', 'admin:stat:user', '查询', 'GET /admin/stat/user', 3);
INSERT INTO `market_permission_table` VALUES (285, '282', '订单统计', '订单统计', NULL, 2);
INSERT INTO `market_permission_table` VALUES (286, '285', 'admin:stat:order', '查询', 'GET /admin/stat/order', 3);
INSERT INTO `market_permission_table` VALUES (287, '282', '商品统计', '商品统计', NULL, 2);
INSERT INTO `market_permission_table` VALUES (288, '287', 'admin:stat:goods', '查询', 'GET /admin/stat/goods', 3);
INSERT INTO `market_permission_table` VALUES (289, NULL, '商城管理', '商城管理', NULL, 1);
INSERT INTO `market_permission_table` VALUES (290, '289', '售后管理', '售后管理', NULL, 2);
INSERT INTO `market_permission_table` VALUES (291, '290', 'admin:aftersale:reject', '审核拒绝', 'POST /admin/aftersale/reject', 3);
INSERT INTO `market_permission_table` VALUES (292, '290', 'admin:aftersale:refund', '退款', 'POST /admin/aftersale/refund', 3);
INSERT INTO `market_permission_table` VALUES (293, '290', 'admin:aftersale:recept', '审核通过', 'POST /admin/aftersale/recept', 3);
INSERT INTO `market_permission_table` VALUES (294, '290', 'admin:aftersale:batch-recept', '批量通过', 'POST /admin/aftersale/batch-recept', 3);
INSERT INTO `market_permission_table` VALUES (295, '290', 'admin:aftersale:batch-reject', '批量拒绝', 'POST /admin/aftersale/batch-reject', 3);
INSERT INTO `market_permission_table` VALUES (296, '290', 'admin:aftersale:list', '查询', 'GET /admin/aftersale/list', 3);

SET FOREIGN_KEY_CHECKS = 1;
