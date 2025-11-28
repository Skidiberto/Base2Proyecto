from gradio_client import Client
import sys

# Conectar con tu Space en Hugging Face
client = Client("HBAB/proyecto")

# Función para analizar el código
def analizar_codigo(archivo_entrada, archivo_salida):
    # Leer el código desde el archivo de entrada
    with open(archivo_entrada, 'r') as file:
        codigo = file.read()

    # Enviar el código a la API
    resultado = client.predict(codigo=codigo, api_name="/predict")

    # Guardar el resultado en el archivo de salida
    with open(archivo_salida, 'w', encoding="utf-8") as file:
        file.write("🧪 Sintaxis:\n" + resultado[0] + "\n\n")
        file.write("📘 Análisis lógico:\n" + resultado[1])

    print(f"El análisis se ha guardado en '{archivo_salida}'.")

# Ejecutar la función con argumentos de línea de comandos
if __name__ == "__main__":
    if len(sys.argv) != 3:
        print("Uso: python callapi.py <archivo_entrada> <archivo_salida>")
    else:
        analizar_codigo(sys.argv[1], sys.argv[2])