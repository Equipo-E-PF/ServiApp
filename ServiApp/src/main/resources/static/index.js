const btn = document.getElementById("servicios");
const menu = document.getElementById("menu");


btn.addEventListener('click', (event) => {
  event.stopPropagation();
  menu.classList.toggle('hidden');
});

document.addEventListener('click', (event) => {
  if (!menu.contains(event.target) && event.target !== btn) {
    menu.classList.add('hidden');
  }
});

menu.addEventListener('click', (event) => {
  event.stopPropagation();
});

const btnReg = document.getElementById("reg");
const menuReg = document.getElementById("menuReg");

btnReg.addEventListener('click', (event) => {
  event.stopPropagation();
  menuReg.classList.toggle('hidden');
});

document.addEventListener('click', (event) => {
  if (!menuReg.contains(event.target) && event.target !== btnReg) {
    menuReg.classList.add('hidden');
  }
});

menuReg.addEventListener('click', (event) => {
  event.stopPropagation();
});






const listEstrellas=document.querySelectorAll(".estrellas");


listEstrellas.forEach(estrellitas => {

    const puntuacion = estrellitas.previousElementSibling; 

    const numero = parseInt(puntuacion.textContent);
    console.log(numero);
  
 
const ul = document.createElement("ul");
    ul.classList.add("flex", "space-x-1");
    
function estrellas (star){
            star.classList.add("fas");
            star.classList.add("fa-star");
}
function amarillo (star){
    star.classList.add("text-yellow-500");
}
function gris (star){
    star.classList.add("text-gray-300");
}
switch (numero) {
    case 5:
        for (let i = 1; i <= 5; i++) {
            const star=document.createElement("li");
            estrellas(star);
            amarillo(star);
            ul.append(star);
        }
        break;
    case 4:
        for (let i = 1; i <= 4; i++) {
            const star=document.createElement("li");
            estrellas(star);
            amarillo(star);
            ul.append(star);
        }
        const star2=document.createElement("li");
        estrellas(star2);
        gris(star2);
        ul.append(star2);
        break;
    case 3:
        for (let i = 1; i <= 3; i++) {
            const star=document.createElement("li");
            estrellas(star);
            amarillo(star);
            ul.append(star);
        }
        for (let i = 1; i <= 2; i++) {
             const star=document.createElement("li");
            estrellas(star);
           gris(star);
            ul.append(star);
        }
        break;
    case 2:
        for (let i = 1; i <= 2; i++) {
            const star=document.createElement("li");
            estrellas(star);
            amarillo(star);
            ul.append(star);
        }
        for (let i = 1; i <= 3; i++) {
             const star=document.createElement("li");
            estrellas(star);
            gris(star);
            ul.append(star);
        }
        break;
    case 1:
        const star1=document.createElement("li");
        estrellas(star1);
            amarillo(star1);
            ul.append(star1);
        for (let i = 1; i <= 4; i++) {
            const star=document.createElement("li");
            estrellas(star);
            gris(star);
            ul.append(star);
        }
        break;
    default:
        break;
}

estrellitas.appendChild(ul);

});