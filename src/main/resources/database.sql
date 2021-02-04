-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: hha
-- ------------------------------------------------------
-- Server version	5.7.32-log

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


-- need to check the meaning of 40101, 50503, engine, set, etc.

--
-- Table structure for table `category`
--

DROP SCHEMA IF EXISTS `hha` ;
-- -----------------------------------------------------
-- Schema hha
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hha` DEFAULT CHARACTER SET latin1 ;
USE `hha` ;

-- 'Category' table

DROP TABLE IF EXISTS category;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
                            `id` int(11) auto_increment NOT NULL,
                            `name` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- 'Weight_type' table

DROP TABLE IF EXISTS `weight_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weight_type` (
                            `id` int(11) auto_increment NOT NULL,
                            `name` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- `Product` table

DROP TABLE IF EXISTS product;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `id` int(11) auto_increment NOT NULL,
                           `name` varchar(45) NOT NULL,
                           `description` longtext,
                           `retail_price` float NOT NULL,
                           `active` BOOLEAN NOT NULL,
                           `image_url` varchar(45) DEFAULT NULL,
                           `category` int(11) NOT NULL,
                           `quantity` int(11) NOT NULL,
                           `weight_value` double not null,
                           `weight_type` int(11) NOT NULL,
                           `brand_name` varchar(45),
                           PRIMARY KEY (`id`),
                           KEY `fk_product_category_idx` (`category`),
                           CONSTRAINT `fk_product_category` FOREIGN KEY (`category`) REFERENCES category (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                           KEY `fk_product_weight_idx` (`weight_type`),
                           CONSTRAINT `fk_product_weight` FOREIGN KEY (`weight_type`) REFERENCES `weight_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- 'Event_type' table

DROP TABLE IF EXISTS `event_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_type` (
                               `id` int(11) auto_increment NOT NULL,
                               `name` varchar(45) NOT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- 'Event' table

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
                             `id` int(11) auto_increment NOT NULL,
                             `event_title` varchar(45),
                             `start_date` date not null,
                             `end_date` date not null,
                             `description` longtext,
                             `event_type` int(11) not null,
                             PRIMARY KEY (`id`),
                             KEY `fk_event_type_idx` (`event_type`),
                             CONSTRAINT `fk_event_type` FOREIGN KEY (`event_type`) REFERENCES `event_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- 'Discount' table

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
                             `id` int(11) auto_increment NOT NULL,
                             `product_id` int(11) NOT NULL,
                             `event_id` int(11) NOT NULL,
                             `discount_price` float not null,
                             `limit` int(11) not null,
                             PRIMARY KEY (`id`),
                             KEY `fk_discount_product_idx` (`product_id`),
                             CONSTRAINT `fk_discount_product_idx` FOREIGN KEY (`product_id`) REFERENCES product (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
                             KEY `fk_discount_event_idx` (`event_id`),
                             CONSTRAINT `fk_discount_event_idx` FOREIGN KEY (`event_id`) REFERENCES `event` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- 'Orders' table

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
                         `id` int(11) auto_increment NOT NULL,
                         `pickup_date` date not null,
                         `price_sum` double not null,
                         `paid_status` boolean not null,
                         `prepared` boolean not null,
                         `email` varchar(55),
                         `phone` varchar(12),
                         `name` varchar(25),
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- 'Order_items' table

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
                         `id` int(11) auto_increment NOT NULL,
                         `total_price` double,
                         `product_id` int(11) not null,
                         `qty` int(11),
                         `orders_id` int(11) not null,
                         PRIMARY KEY (`id`),
                         KEY `fk_order_item_product_idx` (`product_id`),
                         CONSTRAINT `fk_order_item_product_idx` FOREIGN KEY (`product_id`) REFERENCES product (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                         KEY `fk_order_item_order_idx` (`orders_id`),
                         CONSTRAINT `fk_order_item_order_idx` FOREIGN KEY (`orders_id`) REFERENCES orders (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- 'users' table
-- need to change the type id to varchar(32) UUID

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                            `id` int(11) auto_increment NOT NULL,
                            `email` varchar(20) NOT NULL,
                            `password` varchar(255) NOT NULL,
                            `active` boolean not null,
                            `name` varchar(32),
                            PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- 'role_info' table
--

DROP TABLE IF EXISTS `role_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_info` (
                            `id` int(11) auto_increment NOT NULL,
                            `role` varchar(11) NOT NULL,
                            PRIMARY KEY (`id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- 'roles' table
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                              `user_id` int(11),
                              `role_id` int(11),

                              KEY `fk_role_user_idx` (`user_id`),
                              CONSTRAINT `fk_role_user_idx` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                              KEY `fk_role_role_idx` (`role_id`),
                              CONSTRAINT `fk_role_role_idx` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;