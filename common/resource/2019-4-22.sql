/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.12 : Database - myplatform
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`myplatform` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `myplatform`;

/*Table structure for table `dyn_msg_comment` */

DROP TABLE IF EXISTS `dyn_msg_comment`;

CREATE TABLE `dyn_msg_comment` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `pid` int(10) DEFAULT NULL COMMENT '父id,用来判断属于''某楼层''的评论',
  `m_id` int(10) DEFAULT NULL COMMENT 'u_dyn_msg表id',
  `srcUid` int(10) DEFAULT NULL,
  `descUid` int(10) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL,
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `comment_dyn_msg` (`m_id`),
  CONSTRAINT `comment_dyn_msg` FOREIGN KEY (`m_id`) REFERENCES `u_dyn_msg` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;

/*Data for the table `dyn_msg_comment` */

insert  into `dyn_msg_comment`(`id`,`pid`,`m_id`,`srcUid`,`descUid`,`content`,`createTime`) values (4,1,123,6,5,'楼上的xxx','2019-03-12 09:52:20'),(5,1,123,8,6,'厉害了','2019-03-12 11:07:44'),(6,NULL,123,1,NULL,'老铁双击666<img src=\"http://localhost:8082/layui/images/face/1.gif\" alt=\"[嘻嘻]\">','2019-03-13 10:04:01'),(26,6,123,1,1,'haoleba','2019-03-13 11:35:15'),(27,NULL,123,1,NULL,'<img src=\"http://localhost:8082/layui/images/face/53.gif\" alt=\"[耶]\">','2019-03-13 11:36:55'),(28,NULL,123,1,NULL,'<img src=\"http://localhost:8082/layui/images/face/40.gif\" alt=\"[晕]\">','2019-03-13 13:39:27'),(29,NULL,123,1,NULL,'1','2019-03-13 13:52:25'),(30,NULL,123,1,NULL,'2','2019-03-13 13:52:29'),(31,NULL,123,1,NULL,'3','2019-03-13 13:52:35'),(32,NULL,123,1,NULL,'4','2019-03-13 13:52:38'),(33,NULL,123,1,NULL,'5','2019-03-13 13:52:41'),(34,NULL,123,1,NULL,'6','2019-03-13 13:52:44'),(35,NULL,123,1,NULL,'7','2019-03-13 13:52:46'),(36,NULL,123,1,NULL,'8','2019-03-13 13:52:50'),(37,NULL,123,1,NULL,'9','2019-03-13 13:52:52'),(38,1,123,1,5,'1','2019-03-13 13:53:06'),(39,1,123,1,5,'2','2019-03-13 13:53:14'),(40,1,123,1,5,'3','2019-03-13 13:53:17'),(41,1,123,1,5,'5','2019-03-13 13:53:21'),(42,1,123,1,5,'6','2019-03-13 13:53:23'),(43,1,123,1,5,'7','2019-03-13 13:53:26'),(44,1,123,1,5,'8','2019-03-13 13:53:29'),(45,1,123,1,5,'9','2019-03-13 13:53:32'),(46,1,123,1,5,'0','2019-03-13 13:53:34'),(47,NULL,123,1,NULL,'14','2019-03-13 14:32:23'),(48,NULL,123,1,NULL,'15','2019-03-13 14:34:22'),(49,NULL,123,1,NULL,'16','2019-03-13 14:35:25'),(50,NULL,123,1,NULL,'17','2019-03-13 14:36:23'),(51,6,123,1,1,'2','2019-03-13 14:48:17'),(52,27,123,1,1,'1','2019-03-13 14:54:17'),(53,NULL,123,1,NULL,'18','2019-03-13 15:35:57'),(54,NULL,123,1,NULL,'19','2019-03-13 15:37:56'),(55,6,123,1,1,'3','2019-03-13 15:39:06'),(56,NULL,123,1,NULL,'20','2019-03-13 15:39:12'),(57,NULL,123,1,NULL,'21','2019-03-13 15:47:33'),(58,27,123,1,1,'2','2019-03-13 15:47:46'),(59,27,123,1,1,'3','2019-03-13 15:47:51'),(60,NULL,123,1,NULL,'22','2019-03-13 15:49:23'),(61,NULL,123,1,NULL,'23','2019-03-13 15:49:59'),(62,27,123,1,1,'4','2019-03-13 15:50:13'),(63,27,123,1,1,'5','2019-03-13 15:50:18'),(64,27,123,1,1,'6','2019-03-13 15:56:19'),(65,27,123,1,1,'7','2019-03-13 15:56:30'),(66,NULL,123,1,NULL,'24','2019-03-13 15:56:36'),(67,27,123,1,1,'8','2019-03-13 15:56:54'),(68,27,123,1,1,'9','2019-03-13 15:56:58'),(69,27,123,1,1,'10','2019-03-13 15:57:03'),(70,27,123,1,1,'11','2019-03-13 15:57:07'),(71,27,123,1,1,'12','2019-03-13 15:57:16'),(72,27,123,5,1,'6-1','2019-03-13 15:59:57'),(73,27,123,6,5,'6-1-1','2019-03-13 16:06:09'),(74,27,123,6,5,'6-1-2','2019-03-13 16:10:52'),(75,27,123,1,6,'ok','2019-03-13 16:14:29'),(76,6,123,1,1,'4','2019-03-13 16:55:42'),(77,NULL,151,1,NULL,'<img src=\"http://localhost:8082/layui/images/face/0.gif\" alt=\"[微笑]\">ni hao a&nbsp;','2019-03-15 10:21:36'),(78,NULL,152,1,NULL,'<img src=\"/layui/images/face/8.gif\" alt=\"[挤眼]\">','2019-03-19 11:44:13'),(79,28,123,5,1,'<img src=\"/layui/images/face/26.gif\" alt=\"[怒]\">','2019-03-21 17:33:37'),(80,79,123,6,5,'<img src=\"/layui/images/face/11.gif\" alt=\"[爱你]\">','2019-03-21 17:34:03'),(81,NULL,123,2,NULL,'wo','2019-03-22 10:23:04'),(82,28,123,2,1,'<img src=\"/layui/images/face/39.gif\" alt=\"[鼓掌]\">','2019-03-22 10:23:24'),(83,80,123,2,6,'<img src=\"/layui/images/face/36.gif\" alt=\"[酷]\">','2019-03-22 10:23:43'),(87,28,123,2,1,'5','2019-03-22 10:50:06'),(88,28,123,2,1,'6','2019-03-22 10:50:12'),(89,79,123,2,5,'7','2019-03-22 10:50:26'),(91,90,123,1,2,'9','2019-03-22 14:09:47'),(92,89,123,1,2,'huifu','2019-03-22 15:53:46'),(93,92,123,2,1,'ok','2019-03-22 16:03:06'),(94,92,123,2,1,'ok2','2019-03-22 16:05:13');

/*Table structure for table `g_members` */

DROP TABLE IF EXISTS `g_members`;

CREATE TABLE `g_members` (
  `user_id` int(10) DEFAULT NULL COMMENT '用户ID',
  `group_id` int(10) DEFAULT NULL COMMENT '分组ID',
  UNIQUE KEY `g_members_index` (`user_id`,`group_id`),
  KEY `group_members_key` (`group_id`),
  CONSTRAINT `group_members_key` FOREIGN KEY (`group_id`) REFERENCES `u_group` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `g_members` */

insert  into `g_members`(`user_id`,`group_id`) values (1,29),(1,30),(1,31),(1,34),(1,35),(1,36),(2,31),(4,29),(5,22),(5,30),(5,31),(5,32),(6,1),(6,31),(10,22),(12,1);

/*Table structure for table `u_chat` */

DROP TABLE IF EXISTS `u_chat`;

CREATE TABLE `u_chat` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` varchar(1000) DEFAULT NULL,
  `srcUid` int(10) DEFAULT NULL,
  `descUid` int(10) DEFAULT NULL,
  `descGid` int(10) DEFAULT NULL,
  `status` int(1) DEFAULT '0' COMMENT '状态 0:未读 1:已读',
  `createTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8;

/*Data for the table `u_chat` */

insert  into `u_chat`(`id`,`content`,`srcUid`,`descUid`,`descGid`,`status`,`createTime`) values (1,'测试1',5,1,NULL,1,'2019-03-06 10:56:24'),(2,'测试2',1,5,NULL,1,'2019-03-06 10:56:45'),(3,'测试离线',5,1,NULL,1,'2019-03-06 10:57:04'),(4,'测试',5,1,NULL,1,'2019-03-06 11:09:13'),(5,'我离线了',1,5,NULL,1,'2019-03-06 11:09:22'),(6,'测试群',5,NULL,31,1,'2019-03-06 11:19:27'),(7,'好的,测试吧',1,NULL,31,1,'2019-03-06 11:19:48'),(8,'好的',5,1,NULL,1,'2019-03-06 11:26:39'),(9,'测试',6,1,NULL,1,'2019-03-06 11:55:13'),(10,'asd',1,NULL,31,1,'2019-03-06 17:42:38'),(11,'wei',1,NULL,31,1,'2019-03-06 17:46:56'),(12,'asd',1,NULL,31,1,'2019-03-06 17:49:21'),(13,'ads',1,NULL,31,1,'2019-03-06 17:49:30'),(14,'成功',1,NULL,31,1,'2019-03-06 17:59:47'),(15,'看到了',1,6,NULL,1,'2019-03-07 10:24:50'),(16,'ok',1,5,NULL,1,'2019-03-07 10:24:54'),(17,'我们来未数据库多加点内容',5,1,NULL,1,'2019-03-07 10:46:50'),(18,'测试聊天记录分页',1,5,NULL,1,'2019-03-07 10:47:06'),(19,'好的',1,5,NULL,1,'2019-03-07 10:47:10'),(20,'那就多说一点',5,1,NULL,1,'2019-03-07 10:47:25'),(21,'说点什么呢',1,5,NULL,1,'2019-03-07 10:47:29'),(22,'不知道啊',5,1,NULL,1,'2019-03-07 10:47:34'),(23,'那你真是辣鸡',1,5,NULL,1,'2019-03-07 10:47:39'),(24,'彼此彼此',5,1,NULL,1,'2019-03-07 10:48:20'),(25,'face[ok] ',1,5,NULL,1,'2019-03-07 15:24:02'),(26,'img[\\chat\\e80b8a7c43b3435fb2f13c16dea04513.jpg]',1,5,NULL,1,'2019-03-07 15:32:06'),(27,'img[\\chat\\img\\5\\69f30be82fdc4fddb7101c32c7fa01c5.jpg]',5,1,NULL,1,'2019-03-07 16:01:25'),(28,'file(\\chat\\file\\1\\99df39feb790465cb25afe766b6cfa3a.doc)[下载文件]',1,5,NULL,1,'2019-03-07 16:02:15'),(29,'file(\\chat\\file\\1\\流媒体平台设计方案20190307161119.docx)[下载文件]',1,5,NULL,1,'2019-03-07 16:11:19'),(30,'img[\\chat\\img\\5\\120190307161159.jpg]',5,1,NULL,1,'2019-03-07 16:11:59'),(31,'video[http://www.w3school.com.cn//i/movie.ogg]',1,5,NULL,1,'2019-03-07 16:14:17'),(32,'audio[http://gddx.sc.chinaz.com/Files/DownLoad/sound1/201510/6473.mp3]',1,5,NULL,1,'2019-03-07 16:14:39'),(33,'1',1,10,NULL,1,'2019-03-08 17:36:58'),(34,'我是sb',13,1,NULL,1,'2019-03-12 17:29:20'),(35,'为什么这么对自己',1,13,NULL,1,'2019-03-12 17:29:35'),(36,'我乐意',13,1,NULL,1,'2019-03-12 17:29:42'),(37,'111',1,5,NULL,1,'2019-03-14 13:50:29'),(38,'1',1,6,NULL,1,'2019-03-18 17:03:49'),(39,'ok',6,1,NULL,1,'2019-04-01 16:04:14'),(40,'1',1,2,NULL,1,'2019-04-11 16:18:28'),(41,'www.baidu.com',2,1,NULL,1,'2019-04-11 16:22:10'),(42,'a(http://www.baidu.com)[添加好友]',1,2,NULL,1,'2019-04-11 16:25:42'),(43,'[pre class=layui-code]的撒阿瑟东[/pre]',1,6,NULL,1,'2019-04-11 16:34:23'),(44,'a(undefined)[点击查看资料]',1,6,NULL,1,'2019-04-11 16:38:55'),(45,'a(undefined)[点击查看资料]',1,6,NULL,1,'2019-04-11 16:39:15'),(46,'<a onclick=\'toDetail($(\"#user_id\").val())\'>点击查看资料</a>>',1,6,NULL,1,'2019-04-11 16:42:46'),(47,'<a onclick=\"toDetail($(\"#user_id\").val())\">点击查看资料</a>',1,6,NULL,1,'2019-04-11 16:44:41'),(48,'<a onclick=\"toDetail(1)\">点击查看资料</a>',1,6,NULL,1,'2019-04-11 16:46:55'),(49,'<p onclick=\"toDetail(1)\">点击查看资料</p>',1,6,NULL,1,'2019-04-11 16:47:25'),(50,'[a onclick=\"undefined\"点击查看资料[/a]',1,6,NULL,1,'2019-04-11 16:52:20'),(51,'[pre onclick=\"undefined\"点击查看资料[/pre]',1,6,NULL,1,'2019-04-11 16:53:02'),(52,'a(/toDetail(1))[点击查看主页]',1,6,NULL,1,'2019-04-11 16:56:08'),(53,'[pre class=layui-elem-quote]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:03:26'),(54,'[pre class=layui-btn]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:04:43'),(55,'[pre class=layui-btn layui-btn-radius layui-btn-primary onclick=toDetail()]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:05:54'),(56,'[pre class=layui-btn;layui-btn-radius;layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:07:10'),(57,'[pre class=layui-btn$nbsp;layui-btn-radius$nbsp;layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:07:49'),(58,'[pre class=layui-btn&nbsp;layui-btn-radius&nbsp;layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:08:37'),(59,'[pre class=layui-btn\nbsp;layui-btn-radius&nbsp;layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:11:24'),(60,'[pre class=layui-btn layui-btn-radius&nbsp;layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:13:37'),(61,'[pre class=layui-btn       layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:20:30'),(62,'[pre class=layui-btn layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:21:23'),(63,'[pre class=layui-btn%20layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:23:36'),(64,'[pre class=layui-btn+%20layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:24:13'),(65,'[pre class=layui-btn%20layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:24:43'),(66,'[pre class=layui-btn\'+\'%20\'+\'layui-btn-primary onclick=toDetail(\'+id+\')]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:25:58'),(67,'[pre class=layui-btn\'&nbsp;\'layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:27:09'),(68,'[pre class=layui-btn&nbsp;layui-btn-primary onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,1,'2019-04-11 17:27:23'),(69,'[a  onclick=toDetail(1)]点击查看内容[/a]',1,6,NULL,1,'2019-04-11 17:29:51'),(70,'pl',6,1,NULL,1,'2019-04-11 17:31:17'),(71,'[pre style=margin:5px onclick=toDetail(1)]点击查看内容[/pre]',1,6,NULL,0,'2019-04-11 17:32:15'),(72,'[pre style=padding:5px onclick=toDetail(1)]点击查看资料[/pre]',1,6,NULL,0,'2019-04-11 17:33:26'),(73,'[pre class=mybtnclass onclick=toDetail(1)]点击查看资料[/pre]',1,6,NULL,0,'2019-04-11 17:35:19'),(74,'[pre class=mybtnclass onclick=toDetail(1)]点击查看资料[/pre]',1,6,NULL,0,'2019-04-11 17:36:03');

/*Table structure for table `u_dyn_msg` */

DROP TABLE IF EXISTS `u_dyn_msg`;

CREATE TABLE `u_dyn_msg` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `content` mediumtext,
  `img` varchar(1000) DEFAULT NULL,
  `ylike` int(10) DEFAULT '0',
  `unlike` int(10) DEFAULT '0',
  `star` int(10) DEFAULT '0',
  `u_id` int(10) NOT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `primary_u_d` (`u_id`),
  CONSTRAINT `primary_u_d` FOREIGN KEY (`u_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=utf8;

/*Data for the table `u_dyn_msg` */

insert  into `u_dyn_msg`(`id`,`content`,`img`,`ylike`,`unlike`,`star`,`u_id`,`createTime`) values (123,'<img src=\"/layui/images/face/23.gif\" alt=\"[吐]\">才会佛文化佛文化拜佛七二杠七二杠七二杠七二杠地方&nbsp;','\\msg\\5\\fd06019516fb49909dc2604e50f9deb5.jpg',3,3,0,5,'2019-02-22 17:13:37'),(124,'<img src=\"/layui/images/face/18.gif\" alt=\"[右哼哼]\">','\\msg\\1\\fa6b647c3f2c4877aef5fd9a055d7993.jpg',0,0,0,1,'2019-02-27 15:44:46'),(125,'<img src=\"/layui/images/face/0.gif\" alt=\"[微笑]\">','\\msg\\1\\51662d7fc9db4a11bed1400ce1a9d297.JPG',1,1,0,1,'2019-02-27 15:45:06'),(126,'<img src=\"/layui/images/face/33.gif\" alt=\"[睡]\">','\\msg\\1\\f162925474f64acfa30901c627e337fd.jpg',0,0,1,1,'2019-02-27 15:45:15'),(127,'测试','\\msg\\1\\73a9f9a0d3864cc1b36f38c5a553e62e.jpg, \\msg\\1\\e3c83cd8d0f8441587977eaf9a1ae5fa.jpg, \\msg\\1\\a10da74301274648ae2dee062f14778e.jpg, \\msg\\1\\62f27fc9d8f84aefa9f726b09c7f3873.jpg',3,0,1,1,'2019-02-27 17:40:42'),(128,'测试测试','\\msg\\1\\94596cec7b9247a7a88ccd67540bf38c.jpg',0,0,0,1,'2019-02-28 14:53:01'),(129,'测试prepend','\\msg\\1\\721fd7dfcda94f11adc4c8bdaf68021f.jpg, \\msg\\1\\c1f16dc2b54c4c85b5ecd1315ebe27b7.jpg',0,0,0,1,'2019-02-28 15:03:22'),(130,'之前的失败了,再来','\\msg\\1\\dd46b6f48013412fb59cdedb59a00df2.jpg, \\msg\\1\\bde2f9f449a9444c9ab8c25d5ed56e6c.jpg',0,0,0,1,'2019-02-28 15:06:03'),(131,'能成功不','\\msg\\1\\9842d3d522bf461bb33be2a996eb1974.jpg, \\msg\\1\\2332eb239053421fb370d2f468aba3d6.jpg',0,0,0,1,'2019-02-28 15:07:27'),(136,'dasdasdasd','\\msg\\1\\27b9bfc6040c49faba70eb7b136d39ae.jpg, \\msg\\1\\5c88182e72ae4edaa70a4b04dd4b2a2f.jpg',0,0,0,1,'2019-02-28 15:28:58'),(137,'dadasd','\\msg\\1\\17d9d62c9b0a47caaaba36d9309cd0e9.jpg',0,0,0,1,'2019-02-28 15:29:44'),(138,'11111111111111111111111111111111','\\msg\\1\\baa721979b2745a6a702206d3c051413.jpg',0,0,1,1,'2019-02-28 15:30:49'),(140,'<img src=\"/layui/images/face/22.gif\" alt=\"[委屈]\">','\\msg\\5\\8a8c03106b184aa096c98fdc6ea32662.jpg',0,0,0,5,'2019-03-05 14:27:03'),(141,'<img src=\"/layui/images/face/21.gif\" alt=\"[衰]\">','\\msg\\4\\e060643ad6dc4ddf84edb1b7ad46d10c.jpg',0,0,0,4,'2019-03-05 14:57:13'),(142,'<p><img src=\"/layui/images/face/1.gif\" alt=\"[嘻嘻]\"><img src=\"/layui/images/face/7.gif\" alt=\"[害羞]\"></p><p><b>我要写一个长一点的动态,好测试另一个<i>功能</i>有没有实现</b></p><p><b>这个长度够不够,</b>不够再加</p><p><b>这个长度够不够,</b>不够再加</p><p><b>这个长度够不够,</b>不够再加</p>','\\msg\\6\\7855489247bb4e479576afc7ab34080d.jpg, \\msg\\6\\beeb6e847eb24810adb73df38ae56989.jpg, \\msg\\6\\1687472b5491481d85571284e3ba9cf9.jpg, \\msg\\6\\60619964bb974d80a172e4fcd0023133.jpg',0,0,0,6,'2019-03-05 18:30:38'),(143,'<img src=\"/layui/images/face/1.gif\" alt=\"[嘻嘻]\">','\\msg\\1\\499a985afc1d4d2aa59fde799e0cf9c9.jpg',0,0,1,1,'2019-03-07 16:21:15'),(144,'zenmehuishi','\\msg\\1\\9eaadc6b852b4257a386661a1796d537.jpg',0,0,0,1,'2019-03-07 16:22:52'),(145,'又出bug?','\\msg\\1\\3d18dceffde1405fb8c0900a55a78532.jpg',0,0,0,1,'2019-03-07 16:25:00'),(146,'浏览器?','\\msg\\1\\50de19bed92442199164c8e6b3de4f6e.JPG',0,0,0,1,'2019-03-07 16:27:24'),(147,'大苏打','\\msg\\1\\6e854ec7d2ef45f586b716baa69b65a8.jpg',0,0,0,1,'2019-03-07 16:48:35'),(148,'阿瑟东','\\msg\\1\\4595ac4d6f7c4969a41237048c6a0605.jpg',0,0,1,1,'2019-03-07 16:50:15'),(149,'大D','\\msg\\1\\bb760560e2dd4c5eb817b4438dce013e.jpg',0,0,0,1,'2019-03-07 16:51:42'),(150,'<img src=\"/layui/images/face/33.gif\" alt=\"[睡]\">','\\msg\\1\\48cff9ecdc46440f9ccfe236e8e847b7.jpg',0,0,-1,1,'2019-03-08 09:23:17'),(151,'<img alt=\"[爱你]\" src=\"/layui/images/face/11.gif\">','\\msg\\1\\349776331fc948138b16e7dbbcf5ee7e.jpg',0,0,0,1,'2019-03-08 09:24:03'),(152,'ad','\\msg\\1\\8c42ce77251c4136b61a7ad0625dfa32.jpg',1,1,0,1,'2019-03-11 10:15:06'),(153,'我知道你很&gt;^&gt;','\\msg\\1\\5af7f7ba382d4644b05d47df43937aa2.jpg',0,0,0,1,'2019-04-02 17:07:06'),(154,'<img alt=\"[困]\" src=\"/layui/images/face/32.gif\">','\\msg\\1\\3fbd93b547574020a932b31f433f06a7.jpg',0,0,0,1,'2019-04-02 17:08:03'),(155,'<img src=\"/layui/images/face/46.gif\" alt=\"[互粉]\">','\\msg\\1\\4c6898a3afe4432eb96d4884ed08ef88.jpg',0,0,0,1,'2019-04-02 17:50:16'),(156,'<img src=\"/layui/images/face/47.gif\" alt=\"[心]\">','\\msg\\1\\82074a701b834abdae7ad27d0cb968b7.jpg',0,0,0,1,'2019-04-02 17:51:37'),(157,'<img src=\"/layui/images/face/46.gif\" alt=\"[互粉]\"><img src=\"/layui/images/face/46.gif\" alt=\"[互粉]\">','\\msg\\1\\56b5d4dd27084b60b7432e016e184c7d.jpg',0,0,0,1,'2019-04-02 17:53:20'),(158,'<img src=\"/layui/images/face/43.gif\" alt=\"[黑线]\">','\\msg\\1\\52621fbc88ab4f868d3a4dbcb9dce31f.jpg',0,0,0,1,'2019-04-02 17:55:50'),(159,'<img alt=\"[弱]\" src=\"/layui/images/face/58.gif\">','\\msg\\1\\ef3c15b535db419d8b03f340539b34c3.jpg',0,0,0,1,'2019-04-02 17:56:39'),(160,'<img alt=\"[good]\" src=\"/layui/images/face/54.gif\">','\\msg\\1\\2948493509d64d869244849a3bdfa777.jpg',0,0,0,1,'2019-04-02 17:56:59'),(161,'<img src=\"/layui/images/face/71.gif\" alt=\"[蛋糕]\">','\\msg\\1\\91aafe9d62f5423bafa453281ae3296b.JPG',0,0,0,1,'2019-04-02 17:57:46'),(162,'<img src=\"/layui/images/face/26.gif\" alt=\"[怒]\">','\\msg\\1\\a07b5e466b494a41b9adf25ddc8f0196.jpg',0,0,0,1,'2019-04-02 18:01:45'),(163,'<img src=\"/layui/images/face/8.gif\" alt=\"[挤眼]\">','\\msg\\1\\ee09f57169664e35b59198432bf5af22.jpg',0,0,0,1,'2019-04-02 18:03:55');

/*Table structure for table `u_group` */

DROP TABLE IF EXISTS `u_group`;

CREATE TABLE `u_group` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(50) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT '',
  `u_id` int(10) DEFAULT NULL COMMENT '属于哪个用户',
  `type` int(1) DEFAULT NULL COMMENT '0:分组  1:群',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Data for the table `u_group` */

insert  into `u_group`(`id`,`groupname`,`avatar`,`u_id`,`type`) values (1,'我的好友','',1,0),(21,'我的好友','',9,0),(22,'测试分组','',1,0),(29,'我的好友','',5,0),(30,'我的好友','',6,0),(31,'测试群','\\group\\default.jpg',1,1),(32,'我的好友','',4,0),(33,'我的好友','',10,0),(34,'我的好友','',11,0),(35,'我的好友','',12,0),(36,'我的好友','',13,0),(37,'我的好友','',2,0);

/*Table structure for table `u_m_star` */

DROP TABLE IF EXISTS `u_m_star`;

CREATE TABLE `u_m_star` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `u_id` int(10) NOT NULL,
  `m_id` int(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `star_u` (`u_id`),
  KEY `foreign_star_m` (`m_id`),
  CONSTRAINT `foreign_star_m` FOREIGN KEY (`m_id`) REFERENCES `u_dyn_msg` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=54 DEFAULT CHARSET=utf8;

/*Data for the table `u_m_star` */

insert  into `u_m_star`(`id`,`u_id`,`m_id`) values (49,1,127),(50,1,138),(51,1,148),(53,1,143);

/*Table structure for table `u_msgbox` */

DROP TABLE IF EXISTS `u_msgbox`;

CREATE TABLE `u_msgbox` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `srcUid` int(10) DEFAULT NULL COMMENT '来源用户ID',
  `descUid` int(10) DEFAULT NULL COMMENT '目标用户ID',
  `type` int(1) DEFAULT NULL COMMENT '类型: 0:系统 1:加好友 2:邀请加群',
  `content` varchar(100) DEFAULT NULL COMMENT '信息',
  `agree` int(1) DEFAULT '0' COMMENT '是否同意0:未读 1:同意 2:拒绝',
  `srcGid` int(10) DEFAULT NULL COMMENT '来源组ID',
  `descGid` int(10) DEFAULT NULL COMMENT '目标组ID',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `respTime` timestamp NULL DEFAULT NULL COMMENT '应答时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `u_msgbox` */

insert  into `u_msgbox`(`id`,`srcUid`,`descUid`,`type`,`content`,`agree`,`srcGid`,`descGid`,`createTime`,`respTime`) values (4,4,5,1,'test',1,32,29,'2019-03-04 18:00:00','2019-03-05 16:23:03'),(5,1,4,1,'我是武波',2,20,NULL,'2019-03-05 14:57:46','2019-03-05 16:23:03'),(6,1,4,1,'加我吧',2,20,NULL,'2019-03-05 15:06:06','2019-03-05 16:23:03'),(7,6,5,1,'test',1,30,29,'2019-03-05 16:19:13','2019-03-05 16:23:03'),(8,NULL,NULL,1,NULL,0,NULL,NULL,'2019-03-06 17:10:49',NULL),(9,2,1,1,'我是wb',0,37,NULL,'2019-03-29 16:46:45',NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `account` varchar(20) NOT NULL,
  `password` varchar(50) NOT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` int(1) DEFAULT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT '\\head\\default.jpg',
  `sign` varchar(50) DEFAULT 'TA很懒,什么也没留下',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`account`,`password`,`age`,`sex`,`email`,`phone`,`address`,`avatar`,`sign`) values (1,'小m[官方]','admin','e10adc3949ba59abbe56e057f20f883e',1,0,'277379322@qq.com',NULL,NULL,'\\head\\1.png','有什么问题都可以问小m哦'),(2,'武波','wubo','e10adc3949ba59abbe56e057f20f883e',1,0,'277379322@qq.com',NULL,NULL,'\\head\\2.png','TA很懒,什么也没留下'),(4,'刘驰','liuchi','e10adc3949ba59abbe56e057f20f883e',1,0,'277379322@qq.com',NULL,NULL,'\\head\\4.png','TA很懒,什么也没留下233'),(5,'刘帅驰','liushuaichi','a2550eeab0724a691192ca13982e6ebd',22,NULL,'234123412@qq.com','7572724242452','湖南长沙','\\head\\5.png','我,帅!'),(6,'test','test123','cc03e747a6afbbcbf8be7668acfebee5',NULL,NULL,'123@123.com',NULL,NULL,'\\head\\default.jpg','TA很懒,什么也没留下'),(8,'test2','test1234','dc483e80a7a0bd9ef71d8cf973673924',NULL,NULL,'123@123.com',NULL,NULL,'\\head\\default.jpg','TA很懒,什么也没留下'),(9,'test3','test333','3aaa4ff6fa71d98282e0b2e0c49d4066',NULL,NULL,'123@123.com',NULL,NULL,'\\head\\default.jpg','TA很懒,什么也没留下'),(10,'cdnadmin','cdnadmin','9b2890e66d1d23ad7c68b2e2e20abe0e',NULL,NULL,'123@123.com',NULL,NULL,'\\head\\default.jpg','TA很懒,什么也没留下'),(11,'cdnadmin2','cdnadmin2','0376544e9a4354a981a885e9573cd69e',NULL,NULL,'123@123.com',NULL,NULL,'\\head\\default.jpg','TA很懒,什么也没留下'),(13,'杜晓禹','duxiaoyu','11d91533f9d716e6646c64e890be1a5d',NULL,NULL,'1195820348@qq.com',NULL,NULL,'\\head\\13.png','TA很懒,什么也没留下');

/* Trigger structure for table `u_group` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `group_avatar` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `group_avatar` BEFORE INSERT ON `u_group` FOR EACH ROW begin
  if new.type = 1
  then
  SET new.avatar = '\group\default.jpg';
  end if;
end */$$


DELIMITER ;

/* Trigger structure for table `user` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `group_user` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `group_user` AFTER INSERT ON `user` FOR EACH ROW BEGIN
  INSERT INTO `g_members` VALUES(new.id,1);
  INSERT INTO `u_group` (groupname,u_id,TYPE) VALUES('我的好友',new.id,0);
  insert INTO `g_members` values(1,(SELECT id from u_group where u_id = new.id));
END */$$


DELIMITER ;

/* Function  structure for function  `getChildList` */

/*!50003 DROP FUNCTION IF EXISTS `getChildList` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `getChildList`(rootId INT) RETURNS varchar(1000) CHARSET utf8
BEGIN
	DECLARE sChildList VARCHAR(1000);
      DECLARE sChildTemp VARCHAR(1000);
      SET sChildTemp =CAST(rootId AS CHAR);
      WHILE sChildTemp IS NOT NULL DO
        IF (sChildList IS NOT NULL) THEN
          SET sChildList = CONCAT(sChildList,',',sChildTemp);
    ELSE
      SET sChildList = CONCAT(sChildTemp);
    END IF;
        SELECT GROUP_CONCAT(id) INTO sChildTemp FROM dyn_msg_comment WHERE FIND_IN_SET(pid,sChildTemp)>0;
      END WHILE;
      RETURN sChildList;
    END */$$
DELIMITER ;

/* Function  structure for function  `getLengthSeparator` */

/*!50003 DROP FUNCTION IF EXISTS `getLengthSeparator` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `getLengthSeparator`(str VARCHAR(8000), split VARCHAR(10)) RETURNS int(10)
BEGIN
      DECLARE location int(10);
      DECLARE _start INT(10);
      DECLARE _length INT(10);
      IF(str IS NOT NULL) THEN
	SET location=LOCATE(split,str);
	SET _length=1 ;
	WHILE location<>0 DO
		SET _start=location+1; 
		SET location=LOCATE(split,str,_start);
		SET _length=_length+1;
	END WHILE;
      ELSE
	SET _length=0;
      END IF;
      RETURN _length; 
END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
