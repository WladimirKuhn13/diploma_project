-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: trackingdb
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `additional_services`
--

DROP TABLE IF EXISTS `additional_services`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `additional_services` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `manager_id` bigint NOT NULL,
  `stock_employee_id` bigint NOT NULL,
  `order_id` bigint NOT NULL,
  `type_service` varchar(255) DEFAULT NULL,
  `number_of_services` int NOT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `additional_services`
--

LOCK TABLES `additional_services` WRITE;
/*!40000 ALTER TABLE `additional_services` DISABLE KEYS */;
INSERT INTO `additional_services` VALUES (1,37,26,5,'Термовакуумная обработка',2,'Услуги оплачены');
/*!40000 ALTER TABLE `additional_services` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `all_users`
--

DROP TABLE IF EXISTS `all_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `all_users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `all_users`
--

LOCK TABLES `all_users` WRITE;
/*!40000 ALTER TABLE `all_users` DISABLE KEYS */;
INSERT INTO `all_users` VALUES (16,'wladimir','$2a$10$0ZJyMs1dZ5CR2DKTsz3W9.7gqanD0PcrekYLazBdWiUNXAXIk0hLS','Владимир ','Фролов'),(17,'anna','$2a$10$qoVe81jhmscQ/kRkHynGHe14MM8mJQinRRI518Rsmu8KB/XGQGzYm','Анна','Краева'),(18,'andrew','$2a$10$Z4Hro2/gZ.XXIPhFSUoyD.5pts/hM0ZpIPNqi8jv3tAKdq9LFHAEu','Андрей','Дубов'),(19,'alex','$2a$10$j3MUuEf3/FtIamVcWfWEh.RLhKzFncnYSZGJITr2B34ByOqPIqkQi','Александр','Краснов'),(21,'krav','$2a$10$LJN93397OeIlCfRtCz8pOOpTVzR87SVl.6rHdL7g0ab2mUx829Ajm','Петр','Кравчук'),(22,'dronovl','$2a$10$u/pHcM4BEPf/RFCGVv2oQefSKiyOdaPHEak3O8pPMTDy1WKx82nya','Алексей','Дронов'),(23,'dandan','$2a$10$GF91Bl3CL6LADMmtG8hNnerJKHgBuMFEXTYFf2gVWuR6jrBcKNiWC','Денис','Любов'),(24,'nikolp','$2a$10$dR1XWXTEx2okNZs6lCLjVOq10f4dbnYXzEQ7CF8.etnlFeweEqHe2','Николай','Петров'),(25,'olegser','$2a$10$da31ZYhE0jZ/DUgIZ/hPZO18yb5E4mPrRVyl2bH5.n433oz25WJ7i','Олег','Серов'),(26,'lexasos','$2a$10$0jL5mXuI0nsteA6s0ICKAu8YwGQwZlFWgin72OVOq6pMLXQI8BE06','Алексей','Сосновский'),(27,'mixspir','$2a$10$WjkeOROXF/At8/Oz6gNmC.s/.0I9rtR3eTaCiwVa95TP/bfPXiwDy','Михаил','Спирин'),(28,'barkol','$2a$10$aZV2Kw0ExPjEXXcZ2wFWTeWly2Bf4kByLJJJXmthUxxEb/fYdrpUa','Николай','Барков'),(29,'dimatar','$2a$10$0VDwBaA8rCM3XYCmze.27eymUA0uzwynJzyF4b9u1LSt2TBDx/jWW','Дмитрий','Тарасов'),(30,'slava','$2a$10$PuC.V927rw50SWGVzY3GaeIueIELgkkzZKp9yhqUQKMvge068owV.','Вячеслав','Сидоров'),(31,'robrud','$2a$10$5HJpGRUViv6hutCnhKRcwO/XzG.0geGYXJuMZxwVEqH3Y9GtjMfzC','Роберт','Рудаков'),(32,'ruspan','$2a$10$psYA3j.htB7na2NGBLk54OqNqMVacxAWpra7xZrTli/LLIV4TSE8G','Руслан','Панин'),(33,'lexstupin','$2a$10$5egxVLzBGdriaoAzS1Lm2OOJeFAEuRVfq9kt24EzhugohEmag/nh2','Алексей','Ступин'),(34,'serpop','$2a$10$f7locJewEGmi9ofSAIFPq.CX3Ov9xyYvivyEpqV9GtkXwHTSBOAoG','Сергей','Попов'),(35,'vasiliy','$2a$10$s0JjgyIjewQE2xFV0g4BG.wzBBaBehCzSxlYL27YwWND0iTqldH7O','Василий','Бобров'),(36,'antonk','$2a$10$BgV5vdMQ.ibzLiVqfF5p2e0uEh6.HQIUyzZYaviLU96/Jifjyyyvm','Антон','Крупин'),(37,'krismos','$2a$10$NeaNKGEN0UBUAgcutKaAXeb4tXJYVMF6XYsTHgVxPjA1RQsOsU7HG','Кристина','Москвина'),(38,'lubza','$2a$10$jvo77Z08NEwZaVy/WwCatO4atUGUPfyAQhfN57hVdBArQ5OHXLebW','Любовь','Зайцева'),(39,'kormish','$2a$10$50PCE/siboaWprq4ZuZaxew2m/UJWR/yAOvr7zX0uplDSV/MyCvHe','Михаил','Корнев'),(40,'svetapro','$2a$10$p942TkjXKBzFSH9QtXdRreWT/Qy.CEsqzcOXsYSWfhm0Xeau2AExO','Светлана','Просветова'),(41,'kseniya','$2a$10$FJFtHIbdUsLee4rBXaVrp.toG.j0UMGn7voxSqSp8sgs/QmC2mmBq','Ксения','Федорова');
/*!40000 ALTER TABLE `all_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `all_users_copy`
--

DROP TABLE IF EXISTS `all_users_copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `all_users_copy` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(100) NOT NULL,
  `user_password` varchar(250) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `user_role` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `all_users_copy`
--

LOCK TABLES `all_users_copy` WRITE;
/*!40000 ALTER TABLE `all_users_copy` DISABLE KEYS */;
/*!40000 ALTER TABLE `all_users_copy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gas_mixtures_stock`
--

DROP TABLE IF EXISTS `gas_mixtures_stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gas_mixtures_stock` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gas_mixture` varchar(255) NOT NULL,
  `gas_cylinder_volume` int NOT NULL,
  `number_of_gas_cylinder` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `responsible_user_id` bigint NOT NULL,
  `responsible_user_name` varchar(255) DEFAULT NULL,
  `id_employee_fillig_area` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gas_mixtures_stock`
--

LOCK TABLES `gas_mixtures_stock` WRITE;
/*!40000 ALTER TABLE `gas_mixtures_stock` DISABLE KEYS */;
INSERT INTO `gas_mixtures_stock` VALUES (10,'5,5%CO2+33%N2+He',40,14,'Принято на складе',27,'Спирин М',27),(14,'5%CO2+55%N2+He',40,12,'Принято на анализе',27,'Спирин М',27),(18,'1,7%CO2+23,4%N2+He',40,12,'Принято на складе',22,'Дронов А',27),(19,'10%Ar+He',40,10,'Принято на складе',22,'Дронов А',27);
/*!40000 ALTER TABLE `gas_mixtures_stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history_of_changes`
--

DROP TABLE IF EXISTS `history_of_changes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history_of_changes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gas_order_id` bigint NOT NULL,
  `employee_id` bigint NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history_of_changes`
--

LOCK TABLES `history_of_changes` WRITE;
/*!40000 ALTER TABLE `history_of_changes` DISABLE KEYS */;
INSERT INTO `history_of_changes` VALUES (1,1,37,'2025-01-09 00:00:00.000000','Передано на участок заправки'),(2,1,27,'2025-01-09 00:00:00.000000','Принято на учатске заправки'),(3,1,31,'2025-01-09 00:00:00.000000','Передано в лабораторию'),(4,1,16,'2025-01-09 00:00:00.000000','Принято в лаборатории'),(5,1,17,'2025-01-09 00:00:00.000000','Передано на склад для отгрузки'),(6,1,22,'2025-01-09 00:00:00.000000','Принято на складе для отгрузки'),(7,1,26,'2025-01-09 00:00:00.000000','Отправлено клиенту'),(8,2,37,'2025-01-09 00:00:00.000000','Передано на участок подготовки'),(9,2,32,'2025-01-09 00:00:00.000000','Принято на участке подготовки'),(10,2,33,'2025-01-09 00:00:00.000000','Передано на участок заправки'),(11,2,27,'2025-01-09 00:00:00.000000','Принято на учатске заправки'),(12,2,29,'2025-01-09 00:00:00.000000','Передано в лабораторию'),(13,2,16,'2025-01-09 00:00:00.000000','Принято в лаборатории'),(14,2,17,'2025-01-09 00:00:00.000000','Передано на склад для отгрузки'),(15,2,22,'2025-01-09 00:00:00.000000','Принято на складе для отгрузки'),(16,2,26,'2025-01-09 00:00:00.000000','Отправлено клиенту'),(17,3,37,'2025-01-10 00:00:00.000000','Передано на склад'),(18,3,22,'2025-01-10 00:00:00.000000','Принято на складе'),(19,3,22,'2025-01-10 00:00:00.000000','Принято на складе для отгрузки'),(20,3,26,'2025-01-10 00:00:00.000000','Отправлено клиенту'),(21,4,37,'2025-01-10 00:00:00.000000','Передано на приемку баллонов'),(22,4,22,'2025-01-10 00:00:00.000000','Принято на приемке баллонов'),(23,4,25,'2025-01-10 00:00:00.000000','Принято на приемке баллонов'),(24,4,25,'2025-01-10 00:00:00.000000','Передано на участок заправки'),(25,4,27,'2025-01-10 00:00:00.000000','Принято на учатске заправки'),(26,4,29,'2025-01-10 00:00:00.000000','Передано в лабораторию'),(27,4,16,'2025-01-10 00:00:00.000000','Принято в лаборатории'),(28,4,17,'2025-01-10 00:00:00.000000','Передано на склад для отгрузки'),(29,4,22,'2025-01-10 00:00:00.000000','Принято на складе для отгрузки'),(30,4,26,'2025-01-10 00:00:00.000000','Отправлено клиенту'),(31,5,37,'2025-01-10 00:00:00.000000','Передано на приемку баллонов'),(32,5,22,'2025-01-10 00:00:00.000000','Принято на приемке баллонов'),(33,5,26,'2025-01-10 00:00:00.000000','Передано менеджеру'),(34,5,37,'2025-01-10 00:00:00.000000','Принято менеджером'),(35,5,37,'2025-01-10 00:00:00.000000','Ожидание оплаты доп.услуг'),(36,5,37,'2025-01-10 00:00:00.000000','Дополнительные услуги оплачены'),(37,5,37,'2025-01-10 00:00:00.000000','Передано на участок подготовки'),(38,5,32,'2025-01-10 00:00:00.000000','Принято на участке подготовки'),(39,5,32,'2025-01-10 00:00:00.000000','Передано на участок заправки'),(40,5,27,'2025-01-10 00:00:00.000000','Принято на учатске заправки'),(41,5,29,'2025-01-10 00:00:00.000000','Передано в лабораторию'),(42,5,16,'2025-01-10 00:00:00.000000','Принято в лаборатории'),(43,5,17,'2025-01-10 00:00:00.000000','Передано на склад для отгрузки'),(44,5,22,'2025-01-10 00:00:00.000000','Принято на складе для отгрузки'),(45,5,26,'2025-01-10 00:00:00.000000','Отправлено клиенту'),(46,6,37,'2025-01-12 00:00:00.000000','Передано на участок заправки');
/*!40000 ALTER TABLE `history_of_changes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `manager_id` bigint NOT NULL,
  `client` varchar(255) NOT NULL,
  `creation_date` date NOT NULL,
  `deadline` date NOT NULL,
  `gas_mixture` varchar(255) NOT NULL,
  `gas_cylinder_volume` int NOT NULL,
  `number_of_gas_cylinder` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type_order` varchar(255) DEFAULT NULL,
  `responsible_user_name` varchar(255) DEFAULT NULL,
  `responsible_user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (6,37,'ООО ТехноГаз','2025-01-12','2025-01-26','N2 ПНГ',40,12,'Передано на участок заправки','Оборотные баллоны','Москвина К',37);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders_archive`
--

DROP TABLE IF EXISTS `orders_archive`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders_archive` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `gas_order_id` bigint NOT NULL,
  `manager_id` bigint NOT NULL,
  `client` varchar(255) DEFAULT NULL,
  `creation_date` date NOT NULL,
  `deadline` date NOT NULL,
  `gas_mixture` varchar(255) DEFAULT NULL,
  `gas_cylinder_volume` int NOT NULL,
  `number_of_gas_cylinder` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type_order` varchar(255) DEFAULT NULL,
  `responsible_user_name` varchar(255) DEFAULT NULL,
  `responsible_user_id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders_archive`
--

LOCK TABLES `orders_archive` WRITE;
/*!40000 ALTER TABLE `orders_archive` DISABLE KEYS */;
INSERT INTO `orders_archive` VALUES (1,1,37,'ООО ГазЗаЧас','2025-01-09','2025-01-23','Воздух синтетический',40,12,'Отправлено клиенту','Оборотные баллоны','Сосновский А',26),(2,2,37,'ООО ТехноГаз','2025-01-09','2025-01-23','N2 ПНГ',40,10,'Отправлено клиенту','Новые баллоны','Сосновский А',26),(3,3,37,'ООО ОРГ','2025-01-10','2025-01-24','5,5%CO2+33%N2+He',40,10,'Отправлено клиенту','Оборотные баллоны','Сосновский А',26),(4,4,37,'ООО ГазЗаЧас','2025-01-10','2025-01-24','10%Ar+N2',40,7,'Отправлено клиенту','Баллоны клиента','Сосновский А',26),(5,5,37,'ООО МеталлГаз','2025-01-10','2025-01-24','1%O2+H2',5,3,'Отправлено клиенту','Баллоны клиента','Сосновский А',26);
/*!40000 ALTER TABLE `orders_archive` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_MANAGER'),(3,'ROLE_STOCK'),(4,'ROLE_PREPARATION'),(5,'ROLE_FILLING'),(6,'ROLE_LAB'),(8,'ROLE_STOCK_HEAD'),(9,'ROLE_PREPARATION_HEAD'),(10,'ROLE_FILLING_HEAD'),(11,'ROLE_LAB_HEAD'),(12,'ROLE_MANAGER_HEAD');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `user_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `all_users` (`id`),
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (38,2),(39,2),(40,2),(41,2),(23,3),(24,3),(25,3),(26,3),(33,4),(34,4),(35,4),(36,4),(28,5),(29,5),(30,5),(31,5),(17,6),(18,6),(19,6),(21,6),(22,8),(32,9),(27,10),(16,11),(37,12);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-12 21:53:18
