export const options = {
  method: "",
  body: "",
  headers: {
    "Content-type": "application/json; charset=UTF-8",
  },
};

export const optionsGET = {
  method: "GET",
  headers: {
    "Content-type": "application/json; charset=UTF-8",
  },
};

export const urlAutor = "https://libreria-rest.herokuapp.com/api/v1/autor/";
export const urlDesactivarAutor = urlAutor + "desactivar/";
export const urlActivarAutor = urlAutor + "activar/";
