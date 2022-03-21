export async function obtenerJson(url,options){
  
   return await fetch(url,options).then(function(response){
     if(response.ok){
      console.log(response);
       return response.json();
     }else{
       throw new Error("mensaje de error");
     }
    
    });
  }