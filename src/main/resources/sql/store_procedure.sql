/*-------------------------------------------------AUTOR--------------------------------------------------------------*/

CREATE DEFINER=`root`@`localhost` PROCEDURE `libreria_rest`.`lsp_crear_autor`(`pNombre` VARCHAR(64))
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
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `libreria_rest`.`lsp_cambiar_estado_autor`(pIdAutor int, pEstado bit)
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
END

CREATE DEFINER=`root`@`localhost` PROCEDURE `libreria_rest`.`lsp_modificar_autor`(pId int, `pNombre` VARCHAR(64))
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
END
/*---------------------------------------------------EDITORIAL--------------------------------------------------------*/


/*---------------------------------------------------CLIENTE------------------------------------------------------------*/
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_crear_cliente`(`pDocumento` BIGINT, `pNombre` VARCHAR(64), `pApellido`  VARCHAR(64), `pTelefono` VARCHAR(64))
SALIR:BEGIN
    /*
    Permite dar de alta un cliente controlando que el nombre  no exista y no sea NULL
    */
    
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
    
END
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
END
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
END

/*---------------------------------------------------LIBRO------------------------------------------------------------*/

/*--CAMBIAR ESTADO--*/
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
END

/*--MODIFICAR LIBRO--*/

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
END