
const btn = document.getElementById("servicios");
const menu = document.getElementById("menu");

const btnReg = document.getElementById("reg");
const menuReg = document.getElementById("menuReg");

const btnGest = document.getElementById("gest");
const menuGest = document.getElementById("menuGest");

function ocultarMenus() {
    menu.classList.add('hidden');
    menuReg.classList.add('hidden');
    menuGest.classList.add('hidden');
}

//ocultar menus del inicio

if (btn !== null && btnReg !== null) {
    btn.addEventListener('click', (event) => {
        event.stopPropagation();
        menu.classList.toggle('hidden');
        menuReg.classList.add('hidden'); // Oculta el menú de btnReg
    });

    btnReg.addEventListener('click', (event) => {
        event.stopPropagation();
        menuReg.classList.toggle('hidden');
        menu.classList.add('hidden'); // Oculta el menú de btn
    });

    document.addEventListener('click', (event) => {
        if (!menu.contains(event.target) && event.target !== btn && !menuReg.contains(event.target) && event.target !== btnReg) {
            ocultarMenus(); // Oculta todos los menús si se hace clic fuera de ellos
        }
    });

    // Agrega eventos de clic a los menús para detener la propagación
    menu.addEventListener('click', (event) => {
        event.stopPropagation();
    });

    menuReg.addEventListener('click', (event) => {
        event.stopPropagation();
    });
}

//ocultar menus del User

if (btnGest !== null && btn !== null) {
    btn.addEventListener('click', (event) => {
        event.stopPropagation();
        menuGest.classList.toggle('hidden');
        menu.classList.toggle('hidden');
    });

    btnGest.addEventListener('click', (event) => {
        event.stopPropagation();
        menuGest.classList.toggle('hidden');
        menu.classList.toggle('hidden');
    });

    document.addEventListener('click', (event) => {
        if (!menu.contains(event.target) && event.target !== btn && !menuGest.contains(event.target) && event.target !== btnGest) {
            ocultarMenus();
        }
    });
    if (menuGest !== null) {
        menuGest.addEventListener('click', (event) => {
            event.stopPropagation();
        });

        menu.addEventListener('click', (event) => {
            event.stopPropagation();
        });
    }

}
const btnPerfil = document.getElementById("btnPerfil");
const menuPerfil = document.getElementById("menuPerfil");
if (btnPerfil !== null) {
    btnPerfil.addEventListener('click', (event) => {
        event.stopPropagation();
        menuPerfil.classList.toggle('hidden');
    });

    document.addEventListener('click', (event) => {
        if (!menuPerfil.contains(event.target) && event.target !== btnPerfil) {
            menuPerfil.classList.add('hidden');
        }
    });

    menuPerfil.addEventListener('click', (event) => {
        event.stopPropagation();
    });
}
const fotoPerfil = document.getElementById("fotoPerfil");
const opcionesFoto = document.getElementById("opcionesFoto");
const opcionesAbrir = document.getElementById("opcionesAbrir");
const opcionesFormulario = document.getElementById("opcionesFormulario");
const iconoCamara = document.getElementById("iconoCamara");
if (fotoPerfil !== null && opcionesFoto!==null) {
    fotoPerfil.addEventListener('click', () => {

        opcionesFoto.classList.toggle('hidden');
        opcionesAbrir.classList.toggle('hidden');
        iconoCamara.classList.toggle('hidden');
    });

    opcionesAbrir.addEventListener('click', () => {
        opcionesAbrir.classList.toggle('hidden');
        iconoCamara.classList.toggle('hidden');
        opcionesFormulario.classList.toggle('hidden');
    });
    document.addEventListener('click', (event) => {
        if (!opcionesFoto.contains(event.target) && event.target !== fotoPerfil) {
            opcionesAbrir.classList.add('hidden');
            iconoCamara.classList.add('hidden');
            opcionesFoto.classList.add('hidden');
            opcionesFormulario.classList.add('hidden');
        }
    });


    opcionesFoto.addEventListener('click', (event) => {
        event.stopPropagation();
    });

}

const listEstrellas = document.querySelectorAll(".estrellas");
const estrellasPerfil=document.getElementById("estrellasPerfil");
console.log(estrellasPerfil);

listEstrellas.forEach(estrellitas => {

    const puntuacion = estrellitas.previousElementSibling;

    const numero = parseInt(puntuacion.textContent);
console.log(numero)
    const ul = document.createElement("ul");
    ul.classList.add("flex", "space-x-1");

    
    hacerEstrellas(numero, ul);
    estrellitas.appendChild(ul);
});
if (estrellasPerfil!==null) {
    console.log("Script ejecutado");
    estrellasPerfilMetodo();
}



function estrellasPerfilMetodo(){
    const puntuacion = document.getElementById("puntuacion");
    const numero = parseInt(puntuacion.textContent);
    const ul = document.createElement("ul");
    ul.classList.add("flex", "space-x-1");

    
    hacerEstrellas(numero, ul);
    estrellasPerfil.appendChild(ul);
}
function estrellas(star) {
        star.classList.add("fas");
        star.classList.add("fa-star");
    }
    function amarillo(star) {
        star.classList.add("text-yellow-500");
    }
    function gris(star) {
        star.classList.add("text-gray-300");
    }
    function hacerEstrellas(numero, ul) {
        switch (numero) {
            case 5:
                for (let i = 1; i <= 5; i++) {
                    const star = document.createElement("li");
                    estrellas(star);
                    amarillo(star);
                    ul.append(star);
                }
                break;
            case 4:
                for (let i = 1; i <= 4; i++) {
                    const star = document.createElement("li");
                    estrellas(star);
                    amarillo(star);
                    ul.append(star);
                }
                const star2 = document.createElement("li");
                estrellas(star2);
                gris(star2);
                ul.append(star2);
                break;
            case 3:
                for (let i = 1; i <= 3; i++) {
                    const star = document.createElement("li");
                    estrellas(star);
                    amarillo(star);
                    ul.append(star);
                }
                for (let i = 1; i <= 2; i++) {
                    const star = document.createElement("li");
                    estrellas(star);
                    gris(star);
                    ul.append(star);
                }
                break;
            case 2:
                for (let i = 1; i <= 2; i++) {
                    const star = document.createElement("li");
                    estrellas(star);
                    amarillo(star);
                    ul.append(star);
                }
                for (let i = 1; i <= 3; i++) {
                    const star = document.createElement("li");
                    estrellas(star);
                    gris(star);
                    ul.append(star);
                }
                break;
            case 1:
                const star1 = document.createElement("li");
                estrellas(star1);
                amarillo(star1);
                ul.append(star1);
                for (let i = 1; i <= 4; i++) {
                    const star = document.createElement("li");
                    estrellas(star);
                    gris(star);
                    ul.append(star);
                }
                break;
            default:
                break;
        }
    }


const aviso = document.getElementById("avisos");
const cerrarAviso = document.getElementById("cerrarAvisos");
if (aviso !== null) {
    cerrarAviso.addEventListener('click', () => {
        aviso.classList.add('hidden');
    });
}

/*const rol = document.getElementById("nuevoRol");
 const guardar = document.getElementsByClassName("guardar");
 
 function toggleAdminDropdown() {
 const button = document.getElementById('administrarButton');
 const dropdown = document.getElementById('administrarDropdown');
 if(button!==null){
 button.addEventListener('click', () => {
 dropdown.classList.toggle('hidden');
 });
 
 // Ocultar el menú cuando se hace clic fuera de él
 document.addEventListener('click', (e) => {
 if (!button.contains(e.target) && !dropdown.contains(e.target)) {
 dropdown.classList.add('hidden');
 }
 });
 }
 }
 
 // Inicializar el botón Administrar
 toggleAdminDropdown();
 
 
 
 /*const rol = document.getElementById("nuevoRol");
 const guardar = document.getElementsByClassName("guardar");
 
 function mostrar (){
 for (let i = 0; i < guardar.length; i++) {
 guardar[i].classList.remove('hidden');
 
 }
 }
 
 rol.addEventListener('click', mostrar);*/