CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_modificar_cliente`(pId int,`pDocumento` BIGINT, `pNombre` VARCHAR(64), `pApellido`  VARCHAR(64), `pTelefono` VARCHAR(64))
SALIR: BEGIN

    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

    -- Controla que el editorial exista en BBDD 
    IF EXISTS(select * from cliente where documento=pDocumento AND id != pId) THEN
        SELECT 'Existe ese documento.' AS Mensaje;
        LEAVE SALIR;
    END IF;

    -- controla la existencia del nombre de usuario 

      START TRANSACTION;
        UPDATE cliente SET documento = pDocumento, nombre = pNombre, apellido = pApellido, telefono = pTelefono , alta = 1   WHERE  id = pId ;
        SELECT 'OK' AS Mensaje, pId AS 'id';     
     COMMIT;
END