import gradio as gr
from transformers import AutoTokenizer, AutoModelForSeq2SeqLM
import torch

# Cargar modelo y tokenizer de CodeT5
tokenizer = AutoTokenizer.from_pretrained("Salesforce/codet5-base")
model = AutoModelForSeq2SeqLM.from_pretrained("Salesforce/codet5-base")

# Función para generar explicación del código con CodeT5
def analizar_codigo(codigo):
    errores = "⚠️ No se realizó análisis de sintaxis (solo compatible con Python)."
    try:
        # Preparamos el prompt para explicación (puedes ajustar el prompt según la tarea)
        prompt = f"Explain this JavaScript code:\n{codigo}\n"
        inputs = tokenizer(prompt, return_tensors="pt", truncation=True, max_length=512)
        with torch.no_grad():
            summary_ids = model.generate(
                inputs.input_ids,
                max_length=128,
                num_beams=4,
                early_stopping=True
            )
        explicacion = tokenizer.decode(summary_ids[0], skip_special_tokens=True)
    except Exception as e:
        explicacion = f"❌ Error al analizar el código: {str(e)}"
    return errores, explicacion

# Interfaz con Gradio
demo = gr.Interface(
    fn=analizar_codigo,
    inputs=gr.Textbox(lines=15, label="Pega tu código JavaScript aquí"),
    outputs=[
        gr.Textbox(label="Estado de la sintaxis"),
        gr.Textbox(label="Explicación generada por CodeT5")
    ],
    title="🔍 Explicador de código JavaScript con CodeT5",
    description=(
        "Este Space utiliza CodeT5 para generar explicaciones automáticas de tu código JavaScript. "
        "No se realiza análisis de sintaxis ni generación textual automática."
    )
)

demo.launch()
