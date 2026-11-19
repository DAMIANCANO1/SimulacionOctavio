package laboratorio;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

public class PruebaKolmogorov {
    public static String ejecutar(ArrayList<Double> datos, int opcion) {
        if (datos == null || datos.isEmpty()) return "No hay datos para realizar la prueba.";
        ArrayList<Double> ordenados = new ArrayList<Double>(datos);
        Collections.sort(ordenados);
        double mas = 0, menos = 0;
        DecimalFormat f = new DecimalFormat("0.000000");
        StringBuilder texto = new StringBuilder("PRUEBA KOLMOGOROV-SMIRNOV\n\nDatos originales:\n");
        texto.append(PruebaDeMedias.formatearLista(datos,f)).append("\n\ni\ti/n\tri\t(i-1)/n\tD+\tD-\n");
        int n = ordenados.size();
        for (int i = 0; i < n; i++) {
            double numero = ordenados.get(i);
            if (numero < 0 || numero > 1) return "Todos los datos deben estar entre 0 y 1.";
            double iN = (double)(i + 1) / n;
            double anterior = (double)i / n;
            double dp = iN - numero;
            double dm = numero - anterior;
            if (dp > mas) mas = dp;
            if (dm > menos) menos = dm;
            texto.append(i+1).append("\t").append(f.format(iN)).append("\t").append(f.format(numero))
                .append("\t").append(f.format(anterior)).append("\t").append(f.format(dp)).append("\t").append(f.format(dm)).append("\n");
        }
        double calculada = mas > menos ? mas : menos;
        double[] constantes = {1.07,1.14,1.22,1.36,1.63};
        String[] niveles = {"80%","85%","90%","95%","99%"};
        double critica = constantes[opcion] / Math.sqrt(n);
        texto.append("\nD+ = ").append(f.format(mas)).append("\nD- = ").append(f.format(menos));
        texto.append("\nD calculada = ").append(f.format(calculada)).append("\nNivel = ").append(niveles[opcion]);
        texto.append("\nD crítica = ").append(f.format(critica));
        texto.append("\n\nHipótesis nula: los números siguen una distribución uniforme.");
        if (calculada >= critica) texto.append("\nSe rechaza H0.");
        else texto.append("\nNo se rechaza H0.");
        return texto.toString();
    }
}
