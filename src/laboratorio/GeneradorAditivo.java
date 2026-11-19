package laboratorio;

import java.util.ArrayList;

public class GeneradorAditivo {
    public static RegistroSimulacion generar(String mTexto, String valoresTexto) {
        long m;
        try { m = Long.parseLong(mTexto.trim()); } catch (NumberFormatException error) { return new RegistroSimulacion("Ingrese un entero válido para m."); }
        if (m <= 1) return new RegistroSimulacion("m debe ser mayor que 1.");
        ArrayList<Long> iniciales = new ArrayList<Long>();
        String[] partes = valoresTexto.split(",");
        for (int i = 0; i < partes.length; i++) {
            String parte = partes[i].trim();
            if (parte.length() == 0) continue;
            long numero;
            try { numero = Long.parseLong(parte); } catch (NumberFormatException error) { return new RegistroSimulacion("Todos los valores iniciales deben ser enteros."); }
            if (numero < 0 || numero >= m) return new RegistroSimulacion("Cada valor inicial debe estar entre 0 y m - 1.");
            iniciales.add(numero);
        }
        if (iniciales.size() < 5) return new RegistroSimulacion("Debe ingresar al menos 5 valores separados por comas.");
        int n = iniciales.size();
        ArrayList<Double> ri = new ArrayList<Double>();
        ArrayList<Long> internos = new ArrayList<Long>();
        StringBuilder detalle = new StringBuilder("CONGRUENCIAL ADITIVO\n");
        for (int i = 0; i < 10; i++) {
            long x = (iniciales.get(iniciales.size() - 1) + iniciales.get(iniciales.size() - n)) % m;
            double resultado = (double) x / m;
            ri.add(resultado); internos.add(x); iniciales.add(x);
            detalle.append("Nueva iteración ").append(i + 1).append(": X = ").append(x).append(", ri = ").append(resultado).append("\n");
        }
        return new RegistroSimulacion(ri, internos, detalle.toString());
    }
}
