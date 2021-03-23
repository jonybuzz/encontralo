# Contribuir al proyecto

Por favor, tomate unos minutos para leer estos lineamientos y así facilitar el proceso de contribución.
Se agradece el tiempo empleado, pero hay unas reglas de generación y aprobación de
cambios para asegurarnos de hacer de esta plataforma el mejor buscador de mascotas perdidas!
Si te quedaste con alguna duda, podés sumarte al [espacio de Slack](https://join.slack.com/t/encontralocomar/shared_invite/zt-noxjiquf-WVX30v3MB8v_ChKHAC~OYQ)

## Herramientas recomendadas
- IntelliJ Idea o en su defecto Netbeans para el backend
- Visual Studio Code para el frontend o WebStorm (pago)
- DBeaver para crear y consultar la base de datos

## Issues

Se utilizan para [reportar errores](#errores), [solicitar funcionalidades](#funcionalidades) y
[subir Pull Requests](#pull-requests), respetando:

* No usarlo para hacer consultas técnicas de las herramientas.

* No desviar las discuciones con comentarios fuera de tema

<a name="errores"></a>
## Reporte de errores

Cualquiera puede reportar un error o "bug". Es necesario describir el problema
lo mejor posible para que un desarrollador pueda replicarlo y corregirlo.

Pasos:

1. **Buscar en los issues** &mdash; asegurarse de que el mismo problema no fue reportado ya.

2. **Chequear si ya fue solucionado** &mdash; si estás usando una versión local, asegurate
   de que estás usando la última versión de la rama main.

3. **Identificar el problema** &mdash; en lo posible, crear una prueba reducida y precisa
   que desencadene el error.

No debería ser necesario pedirte más información para resolverlo. Sé lo más detallado posible:
el entorno, la versión del navegador, la versión de la app, los pasos necesarios y el
comportamiento esperado.

Ejemplo:

> Titulo corto y descriptivo
>
> Un resumen de cuál es el error y bajo qué circunstancias sucede
>
> 1. Primer paso
> 2. Segundo paso, etc.
>
>
> Lineas de código, posibles soluciones y lo que te parezca relevante.


<a name="funcionalidades"></a>
## Solicitud de nuevas funcionalidades

Todas las ideas son bienvenidas, aunque primero repasá el proyecto, buscá qué se hizo y qué es lo que sigue.

<a name="pull-requests"></a>
## Pull requests

Los pull requests son la mejor forma de contribuir en el código, pero **primero consultar**
si lo que pensás hacer sirve al proyecto o es la mejor solución. Se trata
de coordinar los esfuerzos y no perder tiempo en cosas que tal vez no lleguen a
implementarse. Todos los pull requests **deben estar asociados a un issue
existente**.
Para comenzar, te recomiendo visitar la sección Projects y revisar la columna de issues pendientes.

Mantener el código limpio y respetar las convenciones que tiene configuradas (formateo, estilos, cobertura).

Pasos a seguir:

1. Hacer un fork del proyecto y clonarlo localmente:

   ```bash
   # Clonar tu fork localmente
   git clone https://github.com/<your-username>/encontralo
   cd encontralo
   # Asignar el repo original como un remote llamado "upstream"
   git remote add upstream https://github.com/jonybuzz/encontralo
   ```

2. (Opcional) Si pasó bastante tiempo del clone, actualizar:

   ```bash
   git checkout main
   git pull upstream main
   ```

3. Crear un branch de la funcionalidad o fix:

   ```bash
   git checkout -b <nombre-feature>
   ```

4. Hacer commits con mensajes descriptivos de lo que se fue desarrollando. Va a
   permitir que entendamos mejor y más rápido los cambios

5. (Opcional) Mergear localmente el branch main original hacia tu branch de desarrollo para
   traerte los últimos cambios:

   ```bash
   git pull [--rebase] upstream main
   ```

6. Actualizar el branch de tu fork:

   ```bash
   git push origin <nombre-feature>
   ```

7. Crear un Pull Request en el repo original haciendo referencia al issue:
   en la descripción poner "resolves #45" si resolviera el issue Nº 45

**IMPORTANTE**: _Todo lo que se suba adhiere a la política Open Source del proyecto._


## Definición de Terminado
Cuando se termina una tarea y el Pull Request está listo para ser mergeado, se va a hacer una revisión
del código y de la web para que cumpla con la Definición de Terminado (DDT). Está bueno tenerlo en
cuenta desde el inicio para reducir el ida y vuelta en los issues. No es lo más común, pero puede pasar
que la DDT se modifique en algún momento. Si tenés alguna duda, revisá funcionalidades similares o consultá.

### Funcionamiento
- El backend y frontend compilan correctamente (comando `./mvnw package`)
- El frontend es responsive. Funciona y se ve correctamente para pantallas con ancho: 480, 768 y 1200 píxels
- En el frontend se usan los colores y el logo del proyecto.
### Código
- No hay referencias a recursos locales (del entorno del desarrollador)
- Está formateado por el IDE
- Todas las clases o componentes nuevos tienen una descripción de lo que hacen y el usuario de Github que lo creó

## Meta-contribución
Si te gustaría agregar algo a este documento o pensás que se puede mejorar, sentite libre de crear un PR o
consultar :)
