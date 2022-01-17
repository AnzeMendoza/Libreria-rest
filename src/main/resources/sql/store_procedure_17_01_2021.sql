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
-- Dumping routines for database 'libreria_rest'
--
/*!50003 DROP PROCEDURE IF EXISTS `lsp_buscar_cliente_por_patron_nombre_cliente` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_buscar_cliente_por_patron_nombre_cliente`(`pPatron` VARCHAR(3))
SALIR: BEGIN

    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;


      START TRANSACTION;
        SELECT * FROM cliente where nombre like concat(pPatron , '%');
      
     COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_buscar_por_patron_nombre` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_buscar_por_patron_nombre`(pPatron varchar(3))
SALIR:BEGIN

	-- Manejo de error en la transacción
	declare exit HANDLER for sqlexception
	begin
		show ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;

	START TRANSACTION;
		SELECT * FROM autor WHERE nombre LIKE concat(pPatron,'%');
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_buscar_por_patron_nombre_autor` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_buscar_por_patron_nombre_autor`(pPatron varchar(3))
SALIR:BEGIN

	-- Manejo de error en la transacción
	declare exit HANDLER for sqlexception
	begin
		show ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;

	START TRANSACTION;
		SELECT * FROM autor WHERE nombre LIKE concat(pPatron,'%');
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_buscar_por_patron_nombre_editorial` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_buscar_por_patron_nombre_editorial`(pPatron varchar(3))
SALIR: BEGIN

    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;
    
    START TRANSACTION;
		SELECT * FROM editorial WHERE nombre LIKE concat(pPatron,'%'); 
	COMMIT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_buscar_por_patron_titulo_libro` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_buscar_por_patron_titulo_libro`(pPatron VARCHAR(3))
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_autor`(pIdAutor int, pEstado bit)
SALIR:BEGIN

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
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_cliente`(pId int,`pStatus` BOOLEAN)
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_editorial`(pIdEditorial int, pEstado bit)
SALIR:BEGIN

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
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_libro`(pId int, pEstado bit)
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
	DECLARE pIdLibro int(4);
     -- Manejo de error en la transacción
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN 
		SHOW ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;
	

	IF NOT EXISTS(select * from prestamo where id = pIdPrestamo) THEN
		SELECT 'Debe proveer un Prestamo existente.' AS Mensaje;
		LEAVE SALIR;
    END IF;

    
	START TRANSACTION;
		UPDATE	prestamo SET alta = pEstado  WHERE id = pIdPrestamo;  
		SET pIdLibro =(SELECT prestamo.fk_libro FROM prestamo WHERE id = pIdPrestamo);
		IF (pEstado=TRUE )THEN
      CALL lsp_actualizarStockPostPrestamo(pIdLibro);
      END IF;
      IF (pEstado=FALSE )THEN
      CALL lsp_actualizarStockPostDevolucion(pIdLibro);
      END IF;
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_crear_autor`(`pNombre` VARCHAR(64))
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
	IN `pUserPassword` VARCHAR(100),
	IN `pRoleId` int
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
    if EXISTS(SELECT username FROM cliente WHERE username = pUsername) THEN
        SELECT 'ya existe  un cliente con ese username' AS Mensaje;
        LEAVE SALIR;
    END IF;

    -- controla la existencia del Role
    IF not EXISTS(SELECT 1 FROM role WHERE id = pRoleId) THEN
        SELECT 'No existe  un Role con ese id' AS Mensaje;
        LEAVE SALIR;
    END IF;
   
    START TRANSACTION;
        SET  pIdCliente = 1 + (SELECT COALESCE(MAX(id),0)FROM cliente);
        INSERT INTO cliente (id, documento, nombre, apellido, telefono, alta, username, password, role_id)
        VALUES(pIdCliente, pDocumento, pNombre, pApellido, pTelefono, 1, pUsername, pUserPassword, pRoleId);
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_crear_editorial`(`pNombre` VARCHAR(64))
SALIR:BEGIN
	/*
    Permite dar de alta un editorial controlando que el nombre  no exista y no sea NULL
    */
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
	`pTitulo` VARCHAR(64), `pIsbn` BIGINT, `pAnio` INT, `pEjemplares` INT,
	`pEjemplaresPrestados` INT, `pEjemplaresRestantes` INT, `pAlta` BIT, `pFkAutor` INT, `pFkEditorial` INT)
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
    END ;;
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
			CALL lsp_actualizarStockPostPrestamo(pFkLibro);
        SELECT 'OK' AS Mensaje, pIdPrestamo AS 'id';
    COMMIT;
end;;
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_autor`(pId int, `pNombre` VARCHAR(64))
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_cliente`(pId int,`pDocumento` BIGINT, `pNombre` VARCHAR(64), `pApellido`  VARCHAR(64), `pTelefono` VARCHAR(64))
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_editorial`(pId int, `pNombre` VARCHAR(64))
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_libro`(pId int, `pTitulo` VARCHAR(64),`pIsbn` BIGINT,`pAnio` int,`pEjemplares` int,`pEjemplaresPrestados` int,`pEjemplaresRestantes` int,`pfk_autor` int,`pfk_editorial` int)
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
		
	DECLARE pIdLibroAnterior INT(4);
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
      SET pIdLibroAnterior =(SELECT prestamo.fk_libro FROM prestamo WHERE id = pId);
      CALL lsp_actualizarStockPostDevolucion(pIdLibroAnterior); 
      UPDATE prestamo SET fecha_prestamo = date(pFechaPrestamo), fecha_devolucion = date(pFechaDevolucion), fk_cliente = pFkCliente, fk_libro = pFkLibro WHERE id = pId;
      CALL lsp_actualizarStockPostPrestamo(pFkLibro);
        SELECT 'OK' AS Mensaje, pId AS 'id';     
    COMMIT;
END;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_actualizarStockPostPrestamo` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_actualizarStockPostPrestamo`(
	IN `pIdLibro` INT
)
SALIR: BEGIN
    DECLARE pEjemplares int(4);
    DECLARE pEjemplaresRestantes int(4);
    DECLARE pEjemplaresPrestados int(4);

    START TRANSACTION;
        
			SET pEjemplares = (SELECT ejemplares FROM libro WHERE libro.id=pIdLibro);
			SET pEjemplaresPrestados = (SELECT ejemplares_prestados FROM libro WHERE libro.id=pIdLibro)+1;
			SET pEjemplaresRestantes =pEjemplares-pEjemplaresPrestados;
			UPDATE libro SET libro.ejemplares_prestados =pEjemplaresPrestados , libro.ejemplares_restantes=pEjemplaresRestantes WHERE libro.id=pIdLibro;
       
    COMMIT;
END;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `lsp_actualizarStockPostDevolucion` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;

CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_actualizarStockPostDevolucion`(
	IN `pIdLibro` INT
)
BEGIN

    DECLARE pEjemplares int(4);
    DECLARE pEjemplaresRestantes int(4);
    DECLARE pEjemplaresPrestados int(4);

    START TRANSACTION;
        
			SET pEjemplares = (SELECT ejemplares FROM libro WHERE libro.id=pIdLibro);
			SET pEjemplaresPrestados = (SELECT ejemplares_prestados FROM libro WHERE libro.id=pIdLibro)-1;
			SET pEjemplaresRestantes =pEjemplares-pEjemplaresPrestados;
			UPDATE libro SET libro.ejemplares_prestados =pEjemplaresPrestados , libro.ejemplares_restantes=pEjemplaresRestantes WHERE libro.id=pIdLibro;
       
    COMMIT;
END;;

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

-- Dump completed on 2022-01-12  0:28:16
