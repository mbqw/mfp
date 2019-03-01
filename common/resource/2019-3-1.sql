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

/*Table structure for table `g_members` */

DROP TABLE IF EXISTS `g_members`;

CREATE TABLE `g_members` (
  `user_id` int(10) DEFAULT NULL COMMENT '用户ID,即''我''的ID',
  `group_id` int(10) DEFAULT NULL COMMENT '分组ID',
  KEY `group_members_key` (`group_id`),
  CONSTRAINT `group_members_key` FOREIGN KEY (`group_id`) REFERENCES `u_group` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `g_members` */

insert  into `g_members`(`user_id`,`group_id`) values (6,20),(1,30),(1,31),(1,29),(5,20),(6,31);

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
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8;

/*Data for the table `u_dyn_msg` */

insert  into `u_dyn_msg`(`id`,`content`,`img`,`ylike`,`unlike`,`star`,`u_id`,`createTime`) values (123,'<img src=\"/layui/images/face/23.gif\" alt=\"[吐]\">才会佛文化佛文化拜佛七二杠七二杠七二杠七二杠地方&nbsp;','\\msg\\5\\fd06019516fb49909dc2604e50f9deb5.jpg',3,3,0,5,'2019-02-22 17:13:37'),(124,'<img src=\"/layui/images/face/18.gif\" alt=\"[右哼哼]\">','\\msg\\1\\fa6b647c3f2c4877aef5fd9a055d7993.jpg',0,0,0,1,'2019-02-27 15:44:46'),(125,'<img src=\"/layui/images/face/0.gif\" alt=\"[微笑]\">','\\msg\\1\\51662d7fc9db4a11bed1400ce1a9d297.JPG',1,1,0,1,'2019-02-27 15:45:06'),(126,'<img src=\"/layui/images/face/33.gif\" alt=\"[睡]\">','\\msg\\1\\f162925474f64acfa30901c627e337fd.jpg',0,0,1,1,'2019-02-27 15:45:15'),(127,'测试','\\msg\\1\\73a9f9a0d3864cc1b36f38c5a553e62e.jpg, \\msg\\1\\e3c83cd8d0f8441587977eaf9a1ae5fa.jpg, \\msg\\1\\a10da74301274648ae2dee062f14778e.jpg, \\msg\\1\\62f27fc9d8f84aefa9f726b09c7f3873.jpg',3,0,1,1,'2019-02-27 17:40:42'),(128,'测试测试','\\msg\\1\\94596cec7b9247a7a88ccd67540bf38c.jpg',0,0,0,1,'2019-02-28 14:53:01'),(129,'测试prepend','\\msg\\1\\721fd7dfcda94f11adc4c8bdaf68021f.jpg, \\msg\\1\\c1f16dc2b54c4c85b5ecd1315ebe27b7.jpg',0,0,0,1,'2019-02-28 15:03:22'),(130,'之前的失败了,再来','\\msg\\1\\dd46b6f48013412fb59cdedb59a00df2.jpg, \\msg\\1\\bde2f9f449a9444c9ab8c25d5ed56e6c.jpg',0,0,0,1,'2019-02-28 15:06:03'),(131,'能成功不','\\msg\\1\\9842d3d522bf461bb33be2a996eb1974.jpg, \\msg\\1\\2332eb239053421fb370d2f468aba3d6.jpg',0,0,0,1,'2019-02-28 15:07:27'),(136,'dasdasdasd','\\msg\\1\\27b9bfc6040c49faba70eb7b136d39ae.jpg, \\msg\\1\\5c88182e72ae4edaa70a4b04dd4b2a2f.jpg',0,0,0,1,'2019-02-28 15:28:58'),(137,'dadasd','\\msg\\1\\17d9d62c9b0a47caaaba36d9309cd0e9.jpg',0,0,0,1,'2019-02-28 15:29:44'),(138,'11111111111111111111111111111111','\\msg\\1\\baa721979b2745a6a702206d3c051413.jpg',0,0,1,1,'2019-02-28 15:30:49');

/*Table structure for table `u_group` */

DROP TABLE IF EXISTS `u_group`;

CREATE TABLE `u_group` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(50) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT '',
  `u_id` int(10) DEFAULT NULL COMMENT '属于哪个用户',
  `type` int(1) DEFAULT NULL COMMENT '0:分组  1:群',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

/*Data for the table `u_group` */

insert  into `u_group`(`id`,`groupname`,`avatar`,`u_id`,`type`) values (20,'我的好友','',1,0),(21,'我的好友','',9,0),(22,'测试分组','',1,0),(29,'我的好友','',5,0),(30,'我的好友','',6,0),(31,'测试群','\\group\\default.jpg',1,1);

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
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

/*Data for the table `u_m_star` */

insert  into `u_m_star`(`id`,`u_id`,`m_id`) values (49,1,127),(50,1,138);

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`account`,`password`,`age`,`sex`,`email`,`phone`,`address`,`avatar`,`sign`) values (1,'武波','wubo','e10adc3949ba59abbe56e057f20f883e',1,0,'277379322@qq.com',NULL,NULL,'\\head\\1.png','TA很懒,什么也没留下'),(4,'刘驰','liuchi','e10adc3949ba59abbe56e057f20f883e',1,0,'277379322@qq.com',NULL,NULL,'\\head\\4.png','TA很懒,什么也没留下233'),(5,'刘帅驰','liushuaichi','a2550eeab0724a691192ca13982e6ebd',22,NULL,'234123412@qq.com','7572724242452','高发多发嘎嘎发 爱的嘎嘎','\\head\\5.png',NULL),(6,'test','test123','cc03e747a6afbbcbf8be7668acfebee5',NULL,NULL,'123@123.com',NULL,NULL,'\\head\\default.jpg','TA很懒,什么也没留下'),(8,'test2','test1234','dc483e80a7a0bd9ef71d8cf973673924',NULL,NULL,'123@123.com',NULL,NULL,'\\head\\default.jpg','TA很懒,什么也没留下'),(9,'test3','test333','3aaa4ff6fa71d98282e0b2e0c49d4066',NULL,NULL,'123@123.com',NULL,NULL,'\\head\\default.jpg','TA很懒,什么也没留下');

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
  INSERT `u_group` (groupname,u_id,TYPE) VALUES('我的好友',new.id,0);
END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
