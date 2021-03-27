-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: hha
-- ------------------------------------------------------
-- Server version	8.0.23

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
-- Table structure for table `banner_type`
--

DROP TABLE IF EXISTS `banner_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `banner_type` (
  `banner_type_id` int NOT NULL AUTO_INCREMENT,
  `banner_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`banner_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner_type`
--

LOCK TABLES `banner_type` WRITE;
/*!40000 ALTER TABLE `banner_type` DISABLE KEYS */;
INSERT INTO `banner_type` VALUES (1,'home'),(2,'holiday'),(3,'promotion');
/*!40000 ALTER TABLE `banner_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
  `category_id` int NOT NULL AUTO_INCREMENT,
  `category_name` varchar(45) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'meat'),(2,'produce'),(3,'groceries'),(4,'frozen'),(5,'fruit');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `current_holiday_banner`
--

DROP TABLE IF EXISTS `current_holiday_banner`;
/*!50001 DROP VIEW IF EXISTS `current_holiday_banner`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `current_holiday_banner` AS SELECT 
 1 AS `product_id`,
 1 AS `banner_image_url`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `current_home_banner`
--

DROP TABLE IF EXISTS `current_home_banner`;
/*!50001 DROP VIEW IF EXISTS `current_home_banner`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `current_home_banner` AS SELECT 
 1 AS `product_id`,
 1 AS `product_name`,
 1 AS `description`,
 1 AS `banner_image_url`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `current_promotion`
--

DROP TABLE IF EXISTS `current_promotion`;
/*!50001 DROP VIEW IF EXISTS `current_promotion`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `current_promotion` AS SELECT 
 1 AS `event_id`,
 1 AS `description`,
 1 AS `banner_image_url`,
 1 AS `product_id`,
 1 AS `product_name`,
 1 AS `retail_price`,
 1 AS `discount_price`,
 1 AS `product_image_url`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
  `discount_id` int NOT NULL AUTO_INCREMENT,
  `product_id` int NOT NULL,
  `event_id` int NOT NULL,
  `discount_price` float NOT NULL,
  `discount_limit` int NOT NULL,
  PRIMARY KEY (`discount_id`),
  KEY `fk_discount_product_idx` (`product_id`),
  KEY `fk_discount_event_idx` (`event_id`),
  CONSTRAINT `fk_discount_event_idx` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_discount_product_idx` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
INSERT INTO `discount` VALUES (1,9,1,0.3,50),(2,10,2,0.5,50),(3,9,3,0.2,50),(4,10,4,0.5,50),(5,9,5,0.3,50),(6,40,5,1.3,50),(7,41,5,66.3,50),(8,42,5,4.3,50);
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `event_id` int NOT NULL AUTO_INCREMENT,
  `event_title` varchar(45) DEFAULT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `description` longtext,
  PRIMARY KEY (`event_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'2021 Mar 1st week','2021-03-01','2021-03-07','2021 Mar 1st week'),(2,'2021 Mar 2nd week','2021-03-08','2021-03-14','2021 Mar 2nd week'),(3,'2021 Mar 3rd week','2021-03-15','2021-02-21','2021 Mar 3rd week'),(4,'2021 Mar 4th week','2021-03-22','2021-03-28','2021 Mar 4th week');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `home_banner`
--

DROP TABLE IF EXISTS `home_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `home_banner` (
  `home_banner_id` int NOT NULL AUTO_INCREMENT,
  `banner_image_url` varchar(255) NOT NULL,
  `banner_type` int NOT NULL,
  `product_id` int DEFAULT NULL,
  `start_data` date DEFAULT NULL,
  `end_data` date DEFAULT NULL,
  PRIMARY KEY (`home_banner_id`),
  KEY `fk_home_banner_banner_idx` (`banner_type`),
  KEY `home_banner` (`product_id`),
  CONSTRAINT `fk_home_banner_banner_idx` FOREIGN KEY (`banner_type`) REFERENCES `banner_type` (`banner_type_id`),
  CONSTRAINT `home_banner_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `home_banner`
--

LOCK TABLES `home_banner` WRITE;
/*!40000 ALTER TABLE `home_banner` DISABLE KEYS */;
INSERT INTO `home_banner` VALUES (2,'https://hhamedia.s3.us-east-2.amazonaws.com/455f25cd-d623-4d7d-8b8e-e81056776100.jfif',1,10,'2021-03-23','2021-03-30'),(3,'https://hhamedia.s3.us-east-2.amazonaws.com/455f25cd-d623-4d7d-8b8e-e81056776100.jfif',1,9,'2021-03-23','2021-03-30'),(4,'https://hhamedia.s3.us-east-2.amazonaws.com/455f25cd-d623-4d7d-8b8e-e81056776100.jfif',2,1,'2021-03-23','2021-03-30'),(5,'https://hhamedia.s3.us-east-2.amazonaws.com/455f25cd-d623-4d7d-8b8e-e81056776100.jfif',3,1,NULL,NULL);
/*!40000 ALTER TABLE `home_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `order_items_id` int NOT NULL AUTO_INCREMENT,
  `total_price` double DEFAULT NULL,
  `gst_sum` double GENERATED ALWAYS AS (truncate((`total_price` * 0.05),2)) VIRTUAL,
  `sum` double GENERATED ALWAYS AS (truncate((`total_price` + `gst_sum`),2)) VIRTUAL,
  `product_id` int NOT NULL,
  `qty` int DEFAULT NULL,
  `orders_id` int NOT NULL,
  PRIMARY KEY (`order_items_id`),
  KEY `fk_order_item_product_idx` (`product_id`),
  KEY `fk_order_item_order_idx` (`orders_id`),
  CONSTRAINT `fk_order_item_order_idx` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`orders_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_order_item_product_idx` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `orders_id` int NOT NULL AUTO_INCREMENT,
  `order_date` date NOT NULL,
  `paid_date` date DEFAULT NULL,
  `price_sum` double NOT NULL,
  `gst_sum` double GENERATED ALWAYS AS (truncate((`price_sum` * 0.05),2)) VIRTUAL,
  `sum` double GENERATED ALWAYS AS (truncate((`price_sum` + `gst_sum`),2)) VIRTUAL,
  `status` varchar(10) NOT NULL,
  `order_email` varchar(55) DEFAULT NULL,
  `order_phone` varchar(12) DEFAULT NULL,
  `order_name` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`orders_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_name` varchar(45) NOT NULL,
  `description` longtext,
  `retail_price` float NOT NULL,
  `active` tinyint(1) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `category_id` int NOT NULL,
  `quantity` int NOT NULL,
  `weight_value` double NOT NULL,
  `weight_type_id` int NOT NULL,
  `brand_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`product_id`),
  KEY `fk_product_category_idx` (`category_id`),
  KEY `fk_product_weight_idx` (`weight_type_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_product_weight` FOREIGN KEY (`weight_type_id`) REFERENCES `weight_type` (`weight_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Beef Finger Meat Whole Pack','Beef Finger Meat Whole Pack',8.99,1,'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png',1,100,1,1,NULL),(2,'Pork Hock With Feet','Pork Hock With Feet',2.99,1,'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png',1,100,1,1,NULL),(3,'Orange Navel','Orange Navel',1.29,1,'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png',2,100,1,1,NULL),(4,'Apple Red Delicious','Apple Red Delicious',1.99,1,'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png',2,100,1,1,NULL),(5,'Soup Beef Flavour','Soup Beef Flavour',9.99,1,'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png',3,100,284,2,'Quoc Viet'),(6,'Soup Chicken Flavour','Soup Beef Flavour',9.99,1,'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png',3,100,284,2,'Quoc Viet'),(7,'Basa Steak','Basa Steak',4.49,1,'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png',4,100,812.5,2,'Dolphins'),(8,'Greenshell Mussels H/S','Greenshell Mussels H/S',6.49,1,'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png',4,100,454,2,'Talley\'s'),(9,'Orange','Orange\'s Description',0.39,1,'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png',5,100,3,1,NULL),(10,'Apple','Apple\'s Description',0.59,1,'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png',5,100,3,1,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_info`
--

DROP TABLE IF EXISTS `role_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_info` (
  `role_id` int NOT NULL AUTO_INCREMENT,
  `role` varchar(11) NOT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_info`
--

LOCK TABLES `role_info` WRITE;
/*!40000 ALTER TABLE `role_info` DISABLE KEYS */;
INSERT INTO `role_info` VALUES (1,'ROLE_ADMIN');
/*!40000 ALTER TABLE `role_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `tf_curr_holiday`
--

DROP TABLE IF EXISTS `tf_curr_holiday`;
/*!50001 DROP VIEW IF EXISTS `tf_curr_holiday`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `tf_curr_holiday` AS SELECT 
 1 AS `banner_image_url`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `tf_curr_home_banner`
--

DROP TABLE IF EXISTS `tf_curr_home_banner`;
/*!50001 DROP VIEW IF EXISTS `tf_curr_home_banner`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `tf_curr_home_banner` AS SELECT 
 1 AS `title`,
 1 AS `description`,
 1 AS `banner_image_url`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `tf_curr_promotion`
--

DROP TABLE IF EXISTS `tf_curr_promotion`;
/*!50001 DROP VIEW IF EXISTS `tf_curr_promotion`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `tf_curr_promotion` AS SELECT 
 1 AS `product_id`,
 1 AS `product_name`,
 1 AS `retail_price`,
 1 AS `discount_price`,
 1 AS `banner_image_url`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `tf_event_banner`
--

DROP TABLE IF EXISTS `tf_event_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tf_event_banner` (
  `event_id` int NOT NULL,
  `image_url` varchar(255) NOT NULL,
  PRIMARY KEY (`event_id`),
  CONSTRAINT `tf_event_banner_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_event_banner`
--

LOCK TABLES `tf_event_banner` WRITE;
/*!40000 ALTER TABLE `tf_event_banner` DISABLE KEYS */;
INSERT INTO `tf_event_banner` VALUES (4,'image_for_promotion.png');
/*!40000 ALTER TABLE `tf_event_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_holiday_banner`
--

DROP TABLE IF EXISTS `tf_holiday_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tf_holiday_banner` (
  `holiday_banner_id` int NOT NULL AUTO_INCREMENT,
  `banner_image_url` varchar(255) NOT NULL,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `comment` varchar(255) DEFAULT NULL COMMENT 'Comment for management',
  PRIMARY KEY (`holiday_banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='test feature for holiday banner';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_holiday_banner`
--

LOCK TABLES `tf_holiday_banner` WRITE;
/*!40000 ALTER TABLE `tf_holiday_banner` DISABLE KEYS */;
INSERT INTO `tf_holiday_banner` VALUES (1,'yyy.jpg','2021-03-23','2021-03-31','for test');
/*!40000 ALTER TABLE `tf_holiday_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_home_banner`
--

DROP TABLE IF EXISTS `tf_home_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tf_home_banner` (
  `home_banner_id` int NOT NULL AUTO_INCREMENT,
  `begin_date` date NOT NULL,
  `end_date` date NOT NULL,
  `comment` varchar(255) DEFAULT NULL COMMENT 'Comment for management',
  PRIMARY KEY (`home_banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='test feature for home banner';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_home_banner`
--

LOCK TABLES `tf_home_banner` WRITE;
/*!40000 ALTER TABLE `tf_home_banner` DISABLE KEYS */;
INSERT INTO `tf_home_banner` VALUES (1,'2021-03-25','2021-03-31','Test');
/*!40000 ALTER TABLE `tf_home_banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tf_home_banner_item`
--

DROP TABLE IF EXISTS `tf_home_banner_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tf_home_banner_item` (
  `home_banner_item_id` int NOT NULL AUTO_INCREMENT,
  `home_banner_id` int NOT NULL,
  `banner_image_url` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`home_banner_item_id`),
  KEY `home_banner_id` (`home_banner_id`),
  CONSTRAINT `tf_home_banner_item_ibfk_1` FOREIGN KEY (`home_banner_id`) REFERENCES `tf_home_banner` (`home_banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='test feature for home banner item';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tf_home_banner_item`
--

LOCK TABLES `tf_home_banner_item` WRITE;
/*!40000 ALTER TABLE `tf_home_banner_item` DISABLE KEYS */;
INSERT INTO `tf_home_banner_item` VALUES (1,1,'xxxxxxxx.jpg','test title','test descr'),(2,1,'2222222222.jpg','test title2','test descr2');
/*!40000 ALTER TABLE `tf_home_banner_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `email` varchar(20) NOT NULL,
  `password` varchar(255) NOT NULL,
  `active` tinyint(1) NOT NULL,
  `user_name` varchar(32) DEFAULT NULL,
  `uuid` varchar(36) DEFAULT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` varchar(20) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  KEY `fk_role_user_idx` (`user_id`),
  KEY `fk_role_role_idx` (`role_id`),
  CONSTRAINT `fk_role_role_idx` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_role_user_idx` FOREIGN KEY (`user_id`) REFERENCES `user` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weight_type`
--

DROP TABLE IF EXISTS `weight_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weight_type` (
  `weight_type_id` int NOT NULL AUTO_INCREMENT,
  `weight_type_name` varchar(45) NOT NULL,
  PRIMARY KEY (`weight_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weight_type`
--

LOCK TABLES `weight_type` WRITE;
/*!40000 ALTER TABLE `weight_type` DISABLE KEYS */;
INSERT INTO `weight_type` VALUES (1,'lb'),(2,'g'),(3,'ea');
/*!40000 ALTER TABLE `weight_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `current_holiday_banner`
--

/*!50001 DROP VIEW IF EXISTS `current_holiday_banner`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dev`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `current_holiday_banner` AS select `hb`.`product_id` AS `product_id`,`hb`.`banner_image_url` AS `banner_image_url` from (`home_banner` `hb` join `banner_type` `bt` on((`hb`.`banner_type` = `bt`.`banner_type_id`))) where ((`bt`.`banner_type` = 'holiday') and (`hb`.`start_data` <= curdate()) and (curdate() <= `hb`.`end_data`)) order by `hb`.`start_data` desc limit 1 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `current_home_banner`
--

/*!50001 DROP VIEW IF EXISTS `current_home_banner`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dev`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `current_home_banner` AS select `hb`.`product_id` AS `product_id`,`p`.`product_name` AS `product_name`,`p`.`description` AS `description`,`hb`.`banner_image_url` AS `banner_image_url` from ((`home_banner` `hb` join `banner_type` `bt` on((`bt`.`banner_type_id` = `hb`.`banner_type`))) join `product` `p` on((`hb`.`product_id` = `p`.`product_id`))) where ((`bt`.`banner_type` = 'home') and (`hb`.`start_data` <= curdate()) and (curdate() <= `hb`.`end_data`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `current_promotion`
--

/*!50001 DROP VIEW IF EXISTS `current_promotion`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dev`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `current_promotion` AS select `e`.`event_id` AS `event_id`,`e`.`description` AS `description`,`teb`.`image_url` AS `banner_image_url`,`p`.`product_id` AS `product_id`,`p`.`product_name` AS `product_name`,`p`.`retail_price` AS `retail_price`,`d`.`discount_price` AS `discount_price`,`p`.`image_url` AS `product_image_url` from (((`event` `e` join `discount` `d` on((`e`.`event_id` = `d`.`event_id`))) join `product` `p` on((`d`.`product_id` = `p`.`product_id`))) left join `tf_event_banner` `teb` on((`teb`.`event_id` = `e`.`event_id`))) where ((`e`.`start_date` <= curdate()) and (curdate() <= `e`.`end_date`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `tf_curr_holiday`
--

/*!50001 DROP VIEW IF EXISTS `tf_curr_holiday`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dev`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `tf_curr_holiday` AS select `tf_holiday_banner`.`banner_image_url` AS `banner_image_url` from `tf_holiday_banner` where ((`tf_holiday_banner`.`begin_date` <= curdate()) and (`tf_holiday_banner`.`end_date` >= curdate())) order by `tf_holiday_banner`.`begin_date` desc limit 1 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `tf_curr_home_banner`
--

/*!50001 DROP VIEW IF EXISTS `tf_curr_home_banner`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dev`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `tf_curr_home_banner` AS select `hbi`.`title` AS `title`,`hbi`.`description` AS `description`,`hbi`.`banner_image_url` AS `banner_image_url` from (`tf_home_banner_item` `hbi` join `tf_home_banner` `thb` on((`hbi`.`home_banner_id` = `thb`.`home_banner_id`))) where ((`thb`.`begin_date` <= curdate()) and (`thb`.`end_date` >= curdate())) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `tf_curr_promotion`
--

/*!50001 DROP VIEW IF EXISTS `tf_curr_promotion`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dev`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `tf_curr_promotion` AS select `p`.`product_id` AS `product_id`,`p`.`product_name` AS `product_name`,`p`.`retail_price` AS `retail_price`,`d`.`discount_price` AS `discount_price`,`p`.`image_url` AS `banner_image_url` from ((`event` `e` join `discount` `d` on((`e`.`event_id` = `d`.`event_id`))) join `product` `p` on((`d`.`product_id` = `p`.`product_id`))) where ((`e`.`start_date` <= curdate()) and (curdate() <= `e`.`end_date`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-27 13:22:46
