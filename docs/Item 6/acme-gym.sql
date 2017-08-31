start transaction;

drop database if exists `Acme-Gym`;
create database `Acme-Gym`;

use `Acme-Gym`;

grant select, insert, update, delete on `Acme-Gym`.* to 'acme-user'@'%';
grant select, insert, update, delete, create, drop, references, index, alter,
	create temporary tables, lock tables, create view, create routine,
	alter routine, execute, trigger, show view on `Acme-Gym`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Gym
-- ------------------------------------------------------
-- Server version	5.5.29

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
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `dayWeek` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `endTime` varchar(255) DEFAULT NULL,
  `isCancelled` bit(1) DEFAULT NULL,
  `numSeats` int(11) DEFAULT NULL,
  `startTime` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `gym_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_p7iscm1n2rfw495tpwnkookj3` (`gym_id`),
  CONSTRAINT `FK_p7iscm1n2rfw495tpwnkookj3` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES (63,0,1,'A very funny class','10:00','\0',22,'09:00','Spinning',57),(64,0,2,'A very funny class','19:00','\0',33,'18:00','Tabata',58);
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_customer`
--

DROP TABLE IF EXISTS `activity_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_customer` (
  `Activity_id` int(11) NOT NULL,
  `customers_id` int(11) NOT NULL,
  KEY `FK_i2tb3uoolnxniqpreoojtq99p` (`customers_id`),
  KEY `FK_3g7daexb70kgecsnr7yvgqkek` (`Activity_id`),
  CONSTRAINT `FK_3g7daexb70kgecsnr7yvgqkek` FOREIGN KEY (`Activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_i2tb3uoolnxniqpreoojtq99p` FOREIGN KEY (`customers_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_customer`
--

LOCK TABLES `activity_customer` WRITE;
/*!40000 ALTER TABLE `activity_customer` DISABLE KEYS */;
INSERT INTO `activity_customer` VALUES (63,59),(64,60);
/*!40000 ALTER TABLE `activity_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_pictures`
--

DROP TABLE IF EXISTS `activity_pictures`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_pictures` (
  `Activity_id` int(11) NOT NULL,
  `pictures` varchar(255) DEFAULT NULL,
  KEY `FK_465ltkojh9ooei6h62da9lw3m` (`Activity_id`),
  CONSTRAINT `FK_465ltkojh9ooei6h62da9lw3m` FOREIGN KEY (`Activity_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_pictures`
--

LOCK TABLES `activity_pictures` WRITE;
/*!40000 ALTER TABLE `activity_pictures` DISABLE KEYS */;
INSERT INTO `activity_pictures` VALUES (63,''),(64,'');
/*!40000 ALTER TABLE `activity_pictures` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `activity_trainer`
--

DROP TABLE IF EXISTS `activity_trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `activity_trainer` (
  `Activity_id` int(11) NOT NULL,
  `trainers_id` int(11) NOT NULL,
  KEY `FK_r9qaqslh5b4mepc60066idiv2` (`trainers_id`),
  KEY `FK_23bmrghtx8fr7ryho7h0nvy3u` (`Activity_id`),
  CONSTRAINT `FK_23bmrghtx8fr7ryho7h0nvy3u` FOREIGN KEY (`Activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_r9qaqslh5b4mepc60066idiv2` FOREIGN KEY (`trainers_id`) REFERENCES `trainer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity_trainer`
--

LOCK TABLES `activity_trainer` WRITE;
/*!40000 ALTER TABLE `activity_trainer` DISABLE KEYS */;
INSERT INTO `activity_trainer` VALUES (63,61),(64,62);
/*!40000 ALTER TABLE `activity_trainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actor`
--

DROP TABLE IF EXISTS `actor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actor` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_cgls5lrufx91ufsyh467spwa3` (`userAccount_id`),
  CONSTRAINT `FK_cgls5lrufx91ufsyh467spwa3` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actor`
--

LOCK TABLES `actor` WRITE;
/*!40000 ALTER TABLE `actor` DISABLE KEYS */;
/*!40000 ALTER TABLE `actor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (54,0,'Pablo','Sevilla','España','pablo@hotmail.com','+5 (10) 9132','41001','Pablito',47);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `advice`
--

DROP TABLE IF EXISTS `advice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `advice` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `actor_id` int(11) NOT NULL,
  `step_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_attmgtedkppw0cxae9qqu9s8m` (`step_id`),
  CONSTRAINT `FK_attmgtedkppw0cxae9qqu9s8m` FOREIGN KEY (`step_id`) REFERENCES `step` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `advice`
--

LOCK TABLES `advice` WRITE;
/*!40000 ALTER TABLE `advice` DISABLE KEYS */;
INSERT INTO `advice` VALUES (89,0,'Laura','Good step',59,76),(90,0,'Laura','Bad step',59,77),(91,0,'Laura','Bad step',59,80),(92,0,'Laura','Good step',59,81);
/*!40000 ALTER TABLE `advice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `annotation`
--

DROP TABLE IF EXISTS `annotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `annotation` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `momentWritten` datetime DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `activity_id` int(11) DEFAULT NULL,
  `actorStores_id` int(11) DEFAULT NULL,
  `actorWrites_id` int(11) NOT NULL,
  `gym_id` int(11) DEFAULT NULL,
  `workout_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_472i1t9ukw8tl9vj222ard5lc` (`activity_id`),
  KEY `FK_4xsyicyueumjt4rinr7tcx9r7` (`gym_id`),
  KEY `FK_8rhwm3m8t025odfj9o6xr44ej` (`workout_id`),
  CONSTRAINT `FK_8rhwm3m8t025odfj9o6xr44ej` FOREIGN KEY (`workout_id`) REFERENCES `workout` (`id`),
  CONSTRAINT `FK_472i1t9ukw8tl9vj222ard5lc` FOREIGN KEY (`activity_id`) REFERENCES `activity` (`id`),
  CONSTRAINT `FK_4xsyicyueumjt4rinr7tcx9r7` FOREIGN KEY (`gym_id`) REFERENCES `gym` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `annotation`
--

LOCK TABLES `annotation` WRITE;
/*!40000 ALTER TABLE `annotation` DISABLE KEYS */;
INSERT INTO `annotation` VALUES (65,0,'2018-08-07 20:20:00',3,'A very funny class cool =D',NULL,60,59,NULL,NULL),(66,0,'2019-06-07 20:20:00',3,'good gym',NULL,NULL,60,58,NULL),(67,0,'2018-08-07 20:20:00',3,'good trainer and ver funny',NULL,61,55,NULL,NULL),(68,0,'2019-06-07 20:20:00',2,'not bad activity',64,NULL,60,NULL,NULL),(69,0,'2018-08-07 20:20:00',0,'i am so good trainer that you',NULL,61,62,NULL,NULL),(70,0,'2019-06-07 20:20:00',1,'bad gym',NULL,NULL,56,57,NULL),(71,0,'2018-08-07 20:20:00',3,'very funny activity',63,NULL,61,NULL,NULL),(72,0,'2019-06-07 20:20:00',0,'be careful with your annotations or ban',NULL,62,54,NULL,NULL),(73,0,'2018-08-07 20:20:00',2,'good maganer',NULL,55,59,NULL,NULL);
/*!40000 ALTER TABLE `annotation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula`
--

DROP TABLE IF EXISTS `curricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `education` varchar(255) DEFAULT NULL,
  `nameTrainer` varchar(255) DEFAULT NULL,
  `workExperience` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula`
--

LOCK TABLES `curricula` WRITE;
/*!40000 ALTER TABLE `curricula` DISABLE KEYS */;
INSERT INTO `curricula` VALUES (87,0,'E.S.O','Brook lopez','nothing'),(88,0,'E.S.O','Misty Lopez','Other gym');
/*!40000 ALTER TABLE `curricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_socialidentity`
--

DROP TABLE IF EXISTS `curricula_socialidentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_socialidentity` (
  `Curricula_id` int(11) NOT NULL,
  `socialIdentitys_id` int(11) NOT NULL,
  UNIQUE KEY `UK_4lrx0r4iism8e11nl1f7jhaon` (`socialIdentitys_id`),
  KEY `FK_7ek3lhe91l4tlq5ks8c7b2112` (`Curricula_id`),
  CONSTRAINT `FK_7ek3lhe91l4tlq5ks8c7b2112` FOREIGN KEY (`Curricula_id`) REFERENCES `curricula` (`id`),
  CONSTRAINT `FK_4lrx0r4iism8e11nl1f7jhaon` FOREIGN KEY (`socialIdentitys_id`) REFERENCES `socialidentity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_socialidentity`
--

LOCK TABLES `curricula_socialidentity` WRITE;
/*!40000 ALTER TABLE `curricula_socialidentity` DISABLE KEYS */;
INSERT INTO `curricula_socialidentity` VALUES (87,85),(88,86);
/*!40000 ALTER TABLE `curricula_socialidentity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curricula_specialities`
--

DROP TABLE IF EXISTS `curricula_specialities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curricula_specialities` (
  `Curricula_id` int(11) NOT NULL,
  `specialities` varchar(255) DEFAULT NULL,
  KEY `FK_ento0ded1j0bctinb6c3vh3gt` (`Curricula_id`),
  CONSTRAINT `FK_ento0ded1j0bctinb6c3vh3gt` FOREIGN KEY (`Curricula_id`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curricula_specialities`
--

LOCK TABLES `curricula_specialities` WRITE;
/*!40000 ALTER TABLE `curricula_specialities` DISABLE KEYS */;
INSERT INTO `curricula_specialities` VALUES (87,'hard boy'),(87,'great trainer'),(88,'hard girl'),(88,'great trainer');
/*!40000 ALTER TABLE `curricula_specialities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pwmktpkay2yx7v00mrwmuscl8` (`userAccount_id`),
  CONSTRAINT `FK_pwmktpkay2yx7v00mrwmuscl8` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (59,0,'Laura','Sevilla','España','laura@hotmail.com','+5 (10) 6643','41001','Padial',48),(60,0,'Misty','Sevilla','España','Misty@hotmail.com','+5 (10) 9152','41001','Lopez',49);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_activity`
--

DROP TABLE IF EXISTS `customer_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_activity` (
  `Customer_id` int(11) NOT NULL,
  `activities_id` int(11) NOT NULL,
  KEY `FK_7u1sf71oni51dlk4b937q5osf` (`activities_id`),
  KEY `FK_h2di58qd0lgfs183fe50g5sq1` (`Customer_id`),
  CONSTRAINT `FK_h2di58qd0lgfs183fe50g5sq1` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_7u1sf71oni51dlk4b937q5osf` FOREIGN KEY (`activities_id`) REFERENCES `activity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_activity`
--

LOCK TABLES `customer_activity` WRITE;
/*!40000 ALTER TABLE `customer_activity` DISABLE KEYS */;
INSERT INTO `customer_activity` VALUES (59,63),(60,64);
/*!40000 ALTER TABLE `customer_activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer_gym`
--

DROP TABLE IF EXISTS `customer_gym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer_gym` (
  `Customer_id` int(11) NOT NULL,
  `gyms_id` int(11) NOT NULL,
  KEY `FK_y6bv8tmf8a9d732kvgngm5rg` (`gyms_id`),
  KEY `FK_t58ig2htxsoopo7yrmk8wv3dn` (`Customer_id`),
  CONSTRAINT `FK_t58ig2htxsoopo7yrmk8wv3dn` FOREIGN KEY (`Customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK_y6bv8tmf8a9d732kvgngm5rg` FOREIGN KEY (`gyms_id`) REFERENCES `gym` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer_gym`
--

LOCK TABLES `customer_gym` WRITE;
/*!40000 ALTER TABLE `customer_gym` DISABLE KEYS */;
INSERT INTO `customer_gym` VALUES (59,57),(60,58);
/*!40000 ALTER TABLE `customer_gym` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym`
--

DROP TABLE IF EXISTS `gym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gym` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `fee` double DEFAULT NULL,
  `isDelete` bit(1) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym`
--

LOCK TABLES `gym` WRITE;
/*!40000 ALTER TABLE `gym` DISABLE KEYS */;
INSERT INTO `gym` VALUES (57,0,'pueblo paleta',22.33,'\0','https://i.ytimg.com/vi/iWm0beJM6Bk/maxresdefault.jpg','gym rock'),(58,0,'pueblo paleta',22.33,'\0','https://i.ytimg.com/vi/iWm0beJM6Bk/maxresdefault.jpg','gym water');
/*!40000 ALTER TABLE `gym` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_customer`
--

DROP TABLE IF EXISTS `gym_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gym_customer` (
  `Gym_id` int(11) NOT NULL,
  `customers_id` int(11) NOT NULL,
  KEY `FK_h8rjqemeo4b1feoagcibdbeet` (`customers_id`),
  KEY `FK_4l6u4yg3bbv37oeajn1gtuama` (`Gym_id`),
  CONSTRAINT `FK_4l6u4yg3bbv37oeajn1gtuama` FOREIGN KEY (`Gym_id`) REFERENCES `gym` (`id`),
  CONSTRAINT `FK_h8rjqemeo4b1feoagcibdbeet` FOREIGN KEY (`customers_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_customer`
--

LOCK TABLES `gym_customer` WRITE;
/*!40000 ALTER TABLE `gym_customer` DISABLE KEYS */;
INSERT INTO `gym_customer` VALUES (57,59),(58,60);
/*!40000 ALTER TABLE `gym_customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_trainer`
--

DROP TABLE IF EXISTS `gym_trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gym_trainer` (
  `Gym_id` int(11) NOT NULL,
  `trainers_id` int(11) NOT NULL,
  UNIQUE KEY `UK_56p9go7xb54ens92n1gx9cyle` (`trainers_id`),
  KEY `FK_qumcdkoveo2918q1on1hud6tw` (`Gym_id`),
  CONSTRAINT `FK_qumcdkoveo2918q1on1hud6tw` FOREIGN KEY (`Gym_id`) REFERENCES `gym` (`id`),
  CONSTRAINT `FK_56p9go7xb54ens92n1gx9cyle` FOREIGN KEY (`trainers_id`) REFERENCES `trainer` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_trainer`
--

LOCK TABLES `gym_trainer` WRITE;
/*!40000 ALTER TABLE `gym_trainer` DISABLE KEYS */;
INSERT INTO `gym_trainer` VALUES (57,61),(58,62);
/*!40000 ALTER TABLE `gym_trainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gym_workout`
--

DROP TABLE IF EXISTS `gym_workout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gym_workout` (
  `Gym_id` int(11) NOT NULL,
  `workouts_id` int(11) NOT NULL,
  UNIQUE KEY `UK_jw49pgulxikx27wd1fdhitkri` (`workouts_id`),
  KEY `FK_hoymw9x5ld1evs22p8lhshtf5` (`Gym_id`),
  CONSTRAINT `FK_hoymw9x5ld1evs22p8lhshtf5` FOREIGN KEY (`Gym_id`) REFERENCES `gym` (`id`),
  CONSTRAINT `FK_jw49pgulxikx27wd1fdhitkri` FOREIGN KEY (`workouts_id`) REFERENCES `workout` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gym_workout`
--

LOCK TABLES `gym_workout` WRITE;
/*!40000 ALTER TABLE `gym_workout` DISABLE KEYS */;
INSERT INTO `gym_workout` VALUES (57,82),(57,83),(58,84);
/*!40000 ALTER TABLE `gym_workout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_84bmmxlq61tiaoc7dy7kdcghh` (`userAccount_id`),
  CONSTRAINT `FK_84bmmxlq61tiaoc7dy7kdcghh` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (55,0,'Brook','Sevilla','España','Brook@hotmail.com','+5 (10) 9151','41001','Lopez',52),(56,0,'Misty','Sevilla','España','Misty@hotmail.com','+5 (10) 9152','41001','Lopez',53);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager_gym`
--

DROP TABLE IF EXISTS `manager_gym`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager_gym` (
  `Manager_id` int(11) NOT NULL,
  `gyms_id` int(11) NOT NULL,
  UNIQUE KEY `UK_403tjunmqbwfd05lkf2gl5xuy` (`gyms_id`),
  KEY `FK_8hvl6ijxg55krr2qykaem9qam` (`Manager_id`),
  CONSTRAINT `FK_8hvl6ijxg55krr2qykaem9qam` FOREIGN KEY (`Manager_id`) REFERENCES `manager` (`id`),
  CONSTRAINT `FK_403tjunmqbwfd05lkf2gl5xuy` FOREIGN KEY (`gyms_id`) REFERENCES `gym` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager_gym`
--

LOCK TABLES `manager_gym` WRITE;
/*!40000 ALTER TABLE `manager_gym` DISABLE KEYS */;
INSERT INTO `manager_gym` VALUES (55,57),(56,58);
/*!40000 ALTER TABLE `manager_gym` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socialidentity`
--

DROP TABLE IF EXISTS `socialidentity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socialidentity` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `link` varchar(255) DEFAULT NULL,
  `nameNetwork` varchar(255) DEFAULT NULL,
  `nick` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socialidentity`
--

LOCK TABLES `socialidentity` WRITE;
/*!40000 ALTER TABLE `socialidentity` DISABLE KEYS */;
INSERT INTO `socialidentity` VALUES (85,0,'https://www.facebook.com/profile.php?id=100008310801778','Facebook','Brook'),(86,0,'https://www.facebook.com/profile.php?id=100008310801778','Facebook','Misty');
/*!40000 ALTER TABLE `socialidentity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `step`
--

DROP TABLE IF EXISTS `step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `step` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `videoTutorial` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `step`
--

LOCK TABLES `step` WRITE;
/*!40000 ALTER TABLE `step` DISABLE KEYS */;
INSERT INTO `step` VALUES (76,0,'This is a video tutorial 1','https://www.youtube.com/watch?v=Re9k2-r7OIY'),(77,0,'This is a video tutorial 2','https://www.youtube.com/watch?v=Re9k2-r7OIY'),(78,0,'This is a video tutorial 3, ¡yeah!','https://www.youtube.com/watch?v=Re9k2-r7OIY'),(79,0,'This is a video tutorial 4, ¡yeah!','https://www.youtube.com/watch?v=Re9k2-r7OIY'),(80,0,'This is a video tutorial 5, ¡yeah!','https://www.youtube.com/watch?v=Re9k2-r7OIY'),(81,0,'This is a video tutorial 6, ¡cmon!','https://www.youtube.com/watch?v=Re9k2-r7OIY');
/*!40000 ALTER TABLE `step` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `step_advice`
--

DROP TABLE IF EXISTS `step_advice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `step_advice` (
  `Step_id` int(11) NOT NULL,
  `advices_id` int(11) NOT NULL,
  UNIQUE KEY `UK_4ywidtg1gt06skbhclwx743jg` (`advices_id`),
  KEY `FK_cv5seu81dt4xkvhwd1u5tosco` (`Step_id`),
  CONSTRAINT `FK_cv5seu81dt4xkvhwd1u5tosco` FOREIGN KEY (`Step_id`) REFERENCES `step` (`id`),
  CONSTRAINT `FK_4ywidtg1gt06skbhclwx743jg` FOREIGN KEY (`advices_id`) REFERENCES `advice` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `step_advice`
--

LOCK TABLES `step_advice` WRITE;
/*!40000 ALTER TABLE `step_advice` DISABLE KEYS */;
INSERT INTO `step_advice` VALUES (76,89),(77,90),(80,91),(81,92);
/*!40000 ALTER TABLE `step_advice` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tabuword`
--

DROP TABLE IF EXISTS `tabuword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tabuword` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tabuword`
--

LOCK TABLES `tabuword` WRITE;
/*!40000 ALTER TABLE `tabuword` DISABLE KEYS */;
INSERT INTO `tabuword` VALUES (74,0,'sex'),(75,0,'viagra');
/*!40000 ALTER TABLE `tabuword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer`
--

DROP TABLE IF EXISTS `trainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainer` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `actorName` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `postalAddress` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_1v0ljnwuhlwur1d8ukoe2vlhp` (`userAccount_id`),
  CONSTRAINT `FK_1v0ljnwuhlwur1d8ukoe2vlhp` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer`
--

LOCK TABLES `trainer` WRITE;
/*!40000 ALTER TABLE `trainer` DISABLE KEYS */;
INSERT INTO `trainer` VALUES (61,0,'Brook','Sevilla','España','Brook@hotmail.com','+5 (10) 9142','41001','Lopez',50),(62,0,'Misty','Sevilla','España','Misty@hotmail.com','+5 (10) 9152','41001','Lopez',51);
/*!40000 ALTER TABLE `trainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trainer_curricula`
--

DROP TABLE IF EXISTS `trainer_curricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `trainer_curricula` (
  `Trainer_id` int(11) NOT NULL,
  `curriculas_id` int(11) NOT NULL,
  UNIQUE KEY `UK_jqvrbxbtfqe3en5ok6t11tjkt` (`curriculas_id`),
  KEY `FK_n3l64kf9h3aiflj5iet564qe6` (`Trainer_id`),
  CONSTRAINT `FK_n3l64kf9h3aiflj5iet564qe6` FOREIGN KEY (`Trainer_id`) REFERENCES `trainer` (`id`),
  CONSTRAINT `FK_jqvrbxbtfqe3en5ok6t11tjkt` FOREIGN KEY (`curriculas_id`) REFERENCES `curricula` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trainer_curricula`
--

LOCK TABLES `trainer_curricula` WRITE;
/*!40000 ALTER TABLE `trainer_curricula` DISABLE KEYS */;
INSERT INTO `trainer_curricula` VALUES (61,87),(62,88);
/*!40000 ALTER TABLE `trainer_curricula` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `activate` bit(1) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (47,0,'','21232f297a57a5a743894a0e4a801fc3','admin'),(48,0,'','ffbc4675f864e0e9aab8bdf7a0437010','customer1'),(49,0,'','5ce4d191fd14ac85a1469fb8c29b7a7b','customer2'),(50,0,'','4d9a96c8e1650dc161f1adcf5c5082a0','trainer1'),(51,0,'','6662f54a6c5033357408e6839a5c0a05','trainer2'),(52,0,'','c240642ddef994358c96da82c0361a58','manager1'),(53,0,'','8df5127cd164b5bc2d2b78410a7eea0c','manager2');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (47,'ADMINISTRATOR'),(48,'CUSTOMER'),(49,'CUSTOMER'),(50,'TRAINER'),(51,'TRAINER'),(52,'MANAGER'),(53,'MANAGER');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout`
--

DROP TABLE IF EXISTS `workout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workout` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout`
--

LOCK TABLES `workout` WRITE;
/*!40000 ALTER TABLE `workout` DISABLE KEYS */;
INSERT INTO `workout` VALUES (82,0,'This is workout one to this gym, ¡cmon!','Routine One'),(83,0,'This is workout two to this gym, ¡cmon!','Routine Two'),(84,0,'This is workout three to this other gym, ¡cmon!','Routine Three');
/*!40000 ALTER TABLE `workout` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout_annotation`
--

DROP TABLE IF EXISTS `workout_annotation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workout_annotation` (
  `Workout_id` int(11) NOT NULL,
  `annotations_id` int(11) NOT NULL,
  UNIQUE KEY `UK_1qf26dip8cphyg3cu6osofqkj` (`annotations_id`),
  KEY `FK_il49rk8bwbwp0jmcd46qbkwx1` (`Workout_id`),
  CONSTRAINT `FK_il49rk8bwbwp0jmcd46qbkwx1` FOREIGN KEY (`Workout_id`) REFERENCES `workout` (`id`),
  CONSTRAINT `FK_1qf26dip8cphyg3cu6osofqkj` FOREIGN KEY (`annotations_id`) REFERENCES `annotation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout_annotation`
--

LOCK TABLES `workout_annotation` WRITE;
/*!40000 ALTER TABLE `workout_annotation` DISABLE KEYS */;
/*!40000 ALTER TABLE `workout_annotation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workout_step`
--

DROP TABLE IF EXISTS `workout_step`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workout_step` (
  `Workout_id` int(11) NOT NULL,
  `steps_id` int(11) NOT NULL,
  UNIQUE KEY `UK_3gmmvbsyn14afp0kyyfoaeq2m` (`steps_id`),
  KEY `FK_3cbtwfhrf8wlyxooaiuj6v4ef` (`Workout_id`),
  CONSTRAINT `FK_3cbtwfhrf8wlyxooaiuj6v4ef` FOREIGN KEY (`Workout_id`) REFERENCES `workout` (`id`),
  CONSTRAINT `FK_3gmmvbsyn14afp0kyyfoaeq2m` FOREIGN KEY (`steps_id`) REFERENCES `step` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workout_step`
--

LOCK TABLES `workout_step` WRITE;
/*!40000 ALTER TABLE `workout_step` DISABLE KEYS */;
INSERT INTO `workout_step` VALUES (82,76),(82,77),(83,78),(83,79),(83,80),(84,81);
/*!40000 ALTER TABLE `workout_step` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-31 15:33:27

commit;