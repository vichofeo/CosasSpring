// Call the dataTables jQuery plugin
$(document).ready(function() {

});
let headersJson= {
                       'Accept': 'application/json',
                       'Content-Type': 'application/json'
                     }
async function insertUsers(){

let datos = {}
datos.nombre = document.getElementById('tNombre').value;
datos.apellido = document.getElementById('tApellido').value;
datos.email = document.getElementById('tMail').value;

datos.password = document.getElementById('tPass1').value;
passwor2 = document.getElementById('tPass2').value;

if(datos.password != passwor2){
alert ("las contraseniras no son iguales gil ")
return
}

console.log(datos)

  const rawResponse = await fetch('api/users', {
    method: 'POST',
    headers: headersJson,
    body: JSON.stringify(datos)
  });

  alert("La cuenta fue creada con exito!");
    window.location.href = 'login.html'






}

