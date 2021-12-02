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
end