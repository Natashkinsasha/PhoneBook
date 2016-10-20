CREATE SCHEMA `phonebooknatashkin` ;

CREATE TABLE `phonebooknatashkin`.`contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `secondname` varchar(45) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

CREATE TABLE `phonebooknatashkin`.`telephone` (
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `phonebooknatashkin`.`attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `path` varchar(244) DEFAULT NULL,
  `creation_date` date DEFAULT NULL,
  `comment` varchar(100) DEFAULT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_idx` (`contact_id`),
  CONSTRAINT `` FOREIGN KEY (`contact_id`) REFERENCES `contact` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;



INSERT INTO `emailtemplate` VALUES (1,'templates/usual.vm','Привет','Hi'),(2,'templates/happy_birthday.vm','С ДР','HB');
