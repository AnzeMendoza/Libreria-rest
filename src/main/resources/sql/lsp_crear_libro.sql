
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