-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: hha
-- ------------------------------------------------------
-- Server version	5.7.32-log

-- THIS IS FOR THE TEST DATA

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

USE `hha` ;

-- Category
INSERT INTO `Category` (`name`) VALUES (`meat`);
INSERT INTO `Category` (`name`) VALUES (`produce`);
INSERT INTO `Category` (`name`) VALUES (`groceries`);
INSERT INTO `Category` (`name`) VALUES (`frozen`);
INSERT INTO `Category` (`name`) VALUES (`fruit`);

-- Weight_type
INSERT INTO `Weight_type` (`name`) VALUES (`lb`);
INSERT INTO `Weight_type` (`name`) VALUES (`g`);
INSERT INTO `Weight_type` (`name`) VALUES (`ea`);

-- Product
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Beef Finger Meat Whole Pack`, `Beef Finger Meat Whole Pack`, 8.99, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 1, 100, 1, 1, null);
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Pork Hock With Feet`, `Pork Hock With Feet`, 2.99, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 1, 100, 1, 1, null);
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Orange Navel`, `Orange Navel`, 1.29, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 1, 100, 1, 1, null);
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Orange Navel`, `Orange Navel`, 1.29, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 2, 100, 1, 1, null);
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Apple Red Delicious`, `Apple Red Delicious`, 1.99, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 2, 100, 1, 1, null);
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Soup Beef Flavour`, `Soup Beef Flavour`, 9.99, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 3, 100, 2, 284, `Quoc Viet`);
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Soup Chicken Flavour`, `Soup Beef Flavour`, 9.99, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 3, 100, 2, 284, `Quoc Viet`);
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Basa Steak`, `Basa Steak`, 4.49, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 4, 100, 2, 812.5, `Dolphins`);
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Greenshell Mussels H/S`, `Greenshell Mussels H/S`, 6.49, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 4, 100, 2, 454, `Talley's`);
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Banana`, `Banana`, 0.39, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 5, 100, 3, 1, null);
INSERT INTO `Product` (`name`, `description`, `retail_price`, `active`, `img_url`, `category`, `quantity`, `weight_value`, `weight_type`, `brand_name`) VALUES
(`Apple`, `Apple`, 0.59, 1, `https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png`, 5, 100, 3, 1, null);


-- Event_type
INSERT INTO `Event_type` (`name`) VALUES (`weekly`);
INSERT INTO `Event_type` (`name`) VALUES (`seasonal`);

-- Event
INSERT INTO `Event` (`event_title`, `start_date`, `end_date`, `event_type`, `description`) VALUES
(`2021 Feb 2nd week`, `2021-02-02`, `2021-02-14`, 1, `2021 Feb 2nd week`);
INSERT INTO `Event` (`event_title`, `start_date`, `end_date`, `event_type`, `description`) VALUES
(`2021 Feb 3rd week`, `2021-02-15`, `2021-02-21`, 1, `2021 Feb 3rd week`);

-- Discount
-- apple
INSERT INTO `Discount` (`product_id`, `event_id`, `discount_pice`, `limit`) VALUES
(11, 1, 0.3, 50);
-- banana
INSERT INTO `Discount` (`product_id`, `event_id`, `discount_pice`, `limit`) VALUES
(12, 2, 0.5, 50);


-- Admin
INSERT INTO `role_info` (`role`) VALUES (`admin`);

--
-- 'roles' table
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                              `user_id` varchar(32),
                              `role_id` int(11),

                              KEY `fk_role_user_idx` (`user_id`),
                              CONSTRAINT `fk_role_user_idx` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
                              KEY `fk_role_role_idx` (`role_id`),
                              CONSTRAINT `fk_role_role_idx` FOREIGN KEY (`role_id`) REFERENCES `role_info` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION

) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;