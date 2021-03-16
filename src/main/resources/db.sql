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

/*
-- Category
INSERT INTO `category` (`category_name`) VALUES ('meat');
INSERT INTO `category` (`category_name`) VALUES ('produce');
INSERT INTO `category` (`category_name`) VALUES ('groceries');
INSERT INTO `category` (`category_name`) VALUES ('frozen');
INSERT INTO `category` (`category_name`) VALUES ('fruit');

-- Weight_type
INSERT INTO `weight_type` (`weight_type_name`) VALUES ('lb');
INSERT INTO `weight_type` (`weight_type_name`) VALUES ('g');
INSERT INTO `weight_type` (`weight_type_name`) VALUES ('ea');

-- Product
INSERT INTO `product` (`product_name`, `description`, `retail_price`, `active`, `image_url`, `category_id`, `quantity`, `weight_value`, `weight_type_id`, `brand_name`) VALUES
('Beef Finger Meat Whole Pack', 'Beef Finger Meat Whole Pack', 8.99, 1, 'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png', 1, 100, 1, 1, null);
INSERT INTO `product` (`product_name`, `description`, `retail_price`, `active`, `image_url`, `category_id`, `quantity`, `weight_value`, `weight_type_id`, `brand_name`) VALUES
('Pork Hock With Feet', 'Pork Hock With Feet', 2.99, 1, 'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png', 1, 100, 1, 1, null);
INSERT INTO `product` (`product_name`, `description`, `retail_price`, `active`, `image_url`, `category_id`, `quantity`, `weight_value`, `weight_type_id`, `brand_name`) VALUES
('Orange Navel', 'Orange Navel', 1.29, 1, 'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png', 1, 100, 1, 1, null);
INSERT INTO `product` (`product_name`, `description`, `retail_price`, `active`, `image_url`, `category_id`, `quantity`, `weight_value`, `weight_type_id`, `brand_name`) VALUES
('Apple Red Delicious', 'Apple Red Delicious', 1.99, 1, 'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png', 2, 100, 1, 1, null);
INSERT INTO `product` (`product_name`, `description`, `retail_price`, `active`, `image_url`, `category_id`, `quantity`, `weight_value`, `weight_type_id`, `brand_name`) VALUES
('Soup Beef Flavour', 'Soup Beef Flavour', 9.99, 1, 'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png', 3, 100, 284, 2, 'Quoc Viet');
INSERT INTO `product` (`product_name`, `description`, `retail_price`, `active`, `image_url`, `category_id`, `quantity`, `weight_value`, `weight_type_id`, `brand_name`) VALUES
('Soup Chicken Flavour', 'Soup Beef Flavour', 9.99, 1, 'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png', 3, 100, 284, 2, 'Quoc Viet');
INSERT INTO `product` (`product_name`, `description`, `retail_price`, `active`, `image_url`, `category_id`, `quantity`, `weight_value`, `weight_type_id`, `brand_name`) VALUES
('Basa Steak', 'Basa Steak', 4.49, 1, 'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png', 4, 100, 812.5, 2, 'Dolphins');
INSERT INTO `product` (`product_name`, `description`, `retail_price`, `active`, `image_url`, `category_id`, `quantity`, `weight_value`, `weight_type_id`, `brand_name`) VALUES
('Greenshell Mussels H/S', 'Greenshell Mussels H/S', 6.49, 1, 'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png', 4, 100, 454, 2, 'Talley\'s');
INSERT INTO `product` (`product_name`, `description`, `retail_price`, `active`, `image_url`, `category_id`, `quantity`, `weight_value`, `weight_type_id`, `brand_name`) VALUES
('Orange', 'Orange', 0.39, 1, 'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png', 5, 100, 3, 1, null);
INSERT INTO `product` (`product_name`, `description`, `retail_price`, `active`, `image_url`, `category_id`, `quantity`, `weight_value`, `weight_type_id`, `brand_name`) VALUES
('Apple', 'Apple', 0.59, 1, 'https://sait-capstone.s3-us-west-2.amazonaws.com/dev_image.png', 5, 100, 3, 1, null);


-- Event_type
INSERT INTO `event_type` (`event_type_name`) VALUES ('weekly');
INSERT INTO `event_type` (`event_type_name`) VALUES ('seasonal');

-- Event
INSERT INTO `event` (`event_title`, `start_date`, `end_date`, `description`) VALUES
('2021 Mar 1st week', '2021-03-01', '2021-03-07 23:59:59', '2021 Mar 1st week');
INSERT INTO `event` (`event_title`, `start_date`, `end_date`, `description`) VALUES
('2021 Mar 2nd week', '2021-03-08', '2021-03-14 23:59:59', '2021 Mar 2nd week');
INSERT INTO `event` (`event_title`, `start_date`, `end_date`, `description`) VALUES
('2021 Mar 3rd week', '2021-03-15', '2021-02-21 23:59:59', '2021 Mar 3rd week');
INSERT INTO `event` (`event_title`, `start_date`, `end_date`, `description`) VALUES
('2021 Mar 4th week', '2021-03-22', '2021-03-28 23:59:59', '2021 Mar 4th week');


-- Discount
INSERT INTO `discount` (`product_id`, `event_id`, `discount_price`, `limit`) VALUES
(9, 1, 0.3, 50);
INSERT INTO `discount` (`product_id`, `event_id`, `discount_price`, `limit`) VALUES
(10, 2, 0.5, 50);
INSERT INTO `discount` (`product_id`, `event_id`, `discount_price`, `limit`) VALUES
(9, 3, 0.2, 50);
INSERT INTO `discount` (`product_id`, `event_id`, `discount_price`, `limit`) VALUES
(10, 4, 0.5, 50);


-- Admin
INSERT INTO `role_info` (`role`) VALUES ('ROLE_ADMIN');

DELETE FROM product WHERE product_id = 23 OR product_id = 24;
UPDATE product SET category_id = 2 WHERE product_id = 3;
UPDATE event set end_date = '2099-12-31 23:59:59' WHERE event_id = 5;

INSERT INTO `discount` (`product_id`, `event_id`, `discount_price`, `discount_limit`) VALUES
(9, 5, 0.3, 50);
INSERT INTO `discount` (`product_id`, `event_id`, `discount_price`, `discount_limit`) VALUES
(40, 5, 1.3, 50);
INSERT INTO `discount` (`product_id`, `event_id`, `discount_price`, `discount_limit`) VALUES
(41, 5, 66.3, 50);
INSERT INTO `discount` (`product_id`, `event_id`, `discount_price`, `discount_limit`) VALUES
(42, 5, 4.3, 50);
 */
