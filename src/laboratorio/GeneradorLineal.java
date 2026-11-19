package laboratorio;

import java.util.ArrayList;

public class GeneradorLineal {
    public static RegistroSimulacion generar(String xTexto, String aTexto, String cTexto, String mTexto) {
        long x, a, c, m;
        try {
            x = Long.parseLong(xTexto.trim()); a = Long.parseLong(aTexto.trim());
            c = Long.parseLong(cTexto.trim()); m = Long.parseLong(mTexto.trim());
        } catch (NumberFormatException error) {
            return new RegistroSimulacion("Todas las entradas deben ser enteros.");
        }
        if (x < 0 || a < 0 || c < 0 || m <= 1 || x >= m) return new RegistroSimulacion("X0, a y c no pueden ser negativos; m debe ser mayor que 1 y X0 menor que m.");
        ArrayList<Double> ri = new ArrayList<Double>();
        ArrayList<Long> internos = new ArrayList<Long>();
        StringBuilder detalle = new StringBuilder("CONGRUENCIAL LINEAL\n");
        for (int i = 0; i < 10; i++) {
            x = (a * x + c) % m;
            double resultado = (double) x / m;
            ri.add(resultado); internos.add(x);
            detalle.append("Iteración ").append(i + 1).append(": X = ").append(x).append(", ri = ").append(resultado).append("\n");
        }
        return new RegistroSimulacion(ri, internos, detalle.toString());
    }
}
