package laboratorio;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PruebaDeVarianza {
    public static String ejecutar(ArrayList<Double> datos, int opcion) {
        if (datos == null || datos.size() < 2) return "Se necesitan al menos 2 datos.";
        int gl = datos.size() - 1;
        double suma = 0;
        for (int i = 0; i < datos.size(); i++) suma += datos.get(i);
        double media = suma / datos.size();
        double cuadrados = 0;
        for (int i = 0; i < datos.size(); i++) {
            double diferencia = datos.get(i) - media;
            cuadrados += diferencia * diferencia;
        }
        double varianza = cuadrados / gl;
        double calculada = gl * varianza / (1.0 / 12.0);
        int[] columnas = {0,1,2,4};
        double inferior = TablaCriticaChi.obtenerInferior(gl, columnas[opcion]);
        double superior = TablaCriticaChi.obtenerSuperior(gl, columnas[opcion]);
        if (inferior < 0 || superior < 0) return "La tabla no contiene " + gl + " grados de libertad. No puede ejecutarse.";
        String[] niveles = {"80%","90%","95%","99%"};
        DecimalFormat f = new DecimalFormat("0.000000");
        StringBuilder texto = new StringBuilder("PRUEBA DE VARIANZA\n\nDatos utilizados:\n");
        texto.append(PruebaDeMedias.formatearLista(datos,f));
        texto.append("\nMedia = ").append(f.format(media)).append("\nVarianza muestral = ").append(f.format(varianza));
        texto.append("\nResultado = ").append(f.format(calculada)).append("\nGrados de libertad = ").append(gl);
        texto.append("\nLímite inferior = ").append(f.format(inferior)).append("\nLímite superior = ").append(f.format(superior));
        texto.append("\nNivel = ").append(niveles[opcion]);
        texto.append("\n\nHipótesis nula: la varianza poblacional es 1/12.");
        if (calculada >= inferior && calculada <= superior) texto.append("\nNo se rechaza H0.");
        else texto.append("\nSe rechaza H0.");
        return texto.toString();
    }
}
