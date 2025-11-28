# Proyecto:


## Descripción

Este proyecto implementa un compilador sencillo que realiza análisis léxico, análisis semántico y generación de código intermedio para fragmentos de código escritos en Python o JavaScript. Integra una API de Hugging Face para análisis sintáctico y lógico asistido por IA, y una interfaz web opcional con Gradio.

---
> **Nota:** No será necesario compilar todos los archivos Java, ya que se proporcionará el archivo `Main.class` ya compilado. Solo debes descargarlo y ejecutar el comando `java Main entrada.txt salida.txt` para que el programa realice automáticamente todas las etapas de análisis y generación de código.
## Tabla de Contenidos

- [Información e Integrantes](#información-e-integrantes)
- [Requisitos](#requisitos)
- [Estructura de Archivos](#estructura-de-archivos)
- [Instalación y Ejecución](#instalación-y-ejecución)
    - [1. Generar el Lexer con JFlex](#1-generar-el-lexer-con-jflex)
    - [2. Compilar los archivos Java](#2-compilar-los-archivos-java)
    - [3. Ejecutar el programa principal](#3-ejecutar-el-programa-principal)
    - [4. (Opcional) Ejecutar la interfaz web con IA](#4-opcional-ejecutar-la-interfaz-web-con-ia)
- [Descripción de Archivos Principales](#descripción-de-archivos-principales)
- [Hugging Face](#hugging-face)
- [Ejemplo de Uso](#ejemplo-de-uso)
- [Créditos](#créditos)


---
## Información e Integrantes

- **Equipo:**
    - García Azzúa Jorge Roberto
    - Gonzalez Cavazos Erick Alan
    - Cruz Bonifacio Luis Fernando


- **Profesor:** [Ing. Muñoz Quintero Dante Adolfo]
- **Materia:** Programación de Sistemas Base II
- **Universidad:** [Universidad Autonoma de Tamaulipas UAT]

---
## Requisitos

- **Java JDK** (recomendado 17+)
- **JFlex** para generar el lexer ([https://jflex.de/](https://jflex.de/))
- **Python 3** (para la interfaz web y análisis IA)
- **pip** para instalar dependencias de Python
- Acceso a internet para la API de Hugging Face

---

## Estructura de Archivos

```
lexer.flex           # Definición del analizador léxico (JFlex)
Lexer.java           # Analizador léxico generado por JFlex
Main.java            # Clase principal del proyecto
AnalizadorSemantico.java  # Analizador semántico
CodigoIntermedio.java     # Clase para instrucciones de código intermedio
GeneradorIntermedio.java  # Generador de código intermedio
Token.java           # Clase para representar tokens
callapi.java         # Llamada a la API de Hugging Face desde Java
entrada.txt          # Archivo de entrada de ejemplo (código fuente)
salida.txt           # Archivo de tokens generado por el lexer
app.py               # Interfaz web con Gradio y análisis IA (opcional)
requeriments.txt     # Dependencias de Python para la interfaz web
```

---

## Instalación y Ejecución

### 1. Generar el Lexer con JFlex

Desde la terminal, en la carpeta del proyecto:

```sh
jflex lexer.flex
```
Esto generará el archivo `Lexer.java`.

### 2. Compilar los archivos Java

Asegúrate de que los nombres de los archivos coincidan exactamente con los nombres de las clases públicas.

```sh
javac *.java
```

### 3. Ejecutar el programa principal

El programa espera dos argumentos: archivo de entrada y archivo de salida de tokens.

```sh
java Main entrada.txt salida.txt
```

Esto realizará:

- Análisis léxico (genera `salida.txt`)
- Análisis semántico (muestra errores semánticos en consola)
- Generación de código intermedio (muestra en consola)
- Llamada a la API de Hugging Face (muestra análisis en consola)

### 4. (Opcional) Ejecutar la interfaz web con IA

Instala las dependencias de Python:

```sh
pip install -r requeriments.txt
```

Ejecuta la aplicación web:

```sh
python app.py
```

Esto abrirá una interfaz web donde puedes pegar código y recibir análisis sintáctico y lógico usando modelos de Hugging Face.

---

## Descripción de Archivos Principales

- **lexer.flex**: Define el analizador léxico usando JFlex. Convierte el código fuente en una secuencia de tokens (identificadores, números, operadores, palabras clave, etc.) y los guarda en `salida.txt`.
- **Main.java**: Clase principal que coordina el análisis léxico, semántico, la generación de código intermedio y la llamada a la API de Hugging Face. Muestra los resultados de cada etapa en consola.
- **AnalizadorSemantico.java**: Lee el archivo de tokens y verifica errores semánticos como variables no declaradas o mal uso de funciones. Utiliza una tabla de símbolos para el seguimiento de variables y funciones.
- **CodigoIntermedio.java**: Clase que representa una instrucción de código intermedio en formato de tres direcciones, facilitando la optimización y traducción posterior.
- **GeneradorIntermedio.java**: Lee la secuencia de tokens y genera instrucciones de código intermedio, incluyendo asignaciones, operaciones aritméticas, llamadas a funciones, condicionales y bucles.
- **Token.java**: Clase simple para almacenar el tipo y valor de cada token reconocido por el lexer.
- **callapi.java**: Permite enviar código fuente a una API de Hugging Face para obtener análisis sintáctico y lógico asistido por IA. Recibe el resultado y lo muestra en consola.
- **app.py** y **requeriments.txt**: Implementan una interfaz web con Gradio y análisis IA usando modelos de Hugging Face. `requeriments.txt` contiene las dependencias necesarias para Python.

---
## Hugging Face

### ¿Cómo se creó y cómo funciona la app de Hugging Face?

La aplicación web utiliza [Gradio](https://gradio.app/) para crear una interfaz sencilla donde puedes pegar tu código y recibir un análisis automático. El proceso se divide en dos etapas principales:

1. **Análisis de sintaxis:**  
    Se emplea la librería `pyflakes` para detectar errores de sintaxis en el código Python ingresado. Si se encuentran errores, estos se muestran directamente al usuario.

2. **Análisis semántico con IA:**  
    Si el código no presenta errores de sintaxis, se utiliza el modelo `Salesforce/codet5-base` de Hugging Face mediante la librería `transformers`. Este modelo genera un análisis semántico del código, proporcionando sugerencias o explicaciones automáticas.

#### Código principal de la app

```python
import gradio as gr
from transformers import pipeline
import pyflakes.api
from io import StringIO
import sys

# Análisis semántico con modelo Hugging Face
model = pipeline("text2text-generation", model="Salesforce/codet5-base")

def analizar_codigo(code):
     # Análisis de sintaxis
     buffer = StringIO()
     sys.stderr = buffer
     pyflakes.api.check(code, "análisis")
     errores = buffer.getvalue()
     sys.stderr = sys.__stderr__
     
     if errores:
          return f"Errores de sintaxis:\n{errores}"
     
     # Análisis semántico
     resultado = model(code, max_length=256, do_sample=False)
     return f"Análisis semántico:\n{resultado[0]['generated_text']}"

gr.Interface(fn=analizar_codigo, inputs="text", outputs="text").launch()
```

---

## Ejemplo de Uso

Archivo de entrada (`entrada.txt`):

```txt
a = 5
b = 10
suma = a + b
print(suma)
```

Ejecución:

```sh
java Main entrada.txt salida.txt
```

Salida esperada en consola:

- Tokens generados y guardados en `salida.txt`
- Mensajes de errores semánticos (si los hay)
- Código intermedio generado
- Análisis sintáctico y lógico de la API de Hugging Face

---


