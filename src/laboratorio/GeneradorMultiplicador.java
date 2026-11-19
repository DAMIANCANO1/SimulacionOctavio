package laboratorio;

import java.util.ArrayList;

public class GeneradorMultiplicador {
    public static RegistroSimulacion generar(String constanteTexto, String variableTexto) {
        String constanteCadena = constanteTexto.trim();
        String variableCadena = variableTexto.trim();
        if (!valida(constanteCadena) || !valida(variableCadena)) return new RegistroSimulacion("Las semillas deben tener de 2 a 9 dígitos y no comenzar con cero.");
        if (constanteCadena.length() != variableCadena.length()) return new RegistroSimulacion("Las semillas deben tener la misma longitud.");
        long constante = Long.parseLong(constanteCadena);
        long variable = Long.parseLong(variableCadena);
        int d = constanteCadena.length();
        ArrayList<Double> ri = new ArrayList<Double>();
        ArrayList<Long> internos = new ArrayList<Long>();
        StringBuilder detalle = new StringBuilder("MULTIPLICADOR CONSTANTE\n");
        for (int i = 0; i < 10; i++) {
            long multiplicacion = constante * variable;
            String completo = String.valueOf(multiplicacion);
            while (completo.length() < d || (completo.length() - d) % 2 != 0) completo = "0" + completo;
            int inicio = (completo.length() - d) / 2;
            String centrales = completo.substring(inicio, inicio + d);
            double resultado = Double.parseDouble("0." + centrales);
            ri.add(resultado);
            internos.add(variable);
            detalle.append("Iteración ").append(i + 1).append("\nMultiplicación: ").append(constante).append(" x ")
                .append(variable).append(" = ").append(multiplicacion).append("\nResultado completo: ").append(completo)
                .append("\nDígitos centrales: ").append(centrales).append("\nri: ").append(resultado).append("\n\n");
            variable = Long.parseLong(centrales);
        }
        return new RegistroSimulacion(ri, internos, detalle.toString());
    }
    private static boolean valida(String texto) {
        if (texto.length() < 2 || texto.length() > 9 || texto.charAt(0) == '0') return false;
        for (int i = 0; i < texto.length(); i++) if (texto.charAt(i) < '0' || texto.charAt(i) > '9') return false;
        return true;
    }
}
