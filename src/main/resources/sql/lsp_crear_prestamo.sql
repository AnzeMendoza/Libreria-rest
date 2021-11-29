CREATE DEFINER=`root`@`localhost` PROCEDURE `libreria_rest`.`lsp_crear_libro`(
	IN `pAlta` BIT,
	IN `pFkCliente` INT,
	in `pFechaDevolucion` DATE,
	in `pFechaPrestamo` DATE,
	IN `pFkLibro` INT,
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
    if  not exists (select 1 from libro where id = pFkLibro) THEN
        SELECT 'Libro debe tener un id valido valido' AS Mensaje;
        LEAVE SALIR;
    END IF;

    -- Controla que exista Cliente
    if  not exists (select 1 from cliente where id = pFkCliente) THEN
        SELECT 'Cliente debe tener un id valido valido' AS Mensaje;
        LEAVE SALIR;
    END IF;

    START TRANSACTION;
        SET  pIdPrestamo = 1 + (SELECT COALESCE(MAX(id),0) FROM prestamo);
        INSERT INTO prestamo (alta, fecha_devolucion, fecha_prestamo, fk_cliente, fk_libro)
        VALUES(1, pFechaDevolucion, pFechaPrestamo, pFkCliente, pFkLibro);

        SELECT 'OK' AS Mensaje, pIdLibro AS 'id';
    COMMIT;
end