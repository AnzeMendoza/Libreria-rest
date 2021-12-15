lsp_buscar_por_patron_nombre
CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_buscar_cliente_por_patron_nombre`(`pPatron` VARCHAR(3))
SALIR: BEGIN

    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;


      START TRANSACTION;
        SELECT * FROM cliente where nombre like concat(pPatron , '%');
      
     COMMIT;
END