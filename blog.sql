/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50642
 Source Host           : localhost:3306
 Source Schema         : blog

 Target Server Type    : MySQL
 Target Server Version : 50642
 File Encoding         : 65001

 Date: 23/08/2019 10:51:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_article
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `article_id` bigint(20) NOT NULL COMMENT '文章ID ',
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文章标题',
  `article_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章正文',
  `article_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文章作者',
  `article_pageview` bigint(20) NULL DEFAULT NULL COMMENT '文章浏览量',
  `article_date` datetime(0) NOT NULL COMMENT '文章创作日期',
  `article_tabloid` mediumtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '文章摘要',
  `article_star` int(11) NULL DEFAULT NULL COMMENT '文章点赞',
  `article_categories` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '文章分类',
  `last_article_id` bigint(20) NULL DEFAULT NULL COMMENT '上一篇文章ID',
  `next_article_id` bigint(20) NULL DEFAULT NULL COMMENT '下一篇文章ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_author`(`article_author`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `article_categories`(`article_categories`) USING BTREE,
  CONSTRAINT `t_article_ibfk_1` FOREIGN KEY (`article_author`) REFERENCES `t_user` (`user_name`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_article_ibfk_2` FOREIGN KEY (`article_categories`) REFERENCES `t_classify` (`classify`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 38 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES (30, 1566369878258, 'Springboot1', '## 本项目用到的技术和框架\n\n- 项目构建：Maven\n- web框架：Springboot\n- 数据库ORM：Mybatis\n- 数据库连接池： Druid\n- 分页插件：PageHelper\n- 数据库：MySql\n- 缓存：Redis\n- 前端模板：Thymeleaf\n- 文章展示：Editor.md\n\n<!--more-->\n\n## 配置PageHelper\n\n```java\n@SpringBootApplication\npublic class BlogApplication extends SpringBootServletInitializer {\n\n    public static void main(String[] args) {\n        SpringApplication.run(BlogApplication.class, args);\n    }\n    /**\n     * 配置PageHelper\n     */\n     @Bean\n    public PageHelper pageHelper() {\n        System.out.println(\"开始配置数据分页插件\");\n        PageHelper pageHelper = new PageHelper();\n        Properties properties = new Properties();\n        // 当该参数设置为true时，使用RowBounds分页时，会将offset参数当成pageNum使用，可以用页码和页面大小两个参数进行分页。\n        properties.setProperty(\"offsetAsPageNum\", \"true\");\n        // 当该参数设置为true时，使用RowBounds分页会进行count查询\n        properties.setProperty(\"rowBoundsWithCount\", \"true\");\n        // 当该参数设置为true时，如果pageNum<1会查询第一页，如果pageNum>pages会查询最后一页\n        properties.setProperty(\"reasonable\", \"true\");\n        // 当该参数设置为true时，如果pageSize=0或者RowBounds.limit = 0就会查询出全部的结果（相当于没有执行分页查询，但是返回结果仍然是Page类型）\n        properties.setProperty(\"pageSizeZero\", \"false\");\n        //可选值为oracle,mysql,mariadb,sqlite,hsqldb,postgresql,没有默认值，必须指定该属性\n        properties.setProperty(\"dialect\", \"mysql\");\n        pageHelper.setProperties(properties);\n        return pageHelper;\n}\n```\n\n**PageHelper使用案例**\n\n```java\n@RestController\npublic class PageListController {\n\n    @Autowired\n    PageList pageList;\n\n    /**\n     * 分页查询文章 \n     * @param pageNum 当前页码\n     * @param pageSize 想要查询的信息条数\n     * @return Page<Article> 将返回的实体类放入Page<>中返回\n     */\n    @RequestMapping(value = \"/selectArticle\")\n    public Page<Article> selectArticle(@RequestParam(\"pageNum\") Integer pageNum, @RequestParam(\"pageSize\") Integer pageSize) {\n        // 开启分页，不能遗漏\n        PageHelper.startPage(pageNum, pageSize);\n        Page<Article> articles = pageList.selectArticle();\n        return articles;\n    }\n}\n```\n\n```xml\n<!--Mapper处的返回，直接写实体类就行，不用Page<>包装-->\n<select id=\"selectArticle\" resultType=\"com.example.blog.entity.Article\">\n        select * from t_article\n</select>\n```\n\n\n\n## 配置thymeleaf模板\n\n使用thymeleaf目的：为了方便跳转页面\n\n```YML\n # application.yml\n # thymeleaf模板页面默认放在classpath:/templates/下，后缀默认为.html\n thymeleaf:\n    prefix: classpath:/templates/\n    suffix: .html\n # 静态资源配置\n resources:\n    static-locations: classpath:/static/,classpath:/templates/\n```\n\n**跳转案例**\n\n```JAVA\n// 这里注意是controller\n// 因为thymeleaf的配置，所以会从classpath:/templates/目录下寻找login.html\n@Controller\npublic class BackController {\n    @RequestMapping(\"/\")\n    public String login() {\n        log.info(\"跳转到首页\");\n        return \"login\";\n    }\n}\n```\n\n## 后端打印每次执行的SQL\n\n```YML\n# 打印mysql\nlogging:\n  level:\n  	# DAO接口的位置\n    com.example.blog.dao: debug\n```', '周昱君', 200, '2019-08-21 14:44:38', '本项目用到的技术和框架项目构建：Mavenweb框架：Springboot数据库ORM：Mybatis数据库连接池：Druid分页插件：PageHelper数据库：MySql缓存：Redis前端模板：Thymeleaf文章展示：Editor.md配置PageHelperPageHelper使用案例配置thymeleaf模板使用thymeleaf目的：为了方便跳转页面跳转案例后端打印每次执行的', 12, '技术类', 0, 1566369892338);
INSERT INTO `t_article` VALUES (31, 1566369892338, 'Springboot2', '## 登录逻辑\n\n```java\n /**\n     * 用户每次登录的时候都会生成一个唯一的表示token，用它来作为key，用户信息作为value，然后将token存到Cookie里面返给浏览器。用户下次\n     * 访问用户中心的时候，从Cookie里面取token，再用token从redis中取用户信息，来判断是否允许访问用户中心。\n     *\n     * @param map      前端传入的参数 将它封装成map\n     * @return\n     */\n    @RequestMapping(value = \"/login\", method = RequestMethod.POST)\n    public Integer login(HttpServletResponse response, HttpServletRequest request, @RequestParam Map<String, String> map) throws IOException {\n        // 从cookie取key为onlyNum值，如不存在，则读取输入账号密码，如果存在，取出改值将它设为redis的key，从而取出redis中存储的账号信息\n        String getOnlyNum = CookieUtils.getCookieValue(\"onlyNum\");\n        if (!StringUtils.isBlank(getOnlyNum)) {\n            String accountMsgForJson = RedisUtil.get(getOnlyNum);\n            if (!StringUtils.isBlank(accountMsgForJson)) {\n                // 将redis中取出的账号信息(当前为JSON)转换为Account对象\n                Account accountMsg = JSON.parseObject(accountMsgForJson, Account.class);\n                Account account = loginService.login(accountMsg.getRoleAdmin(), accountMsg.getRolePsw());\n                // 前端ajax接收信息为1时，跳转页面至index.html，信息为0时，页面刷新不跳转\n                if (account != null) {\n                    log.info(\"redis存储的账号或密码正确-----登陆成功\");\n                    return 1;\n                } else {\n                    log.error(\"redis存储的账号或密码错误-----登陆失败\");\n                    return 0;\n                }\n                // cookie中key为onlyNum对应的value，该value对应redis中的key，无法取出对应的redis值时\n            } else {\n                log.error(\"redis并无key为：\" + getOnlyNum);\n                CookieUtils.removeCookie(\"onlyNum\");\n                log.info(\"cookie删除key为\" + getOnlyNum + \"成功\");\n                return 0;\n            }\n            // 如果cookie中没有key为onlyNum（说明从未登录，或者登陆过后已登出）\n        } else {\n            String uuid = CookieUtils.getCookieValue(\"UUID\");\n            if (!StringUtils.isBlank(uuid)) {\n                // 从cookie中取出key为UUID的值，将该值当做redis的key，能够取出真实的验证码\n                String yzm = RedisUtil.get(uuid);\n                if (!StringUtils.isBlank(yzm)) {\n                    // 获取前端页面传入进来的yzm\n                    String getYZM = map.get(\"yzm\");\n                    // 如果前端传入的验证码值和redis中取出的验证码匹配\n                    if (getYZM.equals(yzm)) {\n                        String username = map.get(\"username\");\n                        String password = map.get(\"password\");\n                        // 将密码MD5加密之后\n                        String passwordMd5 = MD5Util.md5Encrpt(password);\n                        Account account = loginService.login(username, passwordMd5);\n                        if (account != null) {\n                            // 登录成功从redis中删除key，再次登录需要重新获取验证码\n                            RedisUtil.remove(uuid);\n                            String onlyNum = UUID.randomUUID().toString().replaceAll(\"-\", \"\");\n                            // 设置cookie，key为onlyNum，值为一个随机生成数\n                            CookieUtils.setCookie(\"onlyNum\", onlyNum);\n                            // 设置redis，将key为cookie的key，一个随机生成数，值为账号对象，有效期为1天\n                            RedisUtil.set(onlyNum, JSON.toJSONString(account), 86400L);\n                            return 1;\n                        } else {\n                            log.error(\"输入的账号或者密码错误\");\n                            return 0;\n                        }\n                    } else {\n                        log.error(\"验证码输入错误\");\n                        return 0;\n                    }\n                } else {\n                    log.error(\"redis中存储验证码的key为空，请刷新验证码\");\n                    return 0;\n                }\n            } else {\n                log.error(\"cookie中UUID为空，请刷新验证码\");\n                return 0;\n            }\n        }\n    }\n```\n\n<!--more-->\n\n## 获取验证码\n\n**工具类**\n\n```java\npackage com.example.blog.utils;\n\nimport java.awt.*;\nimport java.awt.image.BufferedImage;\nimport java.util.Random;\n\n/**\n * Created by HuangLiTe on 2018/8/22.\n */\npublic class VerifyUtil {\n    // 验证码字符集\n    private static final char[] chars = {\n            \'0\', \'1\', \'2\', \'3\', \'4\', \'5\', \'6\', \'7\', \'8\', \'9\',\n            \'A\', \'B\', \'C\', \'D\', \'E\', \'F\', \'G\', \'H\', \'J\', \'K\', \'L\', \'M\', \'N\',\n            \'P\', \'Q\', \'R\', \'S\', \'T\', \'U\', \'V\', \'W\', \'X\', \'Y\', \'Z\'};\n    // 字符数量\n    private static final int SIZE = 4;\n    // 干扰线数量\n    private static final int LINES = 5;\n    // 宽度\n    private static final int WIDTH = 123;\n    // 高度\n    private static final int HEIGHT = 54;\n    // 字体大小\n    private static final int FONT_SIZE = 44;\n\n    /**\n     * 生成随机验证码及图片\n     * Object[0]：验证码字符串；\n     * Object[1]：验证码图片。\n     */\n    public static Object[] createImage() {\n        StringBuffer sb = new StringBuffer();\n        // 1.创建空白图片\n        BufferedImage image = new BufferedImage(\n                WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);\n        // 2.获取图片画笔\n        Graphics graphic = image.getGraphics();\n        // 3.设置画笔颜色\n        graphic.setColor(Color.LIGHT_GRAY);\n        // 4.绘制矩形背景\n        graphic.fillRect(0, 0, WIDTH, HEIGHT);\n        // 5.画随机字符\n        Random ran = new Random();\n        for (int i = 0; i < SIZE; i++) {\n            // 取随机字符索引\n            int n = ran.nextInt(chars.length);\n            // 设置随机颜色\n            graphic.setColor(getRandomColor());\n            // 设置字体大小\n            graphic.setFont(new Font(\n                    null, Font.BOLD + Font.ITALIC, FONT_SIZE));\n            // 画字符\n            graphic.drawString(\n                    chars[n] + \"\", i * WIDTH / SIZE, HEIGHT * 2 / 3);\n            // 记录字符\n            sb.append(chars[n]);\n        }\n        // 6.画干扰线\n        for (int i = 0; i < LINES; i++) {\n            // 设置随机颜色\n            graphic.setColor(getRandomColor());\n            // 随机画线\n            graphic.drawLine(ran.nextInt(WIDTH), ran.nextInt(HEIGHT),\n                    ran.nextInt(WIDTH), ran.nextInt(HEIGHT));\n        }\n        // 7.返回验证码和图片\n        return new Object[]{sb.toString(), image};\n    }\n\n    /**\n     * 随机取色\n     */\n    public static Color getRandomColor() {\n        Random ran = new Random();\n        Color color = new Color(ran.nextInt(256),\n                ran.nextInt(256), ran.nextInt(256));\n        return color;\n    }\n\n}\n```\n\n**获取验证码逻辑**\n\n```java\n/**\n     * 获取验证码\n     *\n     * @param response\n     */\n    @RequestMapping(\"/code\")\n    public void code(HttpServletResponse response, HttpServletRequest request) {\n        Object[] objects = VerifyUtil.createImage();\n        BufferedImage image = (BufferedImage) objects[1];\n        // 设置浏览器不缓存本页\n        response.addHeader(\"Pragma\", \"no-cache\");\n        response.addHeader(\"Cache-Control\", \"no-cache\");\n        response.addHeader(\"Expires\", \"0\");\n        response.setContentType(\"image/jpeg\");\n        log.info(\"验证码为\"+objects[0]);\n        // 获取验证码\n        String yzm = (String) objects[0];\n        // 获取唯一数\n        String uuid = UUID.randomUUID().toString().replaceAll(\"-\", \"\");\n        log.info(\"验证码uuid为\"+uuid);\n        // 将唯一数作为值，key为UUID存入cookie\n        CookieUtils.setCookie(\"UUID\", uuid);\n        // 将唯一数作为key，生成的验证码的值作为value，存入redis，有效期为3分钟\n        RedisUtil.set(uuid, yzm, 180L);\n        try {\n            ImageIO.write(image, \"JPEG\", response.getOutputStream());\n        } catch (IOException e) {\n            e.printStackTrace();\n        }\n    }\n```\n\n## blog摘要生成逻辑\n\n```JAVA\n  public static String buildArticleTabloid(String htmlArticleComment) {\n\n        String regex = \"\\\\s+\";\n        String str = htmlArticleComment.trim();\n        //去掉所有空格\n        String articleTabloid = str.replaceAll(regex, \"\");\n        //将文章从头到尾遍历一遍，把<!--more-->之前的内容取出设置为文章摘要，采用了StringUtils工具类\n        String beforeMeg = StringUtils.substringBefore(articleTabloid, \"&lt;!--more--&gt;\");\n        return beforeMeg;\n}\n```\n\n', '俞民涛', 100, '2019-08-21 14:44:52', '登录逻辑获取验证码工具类获取验证码逻辑blog摘要生成逻辑', 54, '技术类', 1566369878258, 1566369910178);
INSERT INTO `t_article` VALUES (32, 1566369910178, 'Springboot3', '需求：根据后端返回的JSON数组，动态的加载div，比如后端返回4组数据，就动态生成4个div，并将返回的数据内容显示出来\n\n<!--more-->\n\n操作：\n\n```js\n<script>\n// 页面加载时就ajax\n    $(document).ready(function () {\n        $.ajax({\n            async: false,\n            url: \"http://localhost:8888/selectArticle\",\n            type: \"GET\",\n            data: {pageNum: 1, pageSize: 10},\n            error: function (error) {\n                console.log(error);\n            },\n            success: function (data) {\n                // $(\'.articles\').empty();\n                // $(\'.content\').empty();\n                var articles = $(\'.grid\');\n                var content = $(\'.content\');\n                $.each(data, function (i, obj) {\n                    var center = $(\'<a class=\"grid__item\" href=\"#\">\' +\n                        \'<h2 class=\"title title--preview\">\' + obj[\'articleTitle\'] + \'</h2>\' +\n                        \'<div class=\"loader\">\' + \'</div>\' +\n                        \'<span class=\"category\">\' + obj[\'articleTabloid\'] + \'</span>\' +\n                        \'<div class=\"meta meta--preview\">\' +\n                        \'<span class=\"meta__date\">\' + \'<i class=\"fa fa-calendar-o\">\' + \'</i>\' + obj[\'articleDate\'] + \'</span>\' +\n                        \'<span class=\"meta__reading-time\">\' + \'<i class=\"fa fa-clock-o\">\' + \'</i>\' + obj[\'articleAuthor\'] + \'</span>\' +\n                        \'</div>\' +\n                        \'</a>\'\n                    );\n\n                    var tent = $(\'<div class=\"scroll-wrap\">\' +\n                        \'<article class=\"content__item\">\' +\n                        \'<span class=\"category category--full\">Stories for humans</span>\' +\n                        \'<h2 class=\"title title--full\">\' + obj[\'articleTitle\'] + \'</h2>\' +\n                        \'<div class=\"meta meta--full\">\' +\n                        \'<span class=\"meta__author\">\' + obj[\'articleAuthor\'] + \'</span>\' +\n                        \'<span class=\"meta__date\"><i class=\"fa fa-calendar-o\"></i>\' + obj[\'articleDate\'] + \'</span>\' +\n                        \'<span class=\"meta__reading-time\"><i class=\"fa fa-clock-o\"></i> 3 min read</span>\' +\n                        \'</div>\' +\n                        \'<p>正文</p>\' +\n                        \'</article>\' +\n                        \'</div>\'\n                    );\n                    articles.append(center);\n                    content.append(tent);\n                })\n            }\n        })\n    });\n</script>\n```\n\n但是这样会发现js失效了，原因是是Ajax在载入新的dom之前就已经把页面的JS加载完了，再执行事件时，没有绑定到新载入的dom上，所以我的解决方法是**将js也动态加载**，根据我的了解`$(document).ready()`的方法是执行在·、`window.onload = function (){}`前的，所以我使用`$(document).ready()`首先加载div框架，加载完毕之后，再使用`window.onload = function (){}`方法加载js，那么就能保证JS最后加载\n\n```JS\n<!--动态加载JS-->\n<script>\n    window.onload = function () {\n        var body = $(\"body\");\n        <!--此处添加需要动态加载的js-->\n        var js = $(\n            \'<script src=\"../static/assets/js/modernizr.custom.js\">\' +\n            \'\\<\\/script>\' + \'<script src=\"../static/assets/js/classie.js\">\' +\n            \'\\<\\/script>\' + \'<script src=\"../static/assets/js/count.js\">\' +\n            \'\\<\\/script>\'\n        );\n        body.append(js);\n    };\n</script>\n```\n\n', '俞民涛', 50, '2019-08-21 14:45:10', '需求：根据后端返回的JSON数组，动态的加载div，比如后端返回4组数据，就动态生成4个div，并将返回的数据内容显示出来操作：但是这样会发现js失效了，原因是是Ajax在载入新的dom之前就已经把页面的JS加载完了，再执行事件时，没有绑定到新载入的dom上，所以我的解决方法是将js也动态加载，根据我的了解$(document).ready()的方法是执行在·、window.onload=f', 520, '运动类', 1566369892338, NULL);
INSERT INTO `t_article` VALUES (33, 1566369910179, NULL, '', NULL, NULL, '0000-00-00 00:00:00', '', NULL, NULL, NULL, NULL);
INSERT INTO `t_article` VALUES (34, 1566369910180, NULL, '', NULL, NULL, '0000-00-00 00:00:00', '', NULL, NULL, NULL, NULL);
INSERT INTO `t_article` VALUES (35, 1566369910181, NULL, '', NULL, NULL, '0000-00-00 00:00:00', '', NULL, NULL, NULL, NULL);
INSERT INTO `t_article` VALUES (37, 1566369910182, NULL, '', NULL, NULL, '0000-00-00 00:00:00', '', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for t_classify
-- ----------------------------
DROP TABLE IF EXISTS `t_classify`;
CREATE TABLE `t_classify`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classify` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '分类',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `classify`(`classify`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_classify
-- ----------------------------
INSERT INTO `t_classify` VALUES (5, '娱乐圈');
INSERT INTO `t_classify` VALUES (6, '小说类');
INSERT INTO `t_classify` VALUES (2, '技术类');
INSERT INTO `t_classify` VALUES (7, '漫画类');
INSERT INTO `t_classify` VALUES (1, '生活类');
INSERT INTO `t_classify` VALUES (4, '电竞类');
INSERT INTO `t_classify` VALUES (3, '运动类');

-- ----------------------------
-- Table structure for t_message
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message`  (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `message_commenter_id` int(11) NOT NULL COMMENT '留言者ID',
  `message_date` datetime(0) NULL DEFAULT NULL COMMENT '留言日期',
  `message_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '留言内容',
  `article_id` bigint(20) NOT NULL COMMENT '留言文章ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `message_commenter_id`(`message_commenter_id`) USING BTREE,
  CONSTRAINT `t_message_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `t_article` (`article_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `t_message_ibfk_2` FOREIGN KEY (`message_commenter_id`) REFERENCES `t_user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_message
-- ----------------------------
INSERT INTO `t_message` VALUES (1, 1, '2019-08-21 14:57:55', '今天真棒 正文评论', 1566369878258);
INSERT INTO `t_message` VALUES (8, 2, '2019-08-21 15:17:25', '评论2', 1566369878258);
INSERT INTO `t_message` VALUES (9, 2, '2019-08-22 15:17:48', '评论3', 1566369892338);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户权限',
  PRIMARY KEY (`role_id`) USING BTREE,
  INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (2, 'ROLE_ADMIN');
INSERT INTO `t_role` VALUES (1, 'ROLE_USER');
INSERT INTO `t_role` VALUES (3, 'ROLE_VISITOR');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_admin` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `user_psw` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '账号昵称',
  `user_role` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '用户权限',
  `user_date` datetime(0) NULL DEFAULT NULL COMMENT '用户注册时间',
  `user_vip` tinyint(255) NULL DEFAULT NULL COMMENT '用户是否为VIP',
  PRIMARY KEY (`user_id`) USING BTREE,
  INDEX `role_name`(`user_name`) USING BTREE,
  INDEX `user_role`(`user_role`) USING BTREE,
  CONSTRAINT `t_user_ibfk_1` FOREIGN KEY (`user_role`) REFERENCES `t_role` (`role_name`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'root', '64aaf1eb7cba81517a6c659f86491946', '俞民涛', 'ROLE_ADMIN', '2019-08-14 14:39:32', 1);
INSERT INTO `t_user` VALUES (2, 'admin', '2224302a7b58a6a8448a4b04b8120c4', '周昱君', 'ROLE_USER', '2019-08-13 14:39:36', 1);
INSERT INTO `t_user` VALUES (3, 'youke', '8aaa1185f9ada1a1f474ec3420dfcf45', '游客', 'ROLE_VISITOR', '2019-08-04 14:39:40', 0);

SET FOREIGN_KEY_CHECKS = 1;
