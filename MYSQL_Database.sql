-- MySQL dump 10.13  Distrib 5.7.35, for Linux (x86_64)
--
-- Host: localhost    Database: projectZ
-- ------------------------------------------------------
-- Server version	5.7.35-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `acc_id` varchar(25) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `phno` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `reg_date_time` datetime DEFAULT NULL,
  `password` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`acc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES ('adreddy','reddy','1969-08-04','male',9876787656,'reddyrgukt@gmail.com','2020-06-08 09:51:18','reddy123'),('adyugandhar','yugandhar','2000-05-27','male',1234567890,'yuga@gmail.com','2021-03-06 17:06:51','Yuga@123'),('clkonappa','konappa','1980-01-19','male',9876543210,'lkjhgfdsa@gmail.com','2020-06-08 08:25:21','konappa123'),('clpraveen','clpraveen','2000-01-10','male',9876543210,'clnpkr@gmail.com','2021-03-06 17:09:49','Praveen@123'),('ufayugandhar','yugandhar','2000-05-27','male',1234567890,'sfhgfj@gmail.com','2021-02-27 16:20:14','Yugandhar123'),('upgsuresh','suresh','1997-06-04','male',9678987678,'wsdcvghyujm@gmail.com','2020-06-10 20:58:16','suresh123'),('ursramesh','ramesh','1996-11-23','male',6785324563,'uhgfrdsw@gmail.com','2020-06-11 11:27:59','ramesh123'),('uugakash','akash','2000-05-27','male',8748972309,'dkjbkjdnskj@gmail.com','2021-02-27 09:59:26','12345qwert'),('uughemanth','hemanth','2000-05-27','male',1234567890,'r161860@gmail.com','2021-02-24 13:46:38','hemanth123'),('uugmahesh','mahesh','2000-08-19','male',1234567890,'qwertyuiop@gmail.com','2020-06-07 14:30:21','mahesh123'),('uugmanikanta','manikanta','2000-04-27','male',9875232389,'asdfghjk@gmail.com','2020-06-07 18:39:51','manikanta123'),('uugpraveenyadav','praveenyadav','2000-05-03','male',9999999999,'sankaryadav@gmail.com','2021-03-06 17:35:26','Rgukt@123'),('uugpremsai','premsai','2000-05-27','male',9758279877,'dkjbkjdnskj@gmail.com','2021-02-27 10:00:44','1234qwer5t'),('uugsankar','sankar','2000-05-27','male',1234567890,'rdfgf@gmail.com','2021-03-06 16:42:31','Sankar@123'),('uugyugandhar','yugandhar','2000-05-27','male',1234567890,'wedf@gmail.com','2021-03-06 16:45:42','Yugandhar@123');
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `avail_books`
--

DROP TABLE IF EXISTS `avail_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `avail_books` (
  `book_id` int(11) NOT NULL,
  `book_id_offset` int(11) NOT NULL,
  PRIMARY KEY (`book_id`,`book_id_offset`),
  CONSTRAINT `avail_books_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`),
  CONSTRAINT `avail_books_ibfk_2` FOREIGN KEY (`book_id`, `book_id_offset`) REFERENCES `total_books` (`book_id`, `book_id_offset`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `avail_books`
--

LOCK TABLES `avail_books` WRITE;
/*!40000 ALTER TABLE `avail_books` DISABLE KEYS */;
INSERT INTO `avail_books` VALUES (1,1),(1,2),(1,3),(2,1),(2,2),(2,3),(3,1),(3,2),(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),(5,8),(5,9),(5,10),(5,11),(5,12),(7,1),(7,2),(8,1),(9,3),(9,4),(9,5),(9,6),(9,7),(12,1),(12,2),(12,3),(12,4),(12,5),(12,6);
/*!40000 ALTER TABLE `avail_books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `book_id` int(11) NOT NULL,
  `book_name` varchar(40) DEFAULT NULL,
  `book_author` varchar(30) DEFAULT NULL,
  `book_edition` int(11) DEFAULT NULL,
  `copies` int(11) DEFAULT NULL,
  `used` int(11) DEFAULT NULL,
  `latest_used` datetime DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (1,'Algorithms','coremen',1,3,2,'2021-03-06 16:50:04'),(2,'C Programming','Dennis Ritchie',1,3,0,'2020-06-15 00:00:00'),(3,'Operating System','Tanenbum',1,2,3,'2021-02-27 15:56:52'),(4,'Computer Networks','Tanenbum',1,1,3,'2021-03-06 17:39:39'),(5,'Data Structures','Coremen',1,12,0,'2020-06-15 00:00:00'),(7,'Digital Logic Design','Morris Mano',5,2,0,'2020-06-15 00:00:00'),(8,'Computer Organisation and Architecture','William Stallings',8,1,1,'2021-02-27 15:58:20'),(9,'Theory of Computation','Michael Sipser',3,7,0,'2021-02-27 15:27:46'),(12,'cryptograpy network system','William Stallings',6,6,0,'2021-02-27 16:04:55');
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_rules`
--

DROP TABLE IF EXISTS `issue_rules`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue_rules` (
  `acc_type` varchar(2) DEFAULT NULL,
  `max_books` int(11) DEFAULT NULL,
  `duration` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_rules`
--

LOCK TABLES `issue_rules` WRITE;
/*!40000 ALTER TABLE `issue_rules` DISABLE KEYS */;
INSERT INTO `issue_rules` VALUES ('ug',2,600),('pg',4,2592000),('rs',6,7776000),('fa',10,15552000);
/*!40000 ALTER TABLE `issue_rules` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issued`
--

DROP TABLE IF EXISTS `issued`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issued` (
  `isd_id` int(11) NOT NULL,
  `acc_id` varchar(25) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_id_offset` int(11) DEFAULT NULL,
  `req_date_time` datetime DEFAULT NULL,
  `isd_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`isd_id`),
  KEY `acc_id` (`acc_id`),
  KEY `book_id` (`book_id`,`book_id_offset`),
  CONSTRAINT `issued_ibfk_1` FOREIGN KEY (`acc_id`) REFERENCES `account` (`acc_id`),
  CONSTRAINT `issued_ibfk_2` FOREIGN KEY (`book_id`, `book_id_offset`) REFERENCES `total_books` (`book_id`, `book_id_offset`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issued`
--

LOCK TABLES `issued` WRITE;
/*!40000 ALTER TABLE `issued` DISABLE KEYS */;
/*!40000 ALTER TABLE `issued` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penalty`
--

DROP TABLE IF EXISTS `penalty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `penalty` (
  `penalty_id` int(11) DEFAULT NULL,
  `acc_id` varchar(25) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_id_offset` int(11) DEFAULT NULL,
  `req_date_time` datetime DEFAULT NULL,
  `isd_date_time` datetime DEFAULT NULL,
  `return_date_time` datetime DEFAULT NULL,
  `fee` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penalty`
--

LOCK TABLES `penalty` WRITE;
/*!40000 ALTER TABLE `penalty` DISABLE KEYS */;
/*!40000 ALTER TABLE `penalty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `request` (
  `req_id` int(11) NOT NULL,
  `acc_id` varchar(25) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `book_id_offset` int(11) DEFAULT NULL,
  `req_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`req_id`),
  KEY `acc_id` (`acc_id`),
  KEY `book_id` (`book_id`,`book_id_offset`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`acc_id`) REFERENCES `account` (`acc_id`),
  CONSTRAINT `request_ibfk_2` FOREIGN KEY (`book_id`, `book_id_offset`) REFERENCES `total_books` (`book_id`, `book_id_offset`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (1,'uugpraveenyadav',4,1,'2021-03-06 17:39:58'),(2,'uugmahesh',9,1,'2021-03-06 17:43:31'),(3,'uugmahesh',9,2,'2021-03-06 17:43:36');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserve` (
  `res_id` int(11) NOT NULL,
  `acc_id` varchar(25) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `res_date_time` datetime DEFAULT NULL,
  PRIMARY KEY (`res_id`),
  KEY `acc_id` (`acc_id`),
  KEY `book_id` (`book_id`),
  CONSTRAINT `reserve_ibfk_1` FOREIGN KEY (`acc_id`) REFERENCES `account` (`acc_id`),
  CONSTRAINT `reserve_ibfk_2` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `total_books`
--

DROP TABLE IF EXISTS `total_books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `total_books` (
  `book_id` int(11) NOT NULL,
  `book_id_offset` int(11) NOT NULL,
  PRIMARY KEY (`book_id`,`book_id_offset`),
  CONSTRAINT `total_books_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `total_books`
--

LOCK TABLES `total_books` WRITE;
/*!40000 ALTER TABLE `total_books` DISABLE KEYS */;
INSERT INTO `total_books` VALUES (1,1),(1,2),(1,3),(2,1),(2,2),(2,3),(3,1),(3,2),(4,1),(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),(5,7),(5,8),(5,9),(5,10),(5,11),(5,12),(7,1),(7,2),(8,1),(9,1),(9,2),(9,3),(9,4),(9,5),(9,6),(9,7),(12,1),(12,2),(12,3),(12,4),(12,5),(12,6);
/*!40000 ALTER TABLE `total_books` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-14 19:07:52
