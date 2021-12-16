CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_buscar_por_patron_nombre`(
pId int, pFechaDevolucion date, pFechaPrestamo date, pFkCliente INT, pFkLibro INT)
SALIR: BEGIN

    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;
    
      START TRANSACTION;
          SELECT * FROM editorial WHERE nombre LIKE 'nom%';
    COMMIT;
END