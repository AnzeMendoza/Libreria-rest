CREATE DEFINER=`root`@`localhost` PROCEDURE `lsp_crear_cliente`(`pDocumento` BIGINT, `pNombre` VARCHAR(64), `pApellido`  VARCHAR(64), `pTelefono` VARCHAR(64))
SALIR:BEGIN
    /*
    Permite dar de alta un cliente controlando que el nombre  no exista y no sea NULL
    */
    
    DECLARE pIdCliente int(4) ;

    -- Manejo de error en la transacción
    DECLARE EXIT HANDLER FOR SQLEXCEPTION
    BEGIN 
        SHOW ERRORS;
        SELECT 'Error en la transacción. Contáctese con el administrador.' Mensaje;
        ROLLBACK;
    END;

    -- Controla que el nombre sea obligatorio 
    IF  length(pDocumento) <= 5  OR pDocumento IS NULL THEN
        SELECT 'Debe proveer un documento no nulo o de longuitud valida' AS Mensaje;
        LEAVE SALIR;
    END IF;
      IF  pDocumento <0  OR pDocumento >99999999 THEN
        SELECT 'Debe proveer un numero de documento valido' AS Mensaje;
        LEAVE SALIR;
    END IF;
    
    -- controla la existencia del cliente
    IF EXISTS(SELECT documento FROM cliente WHERE documento = pDocumento) THEN
        SELECT 'ya existe  un cliente con ese documento' AS Mensaje;
        LEAVE SALIR;
    END IF;

    START TRANSACTION;
        SET  pIdCliente = 1 + (SELECT COALESCE(MAX(id),0)FROM cliente);       
        INSERT INTO cliente (id, documento, nombre, apellido, telefono, alta)
        VALUES(pIdCliente, pDocumento, pNombre, pApellido, pTelefono, 1);
        SELECT 'OK' AS Mensaje, pIdCliente AS 'id';
    COMMIT;
    
END


