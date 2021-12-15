CREATE DEFINER=`root`@`localhost` PROCEDURE `libreria_rest`.`lsp_buscar_por_patron_titulo`(pPatron VARCHAR(3))
SALIR: BEGIN
    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;
    START TRANSACTION;
        SELECT * FROM libreria_rest.libro WHERE titulo LIKE concat(pPatron , '%');
    COMMIT;
END