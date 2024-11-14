-- MySQL dump 10.13  Distrib 5.7.24, for osx11.1 (x86_64)
--
-- Host: localhost    Database: backendtest_bookingsystem
-- ------------------------------------------------------
-- Server version	8.0.33

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
-- Table structure for table `bs_user`
--

DROP TABLE IF EXISTS `bs_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bs_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `is_verified` tinyint(1) NOT NULL DEFAULT '0',
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `verification_token` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK9d5myufq0sev17gw41fcio4jf` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bs_user`
--

LOCK TABLES `bs_user` WRITE;
/*!40000 ALTER TABLE `bs_user` DISABLE KEYS */;
INSERT INTO `bs_user` VALUES (1,'emma@example.com',1,'Emma','$2a$10$2EHH9Zrox2Fubsip9lUU8OJXHUvWfwTly4Q59zHa6TLBJAuAEkLsW',NULL,NULL),(2,'james@example.com',1,'James','$2a$10$OydrnJ4WVucZlPM6iNwAH.CLa2GrcrBlL0VaeV45mDM7nRQnyXjDu',NULL,'ADMIN'),(10,'user1@example.com',1,'User One','password123',NULL,NULL),(11,'user2@example.com',1,'User Two','password123',NULL,NULL),(13,'mary@example.com',1,'Mary','$2a$10$NxoJSUHC5PAZXWPg51X2rOHJZV4LRwrpOB3uRaA7na4spTQHpEiRC',NULL,'MEMBER'),(14,'kink@example.com',0,'Kink','$2a$10$N0K0r96/qRft0i9/DdsdC.kcZRx7Q0Qp7gYnHakcCsVvKl9TCWATu','e562b1fa-630b-4894-827f-54431629ec4d','MEMBER'),(19,'ella@example.com',1,'Ella','$2a$10$DfMDp6Ri/knWxwN.LAjNZeQqUbWZyEHNvPoKUWAmDXUI5YBEHHXCm',NULL,'MEMBER'),(20,'ruby@example.com',1,'Ruby','$2a$10$mEfN.2BXsVMZXYfcfVZIDO7sdARLwtB.bn4HIDhcO/sLIVw0vrIra',NULL,'MEMBER');
/*!40000 ALTER TABLE `bs_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class_schedule`
--

DROP TABLE IF EXISTS `class_schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `class_schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_time` datetime(6) DEFAULT NULL,
  `max_capacity` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_time` datetime(6) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `available_slots` int NOT NULL,
  `required_credits` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class_schedule`
--

LOCK TABLES `class_schedule` WRITE;
/*!40000 ALTER TABLE `class_schedule` DISABLE KEYS */;
INSERT INTO `class_schedule` VALUES (1,'SG',NULL,'2025-01-05 11:00:00.000000',20,NULL,'2025-01-01 09:00:00.000000','Basic',0,0),(2,'SG','Yoga for Beginner','2025-01-05 11:00:00.000000',20,NULL,'2025-01-01 09:00:00.000000','Yoga',0,0),(5,'SG',NULL,'2024-11-15 11:00:00.000000',20,NULL,'2024-11-15 10:00:00.000000','1 hr Yoga Class SG',5,1),(6,'MY',NULL,'2024-11-15 11:00:00.000000',20,NULL,'2024-11-15 10:00:00.000000','1 hr Yoga Class MY',5,1),(7,'SG',NULL,'2024-11-15 11:00:00.000000',20,NULL,'2024-11-15 10:00:00.000000','1 hr Zumba Class ',5,1),(8,'MM',NULL,'2024-11-15 11:00:00.000000',20,NULL,'2024-11-15 10:00:00.000000','1 hr Zumba Class ',5,1),(9,'SG',NULL,'2024-12-15 11:00:00.000000',1,NULL,'2024-12-15 10:00:00.000000','1 hr Kick Class',1,1),(10,'MM',NULL,'2024-12-15 11:00:00.000000',1,NULL,'2024-12-15 10:00:00.000000','1 hr Kick Class',1,1),(15,'SG',NULL,'2024-11-15 11:00:00.000000',1,NULL,'2024-11-15 10:00:00.000000','1 hr Boxing Class',1,1),(16,'MM',NULL,'2024-11-15 11:00:00.000000',1,NULL,'2024-11-14 01:20:00.000000','1 hr Boxing Class',1,1);
/*!40000 ALTER TABLE `class_schedule` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `package` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `country` varchar(255) DEFAULT NULL,
  `credits` int NOT NULL,
  `expiration_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` VALUES (1,'SG',5,'2025-10-20 00:00:00.000000','Basic',5),(2,'MM',10,'2025-08-10 00:00:00.000000','Basic',5),(3,'MM',15,'2025-08-10 00:00:00.000000','Intermediate',8),(4,'SG',15,'2025-08-10 00:00:00.000000','Intermediate',8),(5,'SG',5,'2025-12-31 00:00:00.000000','Basic Package ',50),(6,'SG',10,'2025-12-31 00:00:00.000000','Standard Package ',90),(7,'MM',5,'2025-12-31 00:00:00.000000','Basic Package ',45),(8,'MM',10,'2025-12-31 00:00:00.000000','Standard Package ',85),(9,'TH',15,'2025-12-31 00:00:00.000000','Premium Package ',120);
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase_history`
--

DROP TABLE IF EXISTS `purchase_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase_history` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `purchase_date` datetime(6) DEFAULT NULL,
  `pack_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKivfffc64jsxrdksvxvs9vw1f4` (`pack_id`),
  KEY `FK9bppxh3bwjcijioj7fsvytjlm` (`user_id`),
  CONSTRAINT `FK9bppxh3bwjcijioj7fsvytjlm` FOREIGN KEY (`user_id`) REFERENCES `bs_user` (`id`),
  CONSTRAINT `FKivfffc64jsxrdksvxvs9vw1f4` FOREIGN KEY (`pack_id`) REFERENCES `package` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase_history`
--

LOCK TABLES `purchase_history` WRITE;
/*!40000 ALTER TABLE `purchase_history` DISABLE KEYS */;
INSERT INTO `purchase_history` VALUES (1,120,'2024-11-13 15:52:32.311596',9,2),(2,45,'2024-11-13 16:54:51.342858',7,1),(3,90,'2024-11-13 18:39:58.331970',6,1),(4,50,'2024-11-13 19:48:17.865943',5,13),(5,85,'2024-11-13 23:40:56.299523',8,13),(6,85,'2024-11-13 23:45:37.322091',8,20);
/*!40000 ALTER TABLE `purchase_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_booking`
--

DROP TABLE IF EXISTS `user_booking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_booking` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `booking_time` datetime(6) DEFAULT NULL,
  `cancellation_time` datetime(6) DEFAULT NULL,
  `checked_in` tinyint(1) NOT NULL DEFAULT '0',
  `is_waitlisted` tinyint(1) NOT NULL DEFAULT '0',
  `scheduled_class_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `user_package_id` bigint DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7ydxmsxybmr7iu3uljiwm9ktt` (`scheduled_class_id`),
  KEY `FKnl9js1f3xtoks0en8yglphr1r` (`user_id`),
  KEY `FK6cv9ytp4u0cufiax6o64v0el6` (`user_package_id`),
  CONSTRAINT `FK6cv9ytp4u0cufiax6o64v0el6` FOREIGN KEY (`user_package_id`) REFERENCES `user_package` (`id`),
  CONSTRAINT `FK7ydxmsxybmr7iu3uljiwm9ktt` FOREIGN KEY (`scheduled_class_id`) REFERENCES `class_schedule` (`id`),
  CONSTRAINT `FKnl9js1f3xtoks0en8yglphr1r` FOREIGN KEY (`user_id`) REFERENCES `bs_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_booking`
--

LOCK TABLES `user_booking` WRITE;
/*!40000 ALTER TABLE `user_booking` DISABLE KEYS */;
INSERT INTO `user_booking` VALUES (2,'2024-11-13 19:31:08.781327','2024-11-13 19:35:41.091727',0,0,7,1,3,'CANCEL'),(3,'2024-11-13 19:42:33.225651',NULL,0,0,9,1,3,'ACTIVE'),(6,'2024-11-13 23:35:26.360955','2024-11-14 00:10:38.822426',0,1,9,13,4,'CANCEL'),(7,'2024-11-13 23:41:50.083244','2024-11-14 00:39:19.692512',0,0,16,13,5,'ACTIVE'),(8,'2024-11-13 23:42:43.745746','2024-11-14 00:54:52.428714',0,0,16,1,2,'CANCEL'),(11,'2024-11-14 03:59:52.235303',NULL,0,0,16,20,6,'ACTIVE'),(13,'2024-11-14 04:05:07.273868',NULL,1,1,16,13,5,'ACTIVE');
/*!40000 ALTER TABLE `user_booking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_package`
--

DROP TABLE IF EXISTS `user_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_package` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  `purchase_date` datetime(6) DEFAULT NULL,
  `remaining_credits` int NOT NULL,
  `package_details_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  `expiration_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbv2f5ecloiooj8kltobi8pj86` (`package_details_id`),
  KEY `FKa5wt2qq60wvkkh4e8ww6ven21` (`user_id`),
  CONSTRAINT `FKa5wt2qq60wvkkh4e8ww6ven21` FOREIGN KEY (`user_id`) REFERENCES `bs_user` (`id`),
  CONSTRAINT `FKbv2f5ecloiooj8kltobi8pj86` FOREIGN KEY (`package_details_id`) REFERENCES `package` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_package`
--

LOCK TABLES `user_package` WRITE;
/*!40000 ALTER TABLE `user_package` DISABLE KEYS */;
INSERT INTO `user_package` VALUES (1,0,NULL,15,9,2,'2025-12-31 00:00:00.000000','ACTIVE'),(2,0,'2024-11-13 16:54:50.401896',6,7,1,'2025-12-31 00:00:00.000000','ACTIVE'),(3,0,'2024-11-13 18:39:58.242634',9,6,1,'2025-12-31 00:00:00.000000','ACTIVE'),(4,0,'2024-11-13 19:48:17.836904',4,5,13,'2025-12-31 00:00:00.000000','ACTIVE'),(5,0,'2024-11-13 23:40:56.277070',11,8,13,'2025-12-31 00:00:00.000000','ACTIVE'),(6,0,'2024-11-13 23:45:37.314333',9,8,20,'2025-12-31 00:00:00.000000','ACTIVE');
/*!40000 ALTER TABLE `user_package` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-14  8:42:25
