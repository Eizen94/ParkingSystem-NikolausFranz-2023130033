-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: parking_system
-- ------------------------------------------------------
-- Server version	8.0.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `parking_rates`
--

DROP TABLE IF EXISTS `parking_rates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking_rates` (
  `id` int NOT NULL AUTO_INCREMENT,
  `vehicle_type` enum('Motor','Mobil') NOT NULL,
  `rate_per_hour` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_rates`
--

LOCK TABLES `parking_rates` WRITE;
/*!40000 ALTER TABLE `parking_rates` DISABLE KEYS */;
INSERT INTO `parking_rates` VALUES (1,'Motor',2000.00),(2,'Mobil',5000.00);
/*!40000 ALTER TABLE `parking_rates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking_transactions`
--

DROP TABLE IF EXISTS `parking_transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `parking_transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `plate_number` varchar(20) NOT NULL,
  `vehicle_type` enum('Motor','Mobil') NOT NULL,
  `entry_time` datetime NOT NULL,
  `exit_time` datetime DEFAULT NULL,
  `parking_fee` decimal(10,2) DEFAULT NULL,
  `status` enum('Active','Completed') DEFAULT 'Active',
  PRIMARY KEY (`id`),
  KEY `idx_status` (`status`),
  KEY `idx_entry_time` (`entry_time`),
  KEY `idx_vehicle_type` (`vehicle_type`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking_transactions`
--

LOCK TABLES `parking_transactions` WRITE;
/*!40000 ALTER TABLE `parking_transactions` DISABLE KEYS */;
INSERT INTO `parking_transactions` VALUES (1,'','Motor','2024-10-23 15:45:53','2024-10-23 15:47:10',0.00,'Completed'),(2,'','Mobil','2024-10-23 15:45:53','2024-10-23 15:47:10',0.00,'Completed'),(3,'','Mobil','2024-10-23 16:56:14','2024-10-23 16:59:32',5000.00,'Completed'),(4,'','Motor','2024-10-23 16:56:26','2024-10-23 16:59:46',2000.00,'Completed'),(5,'','Mobil','2024-10-23 16:56:31','2024-10-23 16:59:56',5000.00,'Completed'),(6,'','Motor','2024-10-23 16:56:35','2024-10-23 16:59:58',2000.00,'Completed'),(7,'','Motor','2024-10-23 16:56:37','2024-10-23 17:00:00',2000.00,'Completed'),(8,'','Motor','2024-10-23 16:56:42','2024-10-23 16:59:52',2000.00,'Completed'),(9,'','Mobil','2024-10-23 16:56:44','2024-10-23 16:59:54',5000.00,'Completed'),(10,'','Motor','2024-10-23 17:00:37','2024-10-23 17:00:45',2000.00,'Completed'),(11,'','Motor','2024-10-23 17:47:05','2024-10-23 17:47:22',2000.00,'Completed'),(12,'','Mobil','2024-10-23 17:50:19','2024-10-23 17:50:45',5000.00,'Completed'),(13,'','Mobil','2024-10-23 08:16:42','2024-10-23 18:17:35',50000.00,'Completed'),(14,'D 1','Mobil','2024-09-23 20:16:01','2024-10-23 20:16:38',3600000.00,'Completed'),(15,'BK 2456 VBU','Motor','2024-10-23 20:21:09','2024-10-23 20:23:55',2000.00,'Completed'),(16,'DK 2248 VAK','Motor','2024-10-23 20:23:31','2024-10-23 20:23:59',2000.00,'Completed');
/*!40000 ALTER TABLE `parking_transactions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-23 20:34:46
