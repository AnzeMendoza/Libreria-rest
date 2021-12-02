CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_editorial`(pId int, `pNombre` VARCHAR(64))
SALIR: BEGIN

              -- Manejo de error en la transacción
	DECLARE EXIT HANDLER FOR SQLEXCEPTION
	BEGIN 
		SHOW ERRORS;
		SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
		ROLLBACK;
	END;

    
    -- Controla que el editorial exista en BBDD 
	IF NOT EXISTS(select nombre from editorial where id = pId) THEN
		SELECT 'Debe proveer un editorial existente.' AS Mensaje;
		LEAVE SALIR;
    END IF;

	-- controla la existencia del nombre de usuario 
  IF EXISTS(select nombre from editorial where nombre = pNombre) THEN
		SELECT 'ya existe  un editorial   con ese nombre' AS Mensaje;
		LEAVE SALIR;
    END IF;

  	START TRANSACTION;
		UPDATE editorial SET nombre = pNombre, alta = 1   WHERE  id = pId ;
		SELECT 'OK' AS Mensaje, pId AS 'id';     
	COMMIT;
END