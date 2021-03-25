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
 1 AS `product_id`,
 1 AS `product_name`,
 1 AS `retail_price`,
 1 AS `discount_price`,
 1 AS `banner_image_url`*/;
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

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
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`dev`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `current_promotion` AS select `p`.`product_id` AS `product_id`,`p`.`product_name` AS `product_name`,`p`.`retail_price` AS `retail_price`,`d`.`discount_price` AS `discount_price`,`hb`.`banner_image_url` AS `banner_image_url` from (((`event` `e` join `discount` `d` on((`e`.`event_id` = `d`.`event_id`))) join `product` `p` on((`d`.`product_id` = `p`.`product_id`))) join `home_banner` `hb` on((`p`.`product_id` = `hb`.`product_id`))) where ((`e`.`start_date` <= curdate()) and (curdate() <= `e`.`end_date`)) */;
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

-- Dump completed on 2021-03-25 21:17:00
