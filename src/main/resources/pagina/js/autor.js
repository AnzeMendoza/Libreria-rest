import { options, urlAutor,optionsGET,urlDesactivarAutor, urlActivarAutor } from "./constantes.js";
import { obtenerJson } from "./asincronico.js";

const d = document,
  $table = d.querySelector(".table"),
  $template = d.getElementById("crud-template").content,
  $fragment = d.createDocumentFragment(),
  $myModal = new bootstrap.Modal(d.getElementById("exampleModal"), options);


function obtenerAutores() {
  obtenerJson(urlAutor).then((autores) => {
    autores.forEach((autor) => {

      $template.querySelector(".name").textContent = autor.nombre;
      $template.querySelector(".status").textContent = autor.alta;
      $template.querySelector(".status").id = "status_" + autor.id;

      $template.querySelector(".ver").dataset.nombre = autor.nombre;

      /* Configuración del boton de desactivar/activar */
      $template.querySelector(".estado").innerHTML = (autor.alta) ? "Desactivar" : "Activar";
      $template.querySelector(".estado").style.backgroundColor = (autor.alta) ? 'red' :"#198754" // #198754 = verde

      $template.querySelector(".estado").dataset.estado = autor.alta;
      $template.querySelector(".estado").dataset.nombre = autor.nombre;
      $template.querySelector(".estado").dataset.id = autor.id;

    
      let $clone = d.importNode($template, true);

      $fragment.appendChild($clone);
    });
    $table.querySelector("tbody").appendChild($fragment);
  });
}

function activarAutor(index) {
  obtenerJson(urlActivarAutor + index,optionsGET).then((response) => {
    {
      console.table(response);
    }
  });
}

function desactivarAutor(index) {
  obtenerJson(urlDesactivarAutor + index, optionsGET).then((response) => {
    {
      console.table(response);
    }
  });
}

function crearAutor(options) {
  obtenerJson(urlAutor, options)
    .then((response) => {
      console.log("aqui se aplica la logica");
      alert(`se creo el autor ${response.nombre}`);
    })
    .catch((error) => console.error(error));
}
function modificarAutor(options) {
  obtenerJson(urlAutor, options)
    .then((response) => {
      console.log("aqui se aplica la logica");
      alert(`se modifico el autor ${response.nombre}`);
    })
    .catch((error) => console.error(error));
}

d.addEventListener("DOMContentLoaded", obtenerAutores());

d.addEventListener("click", async (e) => {
  if (e.target.matches(".ver")) {
    d.querySelector(
      ".modal-body"
    ).innerHTML = `Autor: ${e.target.dataset.nombre}`;
    $myModal.show();
  }

  if (e.target.matches(".editar")) {
    d.querySelector(
      ".modal-body"
    ).innerHTML = `Autor: ${e.target.dataset.nombre}`;
    $myModal.show();
  }

  if (e.target.matches(".estado")) {

    let btn = e.target;

    if (btn.dataset.estado == 'true') {

      desactivarAutor(btn.dataset.id);

      btn.innerHTML = "Activar";
      btn.style.backgroundColor = "#198754"; //verde
      btn.dataset.estado = "false";

      d.getElementById("status_"+ btn.dataset.id).innerHTML = "false";

    } else {

      activarAutor(btn.dataset.id);

      btn.innerHTML = "Desactivar";
      btn.style.backgroundColor = 'red';
      btn.dataset.estado = "true";
      
      d.getElementById("status_"+ btn.dataset.id).innerHTML = "true";

    }

    d.querySelector(
      ".modal-body"
    ).innerHTML = `El estado del autor ${btn.dataset.nombre} ha sido modificado a ${btn.dataset.estado}`;
    $myModal.show();
  }

});
