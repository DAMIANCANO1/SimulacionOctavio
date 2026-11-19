package laboratorio;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PruebaDeMedias {
    public static String ejecutar(ArrayList<Double> datos, int opcion) {
        if (datos == null || datos.isEmpty()) return "No hay datos para realizar la prueba.";
        double[] z = {1.28,1.44,1.64,1.96,2.58};
        String[] niveles = {"80%","85%","90%","95%","99%"};
        double suma = 0;
        for (int i = 0; i < datos.size(); i++) suma += datos.get(i);
        double media = suma / datos.size();
        double inferior = 0.5 - z[opcion] / Math.sqrt(12.0 * datos.size());
        double superior = 0.5 + z[opcion] / Math.sqrt(12.0 * datos.size());
        DecimalFormat f = new DecimalFormat("0.000000");
        StringBuilder texto = new StringBuilder("PRUEBA DE MEDIAS\n\nDatos utilizados:\n");
        texto.append(formatearLista(datos,f));
        texto.append("\nFórmula: suma(ri) / n\nMedia = ").append(f.format(media));
        texto.append("\nLímite inferior = ").append(f.format(inferior)).append("\nLímite superior = ").append(f.format(superior));
        texto.append("\nNivel = ").append(niveles[opcion]).append(", Z = ").append(z[opcion]);
        texto.append("\n\nHipótesis nula: la media poblacional es 0.5.");
        if (media >= inferior && media <= superior) texto.append("\nNo se rechaza H0.");
        else texto.append("\nSe rechaza H0.");
        return texto.toString();
    }
    public static String formatearLista(ArrayList<Double> datos, DecimalFormat f) {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < datos.size(); i++) {
            texto.append(f.format(datos.get(i)));
            if (i + 1 < datos.size()) texto.append(", ");
        }
        return texto.toString();
    }
}
