package laboratorio;

import java.util.ArrayList;

public class GeneradorMultiplicativo {
    public static RegistroSimulacion generar(String xTexto, String kTexto, String gTexto) {
        long x, k;
        int g;
        try {
            x = Long.parseLong(xTexto.trim()); k = Long.parseLong(kTexto.trim()); g = Integer.parseInt(gTexto.trim());
        } catch (NumberFormatException error) { return new RegistroSimulacion("Todas las entradas deben ser enteros."); }
        if (x <= 0 || k < 0 || g < 1 || g > 30) return new RegistroSimulacion("X debe ser mayor que 0, K no negativo y G entre 1 y 30.");
        long m = 1L << g;
        if (x >= m) return new RegistroSimulacion("X debe ser menor que m = 2^G.");
        long a = 3L + 8L * k;
        ArrayList<Long> vistos = new ArrayList<Long>();
        ArrayList<Double> ri = new ArrayList<Double>();
        ArrayList<Long> internos = new ArrayList<Long>();
        StringBuilder detalle = new StringBuilder("CONGRUENCIAL MULTIPLICATIVO\n");
        int indice = 1;
        while (true) {
            x = (a * x) % m;
            if (vistos.contains(x)) break;
            vistos.add(x);
            double resultado = (double) x / m;
            ri.add(resultado); internos.add(x);
            detalle.append("Iteración ").append(indice).append(": X = ").append(x).append(", ri = ").append(resultado).append("\n");
            indice++;
        }
        detalle.append("Dato repetido detenido: no incluido.\nCantidad generada: ").append(ri.size()).append("\n");
        return new RegistroSimulacion(ri, internos, detalle.toString());
    }
}
