const btn = document.getElementById("servicios");
const menu = document.getElementById("menu");

btn.addEventListener('click', ()=>{
    menu.classList.toggle('hidden');
    
})

const btnReg = document.getElementById("reg");
const menuReg = document.getElementById("menuReg");

btnReg.addEventListener('click', ()=>{
    menuReg.classList.toggle('hidden');
    
})