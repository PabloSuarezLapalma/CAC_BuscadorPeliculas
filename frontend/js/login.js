//Se utiliza una constante global par almacenar la URL base de la API, que se utilizará para realizar las solicitudes HTTP.
const localhost = 'http://localhost:8080/api_movie_maven_war';

var nombreUsuario = "nombre";
var password = "123456"



document.getElementById('myForm').addEventListener('submit', function(event) {
    event.preventDefault();
    console.log(this);
    if (validar(this)) {
        this.submit();
    }
});

function limpiarError() {
    var errores = document.getElementsByClassName('error');
    for ( let i = 0; i< errores.length; i++ ){
        errores[i].innerHTML = '';
    }
    limpiarError();
}

function mostrarAlerta(titulo, mensaje, tipo,campoFocus) {
    Swal.fire({
        title: titulo,
        text: mensaje,
        icon: tipo, // 'success', 'error', 'warning', 'info'
        confirmButtonText: 'Aceptar'
    }).then(() => {
        if (campoFocus) {
            campoFocus.focus();
        }
    });
}

function validar(formulario) {
    //valido el nombre
    const nombreUsuario = String(formulario.nombre.value);
    const encodedNombreUsuario = encodeURIComponent(nombreUsuario);
    // Se realiza una solicitud GET a la API que conecta con la BD para poder obtener los usuarios y comparar si el usuario y contraseña ingresados son correctos
    const xhr = new XMLHttpRequest();
    xhr.open('GET', `${localhost}/users?nombre=${encodedNombreUsuario}`, true);
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4){
            if (xhr.status === 200) {
                // Se obtiene la lista de usuarios
            const users = JSON.parse(xhr.responseText);
            if (users.length === 0) {
                // Se muestra una alerta si el usuario no existe
                mostrarAlerta('Error', 'Error al iniciar sesión, Credenciales Incorrectas', 'error', formulario.nombre);
                document.getElementsByClassName('errorName')[0].innerText = 'Credenciales Incorrectas';
                return false;
            }
            else {
                users.forEach(user => {
                    // Se valida la contraseña
                    if(formulario.password.value != user.password){
                        // Se muestra una alerta si la contraseña es incorrecta
                        mostrarAlerta('Error','Error al iniciar sesión,  Credenciales Incorrectas', 'error', formulario.nombre);
                        document.getElementsByClassName('errorName').innerText = 'Credenciales Incorrectas';
                        return false
                    }
                    // Se muestra una alerta si el usuario y la contraseña son correctos
                    mostrarAlerta('Éxito',"Gracias, Se ha logueado Correctamente!", "success")
                    setTimeout(() => { return true; }, 5000);
                });
            }
        }
        else {
            // Se muestra una alerta si la solicitud no se completó correctamente y se muestra un mensaje de error en la consola.
            mostrarAlerta('Error','Error al iniciar sesión, Error interno en el servidor', 'error', formulario.nombre);
            document.getElementsByClassName('errorName').innerText = 'Internal Server Error';
            console.error('Error fetching users:', xhr.responseText);
        }
    }
    };
}