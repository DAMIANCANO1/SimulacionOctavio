package laboratorio;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PruebaDeHuecos {
    public static String ejecutar(ArrayList<Double> datos, double alfa, double beta, int opcion) {
        if (datos == null || datos.size() < 3) return "Se necesitan al menos 3 datos.";
        if (alfa < 0 || beta > 1 || alfa >= beta) return "El intervalo debe cumplir 0 <= alfa < beta <= 1.";
        StringBuilder secuencia = new StringBuilder();
        for (int i = 0; i < datos.size(); i++) {
            double numero = datos.get(i);
            if (numero < 0 || numero > 1) return "Todos los datos deben estar entre 0 y 1.";
            if (numero > alfa && numero < beta) secuencia.append("1");
            else secuencia.append("0");
        }
        ArrayList<Integer> huecos = obtenerHuecos(secuencia);
        if (huecos.isEmpty()) return "No se encontraron dos números dentro del intervalo.";
        int[] observados = new int[6];
        for (int i = 0; i < huecos.size(); i++) {
            int cantidad = huecos.get(i);
            if (cantidad >= 5) observados[5]++;
            else observados[cantidad]++;
        }
        double probabilidad = beta - alfa;
        double cero = 1 - probabilidad;
        double calculada = 0;
        DecimalFormat f = new DecimalFormat("0.000000");
        StringBuilder tabla = new StringBuilder("Tamaño\tOi\tEi\tContribución\n");
        for (int i = 0; i < 6; i++) {
            double esperado = probabilidad * huecos.size();
            if (i == 5) {
                esperado = huecos.size();
                for (int j = 0; j < 5; j++) esperado *= cero;
            } else {
                for (int j = 0; j < i; j++) esperado *= cero;
            }
            double termino = (observados[i] - esperado) * (observados[i] - esperado) / esperado;
            calculada += termino;
            if (i < 5) tabla.append(i);
            else tabla.append("5 o más");
            tabla.append("\t").append(observados[i]).append("\t").append(f.format(esperado)).append("\t").append(f.format(termino)).append("\n");
        }
        int[] columnas = {0,1,2,4};
        double critica = TablaCriticaChi.obtenerSuperior(5, columnas[opcion]);
        String[] niveles = {"80%","90%","95%","99%"};
        StringBuilder texto = new StringBuilder("PRUEBA DE HUECOS\n\nDatos utilizados:\n");
        texto.append(PruebaDeMedias.formatearLista(datos,f));
        texto.append("\nIntervalo abierto = (").append(f.format(alfa)).append(", ").append(f.format(beta)).append(")");
        texto.append("\nProbabilidad p = ").append(f.format(probabilidad));
        texto.append("\nSecuencia completa: ").append(secuencia);
        texto.append("\nHuecos encontrados: ").append(huecos).append("\n").append(tabla);
        texto.append("\nGrados de libertad = 5\nNivel = ").append(niveles[opcion]);
        texto.append("\nChi calculada = ").append(f.format(calculada)).append("\nChi crítica = ").append(f.format(critica));
        texto.append("\n\nHipótesis nula: los números son independientes.");
        if (calculada < critica) texto.append("\nNo se rechaza H0.");
        else texto.append("\nSe rechaza H0.");
        return texto.toString();
    }
    private static ArrayList<Integer> obtenerHuecos(StringBuilder secuencia) {
        ArrayList<Integer> huecos = new ArrayList<Integer>();
        int anterior = -1;
        for (int i = 0; i < secuencia.length(); i++) {
            if (secuencia.charAt(i) == '1') {
                if (anterior != -1) huecos.add(i - anterior - 1);
                anterior = i;
            }
        }
        return huecos;
    }
}
