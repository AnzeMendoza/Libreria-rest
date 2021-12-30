-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: libreria_rest
-- ------------------------------------------------------
-- Server version	8.0.25

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
-- Table structure for table `autor`
--

DROP TABLE IF EXISTS `autor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `autor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `alta` bit(1) DEFAULT NULL,
  `nombre` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autor`
--

LOCK TABLES `autor` WRITE;
/*!40000 ALTER TABLE `autor` DISABLE KEYS */;
INSERT INTO `autor` VALUES (1,_binary '','b0e0c0fa7'),(2,_binary '','Nombre Dos'),(3,_binary '','Nombre Dos'),(4,_binary '','Nombre cinco');
/*!40000 ALTER TABLE `autor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `alta` bit(1) DEFAULT NULL,
  `apellido` varchar(64) DEFAULT NULL,
  `documento` bigint DEFAULT NULL,
  `nombre` varchar(64) DEFAULT NULL,
  `telefono` varchar(64) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `user_password` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,_binary '','PruebaModificar',654252134,'PruebaModificar','PruebaModificar',NULL,''),(2,_binary '',NULL,123456789,'Nombre Uno',NULL,NULL,''),(3,_binary '','Apellido diez',34343434,'Nombre diez','101010101','user diez','password diez'),(4,_binary '','Apellido Uno',35343534,'nombre G','34152','usuario@mail.com','$argon2id$v=19$m=1024,t=1,p=1$ROqFprwxPOiLHzJe7p+gsg$ixWxFu4TNeN6HSbOjrc5QBvp+ZLaJHt+72bvZd/yXKk'),(5,_binary '','Apellido Uno',3577773,'nombre G','3415245','mail@mail.com','$argon2id$v=19$m=1024,t=1,p=1$Dlmrn1QYyOK14ffwv5AkGw$HANynU104gJ8Vg9GqiedsSsSDbzkg6VE0GsxHT9iM/M');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `editorial`
--

DROP TABLE IF EXISTS `editorial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `editorial` (
  `id` int NOT NULL AUTO_INCREMENT,
  `alta` bit(1) DEFAULT NULL,
  `nombre` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `editorial`
--

LOCK TABLES `editorial` WRITE;
/*!40000 ALTER TABLE `editorial` DISABLE KEYS */;
INSERT INTO `editorial` VALUES (1,_binary '','16f178a2b'),(2,_binary '','santillana');
/*!40000 ALTER TABLE `editorial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libro`
--

DROP TABLE IF EXISTS `libro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libro` (
  `id` int NOT NULL AUTO_INCREMENT,
  `alta` bit(1) DEFAULT NULL,
  `anio` int DEFAULT NULL,
  `ejemplares` int DEFAULT NULL,
  `ejemplares_prestados` int DEFAULT NULL,
  `ejemplares_restantes` int DEFAULT NULL,
  `isbn` bigint DEFAULT NULL,
  `titulo` varchar(64) DEFAULT NULL,
  `fk_autor` int DEFAULT NULL,
  `fk_editorial` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh6og8ktfow7eeyy9tuhrn9h8v` (`fk_autor`),
  KEY `FKffymuss78uiv174mmn11r1uio` (`fk_editorial`),
  CONSTRAINT `FKffymuss78uiv174mmn11r1uio` FOREIGN KEY (`fk_editorial`) REFERENCES `editorial` (`id`),
  CONSTRAINT `FKh6og8ktfow7eeyy9tuhrn9h8v` FOREIGN KEY (`fk_autor`) REFERENCES `autor` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libro`
--

LOCK TABLES `libro` WRITE;
/*!40000 ALTER TABLE `libro` DISABLE KEYS */;
INSERT INTO `libro` VALUES (1,_binary '\0',2020,20,10,10,12345678,'1',1,1),(2,_binary '',2021,180,20,160,123454,'física',1,1),(3,_binary '',2021,180,20,160,123455,'Titulo uno',1,1),(4,_binary '',2021,180,20,160,1234555,'Titulo uno',1,1),(5,_binary '',2021,180,20,160,12345555,'Titulo uno',1,1);
/*!40000 ALTER TABLE `libro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prestamo`
--

DROP TABLE IF EXISTS `prestamo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prestamo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `alta` bit(1) DEFAULT NULL,
  `fecha_devolucion` date DEFAULT NULL,
  `fecha_prestamo` date DEFAULT NULL,
  `fk_cliente` int DEFAULT NULL,
  `fk_libro` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKp3p9qs1uty5map7j6e9jk6qq6` (`fk_cliente`),
  KEY `FKj4i451ppoyl897vcidgordx2p` (`fk_libro`),
  CONSTRAINT `FKj4i451ppoyl897vcidgordx2p` FOREIGN KEY (`fk_libro`) REFERENCES `libro` (`id`),
  CONSTRAINT `FKp3p9qs1uty5map7j6e9jk6qq6` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prestamo`
--

LOCK TABLES `prestamo` WRITE;
/*!40000 ALTER TABLE `prestamo` DISABLE KEYS */;
INSERT INTO `prestamo` VALUES (1,_binary '','2021-12-25','2021-12-15',1,1),(2,_binary '','2021-12-30','2021-11-30',1,1),(3,_binary '','2021-12-30','2021-11-30',1,1),(4,_binary '','2021-12-30','2021-11-30',1,1),(5,_binary '','2021-12-30','2021-11-30',1,1),(6,_binary '','2021-12-30','2021-11-30',1,1),(7,_binary '','2021-12-30','2021-11-30',1,1),(8,_binary '','2022-02-10','2021-11-30',1,1),(9,_binary '','2022-02-10','2021-12-01',1,1),(10,_binary '','2022-02-10','2021-12-01',1,1),(11,_binary '','2022-02-10','2021-12-01',1,1),(12,_binary '','2022-02-10','2021-12-01',1,1),(13,_binary '','2022-02-10','2021-12-01',1,1),(14,_binary '','2022-02-10','2021-12-01',1,1),(15,_binary '','2022-02-10','2021-12-01',1,1),(16,_binary '','2022-02-10','2021-12-01',1,1),(17,_binary '','2022-02-10','2021-12-01',1,1),(18,_binary '','2022-02-10','2021-12-01',1,1),(19,_binary '','2022-02-10','2021-12-01',1,1),(20,_binary '','2022-02-10','2021-12-01',1,1),(21,_binary '','2022-02-10','2021-12-01',1,1),(22,_binary '','2021-12-30','2021-12-07',1,1),(23,_binary '','2021-12-30','2021-12-07',1,1),(24,_binary '','2021-12-30','2021-12-07',1,1),(25,_binary '','2021-12-30','2021-12-07',1,1),(26,_binary '','2021-12-30','2021-12-07',1,1);
/*!40000 ALTER TABLE `prestamo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'libreria_rest'
--
/*!50003 DROP PROCEDURE IF EXISTS `lsp_buscar_por_patron_titulo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_buscar_por_patron_titulo`(pPatron VARCHAR(3))
SALIR: BEGIN
    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;
    START TRANSACTION;
        SELECT * FROM libreria_rest.libro WHERE titulo LIKE concat(pPatron , '%');
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_cambiar_estado_autor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_autor`(
	IN `pIdAutor` int,
	IN `pEstado` bit
)
SALIR: BEGIN
	-- Manejo de error en la transacción
	declare exit HANDLER for sqlexception
	begin
		show ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;

	IF NOT EXISTS(select alta from autor where id = pIdAutor) THEN
		SELECT 'Debe proveer un Autor existente.' AS Mensaje;
		LEAVE SALIR;
    END IF;

	START TRANSACTION;
		UPDATE	Autor SET alta = pEstado  WHERE id = pIdAutor;
		SELECT 'OK' AS Mensaje;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_cambiar_estado_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_cliente`(
	IN `pId` int,
	IN `pStatus` BOOLEAN
)
SALIR: BEGIN
    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

    IF NOT EXISTS(select * from editorial where id = pId ) THEN
		SELECT 'Debe proveer un cliente existente.' AS Mensaje;
		LEAVE SALIR;
    END IF;

    -- controla la existencia del nombre de usuario 
    START TRANSACTION;
        UPDATE cliente SET alta = pStatus  WHERE  id = pId ;
        SELECT 'OK' AS Mensaje, pId AS 'id';     
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_cambiar_estado_editorial` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_editorial`(
	IN `pIdEditorial` int,
	IN `pEstado` bit
)
SALIR: BEGIN
    -- Manejo de error en la transacción
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN 
		SHOW ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;

	IF NOT EXISTS(select alta from editorial where id = pIdEditorial) THEN
		SELECT 'Debe proveer un Editorial existente.' AS Mensaje;
		LEAVE SALIR;
    END IF;

	START TRANSACTION;
		UPDATE	editorial SET alta = pEstado  WHERE id = pIdEditorial;  
		SELECT 'OK' AS Mensaje;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_cambiar_estado_libro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_libro`(
	IN `pId` int,
	IN `pEstado` bit
)
SALIR:BEGIN
    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

    IF NOT EXISTS(select alta from libro where id = pId) THEN
        SELECT 'Debe proveer un Editorial existente.' AS Mensaje;
        LEAVE SALIR;
    END IF;

    START TRANSACTION;
        UPDATE    libro SET alta = pEstado  WHERE id = pId;  
        SELECT 'OK' AS Mensaje;
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_cambiar_estado_prestamo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_prestamo`(pIdPrestamo int, pEstado bit)
SALIR:BEGIN

    -- Manejo de error en la transacción
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN 
		SHOW ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;

    -- from alta
	IF NOT EXISTS(select * from prestamo where id = pIdPrestamo) THEN
		SELECT 'Debe proveer un Prestamo existente.' AS Mensaje;
		LEAVE SALIR;
    END IF;
    
	START TRANSACTION;
		UPDATE	prestamo SET alta = pEstado  WHERE id = pIdPrestamo;  
		SELECT 'OK' AS Mensaje;
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_crear_autor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_crear_autor`(
	IN `pNombre` VARCHAR(64)
)
SALIR:BEGIN
	/*Permite dar de alta un cliente controlando que el nombre  no exista y no sea NULL*/
    DECLARE pIdAutor int(4) ;

	-- Manejo de error en la transacción
    declare exit HANDLER for sqlexception
    begin
        show ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

    -- Controla que el nombre sea obligatorio
    IF pNombre = '' OR pNombre IS NULL THEN
        SELECT 'Debe proveer un nombre valido' AS Mensaje;
        LEAVE SALIR;
    END IF;

    START TRANSACTION;
        SET  pIdAutor = 1 + (SELECT COALESCE(MAX(id),0)FROM autor);
        INSERT INTO autor (id, nombre, alta)
        VALUES(pIdAutor, pNombre, 1);
        SELECT 'OK' AS Mensaje, pIdAutor AS 'id';
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_crear_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_crear_cliente`(
	IN `pDocumento` BIGINT,
	IN `pNombre` VARCHAR(64),
	IN `pApellido` VARCHAR(64),
	IN `pTelefono` VARCHAR(64),
	IN `pUsername` VARCHAR(64),
	IN `pUserPassword` VARCHAR(100)
)
SALIR:BEGIN
    /* Permite dar de alta un cliente controlando que el nombre  no exista y no sea NULL*/
    DECLARE pIdCliente int(4) ;

    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

    -- Controla que el nombre sea obligatorio 
    IF  length(pDocumento) <= 5  OR pDocumento IS NULL THEN
        SELECT 'Debe proveer un documento no nulo o de longuitud valida' AS Mensaje;
        LEAVE SALIR;
    END IF;
      IF  pDocumento <0  OR pDocumento >99999999 THEN
        SELECT 'Debe proveer un numero de documento valido' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- controla la existencia del cliente
    IF EXISTS(SELECT documento FROM cliente WHERE documento = pDocumento) THEN
        SELECT 'ya existe  un cliente con ese documento' AS Mensaje;
        LEAVE SALIR;
    END IF;

    -- controla la existencia del cliente
    IF EXISTS(SELECT username FROM cliente WHERE username = pUsername) THEN
        SELECT 'ya existe  un cliente con ese username' AS Mensaje;
        LEAVE SALIR;
    END IF;
   
   
    START TRANSACTION;
        SET  pIdCliente = 1 + (SELECT COALESCE(MAX(id),0)FROM cliente);       
        INSERT INTO cliente (id, documento, nombre, apellido, telefono, alta, username, user_password)
        VALUES(pIdCliente, pDocumento, pNombre, pApellido, pTelefono, 1, pUsername, pUserPassword);
        SELECT 'OK' AS Mensaje, pIdCliente AS 'id';
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_crear_editorial` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_crear_editorial`(
	IN `pNombre` VARCHAR(64)
)
SALIR:BEGIN
	/* Permite dar de alta un editorial controlando que el nombre  no exista y no sea NULL */
     DECLARE pIdEditorial int(4) ;

    -- Manejo de error en la transacción
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN 
		SHOW ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;

	-- Controla que el nombre sea obligatorio 
	IF pNombre = '' OR pNombre IS NULL THEN
		SELECT 'Debe proveer un nombre para el editorial' AS Mensaje;
		LEAVE SALIR;
    END IF;
    
        -- controla la existencia del editorial
	IF EXISTS(SELECT nombre FROM editorial WHERE nombre = pNombre) THEN
		SELECT 'ya existe  un editorial  con ese nombre' AS Mensaje;
		LEAVE SALIR;
    END IF;

	START TRANSACTION;
    	SET  pIdEditorial = 1 + (SELECT COALESCE(MAX(id),0)FROM editorial);       
		INSERT INTO editorial (id,alta,nombre) VALUES(pIdEditorial,1,pNombre);
        SELECT 'OK' AS Mensaje, pIdEditorial AS 'id';
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_crear_libro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_crear_libro`(
	IN `pTitulo` VARCHAR(64),
	IN `pIsbn` BIGINT,
	IN `pAnio` INT,
	IN `pEjemplares` INT,
	IN `pEjemplaresPrestados` INT,
	IN `pEjemplaresRestantes` INT,
	IN `pAlta` BIT,
	IN `pFkAutor` INT,
	IN `pFkEditorial` INT
)
SALIR:BEGIN

    DECLARE pIdLibro int(4) ;

	-- Manejo de error en la transacción
    declare exit HANDLER for sqlexception
    begin
        show ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

    -- Controla que el isbn sea obligatorio
    IF pIsbn is null or LENGTH(pIsbn) < 5 or EXISTS(select 1 from libro where isbn = pIsbn) THEN
        SELECT 'Debe proveer un isbn valido' AS Mensaje;
        LEAVE SALIR;
    END IF;

   	-- Controla que exista el id de Autor
    if  not exists (select 1 from autor where id = pFkAutor) or pFkAutor IS NULL or  LENGTH(pFkAutor) = 0 THEN
        SELECT 'Autor debe tener un id valido valido' AS Mensaje;
        LEAVE SALIR;
    END IF;
       
    -- Controla que exista el id de Editorial
    IF not exists (select 1 from editorial where id = pFkEditorial) or pFkEditorial IS null or LENGTH(pFkEditorial) = 0 THEN
        SELECT 'Editorial debe tener un id valido' AS Mensaje;
        LEAVE SALIR;
    END IF;
   
    -- Controla el año sea mayor a 0 y menor o igual al año actual
    if pAnio < 0 or pAnio > year(now()) then
        SELECT 'Ingrese un año valido' AS Mensaje;
        LEAVE SALIR;
    end if;
   
   	-- Controla que el stock sea valido.
    if  pEjemplaresRestantes + pEjemplaresPrestados != pEjemplares or pEjemplares < 0 or pEjemplaresPrestados < 0 or pEjemplaresRestantes < 0 then
        SELECT 'Verificar las cantidades de los ejemplares.' AS Mensaje;
        LEAVE SALIR;
    end if;

    START TRANSACTION;
        SET  pIdLibro = 1 + (SELECT COALESCE(MAX(id),0) FROM libro);
        INSERT INTO libro (id, alta, anio, ejemplares, ejemplares_prestados, ejemplares_restantes, isbn, titulo, fk_autor, fk_editorial)
        VALUES(pIdLibro, 1, pAnio, pEjemplares, pEjemplaresPrestados, pEjemplaresRestantes, pIsbn, pTitulo, pFkAutor, pFkEditorial);
        SELECT 'OK' AS Mensaje, pIdLibro AS 'id';
    COMMIT;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_crear_prestamo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_crear_prestamo`(
	IN `pFkCliente` INT,
	in `pFechaDevolucion` DATE,
	in `pFechaPrestamo` DATE,
	IN `pFkLibro` INT
)
SALIR:BEGIN

    DECLARE pIdPrestamo int(4) ;

	-- Manejo de error en la transacción
    declare exit HANDLER for sqlexception
    begin
        show ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

   	-- Controla que exista Libro
    if  not exists (select 1 from libro as l where l.id = pFkLibro AND l.alta = 1 ) THEN
        SELECT 'No existe ese libro o no está dado de alta' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla stock de libros
    if ((select ejemplares_restantes from libro where id = pFkLibro)<1) THEN
        SELECT 'No hay stock de libro para prestar' AS Mensaje;
        LEAVE SALIR;
    END IF;

    -- Controla que exista Cliente
    if  not exists (select 1 from cliente as c where c.id = pFkCliente AND c.alta = 1) THEN
        SELECT 'No existe ese cliente o no esta dado de alta' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla la existencia de una fecha de devolución
    IF (pFechaDevolucion is null) THEN
        SELECT 'Debe proveer una fecha de préstamo válida (no nula).' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla que la fecha de devolución no debe se menor que la fecha de prestamo(actual)
    IF ( pFechaPrestamo > pFechaDevolucion) THEN
        SELECT 'Debe proveer una fecha de devolución válida (fecha de devolución debe ser mayor o igual que la de préstamo).' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla la existencia de una fecha de prestamo
    IF (pFechaPrestamo is null) THEN
        SELECT 'Debe proveer una fecha de préstamo válida (no nula).' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla que la fecha de préstamo no se mayor que la actual
    IF (date_format(pFechaPrestamo,'%Y-%M-%D') != date_format(now(),'%Y-%M-%D')) THEN
        SELECT 'Debe proveer una fecha de préstamo válida (fecha actual).' AS Mensaje;
        LEAVE SALIR;
    END IF;

	-- validar formato de fecha (ver)

    START TRANSACTION;
        SET  pIdPrestamo = 1 + (SELECT COALESCE(MAX(id),0) FROM prestamo);
        INSERT INTO prestamo (alta, fecha_devolucion, fecha_prestamo, fk_cliente, fk_libro)
        VALUES(1, pFechaDevolucion, pFechaPrestamo, pFkCliente, pFkLibro);

        SELECT 'OK' AS Mensaje, pIdPrestamo AS 'id';
    COMMIT;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_modificar_autor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_autor`(
	IN `pId` int,
	IN `pNombre` VARCHAR(64)
)
SALIR: BEGIN
	-- Manejo de error en la transacción
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN 
		SHOW ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;
    
    -- Controla que el editorial exista en BBDD 
	IF NOT EXISTS(select nombre from autor where id = pId) THEN
		SELECT 'Debe proveer un autor existente.' AS Mensaje;
		LEAVE SALIR;
    END IF;

  	START TRANSACTION;
		UPDATE autor SET nombre = pNombre, alta = 1 WHERE id = pId;
		SELECT 'OK' AS Mensaje, pId AS 'id';     
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_modificar_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_cliente`(
	IN `pId` int,
	IN `pDocumento` BIGINT,
	IN `pNombre` VARCHAR(64),
	IN `pApellido` VARCHAR(64),
	IN `pTelefono` VARCHAR(64)
)
SALIR: BEGIN
    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

    -- Controla que el editorial exista en BBDD 
    IF EXISTS(select * from cliente where documento=pDocumento AND id != pId) THEN
        SELECT 'Existe ese documento.' AS Mensaje;
        LEAVE SALIR;
    END IF;

    -- controla la existencia del nombre de usuario   
    START TRANSACTION;
        UPDATE cliente SET documento = pDocumento, nombre = pNombre, apellido = pApellido, telefono = pTelefono , alta = 1   WHERE  id = pId ;
        SELECT 'OK' AS Mensaje, pId AS 'id';     
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_modificar_editorial` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_editorial`(
	IN `pId` int,
	IN `pNombre` VARCHAR(64)
)
SALIR: BEGIN
    -- Manejo de error en la transacción
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN 
		SHOW ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;

    -- Controla que el editorial exista en BBDD 
	IF NOT EXISTS(select nombre from editorial where id = pId) THEN
		SELECT 'Debe proveer un editorial existente.' AS Mensaje;
		LEAVE SALIR;
    END IF;

	-- controla la existencia del nombre de usuario 
  	IF EXISTS(select nombre from editorial where nombre = pNombre) THEN
		SELECT 'ya existe  un editorial   con ese nombre' AS Mensaje;
		LEAVE SALIR;
    END IF;

  	START TRANSACTION;
		UPDATE editorial SET nombre = pNombre, alta = 1   WHERE  id = pId ;
		SELECT 'OK' AS Mensaje, pId AS 'id';     
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_modificar_libro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_libro`(
	IN `pId` int,
	IN `pTitulo` VARCHAR(64),
	IN `pIsbn` BIGINT,
	IN `pAnio` int,
	IN `pEjemplares` int,
	IN `pEjemplaresPrestados` int,
	IN `pEjemplaresRestantes` int,
	IN `pfk_autor` int,
	IN `pfk_editorial` int
)
SALIR: BEGIN
    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

    -- Controla que el libro exista en BBDD 
    IF NOT EXISTS(select isbn from libro where id = pId) THEN
        SELECT 'Debe proveer un libro existente.' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla la existencia de un titulo
    IF (pTitulo = '' OR pTitulo is null) THEN
        SELECT 'Debe proveer un titulo válido.' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla que el año de publicacion sea valido (menor al actual)
    IF (pAnio > year (NOW())) THEN
        SELECT 'Debe proveer un año válido.' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
	-- Controla que la valores ingresados sean coherentes 
    IF (pEjemplares != pEjemplaresRestantes+pEjemplaresPrestados) THEN
        SELECT 'La cantidad de ejemplares debe ser igual a la cantidad de ejemplares restantes y prestados' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    IF EXISTS(select * from libro where isbn = pIsbn AND id != pId) THEN
        SELECT 'Existe ese isbn.' AS Mensaje;
        LEAVE SALIR;
    END IF;
    -- Controla que el autor exista en BBDD 
    IF NOT EXISTS(select * from autor where id = pfk_autor) THEN
        SELECT 'Debe proveer un autor existente.' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla que la editorial exista en BBDD 
    IF NOT EXISTS(select * from editorial where id = pfk_editorial) THEN
        SELECT 'Debe proveer una editorial existente.' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    START TRANSACTION;
        UPDATE libro SET titulo = pTitulo, isbn = pIsbn, anio = pAnio, ejemplares = pEjemplares, ejemplares_prestados = pEjemplaresPrestados, ejemplares_restantes = pEjemplaresRestantes, alta = 1 , fk_autor = pfk_autor ,fk_editorial = pfk_editorial WHERE id = pId ;
        SELECT 'OK' AS Mensaje, pId AS 'id';     
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_modificar_prestamo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_prestamo`(
pId int, pFechaDevolucion date, pFechaPrestamo date, pFkCliente INT, pFkLibro INT)
SALIR: BEGIN

    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;
    
    -- Controla que el prestamo exista en BBDD 
    IF NOT EXISTS(select id from prestamo where id = pId) THEN
        SELECT 'Debe proveer un prestamo existente.' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
     -- Controla la existencia de una fecha de devolución
    IF (pFechaDevolucion is null) THEN
        SELECT 'Debe proveer una fecha de préstamo válida (no nula).' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla la existencia de una fecha de prestamo
    IF (pFechaPrestamo is null) THEN
        SELECT 'Debe proveer una fecha de préstamo válida (no nula).' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla que la fecha de devolución no sea menor que la fecha de prestamo
    IF ( pFechaPrestamo > pFechaDevolucion) THEN
        SELECT 'Debe proveer una fecha de devolución válida (fecha de devolución debe ser mayor o igual que la de préstamo).' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla que exista Cliente y este dado de alta
    if  not exists (select 1 from cliente as c where c.id = pFkCliente AND c.alta = 1) THEN
        SELECT 'No existe ese cliente o no esta dado de alta' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
	-- Controla que exista Libro y este dado de alta
    if  not exists (select 1 from libro as l where l.id = pFkLibro AND l.alta = 1 ) THEN
        SELECT 'No existe ese libro o no está dado de alta' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla stock de libros
    if ((select ejemplares_restantes from libro where id = pFkLibro)<1) THEN
        SELECT 'No hay stock de libro para prestar' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- Controla que el libro exista en BBDD 
    IF NOT EXISTS(select id from libro where id = pFkLibro) THEN
        SELECT 'Debe proveer un libro existente.' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
      START TRANSACTION;
        UPDATE prestamo SET fecha_prestamo = date(pFechaPrestamo), fecha_devolucion = date(pFechaDevolucion), fk_cliente = pFkCliente, fk_libro = pFkLibro WHERE id = pId ;
        SELECT 'OK' AS Mensaje, pId AS 'id';     
    COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-12-30 17:11:30
