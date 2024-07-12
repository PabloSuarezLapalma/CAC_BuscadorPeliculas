//Se utiliza una constante global par almacenar la URL base de la API, que se utilizará para realizar las solicitudes HTTP.
const localhost = 'http://localhost:8080/api_movie_maven_war';

// Este código JavaScript se ejecuta cuando el DOM (Document Object Model) 
// ha sido completamente cargado. 
// Hace una solicitud GET a un servidor para obtener una lista de películas, 
// y luego crea y añade elementos de tabla para cada película al cuerpo de una tabla.

document.addEventListener('DOMContentLoaded', function () {
    // Function to fetch movies and update the table
    function fetchMovies() {
        // realizamos una peticion de tipo http a esta api para obtener todas las peliculas de la base:
        // El método GET se usa para recuperar datos, y se especifica que el contenido será JSON.
        // configuracion de options, es un get y no necesita body
        const xhr = new XMLHttpRequest();
        xhr.open('GET', `${localhost}/movies`, true);
        xhr.send();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    try {
                        const movies = JSON.parse(xhr.responseText);
                        console.log('Fetched movies:', movies);
                        const tableBody = document.querySelector('.table tbody');

                        if (!tableBody) {
                            console.error('Table body not found');
                            return;
                        }

                        // Limpiar la tabla antes de agregar las películas
                        tableBody.innerHTML = '';

                        // Recorremos todas las peliculas y agregamos su contenido a la tabla por cada pelicula
                        movies.forEach(movie => {
                            const row = document.createElement('tr');
                            row.innerHTML = `
                                <td>${movie.titulo}</td>
                                <td>${movie.duracion}</td>
                                <td>${movie.genero}</td>
                                <td><img src="${movie.imagen}" alt="${movie.titulo}" width="200"></td>
                            `;
                            tableBody.appendChild(row);
                        });
                    } catch (e) {
                        console.error('Error parsing JSON:', e);
                    }
                } else {
                    console.error(`Error fetching movies: ${xhr.status} ${xhr.statusText}`);
                }
            }
        };
    }

    // LLamado a la función fetchMovies para que se ejecute al cargar la página
    fetchMovies();

    const agregarPelicula = document.getElementById('addMovieButton');

    //Esta función se ejecuta cuando se hace clic en el botón Agregar película, utilizando el método POST para agregar una nueva película en base a los datos ingresados en el form.

    agregarPelicula.onclick =  function () {
        event.preventDefault();
        const titulo = document.getElementById('titulo').value;
        const duracion = document.getElementById('duracion').value;
        const genero = document.getElementById('genero').value;
        const imagen = document.getElementById('imagen').value;

        //Se crea una nueva instancia de XMLHttpRequest y se abre una conexión POST a la URL de la API de películas.
        const xhr = new XMLHttpRequest();
        xhr.open('POST', `${localhost}/movies`, true);
        //Se especifica que el tipo de contenido será pasado en formato JSON
        xhr.setRequestHeader('Content-Type', 'application/json');
        try{
            xhr.send(JSON.stringify({
                titulo: titulo,
                duracion: duracion,
                genero: genero,
                imagen: imagen
            }));
        }catch(e){
            console.error('Error al agregar pelicula:', e);
        }
        xhr.onreadystatechange = function () {
            //Si la solicitud se ha completado y la respuesta está lista, se verifica si el estado de la respuesta es 201 (creado) y se muestra un mensaje en la consola.
            if (xhr.readyState === 4) {
                if (xhr.status === 201) {
                    console.log('Pelicula agregada:', xhr.responseText);
                    // Se llama a la función fetchMovies para actualizar la tabla con la nueva película.
                    fetchMovies();
                } else {
                    //Si la respuesta no es 201, se muestra un mensaje de error en la consola.
                    console.error(`Error al agregar pelicula: ${xhr.status} ${xhr.statusText}`);
                }
            }
        };
    };
});
