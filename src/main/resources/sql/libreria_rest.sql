-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.25 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for libreria_rest
DROP DATABASE IF EXISTS `libreria_rest`;
CREATE DATABASE IF NOT EXISTS `libreria_rest` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `libreria_rest`;

-- Dumping structure for table libreria_rest.autor
DROP TABLE IF EXISTS `autor`;
CREATE TABLE IF NOT EXISTS `autor` (
  `id` int NOT NULL AUTO_INCREMENT,
  `alta` bit(1) DEFAULT NULL,
  `nombre` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table libreria_rest.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `alta` bit(1) DEFAULT NULL,
  `apellido` varchar(64) DEFAULT NULL,
  `documento` bigint DEFAULT NULL,
  `nombre` varchar(64) DEFAULT NULL,
  `telefono` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table libreria_rest.editorial
DROP TABLE IF EXISTS `editorial`;
CREATE TABLE IF NOT EXISTS `editorial` (
  `id` int NOT NULL AUTO_INCREMENT,
  `alta` bit(1) DEFAULT NULL,
  `nombre` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for table libreria_rest.libro
DROP TABLE IF EXISTS `libro`;
CREATE TABLE IF NOT EXISTS `libro` (
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

-- Data exporting was unselected.

-- Dumping structure for table libreria_rest.prestamo
DROP TABLE IF EXISTS `prestamo`;
CREATE TABLE IF NOT EXISTS `prestamo` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Data exporting was unselected.

-- Dumping structure for procedure libreria_rest.lsp_cambiar_estado_autor
DROP PROCEDURE IF EXISTS `lsp_cambiar_estado_autor`;
DELIMITER //
CREATE PROCEDURE `lsp_cambiar_estado_autor`(
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
END//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_cambiar_estado_cliente
DROP PROCEDURE IF EXISTS `lsp_cambiar_estado_cliente`;
DELIMITER //
CREATE PROCEDURE `lsp_cambiar_estado_cliente`(
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
END//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_cambiar_estado_editorial
DROP PROCEDURE IF EXISTS `lsp_cambiar_estado_editorial`;
DELIMITER //
CREATE PROCEDURE `lsp_cambiar_estado_editorial`(
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
END//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_cambiar_estado_libro
DROP PROCEDURE IF EXISTS `lsp_cambiar_estado_libro`;
DELIMITER //
CREATE PROCEDURE `lsp_cambiar_estado_libro`(
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
END//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_crear_autor
DROP PROCEDURE IF EXISTS `lsp_crear_autor`;
DELIMITER //
CREATE PROCEDURE `lsp_crear_autor`(
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
END//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_crear_cliente
DROP PROCEDURE IF EXISTS `lsp_crear_cliente`;
DELIMITER //
CREATE PROCEDURE `lsp_crear_cliente`(
	IN `pDocumento` BIGINT,
	IN `pNombre` VARCHAR(64),
	IN `pApellido` VARCHAR(64),
	IN `pTelefono` VARCHAR(64)
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

    START TRANSACTION;
        SET  pIdCliente = 1 + (SELECT COALESCE(MAX(id),0)FROM cliente);       
        INSERT INTO cliente (id, documento, nombre, apellido, telefono, alta)
        VALUES(pIdCliente, pDocumento, pNombre, pApellido, pTelefono, 1);
        SELECT 'OK' AS Mensaje, pIdCliente AS 'id';
    COMMIT;
END//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_crear_editorial
DROP PROCEDURE IF EXISTS `lsp_crear_editorial`;
DELIMITER //
CREATE PROCEDURE `lsp_crear_editorial`(
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
END//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_crear_libro
DROP PROCEDURE IF EXISTS `lsp_crear_libro`;
DELIMITER //
CREATE PROCEDURE `lsp_crear_libro`(
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
end//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_modificar_autor
DROP PROCEDURE IF EXISTS `lsp_modificar_autor`;
DELIMITER //
CREATE PROCEDURE `lsp_modificar_autor`(
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
END//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_modificar_cliente
DROP PROCEDURE IF EXISTS `lsp_modificar_cliente`;
DELIMITER //
CREATE PROCEDURE `lsp_modificar_cliente`(
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
END//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_modificar_editorial
DROP PROCEDURE IF EXISTS `lsp_modificar_editorial`;
DELIMITER //
CREATE PROCEDURE `lsp_modificar_editorial`(
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
END//
DELIMITER ;

-- Dumping structure for procedure libreria_rest.lsp_modificar_libro
DROP PROCEDURE IF EXISTS `lsp_modificar_libro`;
DELIMITER //
CREATE PROCEDURE `lsp_modificar_libro`(
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
END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
