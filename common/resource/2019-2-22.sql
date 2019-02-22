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

insert  into `g_members`(`user_id`,`group_id`) values (4,1),(1,1),(1,4);

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
) ENGINE=InnoDB AUTO_INCREMENT=124 DEFAULT CHARSET=utf8;

/*Data for the table `u_dyn_msg` */

insert  into `u_dyn_msg`(`id`,`content`,`img`,`ylike`,`unlike`,`star`,`u_id`,`createTime`) values (123,'<img src=\"/layui/images/face/23.gif\" alt=\"[吐]\">才会佛文化佛文化拜佛七二杠七二杠七二杠七二杠地方&nbsp;','\\msg\\5\\fd06019516fb49909dc2604e50f9deb5.jpg',3,3,1,5,'2019-02-22 17:13:37');

/*Table structure for table `u_group` */

DROP TABLE IF EXISTS `u_group`;

CREATE TABLE `u_group` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `groupname` varchar(50) DEFAULT NULL,
  `avatar` varchar(50) DEFAULT '\\group\\avatar.jpg',
  `u_id` int(10) DEFAULT NULL COMMENT '属于哪个用户',
  `type` int(1) DEFAULT NULL COMMENT '0:分组  1:群',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `u_group` */

insert  into `u_group`(`id`,`groupname`,`avatar`,`u_id`,`type`) values (1,'测试组','',1,0),(4,'测试群','\\group\\avatar.jpg',1,1),(5,'测试组2','\\group\\avatar.jpg',1,0);

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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;

/*Data for the table `u_m_star` */

insert  into `u_m_star`(`id`,`u_id`,`m_id`) values (36,5,123);

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
  `sign` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`account`,`password`,`age`,`sex`,`email`,`phone`,`address`,`avatar`,`sign`) values (1,'武波','wubo','e10adc3949ba59abbe56e057f20f883e',1,0,'277379322@qq.com',NULL,NULL,'\\head\\1.png','TA很懒,什么也没留下'),(4,'刘驰','liuchi','e10adc3949ba59abbe56e057f20f883e',1,0,'277379322@qq.com',NULL,NULL,'\\head\\4.png','TA很懒,什么也没留下233'),(5,'刘帅驰','liushuaichi','a2550eeab0724a691192ca13982e6ebd',22,NULL,'234123412@qq.com','7572724242452','高发多发嘎嘎发 爱的嘎嘎','\\head\\5.png',NULL),(6,'test','test123','cc03e747a6afbbcbf8be7668acfebee5',NULL,NULL,'123@123.com',NULL,NULL,'\\head\\default.jpg',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
