package laboratorio;

import java.util.ArrayList;

public class GeneradorProductos {
    public static RegistroSimulacion generar(String primeraTexto, String segundaTexto) {
        String uno = primeraTexto.trim();
        String dos = segundaTexto.trim();
        if (!valida(uno) || !valida(dos)) return new RegistroSimulacion("Las semillas deben tener de 2 a 9 dígitos y no comenzar con cero.");
        if (uno.length() != dos.length()) return new RegistroSimulacion("Las semillas deben tener la misma longitud.");
        long s1 = Long.parseLong(uno);
        long s2 = Long.parseLong(dos);
        int d = uno.length();
        ArrayList<Double> ri = new ArrayList<Double>();
        ArrayList<Long> internos = new ArrayList<Long>();
        StringBuilder detalle = new StringBuilder("PRODUCTOS MEDIOS\n");
        for (int i = 0; i < 10; i++) {
            long producto = s1 * s2;
            String completo = completar(String.valueOf(producto), d * 2);
            int inicio = (completo.length() - d) / 2;
            String centrales = completo.substring(inicio, inicio + d);
            double resultado = Double.parseDouble("0." + centrales);
            ri.add(resultado);
            internos.add(s1);
            detalle.append("Iteración ").append(i + 1).append("\nProducto: ").append(s1).append(" x ").append(s2)
                .append(" = ").append(producto).append("\nResultado completo: ").append(completo)
                .append("\nDígitos centrales: ").append(centrales).append("\nri: ").append(resultado).append("\n\n");
            s1 = s2;
            s2 = Long.parseLong(centrales);
        }
        return new RegistroSimulacion(ri, internos, detalle.toString());
    }
    private static boolean valida(String texto) {
        if (texto.length() < 2 || texto.length() > 9 || texto.charAt(0) == '0') return false;
        for (int i = 0; i < texto.length(); i++) if (texto.charAt(i) < '0' || texto.charAt(i) > '9') return false;
        return true;
    }
    private static String completar(String texto, int longitud) {
        while (texto.length() < longitud) texto = "0" + texto;
        return texto;
    }
}
