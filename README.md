## Contenido 
1. [Información General](#informacion-general)
2. [Requisitos](#requisitos)
3. [Instalación](#instalacin)
4. [Ejemplos de consumo](#ejemplos-de-consumo)
### Informacion General
***
Proyecto de prueba tecnica para el proceso de seleccion con nisum.

(si cuenta con una duda, no dude en remitirla al correo jmo-91@hotmail.com).

Lista de librerias usadas para este proyecto
* JWT
* Lombok
* OkHttp

Metodologías

* Arquitectura limpia
* Programación funcional

Diagrama de la solución



***
## Requisitos

* JAVA 11

## Instalación
***
* 1: Clonar el proyecto.
* 2: Abrir el proyecto con su IDE de confianza.
* 3: Build del proyecto.
* 4: Abrir POSTMAN para el consumo del Servicio.
***
## Ejemplos de consumo
El proyecto implementa un nivel de seguridad mediante Bearer Token, por lo que es obligatorio realizar inicialmente el consumo del método "LOGIN" para obtener el token de seguridad el cual debe de incluir en las peticiones en el header del método "SAVE".

Metodo LOGIN
* URL:http://localhost:9091/api/login
* TIPO:POST
* Body:
```
{
    "email": "jmo@gmail.com",
    "password": "test321"
}
```
Respuesta esperada:
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzZWNyZXQiOiIzNDE5MjcxNy00ZTRhLTRlMjEtYWI5NS00OGU5N2NjOTUyZDMiLCJzdWIiOiJqbW9AZ21haWwuY29tIiwiaWF0IjoxNjU3OTI5OTU3LCJleHAiOjE2NTc5MzQ5NTd9.UEGKxsQ-bJDOIRoOc01pZnpabL4Ac0de0jH9uQaGqGRyQ5xYZCyr4dbtGDg-8kEl4pQVoQKtjvkBepPTF8KMUA"
}
```
***
Metodo Save

* URL:http://localhost:9091/api/save
* TIPO:POST
* Body:
```
{
    "name": "name test",
    "email": "prueba@prueba.com",
    "password": "PRUEBA123##asd",
    "phones": [
        {
            "number": "3128848230",
            "cityCode": "602",
            "contryCode": "57"
        }
    ]
}
```
Respuesta esperada:
```
{
    "userId": "ba420332-269b-4cba-a7a4-ee9c2b21ca9c",
    "name": "prueba2",
    "created": "2022-07-15T19:08:20.4802685",
    "modified": "2022-07-15T19:08:20.4802685",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzZWNyZXQiOiI0MWRhY2Y4NS05OGM2LTRjOTctYWZjOS1jNzVhZjU4NDU4ZTAiLCJzdWIiOiJwcnVlYmFAMS5jb20iLCJpYXQiOjE2NTc5MzAxMDAsImV4cCI6MTY1NzkzNTEwMH0.vJ-WWP_Yi-PZRGfBaiXw8TGP2BbrHHyrJDSF9KHsmRanJ3f9sxFw_6BPW3os3vmNYY2SBB4XOhq4yhUJZG6f7w",
    "lastLogin": "2022-07-15T19:08:20.4802685",
    "active": true
}
```