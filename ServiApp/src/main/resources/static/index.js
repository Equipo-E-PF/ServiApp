

const btn = document.getElementById("servicios");
const menu = document.getElementById("menu");

btn.addEventListener("click", () => {
  menu.classList.toggle("hidden");
});
btn.addEventListener('click', ()=>{
    menu.classList.toggle('hidden');
    
})

const btnReg = document.getElementById("reg");
const menuReg = document.getElementById("menuReg");

btnReg.addEventListener('click', ()=>{
    menuReg.classList.toggle('hidden');
    
})


const $usuario = document.querySelector("#usuarios");
const $servicios = document.querySelector("#servicioss");
const $botonesUsuario = document.querySelector("#botones-usuario");
const $botonesServicio = document.querySelector("#botones-servicio");

$usuario.onclick = function () {
  $botonesUsuario.classList.toggle("hidden");
  if ($botonesServicio.className === "") {
    $botonesServicio.className = "hidden";
  }
};

$servicios.onclick = function () {
  $botonesServicio.classList.toggle("hidden");
  if ($botonesUsuario.className === "") {
    $botonesUsuario.className = "hidden";
  }
};

const $crearUsuario = document.querySelector("#crear-usuario");
const $modificarUsuario = document.querySelector("#modificar-usuario");
const $crearServicio = document.querySelector("#crear-servicio");
const $modificarServicio = document.querySelector("#modificar-servicio");

$crearUsuario.addEventListener(
  "click",
  () => (window.location.href = "https:www.google.com")
);
$modificarUsuario.addEventListener(
  "click",
  () => (window.location.href = "https:www.google.com")
);
$crearServicio.addEventListener(
  "click",
  () => (window.location.href = "https:www.facebook.com")
);
$modificarServicio.addEventListener(
  "click",
  () => (window.location.href = "https:www.facebook.com")
);
