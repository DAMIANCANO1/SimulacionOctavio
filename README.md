# SimulacionOctavio

Aplicacion Swing para generar secuencias pseudoaleatorias y ejecutar ocho pruebas estadisticas. Utiliza Java basico sin frameworks externos.

## Generadores

- Cuadrados medios
- Productos medios
- Multiplicador constante
- Congruencial lineal
- Congruencial aditivo
- Congruencial multiplicativo
- Congruencial cuadratico

## Pruebas estadisticas

- Prueba de medias
- Prueba de varianza
- Prueba Chi-cuadrada
- Kolmogorov-Smirnov
- Corridas arriba y abajo
- Corridas arriba y abajo de la media
- Prueba de Poker
- Prueba de huecos

Los datos generados se conservan en memoria y pueden reutilizarse en pruebas consecutivas.

## Requisitos

- JDK 8 o compatible
- NetBeans con soporte para proyectos Ant

## Abrir y ejecutar en NetBeans

1. Abra NetBeans y seleccione **Abrir proyecto**.
2. Seleccione la carpeta del proyecto.
3. Ejecute con F6 o el boton Ejecutar proyecto.
4. La clase principal es `laboratorio.Arranque`.

## Abrir y ejecutar en IntelliJ IDEA

Este proyecto no incluye archivos `.idea` ni `.iml` para que pueda clonarse en cualquier computadora. Por eso IntelliJ debe configurarse una sola vez:

1. Abra la carpeta raíz `SimulacionOctavio`, no la carpeta `src`.
2. Vaya a **File > Project Structure > Project** y seleccione un **JDK 8 o superior**.
3. En el árbol del proyecto, haga clic derecho sobre `src` y seleccione **Mark Directory as > Sources Root**. La carpeta debe cambiar a color azul.
4. Abra `src\laboratorio\Arranque.java`.
5. Haga clic derecho dentro del método `main` y seleccione **Run 'Arranque.main()'**.

El proyecto incluye el módulo `SimulacionOctavio.iml` y una configuración ejecutable versionada llamada **Arranque**. Después de clonar, también puede elegir **Arranque** en la lista desplegable de configuraciones de IntelliJ y pulsar el botón verde de ejecución.

Si no aparece **Run**, compruebe que el archivo comienza con `package laboratorio;` y que `src` está marcado como **Sources Root**. También puede ejecutar el proyecto desde la terminal integrada:

```bat
rmdir /s /q build
mkdir build\classes
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -d build\classes @sources.txt
del sources.txt
java -cp build\classes laboratorio.Arranque
```

Otra opción es abrir la ventana **Ant** de IntelliJ, agregar `build.xml` y ejecutar los objetivos `clean`, `jar` y `run`.

## Compilacion por terminal

Desde la carpeta del proyecto:

```bat
dir /s /b src\*.java > sources.txt
javac -encoding UTF-8 -d build\classes @sources.txt
del sources.txt
```

Crear y ejecutar el JAR:

```bat
mkdir dist
jar cfm dist\SimulacionOctavio.jar manifest.mf -C build\classes .
java -jar dist\SimulacionOctavio.jar
```

## Clonar

```bat
git clone https://github.com/DAMIANCANO1/SimulacionOctavio.git
```
