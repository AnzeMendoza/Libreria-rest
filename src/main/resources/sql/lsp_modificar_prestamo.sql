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
END