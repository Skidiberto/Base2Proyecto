Calculadora Científica: Compilador con Generación de Código Intermedio

Compilador modular que procesa expresiones matemáticas complejas, genera Código de Tres Direcciones (TAC) como representación intermedia y ejecuta las instrucciones mediante una Máquina Virtual personalizada.

nformación del Curso
Materia:Programación de Sistemas de Base 2 (Compiladores 2)
Institución:Universidad Autónoma de Tamaulipas - Facultad de Ingeniería UAT
Semestre: 2025-3 (Noveno Semestre)
Profesor: Muñoz Quintero Dante Adolfo

Integrantes del Equipo
    García Azzúa Jorge Roberto
    Gonzalez Cavazos Erick Alan
    Cruz Bonifacio Luis Fernando

Estructura del Proyecto
El proyecto sigue una arquitectura de compilador en tres fases:

1.  Frontend (Análisis):
    exer/Parser (ANTLR4): Tokenización y construcción del Árbol de Sintaxis Abstracta.
    Manejo de Errores: Listener personalizado para detección de errores sintácticos.
2.  Middle-end (Síntesis):
    CompilerVisitor: Recorre el arbol las expresiones jerárquicas.
    Generación TAC: Produce código lineal usando variables temporales.
3.  Backend (Ejecución):
    Ejecutor: Interpreta el código TAC, gestiona la memoria de variables temporales y computa el resultado final.

Organización de Directorios
proyecto-compiladores2
/
├── src/          # Código fuente Java y Gramática (.g4)
├── lib/          # Librerías externas (ANTLR Runtime)
├── ejemplos/     # Casos de prueba
├── docu/         # Documentación
└── build.* # Scripts de compilación automática
(build.sh para linux y build.bat para windows)
