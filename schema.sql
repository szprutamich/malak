drop database if exists malak;
create database malak default charset utf8;
grant all privileges on malak.* to 'malak'@'localhost' identified by 'malak';
grant all privileges on malak.* to 'malak'@'%' identified by 'malak';
flush privileges;

DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
