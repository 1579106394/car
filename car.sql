/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2019-03-07 18:06:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `appoint`
-- ----------------------------
DROP TABLE IF EXISTS `appoint`;
CREATE TABLE `appoint` (
  `appoint_id` varchar(36) NOT NULL,
  `appoint_user` varchar(36) DEFAULT NULL COMMENT '学员id',
  `appoint_teacher` varchar(36) DEFAULT NULL COMMENT '教练id',
  `appoint_start_date` varchar(36) DEFAULT NULL COMMENT '开始时间',
  `appoint_end_date` varchar(36) DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`appoint_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appoint
-- ----------------------------
INSERT INTO `appoint` VALUES ('a0de5013-c450-4938-8c70-a52f41a730df', 'f8204873-d4ea-4c85-a382-e4d2386eb9b9', '3fb01ec4-14c3-46a1-bab9-e7888203842f', '2019-03-06 00:00:00', '2019-03-07 00:00:00');

-- ----------------------------
-- Table structure for `car`
-- ----------------------------
DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `car_id` varchar(36) NOT NULL,
  `car_name` varchar(30) DEFAULT NULL,
  `car_num` int(4) DEFAULT NULL COMMENT '可坐人数',
  `car_flag` int(2) DEFAULT NULL COMMENT '1未匹配教师，2已匹配教师',
  PRIMARY KEY (`car_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car
-- ----------------------------
INSERT INTO `car` VALUES ('0d137dfb-cc92-4c40-857c-c283cdb4bd4e', '测', '34', '1');
INSERT INTO `car` VALUES ('1bda80e0-3d34-4207-ba8e-d96fffff7800', '刚刚', '1', '1');
INSERT INTO `car` VALUES ('24b20cfe-2be7-4e63-b5c1-26c5e6da6065', '哈哈', '21', '1');
INSERT INTO `car` VALUES ('3e88d806-26f5-4d52-b56e-e1f425a6bb38', '测试', '4', '2');
INSERT INTO `car` VALUES ('58028666-6a7a-4b18-83a9-75485440e471', '21饿', '2', '2');
INSERT INTO `car` VALUES ('6679e864-d100-4930-afaa-9c1459afdfda', '发', '1', '2');
INSERT INTO `car` VALUES ('705ef002-045c-462a-b67b-8d714137d92f', '测', '2', '2');
INSERT INTO `car` VALUES ('711b0a8e-cc83-48b6-97ad-5324cde5d136', '侧啊啊大', '123', '1');
INSERT INTO `car` VALUES ('8648cd01-c976-4c07-9bcc-90a0cb755105', '哈哈', '2', '2');
INSERT INTO `car` VALUES ('890d8737-13a6-4f37-a5ef-a9a4842a0b45', '个', '1', '2');
INSERT INTO `car` VALUES ('e59f06d1-2ddb-461c-a22e-759349fa1c14', '但是', '1', '2');
INSERT INTO `car` VALUES ('f52986db-60a2-4e9d-84d0-ddf16f3382a5', '测', '2', '2');

-- ----------------------------
-- Table structure for `comment`
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `comment_id` varchar(36) NOT NULL,
  `comment_article` mediumtext,
  `comment_user` varchar(36) DEFAULT NULL COMMENT '评论人id',
  `comment_teacher` varchar(36) DEFAULT NULL COMMENT '评论教师id',
  `comment_time` varchar(36) DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES ('16de25cd-6e43-4dda-9370-7a0e8717c785', '测试', 'f8204873-d4ea-4c85-a382-e4d2386eb9b9', '3fb01ec4-14c3-46a1-bab9-e7888203842f', '2019-03-07');
INSERT INTO `comment` VALUES ('d0a98963-c242-4562-922f-95e3760c256b', '哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈', 'f8204873-d4ea-4c85-a382-e4d2386eb9b9', '3fb01ec4-14c3-46a1-bab9-e7888203842f', '2019-03-07');
INSERT INTO `comment` VALUES ('edb7542d-51da-4cac-89cc-4641d25f2bec', '测测', 'f8204873-d4ea-4c85-a382-e4d2386eb9b9', '3fb01ec4-14c3-46a1-bab9-e7888203842f', '2019-03-07');
INSERT INTO `comment` VALUES ('f02f1ca7-2e18-4726-b172-91c4c2538e20', '啊啊啊啊', 'f8204873-d4ea-4c85-a382-e4d2386eb9b9', '3fb01ec4-14c3-46a1-bab9-e7888203842f', '2019-03-07');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `teacher_id` varchar(36) NOT NULL,
  `teacher_name` varchar(20) DEFAULT NULL,
  `teacher_age` int(3) DEFAULT NULL,
  `teacher_telephone` varchar(13) DEFAULT NULL,
  `teacher_photo` varchar(50) DEFAULT NULL,
  `teacher_car` varchar(36) DEFAULT NULL COMMENT '车辆id',
  `teacher_number` varchar(50) DEFAULT NULL COMMENT '教练编号',
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('3fb01ec4-14c3-46a1-bab9-e7888203842f', '咕咕咕', '213', '12', '/photo/fd4fe1cd2d974e1bafa2dd821791f718.png', '58028666-6a7a-4b18-83a9-75485440e471', '2');
INSERT INTO `teacher` VALUES ('74338137-ffcf-44ba-b14d-0465f9d18aa8', '咕咕', '123', '12321', '/photo/a031234aa3e84d9aaa16990c7327ad45.jpg', '3e88d806-26f5-4d52-b56e-e1f425a6bb38', '4');
INSERT INTO `teacher` VALUES ('7dcf676e-0099-4981-a356-da821546a4ec', '大多数', '43', '33434', '/photo/d5fdd9bb35a943d08f69a38b633eeaad.png', 'e59f06d1-2ddb-461c-a22e-759349fa1c14', '5');
INSERT INTO `teacher` VALUES ('8091c58a-ff24-4a1c-9b29-ff4be243ea1c', '的', '23', '2232', '/photo/7b8dc8ea03b0469db73d85bd24467c0d.jpg', '0d137dfb-cc92-4c40-857c-c283cdb4bd4e', '23');
INSERT INTO `teacher` VALUES ('a58b0caa-fe83-4fb6-ba74-126fee4b88a1', '3213', '2', '2323', '/photo/4c07e1e483384bea8ca813fe7f88db10.jpg', '1bda80e0-3d34-4207-ba8e-d96fffff7800', '213');
INSERT INTO `teacher` VALUES ('a819ae3d-2dc2-4f13-a9f6-f1e14b88f4bb', '啊啊啊', '23', '23', '/photo/efa01100c5aa46dba15eae40ea5f2c19.jpg', '6679e864-d100-4930-afaa-9c1459afdfda', '6');
INSERT INTO `teacher` VALUES ('e8443556-8d45-4a23-8d9f-15a0900fb46e', '新增', '10', '12222', '/photo/7352013037944a518d10edb4e8fd8062.jpg', '24b20cfe-2be7-4e63-b5c1-26c5e6da6065', '7');
INSERT INTO `teacher` VALUES ('f5781121-9843-491c-8fe4-1fc76aac3567', '232', '23', '3213', '/photo/35b487123aa043b4ab14b7ea32526ce2.png', '705ef002-045c-462a-b67b-8d714137d92f', '23223');
INSERT INTO `teacher` VALUES ('f7505006-1a58-454c-9cb3-fd99e6d02a51', '233', '23', '333', '/photo/83a6af07172c4b32a81ac60fe7881b64.jpg', '890d8737-13a6-4f37-a5ef-a9a4842a0b45', '8');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` varchar(36) NOT NULL,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `user_name` varchar(20) DEFAULT NULL,
  `user_gender` int(1) DEFAULT NULL COMMENT '1男2女',
  `user_age` int(3) DEFAULT NULL,
  `user_telephone` varchar(13) DEFAULT NULL,
  `user_number` varchar(50) DEFAULT NULL,
  `user_time` int(5) DEFAULT '20' COMMENT '学时量',
  `user_state` int(2) DEFAULT NULL COMMENT '1未通过，2已通过',
  `user_role` int(2) DEFAULT NULL COMMENT '1学员，2管理员',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('0f227a7d-4286-453e-90f4-0bbb5c58c171', '1232', '213', '23213', '1', '32', '22132', '201903036', '35', '1', '1');
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '超级管理员', '1', '30', '16046798132', '20190303001', '20', '2', '2');
INSERT INTO `user` VALUES ('2d1946fc-6240-47f4-a16d-b9855a2ce941', '12321', '12321', '123123', '1', '21312', '21321', '201903038', '20', '1', '1');
INSERT INTO `user` VALUES ('3d31a41e-e30f-40c9-abec-5282a18729f8', '1233', '3213', '2123', '1', '31', '12', '2019030310', '20', '1', '1');
INSERT INTO `user` VALUES ('5b81aea7-bd24-4c0b-9d02-4b61972da233', '2132', '12312', '3123', '1', '1232', '1213', '201903035', '20', '1', '1');
INSERT INTO `user` VALUES ('928eb6db-30a2-4939-99ab-adaca4f442f6', 'dsf', 'dsf', 'sdf', '1', '12', '12', '2019030310', '20', '1', '1');
INSERT INTO `user` VALUES ('9639e4c7-364e-4dbd-8f58-54d4a86f0629', '123', '2321', '21321', '1', '123', '123', '201903039', '20', '1', '1');
INSERT INTO `user` VALUES ('9a00c3b8-d821-4b8f-837a-02010e6517c2', '21312', '3213', '132132', '1', '213', '123213', '201903037', '20', '1', '1');
INSERT INTO `user` VALUES ('c2b8f7a6-7c90-4cc2-8c75-53f3e25c61ec', '23131', '12', '321312', '1', '123', '12321', '201903034', '20', '1', '1');
INSERT INTO `user` VALUES ('c6c7bfd7-1d72-4e60-b5b8-3c7299136d6b', '111111', '111111', '赵日天', '1', '18', '2313213', '201903033', '20', '1', '1');
INSERT INTO `user` VALUES ('dcc2dee7-377c-4bd0-85ad-9444873ad270', 'sdf', 'dsf', '231', '1', '312', '12', '2019030310', '20', '1', '1');
INSERT INTO `user` VALUES ('f8204873-d4ea-4c85-a382-e4d2386eb9b9', 'gugugu', 'gugugu', '咕哒子', '1', '18', '114156', '201903032', '4', '1', '1');
