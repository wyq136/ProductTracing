/*
Navicat MySQL Data Transfer

Source Server         : hk
Source Server Version : 50620
Source Host           : 10.4.20.139:3306
Source Database       : product_tracing

Target Server Type    : MYSQL
Target Server Version : 50620
File Encoding         : 65001

Date: 2015-09-15 15:57:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for catalog
-- ----------------------------
DROP TABLE IF EXISTS `catalog`;
CREATE TABLE `catalog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catalog_name` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of catalog
-- ----------------------------
INSERT INTO `catalog` VALUES ('1', '甜品');
INSERT INTO `catalog` VALUES ('2', '饮料');
INSERT INTO `catalog` VALUES ('3', '水果');

-- ----------------------------
-- Table structure for component
-- ----------------------------
DROP TABLE IF EXISTS `component`;
CREATE TABLE `component` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) NOT NULL,
  `component_name` varchar(64) NOT NULL,
  `description` text,
  `picture` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=193 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of component
-- ----------------------------
INSERT INTO `component` VALUES ('108', '79', '西瓜', '', null);
INSERT INTO `component` VALUES ('109', '80', '茶叶', '', null);
INSERT INTO `component` VALUES ('110', '81', '咖啡豆', '', null);
INSERT INTO `component` VALUES ('111', '82', '牛奶', '', null);
INSERT INTO `component` VALUES ('112', '82', '牛油果', '', null);
INSERT INTO `component` VALUES ('115', '84', '红枣', '', null);
INSERT INTO `component` VALUES ('116', '84', '桂圆', '', null);
INSERT INTO `component` VALUES ('117', '84', '糖', '', null);
INSERT INTO `component` VALUES ('118', '84', '蜂蜜', '', null);
INSERT INTO `component` VALUES ('119', '85', '火腿', '', null);
INSERT INTO `component` VALUES ('120', '85', '吐司片', '', null);
INSERT INTO `component` VALUES ('121', '86', '赤豆', '', null);
INSERT INTO `component` VALUES ('122', '86', '薏米', '', null);
INSERT INTO `component` VALUES ('123', '87', '巧克力', '', null);
INSERT INTO `component` VALUES ('124', '87', '泡芙', '', null);
INSERT INTO `component` VALUES ('125', '87', '黄油', '', null);
INSERT INTO `component` VALUES ('126', '87', '鸡蛋', '', null);
INSERT INTO `component` VALUES ('127', '87', '面粉', '', null);
INSERT INTO `component` VALUES ('128', '88', '紫米', '', null);
INSERT INTO `component` VALUES ('129', '88', '黄砂糖', '', null);
INSERT INTO `component` VALUES ('130', '89', '牛奶', '', null);
INSERT INTO `component` VALUES ('131', '89', '面粉', '', null);
INSERT INTO `component` VALUES ('132', '89', '糖', '', null);
INSERT INTO `component` VALUES ('133', '89', '黄油', '', null);
INSERT INTO `component` VALUES ('134', '89', '鸡蛋', '', null);
INSERT INTO `component` VALUES ('135', '90', '黄油', '', null);
INSERT INTO `component` VALUES ('136', '90', '鸡蛋', '', null);
INSERT INTO `component` VALUES ('137', '90', '糖', '', null);
INSERT INTO `component` VALUES ('138', '90', '面粉', '', null);
INSERT INTO `component` VALUES ('139', '91', '仙草', '', null);
INSERT INTO `component` VALUES ('140', '91', '糖', '', null);
INSERT INTO `component` VALUES ('141', '91', '红豆', '', null);
INSERT INTO `component` VALUES ('142', '92', '橙子', '', null);
INSERT INTO `component` VALUES ('143', '93', '草莓', '', null);
INSERT INTO `component` VALUES ('144', '94', '巧克力', '', null);
INSERT INTO `component` VALUES ('145', '94', '奶油', '', null);
INSERT INTO `component` VALUES ('146', '94', '奥利奥', '', null);
INSERT INTO `component` VALUES ('147', '95', '巧克力', '', null);
INSERT INTO `component` VALUES ('148', '95', '奶油', '', null);
INSERT INTO `component` VALUES ('149', '95', '鸡蛋', '', null);
INSERT INTO `component` VALUES ('150', '95', '面粉 ', '', null);
INSERT INTO `component` VALUES ('151', '96', '鸡蛋', '', null);
INSERT INTO `component` VALUES ('152', '96', '起司', '', null);
INSERT INTO `component` VALUES ('153', '96', '柠檬', '', null);
INSERT INTO `component` VALUES ('154', '96', '咖啡', '', null);
INSERT INTO `component` VALUES ('155', '96', '饼干', '', null);
INSERT INTO `component` VALUES ('156', '97', '巧克力', '', null);
INSERT INTO `component` VALUES ('157', '97', '樱花', '', null);
INSERT INTO `component` VALUES ('158', '97', '奶油', '', null);
INSERT INTO `component` VALUES ('159', '97', '抹茶', '', null);
INSERT INTO `component` VALUES ('160', '97', '红豆', '', null);
INSERT INTO `component` VALUES ('161', '97', '蜂蜜', '', null);
INSERT INTO `component` VALUES ('162', '97', '蓝莓', '', null);
INSERT INTO `component` VALUES ('163', '97', '糯米', '', null);
INSERT INTO `component` VALUES ('164', '98', '面粉', '', null);
INSERT INTO `component` VALUES ('165', '98', '鸡蛋', '', null);
INSERT INTO `component` VALUES ('166', '98', '糖', '', null);
INSERT INTO `component` VALUES ('167', '98', '牛奶', '', null);
INSERT INTO `component` VALUES ('168', '99', '鸡蛋', '', null);
INSERT INTO `component` VALUES ('169', '99', '沙拉油', '', null);
INSERT INTO `component` VALUES ('170', '99', '糖', '', null);
INSERT INTO `component` VALUES ('171', '100', '牛奶', '', null);
INSERT INTO `component` VALUES ('172', '100', '鸡蛋', '', null);
INSERT INTO `component` VALUES ('173', '100', '面粉', '', null);
INSERT INTO `component` VALUES ('174', '100', '黄油', '', null);
INSERT INTO `component` VALUES ('175', '100', '榴莲', '', null);
INSERT INTO `component` VALUES ('176', '101', '面粉', '', null);
INSERT INTO `component` VALUES ('177', '101', '油', '', null);
INSERT INTO `component` VALUES ('178', '101', '榴莲', '', null);
INSERT INTO `component` VALUES ('179', '101', '蛋黄', '', null);
INSERT INTO `component` VALUES ('180', '102', '杏仁', '', null);
INSERT INTO `component` VALUES ('181', '102', '糖', '', null);
INSERT INTO `component` VALUES ('182', '102', '鸡蛋', '', null);
INSERT INTO `component` VALUES ('183', '103', '鸡蛋', '', null);
INSERT INTO `component` VALUES ('184', '103', '面粉', '', null);
INSERT INTO `component` VALUES ('185', '103', '柠檬', '', null);
INSERT INTO `component` VALUES ('186', '103', '油', '', null);
INSERT INTO `component` VALUES ('187', '103', '牛奶', '', null);
INSERT INTO `component` VALUES ('188', '104', '芒果', '', null);
INSERT INTO `component` VALUES ('189', '104', '糖', '', null);
INSERT INTO `component` VALUES ('190', '104', '冰沙', '', null);
INSERT INTO `component` VALUES ('191', '105', '芒果', '', null);
INSERT INTO `component` VALUES ('192', '105', '西柚', '', null);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(64) NOT NULL,
  `catalog_id` int(11) NOT NULL,
  `description` text,
  `picture` varchar(128) DEFAULT NULL,
  `shop` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('79', '冰镇果汁', '2', '天气越来越热，走在路上最想喝的就是美味的冰镇果汁啦。', '1441528303375.jpg', 'local');
INSERT INTO `product` VALUES ('80', '红茶', '2', '红茶是发酵类的茶，常喝红茶有清热解毒有良好的功效。', '1441528346607.png', 'local');
INSERT INTO `product` VALUES ('81', '咖啡', '2', '咖啡是用经过烘焙的咖啡豆制作出来的饮料，与可可、茶同为流行于世界的主要饮品。', '1441528383272.png', 'local');
INSERT INTO `product` VALUES ('82', '牛油果牛奶', '2', '牛油果的营养价值极高，各种保健、美容功效备受消费者亲睐。牛油果富含多种维生素、多种矿质元素、食用植物纤维，丰富的脂肪中不饱和脂肪酸含量高达80%，为高能低糖水果，有降低胆固醇和血脂，保护心血管和肝脏系统等重要生理功能。', '144152840574210.jpg', '嘉定区金沙江西路1051弄1号江桥万达3078,3079室【果之满满】');
INSERT INTO `product` VALUES ('84', '桂圆红枣茶', '2', '红枣历来有补血养气的功能，功效有健脑益智、温补、丰胸、安神、补血。', '144152848098211.jpg', '嘉定区江桥万达广场金街19号（华江路850弄）【哈嘟茶饮】');
INSERT INTO `product` VALUES ('85', '迷你扒火腿芝士三明治', '1', '这款三明治，选用健康的全麦面包，在两片面包中间依次铺上厚厚一层黄油、火腿片和芝士。锅中抹上黄油将三明治两面中火烤上三分钟，使芝士充分融化；再在表层抹上特制的béchamel酱，最后洒上一层芝士入烤箱烤化即可。吃的时候，搭配上清新爽口的沙拉，外焦内软的全麦面包裹上美味的芝士火腿，轻轻咬上一口，便可惬意地享受午后的美好时光……', '1441528491006.jpg', 'local');
INSERT INTO `product` VALUES ('86', '红豆薏米', '1', '薏米能增强肾功能，并有清热利尿作用，因些对浮肿病人有疗效。经现代药理研究证明，薏米有防癌的作用。其抗癌的有效成分为“薏苡仁脂”、“薏苡仁内脂”等，能有效抑制癌细胞的增殖，可用于胃癌、子宫颈癌的辅助治疗健康人常吃薏米，能使身体轻捷，减少肿瘤发病机会。', '144152855851712.jpg', '嘉定区江桥万达广场金街19号（华江路850弄）【哈嘟茶饮】');
INSERT INTO `product` VALUES ('87', '巧克力手指泡芙', '1', '泡芙（puff）也读泡芺（ao），是一种源自意大利的西式甜点。蓬松张孔的奶油面皮中包裹着奶油、巧克力乃至冰淇淋。传说泡芙16世纪传入法国，是法国皇后凯瑟琳·德·梅第奇发明的。', '1441528586916.png', 'local');
INSERT INTO `product` VALUES ('88', '紫米粥', '1', '紫米有不少食疗效用，滋阴补肾、明目补血，是为女性知己，并比较适合产妇、老人、儿童食用，可以有效地补充营养。', '144152860426013.jpg', '嘉定区江桥万达广场金街19号（华江路850弄）【哈嘟茶饮】');
INSERT INTO `product` VALUES ('89', '肉桂包', '1', '肉桂面包卷（Kanelbullar）是常见于瑞典咖啡聚会上的经典食品。在家庭焙烤盛行的黄金年代，咖啡聚会常变成狂欢，无节制地狂吃发酵甜面包、小饼干、带馅面包、酥皮糕点和蛋糕等。', '1441528695923.jpg', 'local');
INSERT INTO `product` VALUES ('90', '现烤曲奇', '1', '曲奇，来源于英语COOKIE（英音：\'kuki美音：\'kukɪ），是由香港传入的粤语译音，曲奇饼在美国与加拿大解释为细少而扁平的蛋榚式的饼干，而英语的COOKIE是由德文koekje来的，意为\"细少的蛋榚\"。', '1441528784380.jpg', 'local');
INSERT INTO `product` VALUES ('91', '烧仙草', '2', '烧仙草具备有去干降火，美容养颜的功效，备受当下女性的青睐。', '144152880883814.jpg', '奉贤区南桥镇南中路271-1号（南中路南桥路交叉口）');
INSERT INTO `product` VALUES ('92', '新鲜橙子', '3', '橙子（学名：Citrus sinensis）是芸香科柑橘属植物橙树的果实，亦称为黄果、柑子、金环、柳丁。橙子是一种柑果，是柚子（Citrus maxima）与橘子（Citrus reticulata）的杂交品种，起源于东南亚。果实可以剥皮鲜食其果肉，果肉也可以用作其他食物的附加物。果实的另一个重要用途为榨汁。', '1441528826072.jpg', 'local');
INSERT INTO `product` VALUES ('93', '新鲜草莓', '3', '草莓（学名：Fragaria × ananassa Duch，英文：Strawberry）。蔷薇科、草莓属多年生草本，一种红色的花果，又名红莓、洋莓、地莓等，外观呈心形，鲜美红嫩，果肉多汁，含有特殊的浓郁水果芳香。', '1441528870155.png', 'local');
INSERT INTO `product` VALUES ('94', '雪域牛乳芝士', '1', '选自松脆的白巧克力，醇正的乳脂奶油，配合美国原味和浓咖啡苦味的奥利奥饼干，０色素、０添加剂。 当你嚼一口松脆的巧克力打开味蕾，入口即化的奶油和芝士再搭配颗粒感的饼干，给你不一样的体验，不一样的甜品蛋糕', '144152889558715.jpg', '卢湾区徐家汇路618号日月光中心广场B2层TK43商铺');
INSERT INTO `product` VALUES ('95', '黑森林蛋糕', '1', '黑森林蛋糕是德国著名甜点。它融合了樱桃的酸、奶油的甜、巧克力的苦、樱桃酒的醇香。', '144152907673116.jpg', '松江区大学城一期文汇路652弄2楼211室（松江大学城派出所向西200米，文汇路龙腾路交叉口与复旦视觉艺术学院北门之间；正对龙胜路公交站牌，楼下武林猛煮，楼上k155连锁旅店，西靠新东方教育，东临学生超市）');
INSERT INTO `product` VALUES ('96', '提拉米苏', '1', '提拉米苏(Tiramisu)作为意大利甜点的代表，外貌绚丽、姿态娇媚，是风靡各大咖啡厅、烘焙门市及西餐厅的时髦甜点。主要食用功效为补钙、增强抵抗力。但肥胖者慎食。', '144152916488417.gif', '松江区大学城一期文汇路652弄2楼211室（松江大学城派出所向西200米，文汇路龙腾路交叉口与复旦视觉艺术学院北门之间；正对龙胜路公交站牌，楼下武林猛煮，楼上k155');
INSERT INTO `product` VALUES ('97', '摩提', '1', '是一种日式 \"菓子\"，是日本传统贵族的甜点小吃，是庆典和应季的必备食品，寓意健康快乐和永恒。', '144152931896418.jpg', '松江区新松江路925号开元地中海商业广场2楼（近西树泡芙，DQ对面）');
INSERT INTO `product` VALUES ('98', '鸡蛋仔', '1', '香港鸡蛋仔独具特色，让你闻香而来，看一眼就会爱上它。口感超好：独有配方精制而成的鸡蛋仔外壳酥脆，咬开时看到分成两层，上层中空，下层是软绵的夹心，口感超好没的比。色泽诱人：原味鸡蛋仔为金黄色，看上去就知道，一定是酥脆无比的美味。', '144152940601619.jpg', '浦东新区金海路2593-1号（网鱼网咖楼下、神之童乐园旁边）');
INSERT INTO `product` VALUES ('99', '盆栽蛋糕', '1', '盆栽蛋糕是一种创意蛋糕，盆栽蛋糕命名是根据外形而定的，因为和外形和盆栽一样，但实际上里面的泥土是由蛋糕制作而成的，十分美味。', '144152948508920.jpg', '奉贤区南桥镇百兴路1953号（美人鱼广场zoocoffee对面）');
INSERT INTO `product` VALUES ('100', '榴莲班戟', '1', '班戟除了各种不同的美味馅儿以外，人们同样非常喜爱它既薄又具有Q劲儿的表皮。制作班戟的时候，最主要的功夫，也花在这皮上了，只要皮做好了，搭配上鲜奶油和水果的馅儿，怎么都不会难吃。', '144152956902521.jpg', '黄浦区黄河路141号');
INSERT INTO `product` VALUES ('101', '榴莲忘返', '1', '榴莲能滋补身体、能活血散寒、含有较高的糖分，还有淀粉、蛋白质、脂肪、钙、铁、磷和多种维生素，对于健脾补气、补肾壮阳，同时还能够促进肠胃蠕动，帮助消化。', '144152962223422.jpg', '黄浦区黄河路141号');
INSERT INTO `product` VALUES ('102', '马卡龙 ', '1', '是一种用蛋白、杏仁粉、白砂糖和糖霜所做的法国甜点，通常在两块饼干之间夹有水果酱或奶油等内馅。这种甜食出炉后，以一个圆形平底的壳作基础，上面涂上调合蛋白，最后加上一个半球状的上壳，形成一个圆形小巧甜点，呈现出丰富的口感，是法国西边维埃纳省最具地方特色的美食。', '144152969155323.jpg', '徐汇区肇嘉浜路1111号美罗城地下一层A区B1-42单元【皇晟ROYAL SHEN】');
INSERT INTO `product` VALUES ('103', '雪藏戚风蛋糕', '1', '来自台湾，源于日本，超人气雪藏戚风蛋糕！！奶油和松软无比戚风蛋糕的绝配演绎，产生绝妙的口感黄金比例！', '144152984018824.jpg', '浦东新区祖冲之路1239弄长泰广场一层生活馆【摩米季】');
INSERT INTO `product` VALUES ('104', '白雪黑珍珠', '2', '白雪黑珍珠是香港最时尚夏天最受欢迎的的一种甜品小吃。白雪黑珍珠是回头客甜品培训中心研发出来的产品。牛奶味香浓。制作方法简单。各种口味不同口感。', '144152991667125.jpg', '浦东新区金海路2505弄曹路宝龙城市广场1楼【茗记甜品】');
INSERT INTO `product` VALUES ('105', '杨枝甘露', '2', '杨枝甘露是港式甜品，主要食材是西柚和芒果。芒果和西柚都含有丰富的维生素，是一道营养丰富的甜品。芒果具有抗菌消炎、防癌抗癌、祛痰止咳、降低胆固醇和甘油三酯、名目的功效。西柚具有降低胆固醇、降低血糖、加速复原受伤的皮肤组织功能的作用', '144152996394026.jpg', '浦东新区金海路2505弄曹路宝龙城市广场1楼【茗记甜品】');
