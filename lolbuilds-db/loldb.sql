-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: loldb
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.26-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `build`
--

DROP TABLE IF EXISTS `build`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `build` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `type` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  `champion_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`user_id`),
  KEY `champion_id_idx` (`champion_id`),
  CONSTRAINT `champion_id` FOREIGN KEY (`champion_id`) REFERENCES `champion` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `build`
--

LOCK TABLES `build` WRITE;
/*!40000 ALTER TABLE `build` DISABLE KEYS */;
INSERT INTO `build` VALUES (12,'Ferao Crazy','MEIO',20,9),(13,'KAZIKA VOA BARATA1','ATIRADOR',20,10),(17,'KAZIKA VOA BARATA1 (1)','ATIRADOR',20,10),(18,'KAZIKA VOA BARATA1 (2)','ATIRADOR',20,10),(19,'KAZIKA VOA BARATA1 (3)','ATIRADOR',20,10),(20,'KAZIKA VOA BARATA1 (4)','ATIRADOR',20,10),(21,'KAZIKA VOA BARATA1 (5)','ATIRADOR',20,10),(22,'KAZIKA VOA BARATA1 (6)','ATIRADOR',20,10),(23,'KAZIKA VOA BARATA1 (7)','ATIRADOR',20,10),(24,'KAZIKA VOA BARATA1 (8)','ATIRADOR',20,10),(25,'KAZIKA VOA BARATA1 (9)','ATIRADOR',20,10),(26,'KAZIKA VOA BARATA1 (10)','ATIRADOR',20,10),(27,'KAZIKA VOA BARATA1 (11)','ATIRADOR',20,10),(28,'KAZIKA VOA BARATA1 (12)','ATIRADOR',20,10),(29,'KAZIKA VOA BARATA1 (13)','ATIRADOR',20,10),(30,'KAZIKA VOA BARATA1 (14)','ATIRADOR',20,10);
/*!40000 ALTER TABLE `build` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `build_has_item`
--

DROP TABLE IF EXISTS `build_has_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `build_has_item` (
  `build_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_build_has_item_item1_idx` (`item_id`),
  KEY `fk_build_has_item_build1_idx` (`build_id`),
  CONSTRAINT `fk_build_has_item_build1` FOREIGN KEY (`build_id`) REFERENCES `build` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_build_has_item_item1` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `build_has_item`
--

LOCK TABLES `build_has_item` WRITE;
/*!40000 ALTER TABLE `build_has_item` DISABLE KEYS */;
INSERT INTO `build_has_item` VALUES (12,19,9),(12,19,10),(12,20,11),(12,20,12),(12,20,13),(12,20,14),(13,19,15),(13,21,16),(13,22,17),(13,23,18),(13,24,19),(13,25,20),(13,19,21),(13,21,22),(13,22,23),(13,23,24),(13,24,25),(13,25,26),(13,19,27),(13,21,28),(13,22,29),(13,23,30),(13,24,31),(13,25,32),(13,19,33),(13,21,34),(13,22,35),(13,23,36),(13,24,37),(13,25,38),(17,19,39),(17,21,40),(17,22,41),(17,23,42),(17,24,43),(17,25,44),(13,19,45),(13,21,46),(13,22,47),(13,23,48),(13,24,49),(13,25,50),(19,19,69),(19,21,70),(19,22,71),(19,23,72),(19,24,73),(19,25,74),(20,19,75),(20,21,76),(20,22,77),(20,23,78),(20,24,79),(20,25,80),(21,19,87),(21,21,88),(21,22,89),(21,23,90),(21,24,91),(21,25,92),(22,19,93),(22,21,94),(22,22,95),(22,23,96),(22,24,97),(22,25,98),(23,19,99),(23,21,100),(23,22,101),(23,23,102),(23,24,103),(23,25,104),(24,19,105),(24,21,106),(24,22,107),(24,23,108),(24,24,109),(24,25,110),(25,19,111),(25,21,112),(25,22,113),(25,23,114),(25,24,115),(25,25,116),(26,19,117),(26,21,118),(26,22,119),(26,23,120),(26,24,121),(26,25,122),(27,19,123),(27,21,124),(27,22,125),(27,23,126),(27,24,127),(27,25,128),(28,19,129),(28,21,130),(28,22,131),(28,23,132),(28,24,133),(28,25,134),(29,19,135),(29,21,136),(29,22,137),(29,23,138),(29,24,139),(29,25,140),(30,19,141),(30,21,142),(30,22,143),(30,23,144),(30,24,145),(30,25,146);
/*!40000 ALTER TABLE `build_has_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `build_has_spell`
--

DROP TABLE IF EXISTS `build_has_spell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `build_has_spell` (
  `build_id` int(11) NOT NULL,
  `spell_id` int(11) NOT NULL,
  PRIMARY KEY (`build_id`,`spell_id`),
  KEY `fk_build_has_spell_spell1_idx` (`spell_id`),
  KEY `fk_build_has_spell_build1_idx` (`build_id`),
  CONSTRAINT `fk_build_has_spell_build1` FOREIGN KEY (`build_id`) REFERENCES `build` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_build_has_spell_spell1` FOREIGN KEY (`spell_id`) REFERENCES `spell` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `build_has_spell`
--

LOCK TABLES `build_has_spell` WRITE;
/*!40000 ALTER TABLE `build_has_spell` DISABLE KEYS */;
INSERT INTO `build_has_spell` VALUES (12,7),(12,8),(13,9),(13,10),(17,9),(17,10),(19,9),(19,10),(20,9),(20,10),(21,9),(21,10),(22,9),(22,10),(23,9),(23,10),(24,9),(24,10),(25,9),(25,10),(26,9),(26,10),(27,9),(27,10),(28,9),(28,10),(29,9),(29,10),(30,9),(30,10);
/*!40000 ALTER TABLE `build_has_spell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `champion`
--

DROP TABLE IF EXISTS `champion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `champion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `champion`
--

LOCK TABLES `champion` WRITE;
/*!40000 ALTER TABLE `champion` DISABLE KEYS */;
INSERT INTO `champion` VALUES (9,'Rengar','Rengar'),(10,'Khazikero','Khazikero');
/*!40000 ALTER TABLE `champion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (19,'BOTA','BOTA'),(20,'Any','Any'),(21,'ITEM DA JUNGLE TOP','ITEM DA JUNGLE TOP'),(22,'ITEM DE CRITICU','ITEM DE CRITICU'),(23,'ITEM DE TANKI','ITEM DE TANKI'),(24,'QLQ COISA Q N REPETE','QLQ COISA Q N REPETE'),(25,'STAFF DE AP','STAFF DE AP');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `spell`
--

DROP TABLE IF EXISTS `spell`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `spell` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `spell`
--

LOCK TABLES `spell` WRITE;
/*!40000 ALTER TABLE `spell` DISABLE KEYS */;
INSERT INTO `spell` VALUES (7,'Flash','Flash'),(8,'Teleport','Teleport'),(9,'Revive','Revive'),(10,'Ignite','Ignite');
/*!40000 ALTER TABLE `spell` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (20,'2fd6cc1498c93dfc72ec88de634793f1','lucas');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-08 22:37:46
