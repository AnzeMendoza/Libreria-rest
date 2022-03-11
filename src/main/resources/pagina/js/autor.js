import {options, urlAutor } from "./constantes.js";
import {obtenerJson } from "./asincronico.js";


const d = document,
$table = d.querySelector(".table"),
$template = d.getElementById("crud-template").content,
$fragment = d.createDocumentFragment(),
$myModal = new bootstrap.Modal(d.getElementById('exampleModal'), options);

 function obtenerAutor(url,index){
  obtenerJson(url+index).then(response => {
    console.log("*** aqui devuelvo uno");
    console.warn(response)});
}


function obtenerAutores(url){
obtenerJson(url).then(autores => {
  autores.forEach(autor => {
    
    $template.querySelector(".name").textContent = autor.nombre;
    $template.querySelector(".status").textContent = autor.alta;
    $template.querySelector(".ver").dataset.nombre = autor.nombre;
    $template.querySelector(".estado").dataset.estado = autor.alta;

    /*$template.querySelector(".edit").dataset.id = el.id;
    $template.querySelector(".edit").dataset.name = el.nombre;
    $template.querySelector(".edit").dataset.status = el.constelacion;*/
    let $clone = d.importNode($template, true);
    $fragment.appendChild($clone);
  });
  $table.querySelector("tbody").appendChild($fragment);
  
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
options.method='PUT';
options.body =JSON.stringify({
  nombre: "Gabriel Garcia Marquez"
});
//modificarAutor(urlAutor+55,options)
const form = document.querySelector("form");
form.addEventListener("submit", function(e){
  e.preventDefault();              
  const data = new FormData(e.target);
  const body = Object.fromEntries(data.entries());
  options.method='POST';
  options.body= JSON.stringify(body);
  crearAutor(urlAutor,options);
});


  d.addEventListener("DOMContentLoaded", obtenerAutores(urlAutor));

  d.addEventListener("click", async e => {
    if (e.target.matches(".ver")) { 
      d.querySelector(".modal-body").innerHTML= `Autor: ${e.target.dataset.nombre}`;
      $myModal.show();
    
    }
    

    if (e.target.matches(".editar")) { 
      d.querySelector(".modal-body").innerHTML= `Autor: ${e.target.dataset.nombre}`;
      $myModal.show();
    
    }

    if (e.target.matches(".estado")) { 

      if(e.target.dataset.estado){
        // desactivar
         //desactivarAutor();
        alert(e.target.dataset.estado);

      }else{
        //activar
        //activarAutor();
        alert(e.target.dataset.estado);

      }
          


      d.querySelector(".modal-body").innerHTML= `aqui agregro la logica para el cambio de estado`;
      $myModal.show();
    
    }

  /*
    if (e.target.matches(".delete")) {
      let isDelete = confirm(`¿Estás seguro de eliminar el id ${e.target.dataset.id}?`);

      if (isDelete) {
        //Delete - DELETE
        try {
          let options = {
            method: "DELETE",
            headers: {
              "Content-type": "application/json; charset=utf-8"
            }
          },
            res = await fetch(`http://localhost:5555/santos/${e.target.dataset.id}`, options),
            json = await res.json();

          if (!res.ok) throw { status: res.status, statusText: res.statusText };

          location.reload();
        } catch (err) {
          let message = err.statusText || "Ocurrió un error";
          alert(`Error ${err.status}: ${message}`);
        }
      }
    }*/





  })


 


