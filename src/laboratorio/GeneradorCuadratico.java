package laboratorio;

import java.util.ArrayList;

public class GeneradorCuadratico {
    public static RegistroSimulacion generar(String xTexto, String mTexto, String aTexto, String bTexto, String cTexto) {
        long x, m, a, b, c;
        try {
            x = Long.parseLong(xTexto.trim()); m = Long.parseLong(mTexto.trim()); a = Long.parseLong(aTexto.trim());
            b = Long.parseLong(bTexto.trim()); c = Long.parseLong(cTexto.trim());
        } catch (NumberFormatException error) { return new RegistroSimulacion("Todas las entradas deben ser enteros."); }
        if (x < 0 || m < 2 || m > 100000 || a < 0 || b < 0 || c < 1 || x >= m || a % 2 != 0 || c % 2 != 1) {
            return new RegistroSimulacion("Valores fuera de rango o condiciones incumplidas.");
        }
        a = a % m; b = b % m; c = c % m;
        ArrayList<Double> ri = new ArrayList<Double>();
        ArrayList<Long> internos = new ArrayList<Long>();
        StringBuilder detalle = new StringBuilder("CONGRUENCIAL CUADRÁTICO\n");
        for (int i = 0; i < m; i++) {
            x = (a * x * x + b * x + c) % m;
            double resultado = (double) x / m;
            ri.add(resultado); internos.add(x);
            detalle.append("Iteración ").append(i + 1).append(": X = ").append(x).append(", ri = ").append(resultado).append("\n");
        }
        return new RegistroSimulacion(ri, internos, detalle.toString());
    }
}
