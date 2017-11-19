USE `loldb`;
DROP TABLE IF EXISTS `champion`;
CREATE TABLE `champion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `spell`;
CREATE TABLE `spell` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `build`;
CREATE TABLE `build` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  `champion_id` int(11) NOT NULL,
  `datetime` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `champion_id_idx` (`champion_id`),
  CONSTRAINT `champion_id` FOREIGN KEY (`champion_id`) REFERENCES `champion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `build_has_item`;
CREATE TABLE `build_has_item` (
  `build_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_build_has_item_item1_idx` (`item_id`),
  KEY `fk_build_has_item_build1_idx` (`build_id`),
  CONSTRAINT `fk_build_has_item_build1` FOREIGN KEY (`build_id`) REFERENCES `build` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_build_has_item_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `build_has_spell`;
CREATE TABLE `build_has_spell` (
  `build_id` int(11) NOT NULL,
  `spell_id` int(11) NOT NULL,
  PRIMARY KEY (`build_id`,`spell_id`),
  KEY `fk_build_has_spell_spell1_idx` (`spell_id`),
  KEY `fk_build_has_spell_build1_idx` (`build_id`),
  CONSTRAINT `fk_build_has_spell_build1` FOREIGN KEY (`build_id`) REFERENCES `build` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_build_has_spell_spell1` FOREIGN KEY (`spell_id`) REFERENCES `spell` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;