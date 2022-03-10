import {options, urlAutor } from "./constantes.js";
import {obtenerJson } from "./asincronico.js";

 function obtenerAutor(url,index){
  obtenerJson(url+index).then(response => {
    console.log("*** aqui devuelvo uno");
    console.warn(response)});
}

function obtenerAutores(url){
obtenerJson(url).then(response => {
  console.log("*** aqui devuelvo todos los autores***")  
  console.table(response)
});
}

function activarAutor(url,index){
   obtenerJson(url+index).then(response => {
    { console.log("aqui se aplica la logica");
   console.table(response);
 }});
}

 function desactivarAutor(url, index){
  obtenerJson(url+index).then(response => {
    { console.log("aqui se aplica la logica");
   console.table(response);
 }});
}

function crearAutor(url,options){
 obtenerJson(url,options).then(response => {
   console.log("aqui se aplica la logica")
    alert(`se creo el autor ${response.nombre}`);
 }).catch(error=>console.error(error));
}


 function modificarAutor(url,options){
  obtenerJson(url,options).then(response => {
    console.log("aqui se aplica la logica")
     alert(`se modifico el autor ${response.nombre}`);
  }).catch(error=>console.error(error));
    //obtenerJson(url,options).then(response => console.log(response));
}

//obtenerAutores("http://localhost:8080/api/v1/autor/");
//obtenerAutor("http://localhost:8080/api/v1/autor/",1)
//activarAutor("http://localhost:8080/api/v1/autor/activar/",1);
//desactivarAutor("http://localhost:8080/api/v1/autor/desactivar/",1)


//options.method='POST';
//options.body= JSON.stringify({nombre: "Prueba Facundo"});
//crearAutor("http://localhost:8080/api/v1/autor/",options)
//options.method='PUT';
//options.body= JSON.stringify({nombre: "PruebaF"});
//modificarAutor("http://localhost:8080/api/v1/autor/"+45,options)

   

//desactivarAutor(urlAutor+'desactivar/',1)
//activarAutor(urlAutor+'activar/',1);
//obtenerAutor(urlAutor,1);
//obtenerAutores(urlAutor);
options.method='PUT';
options.body =JSON.stringify({
  nombre: "Gabriel Garcia Marquez"
});
modificarAutor(urlAutor+55,options)




const form = document.querySelector("form");

form.addEventListener("submit", function(e){
  e.preventDefault();              
  const data = new FormData(e.target);
  const body = Object.fromEntries(data.entries());
  options.method='POST';
  options.body= JSON.stringify(body);
  crearAutor(urlAutor,options);
});
