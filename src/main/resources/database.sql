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
                            `category_id` int(11) auto_increment NOT NULL,
                            `category_name` varchar(45) NOT NULL,
                            PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- 'Weight_type' table

DROP TABLE IF EXISTS `weight_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `weight_type` (
                               `weight_type_id` int(11) auto_increment NOT NULL,
                               `weight_type_name` varchar(45) NOT NULL,
                               PRIMARY KEY (`weight_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- `Product` table

DROP TABLE IF EXISTS product;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `product_id` int(11) auto_increment NOT NULL,
                           `product_name` varchar(45) NOT NULL,
                           `description` longtext,
                           `retail_price` float NOT NULL,
                           `active` BOOLEAN NOT NULL,
                           `image_url` varchar(255) DEFAULT NULL,
                           `category_id` int(11) NOT NULL,
                           `quantity` int(11) NOT NULL,
                           `weight_value` double not null,
                           `weight_type_id` int(11) NOT NULL,
                           `brand_name` varchar(45),
                           PRIMARY KEY (`product_id`),
                           KEY `fk_product_category_idx` (`category_id`),
                           CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES category (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                           KEY `fk_product_weight_idx` (`weight_type_id`),
                           CONSTRAINT `fk_product_weight` FOREIGN KEY (`weight_type_id`) REFERENCES `weight_type` (`weight_type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- 'Event' table
DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
                         `event_id` int(11) auto_increment NOT NULL,
                         `event_title` varchar(45),
                         `start_date` date not null,
                         `end_date` date not null,
                         `description` longtext,
                         PRIMARY KEY (`event_id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- 'Discount' table

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discount` (
                            `discount_id` int(11) auto_increment NOT NULL,
                            `product_id` int(11) NOT NULL,
                            `event_id` int(11) NOT NULL,
                            `discount_price` float not null,
                            `discount_limit` int(11) not null,
                            PRIMARY KEY (`discount_id`),
                            KEY `fk_discount_product_idx` (`product_id`),
                            CONSTRAINT `fk_discount_product_idx` FOREIGN KEY (`product_id`) REFERENCES product (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
                            KEY `fk_discount_event_idx` (`event_id`),
                            CONSTRAINT `fk_discount_event_idx` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



-- 'Orders' table
DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
                          `orders_id` int(11) auto_increment NOT NULL,
                          `order_date` date not null,
                          `paid_date` date,
                          `price_sum` double not null,
                          `gst_sum` double AS (TRUNCATE(price_sum * 0.05, 2)),
                          `sum` double AS (TRUNCATE(price_sum+gst_sum,2)),
                          `status` varchar(10) not null,
                          `order_email` varchar(55),
                          `order_phone` varchar(12),
                          `order_name` varchar(25),
                          PRIMARY KEY (`orders_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


-- 'Order_items' table

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
                               `order_items_id` int(11) auto_increment NOT NULL,
                               `total_price` double,
                               `gst_sum` double AS (TRUNCATE(total_price * 0.05,2)),
                               `sum` double AS (TRUNCATE(total_price+gst_sum, 2)),
                               `product_id` int(11) not null,
                               `qty` int(11),
                               `orders_id` int(11) not null,
                               PRIMARY KEY (`order_items_id`),
                               KEY `fk_order_item_product_idx` (`product_id`),
                               CONSTRAINT `fk_order_item_product_idx` FOREIGN KEY (`product_id`) REFERENCES product (`product_id`) ON DELETE NO ACTION ON UPDATE CASCADE,
                               KEY `fk_order_item_order_idx` (`orders_id`),
                               CONSTRAINT `fk_order_item_order_idx` FOREIGN KEY (`orders_id`) REFERENCES orders (`orders_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- 'users' table
-- need to change the type id to varchar(32) UUID

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
                        `email` varchar(20) NOT NULL,
                        `password` varchar(255) NOT NULL,
                        `active` boolean not null,
                        `user_name` varchar(32),
                        `uuid` varchar(36),
                        PRIMARY KEY (`email`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- 'role_info' table
--

DROP TABLE IF EXISTS `role_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role_info` (
                             `role_id` int(11) auto_increment NOT NULL,
                             `role` varchar(11) NOT NULL,
                             PRIMARY KEY (`role_id`)

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- 'roles' table
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                              `user_id` varchar(20),
                              `role_id` int(11),

                              KEY `fk_role_user_idx` (`user_id`),
                              CONSTRAINT `fk_role_user_idx` FOREIGN KEY (`user_id`) REFERENCES `user` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                              KEY `fk_role_role_idx` (`role_id`),
                              CONSTRAINT `fk_role_role_idx` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;


create table banner_type
(
    banner_type_id int auto_increment
        primary key,
    banner_type varchar(20) null
);

create table home_banner
(
    home_banner_id int auto_increment
        primary key,
    event_id int null,
    banner_image_url varchar(255) not null,
    banner_type int not null,
    constraint fk_home_banner_banner_idx
        foreign key (banner_type) references banner_type (banner_type_id)
);

