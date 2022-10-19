// Call the dataTables jQuery plugin
$(document).ready(function() {

});
let headersJson= {
                       'Accept': 'application/json',
                       'Content-Type': 'application/json'
                     }
async function loginUsers(){

let datos = {}

datos.email = document.getElementById('tMail').value;
datos.password = document.getElementById('tPass').value;


  const rawRequest = await fetch('api/login', {
    method: 'POST',
    headers: headersJson,
    body: JSON.stringify(datos)
  });

  const response = await rawRequest.text();

console.log(response);
if(response != 'FAIL'){
//guardando localstore
localStorage.token = response;
localStorage.email = datos.email;
window.location.href = "usersList.html";
}else{
alert("noe s el login correcto.");
}



}

