#!/bin/bash
echo "=========================================="
echo "     CONSTRUYENDO COMPILADOR CALCULADORA"
echo "=========================================="

echo "[1/3] Generando archivos de ANTLR..."
java -jar lib/antlr-4.13.1-complete.jar src/calc.g4 -visitor -no-listener

echo "[2/3] Compilando codigo Java..."
javac -cp .:lib/antlr-4.13.1-complete.jar src/*.java

echo "[3/3] Ejecutando Calculadora..."
echo "------------------------------------------"
java -cp src:lib/antlr-4.13.1-complete.jar Calculadora
