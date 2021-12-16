CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_buscar_por_patron_nombre`(pPatron varchar(3))
SALIR:BEGIN

	-- Manejo de error en la transacción
	declare exit HANDLER for sqlexception
	begin
		show ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;

	START TRANSACTION;
		SELECT * FROM autor WHERE nombre LIKE concat(pPatron,'%');
	COMMIT;
END