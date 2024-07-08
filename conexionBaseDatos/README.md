# Maven-Tomcat-Movies

## Grupo 2 del curso Codo a Codo. Comisión 24103

Integrantes:

1. Paola, Condotto
2. Lorena, Suarez
3. Dante, Alvarez
4. Hernan, Mamani
5. Maximiliano, Peralta
6. Pablo, Suárez Lapalma

## Descripción
Este repositorio contiene el backend en Java del proyecto de gestión de películas realizado en el curso de Codo a Codo en el primer cuatrimestre de 2024. Este backend brinda endpoints a los cuales poder realizar las peticiones GET,PUT,POST y DELETE, conectándose a una base de datos de tipo MySQL.

## Instalación
Existen dos formas de ejecutar este proyecto.

1. Ejecutar el proyecto desde un Editor de Código/IDE (Eclipse, IntelliJ, Visual Studio Code)
   - Requisitos:
      - [Java JDK 17](https://www.oracle.com/ar/java/technologies/downloads/)
      - [Apache Tomcat](https://tomcat.apache.org/download-90.cgi).
   - Clone el repositorio del proyecto utilizando el comando `git clone https://github.com/PabloSuarezLapalma/maven-tomcat-movies.git`
   - Abra el proyecto en su IDE/Editor de Código de preferencia
   - Configure JDK 17 y Tomcat 9.0.90 para ser utilzado en el proyecto
   - Ejecute el proyecto como una aplicación Maven

  
2. Ejecutar el proyecto en un contenedor Docker
   - Deberá tener instalado [Docker](https://docs.docker.com/get-docker/) en su equipo 
   - Clone el repositorio del proyecto utilizando el comando `git clone https://github.com/PabloSuarezLapalma/maven-tomcat-movies.git`
   - Cree la imagen de Docker del proyecto con el comando `docker build -t maven-tomcat-movies .`
   - Descargue la imagen de MySQL de Docker con el comando `docker pull mysql`
   - Cree una red para los contenedores con el comando `docker network create internalnet`
   - Ejecute la imagen de MySQL en un contenedor con el comando siguiente (modifique el usuario y la contraseña de la base de datos según corresponda)  `docker run --name mysql --network internalnet -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=movies -p 3306:3306 -d mysql`
   - Ejecute el proyecto de backend en un contenedor con el siguiente comando `docker run --name maven-tomcat-movies --network internalnet -p 8080:8080 -d maven-tomcat-movies`

## Modo de Uso
Una vez que se esté ejecutando el proyecto, podrá utilizar los siguientes endpoints:

### Películas
- GET /movies: Obtiene todas las películas
- POST /movie: Permite agregar una película 
- PUT /movie/{id}: Permite modificar una película pasando el `id` como parámetro
- DELETE /movie/{id}: Permite eliminar una película pasando el `id` como parámetro

### Users
- GET /users: Obtiene todos los usuarios
- POST /user: Permite agregar una película
- PUT /user/{id}: Permite modificar una usuario pasando el `id` como parámetro
- DELETE /user/{id}: Permite eliminar una usuario pasando el `id` como parámetro

## License
Apache License, Version 2.0
