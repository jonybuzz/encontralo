
<!-- ALL-CONTRIBUTORS-BADGE:START - Do not remove or modify this section -->
[![All Contributors](https://img.shields.io/badge/all_contributors-2-orange.svg?style=flat-square)](#contributors-)
<!-- ALL-CONTRIBUTORS-BADGE:END -->

<h1> <img alt="logo" src="src/main/frontend/assets-src/web-icon.png" height="35"/> encontralo.com.ar </h1>

Web que reúne mascotas perdidas con sus dueños. Roadmap: https://github.com/jonybuzz/encontralo/projects/1

## :star: Cómo contribuir

Si querés ayudarnos, ésta es la [guía para contribuir](CONTRIBUTING.md). También podés sumarte al [espacio de Slack](https://join.slack.com/t/encontralocomar/shared_invite/zt-noxjiquf-WVX30v3MB8v_ChKHAC~OYQ) para ver en qué andamos o hacer consultas

## :rocket: Empezar

1. Crear una base de datos MySQL con el nombre `encontralo`, accesible por root:root
2. Si estás usando IntelliJ, ejecutar la config "Boot local". 
   Si usás otro IDE, configurar la variable de entorno `SPRING_PROFILES_ACTIVE=local` y ejecutar ./mvnw spring-boot:run
3. Para cargar algunos datos de pruebas, ejecutar el [SQL de test](/src/test/resources/db/migration/V999__data.sql) en la base de datos

El desarrollo debe hacerse localmente porque aún no contamos con un entorno remoto para eso.

## :page_facing_up: Diseño

:arrow_upper_right: <a href="https://app.diagrams.net/#Hjonybuzz%2Fencontralo%2Fmain%2Fdocumentacion%2Fdiagrama-clases.svg" target="_blank">Diagrama de clases</a>

![Diseño](documentacion/diagrama-despliegue.svg)

:arrow_upper_right: <a href="https://app.diagrams.net/#Hjonybuzz%2Fencontralo%2Fmain%2Fdocumentacion%2Fdiagrama-despliegue.svg" target="_blank">Editar en diagrams.net</a>

El ejecutable de la aplicación es un JAR que contiene todo lo necesario para correr. El frontend maneja el código como un proyecto independiente y está ubicado en [/src/main/frontend](/src/main/frontend). Al momento de empaquetar el JAR, se compila el frontend y se incluye dentro.

## :art: Frontend

Ver [README](/src/main/frontend/README.md) del frontend

## :floppy_disk: Tecnologías

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-security)
* [Flyway Migration](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#howto-execute-flyway-database-migrations-on-startup)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#production-ready)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#using-boot-devtools)

### :book: Guías oficiales

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

## Colaboradores ✨

Se agradece el trabajo de estos devs ([emoji key](https://allcontributors.org/docs/en/emoji-key)):

<!-- ALL-CONTRIBUTORS-LIST:START - Do not remove or modify this section -->
<!-- prettier-ignore-start -->
<!-- markdownlint-disable -->
<table>
  <tr>
    <td align="center"><a href="https://github.com/ghostframe"><img src="https://avatars.githubusercontent.com/u/22096592?v=4?s=100" width="100px;" alt=""/><br /><sub><b>ghostframe</b></sub></a><br /><a href="https://github.com/jonybuzz/encontralo/commits?author=ghostframe" title="Code">💻</a></td>
    <td align="center"><a href="https://github.com/Adrianzubieta"><img src="https://avatars.githubusercontent.com/u/29063483?v=4?s=100" width="100px;" alt=""/><br /><sub><b>Adrian Zubieta Hernández</b></sub></a><br /><a href="https://github.com/jonybuzz/encontralo/commits?author=Adrianzubieta" title="Code">💻</a></td>
  </tr>
</table>

<!-- markdownlint-restore -->
<!-- prettier-ignore-end -->

<!-- ALL-CONTRIBUTORS-LIST:END -->

_El proyecto usa la especificación de [all-contributors](https://github.com/all-contributors/all-contributors). La colaboración siempre es bienvenida!_
