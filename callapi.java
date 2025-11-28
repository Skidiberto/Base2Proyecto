import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class callapi {
    public static void main(String[] args) {
        String codigo = "let num1 = \"10\";\nlet num2 = 5;\nfunction suma(a, b) {\n    if (a > b) {\n        return a - b;\n    } else {\n        return b + a;\n    }\n}\nlet resultado = suma(num1, num2);\nconsole.log(result);";
        try {
            String[] respuesta = llamarApiHuggingFace(codigo);
            System.out.println("🧪 Sintaxis:\n" + (respuesta.length > 0 ? respuesta[0] : "No hay respuesta de sintaxis."));
            System.out.println("📘 Análisis lógico:\n" + (respuesta.length > 1 ? respuesta[1] : "No hay análisis lógico."));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] llamarApiHuggingFace(String codigo) throws IOException {
        // Paso 1: POST para obtener EVENT_ID
        String apiUrl = "https://hbab-proyecto.hf.space/gradio_api/call/predict";
        String jsonInput = "{\"data\": [\"" + codigo.replace("\n", "\\n").replace("\"", "\\\"") + "\"]}";

        HttpURLConnection postConn = (HttpURLConnection) new URL(apiUrl).openConnection();
        postConn.setRequestMethod("POST");
        postConn.setRequestProperty("Content-Type", "application/json");
        postConn.setDoOutput(true);

        try (OutputStream os = postConn.getOutputStream()) {
            byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        StringBuilder postResponse = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(postConn.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                postResponse.append(responseLine.trim());
            }
        }

        // Extraer EVENT_ID del JSON de respuesta
        String eventId = null;
        String resp = postResponse.toString();
        int idx = resp.indexOf("\"event_id\":\"");
        if (idx != -1) {
            int start = idx + 12;
            int end = resp.indexOf("\"", start);
            eventId = resp.substring(start, end);
        } else {
            throw new IOException("No se pudo obtener EVENT_ID de la respuesta: " + resp);
        }

        // Paso 2: GET para obtener el resultado
        String getUrl = "https://hbab-proyecto.hf.space/gradio_api/call/predict/" + eventId;
        HttpURLConnection getConn = (HttpURLConnection) new URL(getUrl).openConnection();
        getConn.setRequestMethod("GET");

        StringBuilder getResponse = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(getConn.getInputStream(), StandardCharsets.UTF_8))) {
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                getResponse.append(responseLine.trim());
            }
        }

        // Extraer los dos resultados del JSON (muy simple, para ejemplo)
        // Extraer los dos resultados del JSON (adaptado para Server-Sent Events)
        String result = getResponse.toString();
        int dataIdx = result.indexOf("data: [");
        if (dataIdx != -1) {
            int start = result.indexOf("[", dataIdx);
            int end = result.indexOf("]", start);
            String data = result.substring(start + 1, end);
            String[] parts = data.split("\",\"");
            // Limpiar comillas y caracteres de escape
            for (int i = 0; i < parts.length; i++) {
                parts[i] = parts[i].replaceAll("^\"|\"$", "").replace("\\n", "\n").replace("\\u274c", "❌").replace("\\u00ed", "í");
            }
            return parts;
        } else {
            throw new IOException("No se pudo extraer el resultado de la respuesta: " + result);
        }
            }
}