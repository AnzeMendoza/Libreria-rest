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


/*---------------------------------------------------AUTOR------------------------------------------------------------*/


/*---------------------------------------------------LIBRO------------------------------------------------------------*/

CREATE DEFINER=`root`@`localhost` PROCEDURE `libreria_rest`.`lsp_crear_libro`(
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
    IF pIsbn is null or LENGTH(pIsbn) < 5 or EXISTS(select * from libro where isbn = pIsbn) IS NULL THEN
        SELECT 'Debe proveer un isbn valido' AS Mensaje;
        LEAVE SALIR;
    END IF;

   	-- Controla que exista el id de Autor
    if  pFkAutor IS NULL or  LENGTH(pFkAutor) = 0 or not exists (select 1 from autor where id = pFkAutor) THEN
        SELECT 'Autor debe tener un id valido valido' AS Mensaje;
        LEAVE SALIR;
    END IF;

   -- Controla que exista el id de Editorial
    IF pFkEditorial IS null or LENGTH(pFkEditorial) = 0 or not exists (select 1 from editorial where id = pFkEditorial) THEN
        SELECT 'Editorial debe tener un id valido' AS Mensaje;
        LEAVE SALIR;
    END IF;

    START TRANSACTION;
        SET  pIdLibro = 1 + (SELECT COALESCE(MAX(id),0)FROM libro);
        INSERT INTO libro (id, alta, anio, ejemplares, ejemplares_prestados, ejemplares_restantes, isbn, titulo, fk_autor, fk_editorial)
        VALUES(pIdLibro, 1, pAnio, pEjemplares, pEjemplaresPrestados, pEjemplaresRestantes, pIsbn, pTitulo, pFkAutor, pFkEditorial);
        SELECT 'OK' AS Mensaje, pIdLibro AS 'id';
    COMMIT;
end