CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_buscar_por_patron_nombre_editorial`(pPatron varchar(3))
SALIR: BEGIN

    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;
    
    START TRANSACTION;
		SELECT * FROM editorial WHERE nombre LIKE concat(pPatron,'%'); 
	COMMIT;
END