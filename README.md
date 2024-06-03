# prueba-users
** Requisitos **
Se usa java 21
Maven 3
postgres

# Prueba de cobertura

imagen prueba de covertura de text unitarios junit y mockito 

![Texto alternativo](https://github.com/Farius-red/prueba-users/blob/master/imgDocumentacion/jacoco.png)

# Arquitectura  Hexagonal


![Texto alternativo](https://github.com/Farius-red/prueba-users/blob/master/imgDocumentacion/arquitectura.png)

En el paquete de infraestructura  esta la logica del negocio separada por dos paquetes

**paquete primary**
Este se encarga de implementar toda la logica de negocio

**paquete secundary**
Este se encarga de interactuar con la base de datos


## Proceso de installacion y ejecucion sin ide

**paso 1**  descargar repositorio

**paso 2** entrar ala carpeta prueba

**paso 3**  instalar en maquina local jdk 21

**paso 5**   instalar postgres

**paso 7** crear una  base de datos



**paso 6** instalar maven

**paso 7**  ubicarse en la consola en la carpeta prueba

**paso 8**  ejecutar mvn clean  install 



![Texto alternativo](https://github.com/Farius-red/mcs-financial/blob/master/imgDocumentacion/creaciondeJar.png)

**una salida en la terminal  similar a esta**


**paso  9**  copar la ruta que aparece en lo resaltado en blanco

**paso 10**  ejecutar  la ruta que copiamos sin la parte final del .jar
cd  /home/daniel-juliao-sistem/Documentos/desarrollo/backend/mcs-financial/target/

es un ejemplo para poder correrlo localmente las carpetas tienen q ser en la ubicacion en la que se descarga y cambiando las rutas 




**paso 11**
java
-DDB_HOST=aqui host de su base dedatosvlocal;
-DDB_PORT=aqui el puerto de conexión;
-DDB_DATABASE=nombre de la base de datos ;
-DDB_USERNAME=su usuario ;
-DDB_PASSWORD=su contraseña  -jar mcs-financial-v1.jar

**esto ejecutara la aplicación**


**paso 12**
ejecutar los siguientes script
esto para que no de fallos ala hora de registrar usuarios 


INSERT INTO public.roles (id_rol,fecha_creacion,creador,nombre_rol,realm)
VALUES ('c779baf0-122a-48a6-94c4-b292d6ca6935'::uuid,'2024-06-02 19:36:01.815','Daniel','USUARIO','prueba');

INSERT INTO public.estados (id_estado,descripcion,nombre_estado)
VALUES ('3fa85f64-5717-4562-b3fc-2c963f66afa6'::uuid,'usuario sin contraseña o sin logearse en 2 meses','INACTIVO');

estos ultimos ejecutarlos en orden   

 **primero** 
INSERT INTO public.paises (id_pais,codigo_iso,nombre)
VALUES (57,'57','Colombia');

**segundo** 

INSERT INTO public.ciudades (id_ciudad,nombre,id_pais)
VALUES (601,'Bogota',57);



**paso 13** 


ir a google y poner esta url
http://localhost:8080/documentacion

**Debe aparecer la interfase visual  de swagger**


# Correr proyecto con intelliJ Idea

se requiere agregar en variables de ambiente

DB_HOST= url de conexion;
DB_PORT=su puerto;
DB_DATABASE=su nombre de base de datos;
DB_USERNAME=su usuario ;DB_PASSWORD=aqui la clave

**adjunto imagen  referencia**

![Texto alternativo](https://github.com/Farius-red/mcs-financial/blob/master/imgDocumentacion/intelliJ.png)

# Curl enpoint 
 
**Crear usuario**

importante la ciudad debe estar registrada en la base de datos 

 
curl --location 'http://localhost:8080/add' \
--header 'Content-Type: application/json' \
--data-raw '{
"idBussines": 2,
"estado": "string",
"id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"email": "prueba123@gmail.com",
"password": "string",
"datesUser": {
"idDatesUser": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"firstName": "prueba1",
"secondName": "prueba2",
"phone": [
{
"number": "3186877469",
"cityCode": 57,
"countryCode": 601,
"nameCity": "Bogota",
"nameCountry": "Colombia"
}
],
"idUrl": "string",
"addresses": [
{
"adress": "string",
"propertyTypes": [
{
"idPropertyType": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"namePropertyType": "string"
}
],
"cityDTO": {
"idCity": 0,
"name": "string",
"idCountry": 0
}
}
],
"estado": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
"nombreRol": "string"
},
"token": "string"
}'

**Obtener usuario con filtro por idBussines**

curl --location 'http://localhost:8080/user/all?idBussines=1'

 **Obtener todos los usuarios**

curl --location 'http://localhost:8080/user/all'


**Generar Reporte Excel**

curl --location 'http://localhost:8080/user/export/excel'


## Front

Desarrollado en angular requisitos para correrlo 

node  v20.14.0
npm version  10.7.0 
angular version  18

ir ala carpeta front users  
ejecutar npm install 
ng serve 

