const btn = document.getElementById("servicios");
const menu = document.getElementById("menu");

btn.addEventListener('click', () => {
    menu.classList.toggle('hidden');
    menuReg.classList.add('hidden');
});

const btnReg = document.getElementById("reg");
const menuReg = document.getElementById("menuReg");

btnReg.addEventListener('click', () => {
    menuReg.classList.toggle('hidden');
    menu.classList.add('hidden');

});

document.addEventListener('click', (event) => {
    const menu = document.getElementById('menu');
    const menuReg = document.getElementById('menuReg'); 

    if (!menu.contains(event.target) && !btn.contains(event.target) &&
        !menuReg.contains(event.target) && !btnReg.contains(event.target)) {
        menu.classList.add('hidden');
        menuReg.classList.add('hidden');
    }
});