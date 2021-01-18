/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.220
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 192.168.1.220:3306
 Source Schema         : offlinedb

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 19/11/2020 20:10:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dump_columns
-- ----------------------------
DROP TABLE IF EXISTS `dump_columns`;
CREATE TABLE `dump_columns`  (
  `TABLE_CAT` binary(0) NULL DEFAULT NULL,
  `TABLE_SCHEM` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `TABLE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `COLUMN_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `DATA_TYPE` int(1) NULL DEFAULT 0,
  `TYPE_NAME` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `COLUMN_SIZE` int(1) NULL DEFAULT 0,
  `DECIMAL_DIGITS` int(1) NULL DEFAULT 0,
  `REMARKS` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `COLUMN_DEF` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `ORDINAL_POSITION` bigint(21) UNSIGNED NULL DEFAULT 0,
  `IS_NULLABLE` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `IS_AUTOINCREMENT` varchar(3) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `DATABASE_INFO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT ''
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for dump_indexes
-- ----------------------------
DROP TABLE IF EXISTS `dump_indexes`;
CREATE TABLE `dump_indexes`  (
  `TABLE_CAT` binary(0) NULL DEFAULT NULL,
  `TABLE_SCHEM` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `TABLE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `NON_UNIQUE` int(1) NULL DEFAULT 0,
  `INDEX_QUALIFIER` binary(0) NULL DEFAULT NULL,
  `INDEX_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `TYPE` int(1) NULL DEFAULT 0,
  `ORDINAL_POSITION` bigint(2) NULL DEFAULT 0,
  `COLUMN_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `ASC_OR_DESC` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `CARDINALITY` bigint(21) NULL DEFAULT NULL,
  `PAGES` int(1) NULL DEFAULT 0,
  `FILTER_CONDITION` binary(0) NULL DEFAULT NULL,
  `DATABASE_INFO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT ''
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for dump_pks
-- ----------------------------
DROP TABLE IF EXISTS `dump_pks`;
CREATE TABLE `dump_pks`  (
  `TABLE_CAT` binary(0) NULL DEFAULT NULL,
  `TABLE_SCHEM` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `TABLE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `COLUMN_NAME` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `KEY_SEQ` bigint(2) NULL DEFAULT 0,
  `PK_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `DATABASE_INFO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT ''
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for dump_routines
-- ----------------------------
DROP TABLE IF EXISTS `dump_routines`;
CREATE TABLE `dump_routines`  (
  `ROUTINE_CAT` binary(0) NULL DEFAULT NULL,
  `ROUTINE_SCHEM` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `ROUTINE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `ROUTINE_TYPE` varchar(9) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `LINE` int(1) NULL DEFAULT 0,
  `TEXT` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `ROUTINE_DEFINITION` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `PARAMETERS` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `RETURN_V` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `DATABASE_INFO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT ''
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for dump_tables
-- ----------------------------
DROP TABLE IF EXISTS `dump_tables`;
CREATE TABLE `dump_tables`  (
  `TABLE_CAT` binary(0) NULL DEFAULT NULL,
  `TABLE_SCHEM` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `TABLE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `TABLE_TYPE` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `REMARKS` varchar(2048) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `DATABASE_INFO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT ''
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for dump_views
-- ----------------------------
DROP TABLE IF EXISTS `dump_views`;
CREATE TABLE `dump_views`  (
  `TABLE_CAT` binary(0) NULL DEFAULT NULL,
  `TABLE_SCHEM` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `TABLE_NAME` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `REMARKS` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `TEXT` longtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `DATABASE_INFO` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT ''
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
