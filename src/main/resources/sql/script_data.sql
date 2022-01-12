use libreria_rest;

-- CREAR EDITORIALES
CALL `lsp_crear_editorial`('Plaza & Janés');
CALL `lsp_crear_editorial`('Espasa-Calpe');
CALL `lsp_crear_editorial`('Columbia,');
CALL `lsp_crear_editorial`('Optima');
CALL `lsp_crear_editorial`('De Bolsillo');
CALL `lsp_crear_editorial`('Unidad Editorial');
CALL `lsp_crear_editorial`('FisicalBook');
CALL `lsp_crear_editorial`('Bibliotex');
CALL `lsp_crear_editorial`('Verticales de Bolsillo');
CALL `lsp_crear_editorial`('Notorius Ediciones');

-- CREAR AUTORES
CALL `lsp_crear_autor`('Gabriel Garcia Marquez');
CALL `lsp_crear_autor`('Fyodor Dostoyevsky');
CALL `lsp_crear_autor`('Isabel Allende');
CALL `lsp_crear_autor`('Isaac Asimov');
CALL `lsp_crear_autor`('Miguel de Cervantes Saavedra');
CALL `lsp_crear_autor`('William Shakespeare');
CALL `lsp_crear_autor`('Victor Hugo');
CALL `lsp_crear_autor`('Jonathan Swift');
CALL `lsp_crear_autor`('Herman Melville');
CALL `lsp_crear_autor`('Lee Harper');

-- CREAR LIBROS
CALL `lsp_crear_libro`('Cien años de soledad', '138425536001871', '1982', '10', '0', '10', 1, '1', '1');
CALL `lsp_crear_libro`('Crimen y castigo', '139788437624907', '1951', '10', '0', '10', 1, '2', '2');
CALL `lsp_crear_libro`('La casa de los espíritus', '139788401423024', '1997', '10', '0', '10', 1, '3', '3');
CALL `lsp_crear_libro`('Preludio a la fundación', '139788401322648', '1988', '10', '0', '10', 1, '4', '4');
CALL `lsp_crear_libro`('Don Quijote De La Mancha', '139788441320536', '1998', '10', '0', '10', 1, '5', '5');
CALL `lsp_crear_libro`('Hamlet', '138425536001866', '1999', '10', '0', '10', 1, '6', '6');
CALL `lsp_crear_libro`('Los miserables', '139788481301298', '1999', '10', '0', '10', 1, '7', '7');
CALL `lsp_crear_libro`('Los viajes de Gulliver', '139788481301915', '1999', '10', '0', '10', 1, '8', '8');
CALL `lsp_crear_libro`('Moby Dick', '139788496246188', '2004', '10', '0', '10', 1, '9', '9');
CALL `lsp_crear_libro`('Matar un ruiseñor', '138436534539532', '1961', '10', '0', '10', 1, '10', '10');

-- CREAR ROLES
INSERT INTO libreria_rest.`role` (description, name) VALUES('administración', 'ADMIN');
INSERT INTO libreria_rest.`role` (description, name) VALUES('cliente', 'CLIENTE');
INSERT INTO libreria_rest.`role` (description, name) VALUES('personal', 'PERSONAL');

-- CREAR CLIENTE SIN ENCRIPTAR
call lsp_crear_cliente (11111111, "Nombre Cliente", "Apellido Cliente", "123123123", "cliente@gmail.com", "123", 1)

