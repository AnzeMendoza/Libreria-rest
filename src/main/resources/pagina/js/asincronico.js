export async function obtenerJson(url,options){
   return await fetch(url,options).then(function(response){
     if(response.ok){
       return response.json();
     }else{
       throw new Error("mensaje de error");
     }
    
    });
  }