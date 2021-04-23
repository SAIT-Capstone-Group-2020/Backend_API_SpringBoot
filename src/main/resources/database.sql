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
-- Temporary view structure for view `curr_holiday`
--

DROP TABLE IF EXISTS `curr_holiday`;
/*!50001 DROP VIEW IF EXISTS `curr_holiday`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `curr_holiday` AS SELECT
                                           1 AS `banner_image_url`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `curr_home_banner`
--

DROP TABLE IF EXISTS `curr_home_banner`;
/*!50001 DROP VIEW IF EXISTS `curr_home_banner`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `curr_home_banner` AS SELECT
                                               1 AS `title`,
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
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
-- Table structure for table `event_banner`
--

DROP TABLE IF EXISTS `event_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_banner` (
                                `event_id` int NOT NULL,
                                `image_url` varchar(255) NOT NULL,
                                `comment` varchar(255) DEFAULT NULL,
                                PRIMARY KEY (`event_id`),
                                CONSTRAINT `event_banner_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `holiday_banner`
--

DROP TABLE IF EXISTS `holiday_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `holiday_banner` (
                                  `holiday_banner_id` int NOT NULL AUTO_INCREMENT,
                                  `banner_image_url` varchar(255) NOT NULL,
                                  `begin_date` date NOT NULL,
                                  `end_date` date NOT NULL,
                                  `comment` varchar(255) DEFAULT NULL COMMENT 'Comment for management',
                                  PRIMARY KEY (`holiday_banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COMMENT='test feature for holiday banner';
/*!40101 SET character_set_client = @saved_cs_client */;

/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`dev`@`%`*/ /*!50003 TRIGGER `holiday_banner_insert_trigger` BEFORE INSERT ON `holiday_banner` FOR EACH ROW begin

    declare count integer default 0;

    if NEW.begin_date is null or NEW.end_date is null or NEW.begin_date > NEW.end_date then

        signal sqlstate '45000' set message_text = 'begin date should <= end date and should not null';

    else

        select count(*) into count from holiday_banner where end_date >= NEW.begin_date;

        if count != 0 then

            signal sqlstate '45000' set message_text = 'date overlap with exist home banner';

        end if;

    end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`dev`@`%`*/ /*!50003 TRIGGER `holiday_banner_update_trigger` BEFORE UPDATE ON `holiday_banner` FOR EACH ROW begin

    declare count integer default 0;

    if NEW.begin_date is null or NEW.end_date is null or NEW.begin_date > NEW.end_date then

        signal sqlstate '45000' set message_text = 'begin date should <= end date and should not null';

    else

        select count(*) into count from holiday_banner where end_date >= NEW.begin_date and holiday_banner_id!=NEW.holiday_banner_id;

        if count != 0 then

            signal sqlstate '45000' set message_text = 'date overlap with exist home banner';

        end if;

    end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `home_banner`
--

DROP TABLE IF EXISTS `home_banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `home_banner` (
                               `home_banner_id` int NOT NULL AUTO_INCREMENT,
                               `begin_date` date NOT NULL,
                               `end_date` date NOT NULL,
                               `comment` varchar(255) DEFAULT NULL COMMENT 'Comment for management',
                               PRIMARY KEY (`home_banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COMMENT='test feature for home banner';
/*!40101 SET character_set_client = @saved_cs_client */;

/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`dev`@`%`*/ /*!50003 TRIGGER `home_banner_insert_trigger` BEFORE INSERT ON `home_banner` FOR EACH ROW begin

    declare count integer default 0;

    if NEW.begin_date is null or NEW.end_date is null or NEW.begin_date > NEW.end_date then

        signal sqlstate '45000' set message_text = 'begin date should <= end date and should not null';

    else

        select count(*) into count from home_banner where end_date >= NEW.begin_date;

        if count != 0 then

            signal sqlstate '45000' set message_text = 'date overlap with exist home banner';

        end if;

    end if;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`dev`@`%`*/ /*!50003 TRIGGER `home_banner_update_trigger` BEFORE UPDATE ON `home_banner` FOR EACH ROW begin
    declare count integer default 0;
    if NEW.begin_date is null or NEW.end_date is null or NEW.begin_date > NEW.end_date then
        signal sqlstate '45000' set message_text = 'begin date should <= end date and should not null';
    else
        select count(*)
        into count
        from home_banner
        where end_date >= NEW.begin_date and home_banner_id != NEW.home_banner_id;
        if count != 0 then
            signal sqlstate '45000' set message_text = 'date overlap with exist home banner';
        end if;
    end if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `home_banner_item`
--

DROP TABLE IF EXISTS `home_banner_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `home_banner_item` (
                                    `home_banner_item_id` int NOT NULL AUTO_INCREMENT,
                                    `home_banner_id` int NOT NULL,
                                    `banner_image_url` varchar(255) NOT NULL,
                                    `title` varchar(255) DEFAULT NULL,
                                    `description` varchar(255) DEFAULT NULL,
                                    PRIMARY KEY (`home_banner_item_id`),
                                    KEY `home_banner_id` (`home_banner_id`),
                                    CONSTRAINT `home_banner_item_ibfk_1` FOREIGN KEY (`home_banner_id`) REFERENCES `home_banner` (`home_banner_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1 COMMENT='test feature for home banner item';
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
-- Final view structure for view `curr_holiday`
--

/*!50001 DROP VIEW IF EXISTS `curr_holiday`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
    /*!50013 DEFINER=`dev`@`%` SQL SECURITY DEFINER */
    /*!50001 VIEW `curr_holiday` AS select `holiday_banner`.`banner_image_url` AS `banner_image_url` from `holiday_banner` where ((`holiday_banner`.`begin_date` <= curdate()) and (`holiday_banner`.`end_date` >= curdate())) order by `holiday_banner`.`begin_date` desc limit 1 */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `curr_home_banner`
--

/*!50001 DROP VIEW IF EXISTS `curr_home_banner`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
    /*!50013 DEFINER=`dev`@`%` SQL SECURITY DEFINER */
    /*!50001 VIEW `curr_home_banner` AS select `hbi`.`title` AS `title`,`hbi`.`description` AS `description`,`hbi`.`banner_image_url` AS `banner_image_url` from (`home_banner_item` `hbi` join `home_banner` `thb` on((`hbi`.`home_banner_id` = `thb`.`home_banner_id`))) where ((`thb`.`begin_date` <= curdate()) and (`thb`.`end_date` >= curdate())) */;
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
    /*!50001 VIEW `current_promotion` AS select `e`.`event_id` AS `event_id`,`e`.`description` AS `description`,`teb`.`image_url` AS `banner_image_url`,`p`.`product_id` AS `product_id`,`p`.`product_name` AS `product_name`,`p`.`retail_price` AS `retail_price`,`d`.`discount_price` AS `discount_price`,`p`.`image_url` AS `product_image_url` from (((`event` `e` join `discount` `d` on((`e`.`event_id` = `d`.`event_id`))) join `product` `p` on((`d`.`product_id` = `p`.`product_id`))) left join `event_banner` `teb` on((`teb`.`event_id` = `e`.`event_id`))) where ((`e`.`start_date` <= curdate()) and (curdate() <= `e`.`end_date`)) */;
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

-- Dump completed on 2021-04-07 21:42:12
