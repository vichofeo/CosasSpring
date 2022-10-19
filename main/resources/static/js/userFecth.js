// Call the dataTables jQuery plugin
$(document).ready(function() {
loadUsers();
  $('#usersList').DataTable();
});
let headersJson= {
                       'Accept': 'application/json',
                       'Content-Type': 'application/json',
                       'Authorization': localStorage.token
                     }
async function loadUsers(){

  const rawResponse = await fetch('api/users', {
    method: 'GET',
    headers: headersJson,
//    body: JSON.stringify({a: 1, b: 'Textual content'})
  });
  const requestUsers = await rawResponse.json();

  //console.log(content);


let filas="";
  for(user of requestUsers){
  let botonDel= '<a href="#" onClick="delUser('+user.userId+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>'
let tr = ' <tr><td>'+user.nombre+' '+user.apellido+' </td><td>'+ user.telefono   +'</td><td>'+user.email+'</td><td>'+user.userId+'</td><td>'+botonDel+'</td></tr>';
filas += tr;
    }

    document.querySelector('#usersList tbody').outerHTML = filas;

}

async function delUser(id){

if(!confirm('Desea aliminar el usuario')){
return ;

}



const rawResponse = await fetch('api/users/'+id, {
    method: 'DELETE',
    headers: headersJson,
//    body: JSON.stringify({a: 1, b: 'Textual content'})

  });
location.reload();
}