package laboratorio;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PruebaChiCuadrada {
    public static String ejecutar(ArrayList<Double> datos, int opcion) {
        if (datos == null || datos.size() < 2) return "Se necesitan al menos 2 datos.";
        int n = datos.size();
        int intervalos = (int) Math.sqrt(n);
        if (intervalos < 2) intervalos = 2;
        int gl = intervalos - 1;
        int[] observados = new int[intervalos];
        for (int i = 0; i < n; i++) {
            double numero = datos.get(i);
            if (numero < 0 || numero > 1) return "Todos los datos deben estar entre 0 y 1.";
            int posicion = (int) (numero * intervalos);
            if (posicion >= intervalos) posicion = intervalos - 1;
            observados[posicion]++;
        }
        double esperado = (double) n / intervalos;
        double calculada = 0;
        DecimalFormat f = new DecimalFormat("0.000000");
        StringBuilder texto = new StringBuilder("PRUEBA CHI-CUADRADA\n\nDatos utilizados:\n");
        texto.append(PruebaDeMedias.formatearLista(datos,f)).append("\n\nIntervalo\tOi\tEi\tContribución\n");
        for (int i = 0; i < intervalos; i++) {
            double termino = (observados[i] - esperado) * (observados[i] - esperado) / esperado;
            calculada += termino;
            texto.append(f.format((double)i/intervalos)).append(" - ").append(f.format((double)(i+1)/intervalos))
                .append("\t").append(observados[i]).append("\t").append(f.format(esperado)).append("\t").append(f.format(termino)).append("\n");
        }
        int[] columnas = {0,1,2,4};
        double critica = TablaCriticaChi.obtenerSuperior(gl, columnas[opcion]);
        if (critica < 0) return "No existe una fila para " + gl + " grados de libertad.";
        String[] niveles = {"80%","90%","95%","99%"};
        texto.append("\nIntervalos = ").append(intervalos).append("\nEsperado = ").append(f.format(esperado));
        texto.append("\nGrados de libertad = ").append(gl).append("\nNivel = ").append(niveles[opcion]);
        texto.append("\nChi calculada = ").append(f.format(calculada)).append("\nChi crítica = ").append(f.format(critica));
        texto.append("\n\nHipótesis nula: los números siguen una distribución uniforme.");
        if (calculada < critica) texto.append("\nNo se rechaza H0.");
        else texto.append("\nSe rechaza H0.");
        return texto.toString();
    }
}
