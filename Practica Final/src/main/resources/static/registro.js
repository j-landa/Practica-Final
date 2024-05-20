function compruebaPass() {
  let password1 = document.getElementById("password").value;
  let password2 = document.getElementById("password2").value;

  if (password1 === password2) {
    mostrarAviso('✔ Contraseñas coinciden', 'success'); // Muestra un mensaje de éxito si las contraseñas coinciden
    return true;
  } else {
    mostrarAviso('✖︎ Las contraseñas no coinciden', 'error'); // Muestra un mensaje de error si las contraseñas no coinciden
    return false;
  }
}


function registrarUsuario(datosJsonFormulario) {
  if (!compruebaPass()) return;
  fetch('/api/users', {
    method: 'POST',
    body: datosJsonFormulario,
    headers: {'content-type': 'application/json'}
  })
  .then(response => {
    if (response.status === 201) {
      location.href = 'index.html';
    } else if (response.status === 409) {
      return response.json().then(data => {
        mostrarAviso(`✖︎ ${data.message}`, 'error');
      });
    } else {
      mostrarAviso('✖︎ Error en el registro', 'error');
    }
  })
  .catch(error => {
    console.error('Error en la solicitud fetch:', error);
    mostrarAviso('✖︎ Error en la solicitud', 'error');
  });
}


function mostrarAviso(texto, tipo) {
  const aviso = document.getElementById("aviso");
  aviso.textContent = texto;
  aviso.className = tipo;
}

function form2json(event) {
  event.preventDefault();
  const data = new FormData(event.target);
  return JSON.stringify(Object.fromEntries(data.entries()));
}