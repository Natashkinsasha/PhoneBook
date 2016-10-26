BEGIN;
CREATE DATABASE  IF NOT EXISTS `phonebooknatashkin`;
USE `phonebooknatashkin`;
SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `contact`;
CREATE TABLE `contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) NOT NULL,
  `secondname` varchar(45) NOT NULL,
  `patronymic` varchar(45) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `male` tinyint(1) DEFAULT NULL,
  `nationality` varchar(45) DEFAULT NULL,
  `relationshipstatus` varchar(45) DEFAULT NULL,
  `website` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `country` varchar(45) DEFAULT NULL,
  `city` varchar(45) DEFAULT NULL,
  `street` varchar(45) DEFAULT NULL,
  `ind` varchar(45) DEFAULT NULL,
  `company` varchar(45) DEFAULT NULL,
  `photo_path` varchar(244) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
DROP TABLE IF EXISTS `attachment`;
CREATE TABLE `attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(244) NOT NULL,
  `creation_date` date NOT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `contact_id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`contact_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
DROP TABLE IF EXISTS `telephone`;
CREATE TABLE `telephone` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `country_code` varchar(45) DEFAULT NULL,
  `operator_code` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`contact_id`),
  CONSTRAINT `id` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
COMMIT;
