// Define urlVuelta como la URL actual inicialmente
let urlVuelta = window.location.href;

// ...

// Función para enviar el formulario
function submitForm() {
    const searchForm = document.getElementById("searchForm");
    const busqueda = searchForm.querySelector('input[name="busqueda"]').value;
    const ordenarPor = document.getElementById("ordenarPor").value;

    // Actualiza urlVuelta con la URL que incluye la búsqueda y el ordenamiento
    urlVuelta = `${window.location.pathname}?busqueda=${busqueda}&ordenarPor=${ordenarPor}`;

    // Actualiza la URL en el navegador
    window.history.pushState(null, null, urlVuelta);

    // Envía el formulario
    searchForm.submit();
}

// ...

// Escucha cambios en el formulario de búsqueda
searchForm.addEventListener("submit", (event) => {
    event.preventDefault(); // Evita enviar el formulario
    submitForm();
});

// Escucha cambios en la selección de ordenarPor
ordenarPorSelect.addEventListener("change", () => {
    submitForm();
});
