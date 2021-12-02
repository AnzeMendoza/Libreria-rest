CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_editorial`(pIdEditorial int, pEstado bit)
SALIR:BEGIN

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
END