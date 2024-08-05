# Desafio products

El proyecto es una API rest con los endpoints solicitados.

Para ejecutar se puede hacer desde aca `./gradlew bootrun` o se puede ejecutar el comando en una terminal en la raiz del proyecto. 
IntelliJ da la opcion de correr la app desde la clase main `EvalApplication`

Tiene una autenticación basic, en el proyecto esta configurado 
un usuario con su clave  y password.

Levanta una Base de datos H2 tiene la consola activada por lo que se puede accedder a ella de forma 
visual en la url http://localhost:8080/h2-console

En la raiz del proyecto esta una coleccion de postman llamada 
Product.postman_collection.json se puede importar a postmant y 
tiene curl a los distintos endpoints de la aplicacion. Esta coleccion tiene configurado ya
las credenciales del usuario

