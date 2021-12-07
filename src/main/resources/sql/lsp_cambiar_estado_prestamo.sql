CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_prestamo`(pIdPrestamo int, pEstado bit)
SALIR:BEGIN

     -- Manejo de error en la transacción
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN 
		SHOW ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;
        --o from alta
	IF NOT EXISTS(select * from prestamo where id = pIdPrestamo) THEN
		SELECT 'Debe proveer un Prestamo existente.' AS Mensaje;
		LEAVE SALIR;
    END IF;

    
	START TRANSACTION;
		UPDATE	prestamo SET alta = pEstado  WHERE id = pIdPrestamo;  
		SELECT 'OK' AS Mensaje;
	COMMIT;
END