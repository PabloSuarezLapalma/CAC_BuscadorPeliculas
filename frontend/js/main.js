//Se utiliza una constante global par almacenar la URL base de la API, que se utilizará para realizar las solicitudes HTTP.
const localhost = 'http://localhost:8080/api_movie_maven_war';

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


async function validar(formulario) {
    //variable para el campo email
    var expReg =  /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,4}$/
    //valido el nombre
    if(formulario.name.value.length == 0){
        mostrarAlerta('Su Registro es incorrecto, falta Nombre');
        document.getElementsByClassName('errorName').innerText = "Completar Campo";
        formulario.name.focus();
        return false
    } else{
        document.getElementsByClassName('errorName').innerText = "Campo Correcto";
    }
    //valido el apellido
    if(formulario.lastName.value.length == 0){
        mostrarAlerta('Su Registro es incorrecto, falta Apellido');
        document.getElementsByClassName('errorLastName').innerText = "Completar campo";
        formulario.lastName.focus();
        return false
    }else{
        document.getElementsByClassName('errorLastName').innerText = "Campo Correcto";
    }
    //valido email
    if(!expReg.test(formulario.email.value)) {
        mostrarAlerta('Su Registro es incorrecto, ingrese un mail valido');
        document.getElementsByClassName('errorEmail').innerText = "ingresar un email valido";
        formulario.email.focus();
        return false;
    }else{
        document.getElementsByClassName('errorEmail').innerText = "Campo Correcto";
    }
    //validar password
    if(formulario.password.value.trim().length ==0) {
        mostrarAlerta('Su Registro es incorrecto, Ingresar una contraseña valida');
        document.getElementsByClassName('errorPassword').innerText = 'Ingresar una contraseña valida';
        formulario.password.focus();
        return false;
    }
    //validacio de pais
    if(formulario.pais.value==''){
        mostrarAlerta('Su Registro es incorrecto, Ingresar un pais');
        document.getElementsByClassName('errorPais').innerText = 'Ingresar un pais';
        formulario.pais.focus();
        return false;
    }
    //validar fecha
     //validación de fecha
    if (formulario.fecha.value == ''){  
        mostrarAlerta('Su Registro es incorrecto, Seleccione una fecha válida');
        document.getElementsByClassName('errorFecha').innerText = 'Seleccione una fecha válida';
        formulario.fecha.focus();
        return false;
    }
    //contraseñass iguales
    if (formulario.password.value != formulario.confirmarPassword.value){
        mostrarAlerta('Su Registro es incorrecto, Las contraseñas no son iguales');
        document.getElementsByClassName('confirmarErrorPassword').innerText = 'Las contraseñas no son iguales';
        formulario.confirmarPassword.focus();
        return false;
    }else{
        document.getElementsByClassName('errorEmail').innerText = "Campo Correcto";
    }
    if(!formulario.terminos.checked){
        mostrarAlerta('Su Registro es incorrecto, terminos no esta confirmado');
        document.getElementsByClassName('confirmarTerminos').innerText = 'debe confirmar terminos';
        formulario.terminos.focus();
        return false;
    }
    const nombre = formulario.name.value;
    const apellido = formulario.lastName.value;
    const email = formulario.email.value;
    const password = formulario.password.value;
    const pais = formulario.pais.value;
    const fechaNacimiento = formulario.fecha.value;
    //Parsea la fecha al formato aceptado por el Backend
    let date = new Date(fechaNacimiento);
    let formattedDate = new Intl.DateTimeFormat('en-US', { year: 'numeric', month: '2-digit', day: '2-digit' }).format(date);
    
    try {
        //Se envía la solicitud con los datos del nuevo usuario utilizando fetch
        const response = await fetch(`${localhost}/users`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                nombre: nombre,
                apellido: apellido,
                email: email,
                password: password,
                pais: pais,
                fechaNacimiento: formattedDate
            })
        });
        
        if (response.ok) {
            //Se muestra un mensaje de éxito si el usuario se ha registrado correctamente
            Swal.fire("Gracias, Se ha registrado Correctamente!");
            console.log('Usuario agregado correctamente:', response);
        } else {
            //Si la respuesta no es 200, es decir hubo un error, se muestra un mensaje de error en la consola.
            Swal.fire("Hubo un error al registrar el usuario, intente nuevamente");
            console.error(`Error al agregar el usuario: ${response.status} ${response.statusText}`);
        }
    } catch (e) {
        console.error('Error al agregar usuario:', e);
    }
    
    return true;
}





function mostrarAlerta(mensaje) {
    Swal.fire(mensaje);
}