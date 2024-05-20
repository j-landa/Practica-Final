function inicializar() {
  if (location.search === '?registrado') {
    mostrarAviso('✓ ¡Registrado! Prueba a entrar', 'success');
  }
}
function entrar(datosJsonFormulario) {
      fetch('/api/users/me/session', {
        method: 'POST',
        body: datosJsonFormulario,
        headers: {
          'content-type': 'application/json'
        },
        credentials: 'include' // Incluye las cookies en la petición
      })
      .then(response => {
        if (response.status === 201) {
          location.href = 'inicio.html';
        } else {
          mostrarAviso('✖︎ Credenciales incorrectas', 'error');
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