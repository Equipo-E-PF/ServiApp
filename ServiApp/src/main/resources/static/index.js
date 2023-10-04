const btn = document.getElementById("servicios");
const menu = document.getElementById("menu");

const btnReg = document.getElementById("reg");
const menuReg = document.getElementById("menuReg");

const btnGest = document.getElementById("gest");
const menuGest = document.getElementById("menuGest");

const btnPerfil = document.getElementById("btnPerfil");
const menuPerfil = document.getElementById("menuPerfil");

const fotoPerfil = document.getElementById("fotoPerfil");
const opcionesFoto = document.getElementById("opcionesFoto");
const opcionesAbrir = document.getElementById("opcionesAbrir");
const opcionesFormulario = document.getElementById("opcionesFormulario");
const iconoCamara = document.getElementById("iconoCamara");

const btnPass = document.getElementById("contraseña");
const changePass = document.getElementById("changePass");
const formularioPerfil = document.getElementById("formularioPerfil");

const aviso = document.getElementById("avisos");
const cerrarAviso = document.getElementById("cerrarAvisos");

const contacatarProv = document.getElementById("contactar");
const menuContactarProv = document.getElementById("menuContactar");

const btnCalificar = document.getElementById("btnCalificar");
const menuCalificar = document.getElementById("menuCalificar");

const listEstrellas = document.querySelectorAll(".estrellas");
const estrellasPerfil = document.getElementById("estrellasPerfil");

const addClassToElements = (elementos) => {
    elementos.forEach(elemento => elemento?.classList.add("hidden"));
};


//contactar proveedor
if (contacatarProv !== null) {
    contacatarProv.addEventListener('click', (event) => {
        event.stopPropagation();
        menuContactarProv.classList.toggle('hidden');
    });

    document.addEventListener('click', (event) => {
        if (!menuContactarProv.contains(event.target) && event.target !== contacatarProv) {
            menuContactarProv.classList.add('hidden');
        }
    });

    menuContactarProv.addEventListener('click', (event) => {
        event.stopPropagation();
    });
}

if (btnCalificar !== null) {
    btnCalificar.addEventListener('click', (event) => {
        event.stopPropagation();
        menuCalificar.classList.toggle('hidden');
    });

    document.addEventListener('click', (event) => {
        if (!menuCalificar.contains(event.target) && event.target !== btnCalificar) {
            menuCalificar.classList.add('hidden');
        }
    });

    menuCalificar.addEventListener('click', (event) => {
        event.stopPropagation();
    });
}
//cambiar contraseña desde el perfil

if (btnPass !== null) {
    btnPass.addEventListener('click', (event) => {

        changePass.classList.toggle('hidden');
        formularioPerfil.classList.toggle('hidden');

    });
}

function ocultarMenus() {
    addClassToElements([menu, menuReg]);
}

function ocultarMenusUsuario() {
    addClassToElements([menu, menuGest, menuPerfil]);
}

//ocultar menus del inicio

if (btnReg !== null) {
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

if (btnGest !== null) {
    btn.addEventListener('click', (event) => {
        event.stopPropagation();
        menu.classList.toggle('hidden');
        menuGest.classList.add('hidden');
        menuPerfil.classList.add('hidden');
    });

    btnGest.addEventListener('click', (event) => {
        event.stopPropagation();
        menu.classList.add('hidden');
        menuGest.classList.toggle('hidden');
        menuPerfil.classList.add('hidden');
    });

    btnPerfil.addEventListener('click', (event) => {
        event.stopPropagation();
        menu.classList.add('hidden');
        menuGest.classList.add('hidden');
        menuPerfil.classList.add('hidden');
    });

    document.addEventListener('click', (event) => {
        if (!menu.contains(event.target) && event.target !== btn && !menuGest.contains(event.target) && event.target !== btnGest) {
            ocultarMenusUsuario();
        }
    });

//    if (menuGest !== null) {
        menuGest.addEventListener('click', (event) => {
            event.stopPropagation();
        });

//        menu.addEventListener('click', (event) => {
//            event.stopPropagation();
//        });
//
//        menuPerfil.addEventListener('click', (event) => {
//            event.stopPropagation();
//        });
//    }

}

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


if (fotoPerfil !== null && opcionesFoto !== null) {
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

listEstrellas.forEach(estrellitas => {

    const puntuacion = estrellitas.previousElementSibling;

    const numero = parseInt(puntuacion.textContent);
    const ul = document.createElement("ul");
    ul.classList.add("flex", "space-x-1");


    hacerEstrellas(numero, ul);
    estrellitas.appendChild(ul);
});

if (estrellasPerfil !== null) {
    estrellasPerfilMetodo();
}


function estrellasPerfilMetodo() {
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

if (aviso !== null) {
    cerrarAviso.addEventListener('click', () => {
        aviso.classList.add('hidden');
    });
}