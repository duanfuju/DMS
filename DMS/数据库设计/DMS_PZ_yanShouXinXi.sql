/*
 Navicat Premium Data Transfer

 Source Server         : jl
 Source Server Type    : SQL Server
 Source Server Version : 10502500
 Source Host           : 202.102.79.148
 Source Database       : DMS_XZ
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 10502500
 File Encoding         : utf-8

 Date: 08/08/2016 11:49:11 AM
*/

-- ----------------------------
--  Table structure for DMS_PZ_yanShouXinXi
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID('[dbo].[DMS_PZ_yanShouXinXi]') AND type IN ('U'))
	DROP TABLE [dbo].[DMS_PZ_yanShouXinXi]
GO
CREATE TABLE [dbo].[DMS_PZ_yanShouXinXi] (
	[ID] int IDENTITY(1,1) NOT NULL,
	[year] int NULL,
	[一级编号] varchar(10) COLLATE Chinese_PRC_CI_AS NULL,
	[一级细目] varchar(50) COLLATE Chinese_PRC_CI_AS NULL,
	[二级编号] varchar(10) COLLATE Chinese_PRC_CI_AS NULL,
	[二级细目] varchar(50) COLLATE Chinese_PRC_CI_AS NULL,
	[三级编号] varchar(10) COLLATE Chinese_PRC_CI_AS NULL,
	[三级细目] varchar(50) COLLATE Chinese_PRC_CI_AS NULL,
	[单位] varchar(10) COLLATE Chinese_PRC_CI_AS NULL,
	[单价] decimal(9,2) NULL,
	[更新时间] datetime NULL
)
ON [PRIMARY]
GO

-- ----------------------------
--  Records of DMS_PZ_yanShouXinXi
-- ----------------------------
BEGIN TRANSACTION
GO
SET IDENTITY_INSERT [dbo].[DMS_PZ_yanShouXinXi] ON
GO
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('20', '2016', '3', N'路基', '3.1', N'修理挡土墙', null, null, N'元/m3', '220.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('21', '2016', '3', N'路基', '3.2', N'边坡培土', null, null, N'元/m3', '118.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('22', '2016', '3', N'路基', '3.3', N'浆砌片石防护工程', null, null, N'元/m3', '465.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('23', '2016', '3', N'路基', '3.4', N'拱形护坡砼预制块更换', null, null, N'元/m3', '530.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('24', '2016', '3', N'路基', '3.5', N'锥坡空心六角块修理、更换', null, null, N'元/块', '15.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('25', '2016', '3', N'路基', '3.6', N'砌体勾缝', null, null, N'元/m', '7.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('26', '2016', '3', N'路基', '3.7', N'修理急流槽', null, null, N'元/m', '51.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('27', '2016', '3', N'路基', '3.8', N'圬工体砂浆抹面', null, null, N'元/m2', '9.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('28', '2016', '3', N'路基', '3.9', N'修理边沟预制件（更换）', null, null, N'元/m', '162.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('29', '2016', '3', N'路基', '3.10', N'土路肩、边坡8%灰土填筑', null, null, N'元/m3', '153.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('30', '2016', '3', N'路基', '3.11', N'排水设施清理', null, null, N'元/m', '55.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('31', '2016', '3', N'路基', '3.12', N'圬工体修复', null, null, N'元/m3', '453.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('32', '2016', '3', N'路基', '3.13', N'路基边坡植草防护（撒草籽）', null, null, N'元/m2', '10.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('33', '2016', '3', N'路基', '3.14', N'路基边坡植草防护（铺草皮）', null, null, N'元/m2', '25.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('34', '2016', '3', N'路基', '3.15', N'中分带排水沟清理', null, null, N'元/公里', '3500.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('35', '2016', '3', N'路基', '3.16', N'清理边沟积淤', null, null, N'元/m3', '30.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('36', '2016', '4', N'路面', '4.1', N'清洗油污及其它污染清除', null, null, N'元/m2', '30.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('37', '2016', '4', N'路面', '4.2', N'路缘石更换', null, null, N'元/m', '68.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('38', '2016', '4', N'路面', '4.3', N'路缘石修整', null, null, N'元/m', '11.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('39', '2016', '4', N'路面', '4.4', N'截水沟维护', null, null, N'元/m', '51.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('40', '2016', '4', N'路面', '4.5', N'冷补路面坑塘（厚4cm）', null, null, N'元/m2', '330.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('41', '2016', '4', N'路面', '4.6', N'热补路面坑塘（厚4cm）', null, null, N'元/m2', '900.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('42', '2016', '4', N'路面', '4.7', N'修补路面千斤顶压痕', null, null, N'元/处', '20.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('43', '2016', '4', N'路面', '4.8', N'沥青路面灌缝（乳化沥青）', null, null, N'元/m', '7.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('44', '2016', '4', N'路面', '4.9', N'沥青路面灌缝（密封胶）', null, null, N'元/m', '35.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('45', '2016', '4', N'路面', '4.1', N'路面刷胶', null, null, N'元/m2', '50.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('46', '2016', '4', N'路面', '4.11', N'水泥砼路面修补', null, null, N'元/m3', '650.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('47', '2016', '4', N'路面', '4.12', N'水泥砼路面灌缝（缩缝）', null, null, N'元/m', '5.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('48', '2016', '4', N'路面', '4.13', N'水泥砼路面灌缝（胀缝）', null, null, N'元/m', '20.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('49', '2016', '4', N'路面', '4.14', N'截水沟井盖（水泥）', null, null, N'元/块', '50.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('50', '2016', '5', N'桥梁', '5.1', N'修理桥栏杆', null, null, N'元/m', '150.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('51', '2016', '5', N'桥梁', '5.2', N'桥梁泄水孔盖补缺', null, null, N'元/个', '25.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('52', '2016', '5', N'桥梁', '5.3', N'桥梁伸缩缝砼维修（环氧树脂）', null, null, N'元/m3', '50000.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('53', '2016', '5', N'桥梁', '5.4', N'桥梁伸缩缝橡胶条更换', null, null, N'元/m', '180.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('54', '2016', '5', N'桥梁', '5.5', N'防撞墙刷白', null, null, N'元/m2', '17.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('55', '2016', '5', N'桥梁', '5.6', N'桥梁小部位维修（水泥砼）', null, null, N'元/m3', '350.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('56', '2016', '5', N'桥梁', '5.7', N'桥梁小部位维修（环氧树脂）', null, null, N'元/m3', '50000.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('57', '2016', '5', N'桥梁', '5.8', N'桥梁管箱盒修复', null, null, N'元/m', '100.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('58', '2016', '5', N'桥梁', '5.9', N'桥梁管箱盒油漆', null, null, N'元/m', '8.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('59', '2016', '5', N'桥梁', '5.10 ', N'桥梁落水管安装', null, null, N'元/m', '102.60', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('60', '2016', '5', N'桥梁', '5.11', N'维修互通匝道桥中分带砼盖板', null, null, N'元/米', '120.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('61', '2016', '5', N'桥梁', '5.12', N'桥下堆积物清理', null, null, N'元/天', '900.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('62', '2016', '6', N'涵洞通道', '6.1', N'涵洞清淤', null, null, N'元/m3', '61.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('63', '2016', '6', N'涵洞通道', '6.2', N'涵洞维修', null, null, N'元/m3', '310.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('64', '2016', '6', N'涵洞通道', '6.3', N'护坡维修', null, null, N'元/m2', '106.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('65', '2016', '6', N'涵洞通道', '6.4', N'锥坡素土回填夯实', null, null, N'元/m3', '153.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('66', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.1', N'更换防撞护栏板（4m，镀锌）', N'元/块', '500.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('67', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.2', N'更换防撞护栏板（2m，镀锌）', N'元/块', '320.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('68', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.3', N'调整防撞护栏板线形', N'元/m', '20.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('69', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.4', N'更换防阻块（镀锌）', N'元/个', '47.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('70', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.5', N'更换边桩立柱（140，镀锌）', N'元/根', '220.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('71', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.6', N'更换中桩立柱（114，镀锌）', N'元/根', '212.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('72', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.7', N'修整立柱', N'元/根', '60.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('73', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.8', N'安装立柱盖帽', N'元/个', '10.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('74', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.9', N'补添长螺丝', N'元/只', '4.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('75', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.10', N'补添短螺丝', N'元/只', '2.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('76', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.11', N'更换防眩板', N'元/块', '70.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('77', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.12', N'更换防眩板支架', N'元/块', '65.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('78', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.13', N'调整防眩板', N'元/块', '1.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('79', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.14', N'更换插拔式活动护栏', N'元/m', '400.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('80', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.15', N'更换里程牌(含支架、安装）', N'元/块', '250.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('81', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.16', N'附着式轮廓标补缺', N'元/只', '15.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('82', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.17', N'桩柱式轮廓标补缺', N'元/只', '100.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('83', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.18', N'贴百米标', N'元/条', '8.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('84', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.19', N'更换百米桩', N'元/根', '110.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('85', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.20', N'更换防撞分流桶', N'元/只', '800.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('86', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.21', N'道钉补缺', null, null, null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('87', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.22', N'普通道钉补缺', N'元/个', '20.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('88', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.23', N'更换镀锌防撞护栏350端头', N'元/个', '270.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('89', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.24', N'更换镀锌防撞护栏320端头', N'元/个', '220.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('90', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.25', N'更换镀锌防撞护栏250端头', N'元/个', '205.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('91', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.26', N'更换镀锌防撞护栏160端头', N'元/个', '145.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('92', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.27', N'三波护栏板更换', N'元/米', '259.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('93', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.28', N'130＊130方型立柱', N'元/根', '314.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('94', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.29', N'方型防阻块', N'元/个', '63.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('95', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.30', N'清洗护栏', N'元/百米次', '14.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('96', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.31', N'矫正防阻块', N'元/个', '10.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('97', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.32', N'矫正方形防阻块', N'元/个', '20.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('98', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.33', N'更换防眩板夹板', N'元/付', '15.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('99', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.34', N'更换活动护栏防眩板卡箍', N'元/付', '20.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('100', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.35', N'更换百米标支架', N'元/个', '25.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('101', '2016', '7', N'安全设施', '7.1', N'防撞护栏等维修', '7.1.36', N'油漆插拔式活动护栏', N'元/片', '160.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('102', '2016', '7', N'安全设施', '7.2', N'隔离栅', '7.2.1', N'更换隔离栅片(绿)', N'元/块', '360.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('103', '2016', '7', N'安全设施', '7.2', N'隔离栅', '7.2.2', N'更换隔离栅立柱(绿)', N'元/根', '90.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('104', '2016', '7', N'安全设施', '7.2', N'隔离栅', '7.2.3', N'更换防落物网', N'元/m2', '200.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('105', '2016', '7', N'安全设施', '7.2', N'隔离栅', '7.2.4', N'封闭隔离栅', N'元/块', '3.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('106', '2016', '7', N'安全设施', '7.2', N'隔离栅', '7.2.5', N'修补异形网', N'元/', '47.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('107', '2016', '7', N'安全设施', '7.2', N'隔离栅', '7.2.6', N'矫正隔离栅立柱', N'元/根', '30.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('108', '2016', '7', N'安全设施', '7.3', N'标志清洁、维修及更换', '7.3.1', N'标志更换', N'元/m2', '850.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('109', '2016', '7', N'安全设施', '7.3', N'标志清洁、维修及更换', '7.3.2', N'标志整修', N'元/块', '43.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('110', '2016', '7', N'安全设施', '7.3', N'标志清洁、维修及更换', '7.3.3', N'标志反光膜粘贴', N'元/m2', '200.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('111', '2016', '7', N'安全设施', '7.3', N'标志清洁、维修及更换', '7.3.4', N'更换里程牌（无支架）', N'元/块', '170.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('112', '2016', '7', N'安全设施', '7.3', N'标志清洁、维修及更换', '7.3.5', N'更换导向牌', N'元/块', '550.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('113', '2016', '7', N'安全设施', '7.3', N'标志清洁、维修及更换', '7.3.6', N'更换导向牌支架', N'元/根', '120.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('114', '2016', '7', N'安全设施', '7.4', N'局部标线补划', '7.4.1', N'水性漆补划', N'元/m2', '14.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('115', '2016', '7', N'安全设施', '7.4', N'局部标线补划', '7.4.2', N'热熔标线补划', N'元/m2', '40.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('116', '2016', '7', N'安全设施', '7.4', N'局部标线补划', '7.4.3', N'震荡标线补划', N'元/m2', '167.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('117', '2016', '8', N'沿线设施', '8.1', N'安装收费站减速板', null, null, N'元/m', '221.40', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('118', '2016', '8', N'沿线设施', '8.2', N'清理人手井', null, null, N'元/处', '140.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('119', '2016', '8', N'沿线设施', '8.3', N'油漆收费岛防撞立柱', null, null, N'元/m2', '25.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('120', '2016', '8', N'沿线设施', '8.4', N'油漆收费岛(普通油漆)', null, null, N'元/m2', '36.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('121', '2016', '8', N'沿线设施', '8.6', N'更换蘑菇桶', '8.6.1', N'更换蘑菇桶', N'元/个', '390.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('122', '2016', '8', N'沿线设施', '8.6', N'更换蘑菇桶', '8.6.2', N'更换蘑菇桶（塑料）', N'元/个', '286.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('123', '2016', '8', N'沿线设施', '8.7', N'更换蘑菇桶连接铁链', null, null, N'元/m', '15.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('124', '2016', '8', N'沿线设施', '8.8', N'收费岛头维修', null, null, N'元/m2', '62.90', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('125', '2016', '8', N'沿线设施', '8.9', N'收费岛地砖维修', null, null, N'元/m2', '80.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('126', '2016', '8', N'沿线设施', '8.10', N'更换收费站排水沟铸铁盖板', null, null, N'元/m', '250.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('127', '2016', '8', N'沿线设施', '8.11', N'人手井井盖补缺', null, null, N'元/个', '392.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('128', '2016', '8', N'沿线设施', '8.12', N'更换警示桩', null, null, N'元/个', '100.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('129', '2016', '8', N'沿线设施', '8.13', N'恶劣天气标牌摆放', null, null, N'元/次·道', '292.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('130', '2016', '8', N'沿线设施', '8.14', N'更换收费岛防撞柱', null, null, N'元/根', '800.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('131', '2016', '8', N'沿线设施', '8.15', N'更换不锈钢手动栏杆', null, null, N'元/套', '2500.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('132', '2016', '8', N'沿线设施', '8.16', N'更换收费亭侧护栏', null, null, N'元/m', '244.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('133', '2016', '8', N'沿线设施', '8.17', N'更换模塑井盖', null, null, N'元/块', '182.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('134', '2016', '8', N'沿线设施', '8.18', N'服务区排水沟维修', null, null, N'元/m', '63.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('135', '2016', '8', N'沿线设施', '8.19', N'更换收费站防撞柱斜拉杆', null, null, N'元/根', '300.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('136', '2016', '8', N'沿线设施', '8.20 ', N'维修收费站防撞柱斜拉杆钢筋砼基础', null, null, N'元/处', '200.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('137', '2016', '8', N'沿线设施', '8.21', N'更换收费站防撞柱斜拉杆抱箍', null, null, N'元/只', '60.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('138', '2016', '8', N'沿线设施', '8.22', N'收费亭侧护栏油漆', null, null, N'元/m2', '25.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('139', '2016', '9', N'计日工', '9.1', N'计日工', null, null, N'元/工日', '100.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('140', '2016', '9', N'计日工', '9.2', N'载货小汽车', null, null, N'元/台班', '300.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('141', '2016', '9', N'计日工', '9.3', N'应急处理', null, null, N'元/次', '350.00', null);
INSERT INTO [dbo].[DMS_PZ_yanShouXinXi] ([ID], [year], [一级编号], [一级细目], [二级编号], [二级细目], [三级编号], [三级细目], [单位], [单价], [更新时间]) VALUES ('142', '2016', '1', N'清扫保洁', '1.1', N'人工清扫保洁', '1.1.1', N'清扫、清理路面', N'元/km.年', '8000.00', '2015-09-14 16:11:18.360');
GO
SET IDENTITY_INSERT [dbo].[DMS_PZ_yanShouXinXi] OFF
GO
COMMIT
GO


-- ----------------------------
--  Primary key structure for table DMS_PZ_yanShouXinXi
-- ----------------------------
ALTER TABLE [dbo].[DMS_PZ_yanShouXinXi] ADD
	CONSTRAINT [PK_d_细目及单价] PRIMARY KEY CLUSTERED ([ID]) 
	WITH (PAD_INDEX = OFF,
		IGNORE_DUP_KEY = OFF,
		ALLOW_ROW_LOCKS = ON,
		ALLOW_PAGE_LOCKS = ON)
	ON [default]
GO

-- ----------------------------
--  Options for table DMS_PZ_yanShouXinXi
-- ----------------------------
ALTER TABLE [dbo].[DMS_PZ_yanShouXinXi] SET (LOCK_ESCALATION = TABLE)
GO
DBCC CHECKIDENT ('[dbo].[DMS_PZ_yanShouXinXi]', RESEED, 142)
GO

