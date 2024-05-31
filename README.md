# prueba-users
** Requisitos **
Se usa java 21
Maven 3
postgres

# Prueba de cobertura

imagen prueba de covertura

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

