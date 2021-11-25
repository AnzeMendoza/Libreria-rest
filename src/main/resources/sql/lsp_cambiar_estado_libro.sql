CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_cambiar_estado_libro`(pId int, pEstado bit)
SALIR:BEGIN

     -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

    IF NOT EXISTS(select alta from libro where id = pId) THEN
        SELECT 'Debe proveer un Editorial existente.' AS Mensaje;
        LEAVE SALIR;
    END IF;

    
    START TRANSACTION;
        UPDATE    libro SET alta = pEstado  WHERE id = pId;  
        SELECT 'OK' AS Mensaje;
    COMMIT;
END