@echo off
setlocal
cd /d "%~dp0"

if exist build rmdir /s /q build
mkdir build\classes

dir /s /b src\*.java > sources.list
javac --release 8 -encoding UTF-8 -d build\classes @sources.list
if errorlevel 1 (
    del sources.list
    echo.
    echo No se pudo compilar el proyecto.
    pause
    exit /b 1
)
del sources.list

java -cp build\classes laboratorio.Arranque
if errorlevel 1 pause
