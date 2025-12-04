@echo off
cls
echo ==========================================
echo      CONSTRUYENDO COMPILADOR CALCULADORA
echo ==========================================

:: 1. Generar los archivos Java desde la gramática (.g4)
echo [1/3] Generando archivos de ANTLR...
java -jar lib\antlr-4.13.1-complete.jar src\calc.g4 -visitor -no-listener

:: 2. Compilar todos los archivos .java dentro de src/
echo [2/3] Compilando codigo Java...
javac -cp ".;lib\antlr-4.13.1-complete.jar" src\*.java

:: 3. Ejecutar la clase principal
echo [3/3] Ejecutando Calculadora...
echo ------------------------------------------
:: Se agrega "src" al ClassPath porque ahí quedan los .class
java -cp "src;lib\antlr-4.13.1-complete.jar" Calculadora

pause
