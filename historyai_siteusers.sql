CREATE DATABASE  IF NOT EXISTS `historyai` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `historyai`;
-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: historyai
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `siteusers`
--

DROP TABLE IF EXISTS `siteusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `siteusers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_admin` bit(1) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `siteusers`
--

LOCK TABLES `siteusers` WRITE;
/*!40000 ALTER TABLE `siteusers` DISABLE KEYS */;
INSERT INTO `siteusers` VALUES (1,_binary '\0','petar123@abv.bg','parola12345','Peter'),(2,_binary '\0','ivancho123@abv.bg','parola12345678','Ivan'),(3,_binary '\0','sdkdsfk@gmail.com','',''),(4,_binary '\0','ivan3@abv.bg','parola45678','Kenedi'),(5,_binary '\0','ivan1233@abv.bg','para45678','KenediJr'),(6,_binary '\0','sdfsdfk@gmail.com','dsjfnksdf3124','dkfjssjkdf'),(7,_binary '\0','pesho1123@gmail.com','dsfjksdf21332','Peshko'),(8,_binary '\0','pesho13323@gmail.com','1234djkvxv','Peshkot'),(9,_binary '\0','pesh124@gmail.com','sdfs324','Peshkoto'),(10,_binary '\0','kteh124@gmail.com','kjdsfnskdf324','Keto'),(11,_binary '\0','koteh124@gmail.com','аяояаояао42434','Ketoоо'),(12,_binary '\0','koteha124@gmail.com','dskjdsfh234','Ketoооn'),(13,_binary '\0','koteha1324@gmail.com','dsjkfhskdf34234234','Ketoооna'),(14,_binary '\0','kotehaa1324@gmail.com','sdfjnskfd13','Ketoооnaa'),(15,_binary '\0','loca@gmail.com','sdjkfhsdf13213','Location1');
/*!40000 ALTER TABLE `siteusers` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-14 13:08:29
