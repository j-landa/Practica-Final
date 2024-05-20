// Función para mostrar la información de la pecera
function mostrarPecera() {
  fetch(`/api/peces`, {
    method: 'GET',
    headers: {
      'content-type': 'application/json'
    },
    credentials: 'include' // Incluye las cookies en la petición
  })
  .then(response => {
    if (response.ok) {
      return response.json();
    }
  })
  .then(data => {
    const infoPecera = document.getElementById('info-pecera');
    infoPecera.innerHTML = `<h2>Pecera</h2>`;

    data.forEach(pez => {
      const pezHtml = `
        <div class="pez">
          <p><strong>Usuario:</strong> ${pez.appUser.nombre}</p>
          <p><strong>Tipo de Pez:</strong> ${pez.tipo}</p>
          <p><strong>Color:</strong> ${pez.color}</p>
          <p><strong>Sexo:</strong> ${pez.sexo}</p>
        </div>
      `;
      infoPecera.innerHTML += pezHtml;
    });
  })
  .catch(error => {
    console.error('Error al obtener la información de la pecera:', error);
    mostrarAviso('✖︎ Error al obtener la información de la pecera', 'error');
  });
}

// Función para agregar un pez a la pecera
function agregarPez(peceraId) {
  const tipo = document.getElementById('tipo-1').value;
  const color = document.getElementById('color-1').value;
  const sexo = document.getElementById('sexo-1').value;
  const email = document.getElementById('email-1').value;

  const pez = {
    tipo: tipo,
    color: color,
    sexo: sexo,
    correo: email // Envia el email
  };

  fetch(`/api/pez`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(pez),
    credentials: 'include' // Incluye las cookies en la petición
  })
  .then(response => {
    if (response == 200) {
      //mostrarPecera(); // Refresca la lista de peces después de agregar uno nuevo
    }
  })
}

// Función para eliminar un pez por ID
function eliminarPez() {
  const idPez = document.getElementById('id-pez').value;

  fetch(`/api/pez/${idPez}`, {
    method: 'DELETE',
    headers: {
      'content-type': 'application/json'
    },
    credentials: 'include' // Incluye las cookies en la petición
  })
  .then(response => {
    if (response.ok) {
      mostrarAviso('✔︎ Pez eliminado con éxito', 'success');
    } else {
      throw new Error('Error al eliminar el pez');
    }
  })
  .catch(error => {
    console.error('Error al eliminar el pez:', error);
    mostrarAviso('✖︎ Error al eliminar el pez', 'error');
  });
}

// Función para mostrar avisos al usuario
function mostrarAviso(mensaje, tipo) {
  alert(`${tipo.toUpperCase()}: ${mensaje}`);
}
