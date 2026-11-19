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
