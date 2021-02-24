# Contribuir al proyecto

Por favor, tomáte unos minutos para leer estos lineamientos y así facilitar el proceso de contribución.
Se agradece el tiempo empleado, pero hay unas reglas de generación y aprobación de
cambios para asegurarnos de hacer de esta plataforma el mejor buscador de mascotas perdidas.

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

2. Si pasó bastante tiempo del clone, actualizar:

   ```bash
   git checkout main
   git pull upstream main
   ```

3. Crear un branch de la funcionalidad o fix:

   ```bash
   git checkout -b <topic-branch-name>
   ```

4. Hacer commits con mensajes descriptivos de lo que se fue desarrollando. Va a
   permitir que entendamos mejor y más rápido los cambios

5. Mergear localmente el branch main original hacia tu branch de desarrollo para
   traerte los últimos cambios:

   ```bash
   git pull [--rebase] upstream main
   ```

6. Pushear tu branch hacia el fork:

   ```bash
   git push origin <topic-branch-name>
   ```

7. Crear un Pull Request en el repo origianl haciendo referencia al issue:
   en la descripción poner "resolves #45" si resolviera el issue Nº 45

**IMPORTANTE**: _Todo lo que se suba adhiere a la política de Open Source del proyecto._
