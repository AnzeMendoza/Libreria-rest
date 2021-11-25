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
END