
call lsp_alta_editorial("santilla");   
call lsp_activar_editorial(1);
call lsp_desactivar_editorial(1);
select * from  editorial;
call lsp_cambiar_estado_editorial(50,0);