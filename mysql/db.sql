-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.28 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE TABLE `user` (
	`id` INT(10) NOT NULL AUTO_INCREMENT,
	`enabled` TINYINT(3) NOT NULL,
	`password` VARCHAR(60) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`user_name` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`role` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=6
;

-- Dumping data for table demo.user: ~3 rows (approximately)
DELETE FROM `user`;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `enabled`, `password`, `user_name`, `role`) VALUES
	(1, 1, '$2a$10$ZLrzhcQVjHw8Yxp10HXBWO2C1pth5BpHUl7UupEGqH0.ODUDntcW2', 'demo', 'ROLE_ADMIN'),
	(2, 1, '$2a$10$ZLrzhcQVjHw8Yxp10HXBWO2C1pth5BpHUl7UupEGqH0.ODUDntcW2', 'demo1', 'ROLE_USER'),
	(5, 1, '$2a$10$ZLrzhcQVjHw8Yxp10HXBWO2C1pth5BpHUl7UupEGqH0.ODUDntcW2', 'demo3', 'ROLE_SUPER_ADMIN');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
