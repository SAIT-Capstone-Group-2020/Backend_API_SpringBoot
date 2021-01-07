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

--
-- Table structure for table `category`
--

DROP SCHEMA IF EXISTS `hha` ;
-- -----------------------------------------------------
-- Schema hha
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hha` DEFAULT CHARACTER SET latin1 ;
USE `hha` ;

-- 'category' table

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
                            `id` int(11) NOT NULL,
                            `name` varchar(45) NOT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*INSERT INTO `category` VALUES (1,'Frozen','FROZ','Product in Frozen','frozenDept.png'),(2,'Meat','MEAT','Product in Meat Department','meat.png');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- `product` table
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `id` int(11) NOT NULL,
                           `name` varchar(45) NOT NULL,
                           `description` longtext,
                           `price` float NOT NULL,
                           'active' BOOLEAN NOT NULL,
                           `image` varchar(45) DEFAULT NULL,
                           `category` int(11) NOT NULL,
                           `inventory` int(11) NOT NULL,
                           `last_updated` date not null,
                           'expired_date' date,
                           PRIMARY KEY (`id`),
                           KEY `fk_product_category_idx` (`category`),
                           CONSTRAINT `fk_product_category` FOREIGN KEY (`category`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*INSERT INTO `product` VALUES (1,'Frozen Crap','FCRA','Frozen Crap',10.99,'image.png',1),(2,'Frozen Crap','FCRA','Frozen Crap',10.99,'image.png',1),(3,'Beef finger meat','BEFM','Beef finger meat',10.99,'image.png',2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

--
-- 'item' table
-- salesPrice updated(triggered) on server side daily & when promotion table updated

DROP TABLE IF EXISTS `sales_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sales_item` (
                           `id` int(11) NOT NULL,
                           `product_id` int(11) NOT NULL,
                           `sales_price` float not null,
                           PRIMARY KEY (`id`),
                           KEY `fk_item_productId_idx` (`product_id`),
                           CONSTRAINT `fk_item_productId_idx` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- 'promotion' table
-- should decrease the limit when order was made

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
                        `id` int(11) NOT NULL,
                        `product_id` int(11) NOT NULL,
                        `start_date` date not null,
                        `end_date` date not null,
                        `discount` float not null,
                        `limit` int(11) not null,
                        PRIMARY KEY (`id`),
                        KEY `fk_promotion_productId_idx` (`product_id`),
                        CONSTRAINT `fk_promotion_productId_idx` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;


--
-- 'order' table
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
                             `id` int(11) NOT NULL,
                             `order_date` date NOT NULL,
                             `pickup_date` date NOT NULL,
                             `status` varchar(10),
                             `total` float not null,
                             `payment_id` int(11) not null,
                             `item_id` int(11) not null,
                             PRIMARY KEY (`id`),
                             KEY `fk_order_itemId_idx` (`item_id`),
                             CONSTRAINT `fk_order_itemId_idx` FOREIGN KEY (`item_id`) REFERENCES `sales_item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
                             /*KEY `fk_order_paymentId_idx` (`payment_id`),
                             CONSTRAINT `fk_order_paymentId_idx` FOREIGN KEY (`payment_id`) REFERENCES `payment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION*/
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-27 19:55:11


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

--
-- Table structure for table `category`
--

DROP SCHEMA IF EXISTS `hha` ;
-- -----------------------------------------------------
-- Schema hha
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hha` DEFAULT CHARACTER SET latin1 ;
USE `hha` ;

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
                            `id` int(11) NOT NULL,
                            `name` varchar(45) NOT NULL,
                            `code` varchar(4) NOT NULL,
                            `description` longtext,
                            `image` varchar(45) NOT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Frozen','FROZ','Product in Frozen','frozenDept.png'),(2,'Meat','MEAT','Product in Meat Department','meat.png');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `id` int(11) NOT NULL,
                           `name` varchar(45) NOT NULL,
                           `code` varchar(4) NOT NULL,
                           `descrription` longtext,
                           `price` float NOT NULL,
                           `image` varchar(45) DEFAULT NULL,
                           `category` int(11) NOT NULL,
                           PRIMARY KEY (`id`),
                           KEY `fk_product_category_idx` (`category`),
                           CONSTRAINT `fk_product_category` FOREIGN KEY (`category`) REFERENCES `category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Frozen Crap','FCRA','Frozen Crap',10.99,'image.png',1),(2,'Frozen Crap','FCRA','Frozen Crap',10.99,'image.png',1),(3,'Beef finger meat','BEFM','Beef finger meat',10.99,'image.png',2);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-27 19:55:11
