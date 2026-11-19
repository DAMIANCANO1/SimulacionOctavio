package laboratorio;

import java.util.ArrayList;

public class GeneradorCuadrados {
    public static RegistroSimulacion generar(String texto) {
        String valorTexto = texto.trim();
        if (!semillaValida(valorTexto)) {
            return new RegistroSimulacion("La semilla debe tener de 2 a 9 dígitos y no comenzar con cero.");
        }
        long valor = Long.parseLong(valorTexto);
        int digitos = valorTexto.length();
        ArrayList<Double> ri = new ArrayList<Double>();
        ArrayList<Long> internos = new ArrayList<Long>();
        StringBuilder detalle = new StringBuilder("CUADRADOS MEDIOS\n");
        for (int i = 0; i < 10; i++) {
            long cuadrado = valor * valor;
            String completo = completar(String.valueOf(cuadrado), digitos * 2);
            int inicio = (completo.length() - digitos) / 2;
            String centrales = completo.substring(inicio, inicio + digitos);
            double resultado = Double.parseDouble("0." + centrales);
            ri.add(resultado);
            internos.add(valor);
            detalle.append("Iteración ").append(i + 1).append("\nValor original: ").append(valor)
                .append("\nCuadrado: ").append(cuadrado).append("\nResultado completo: ").append(completo)
                .append("\nDígitos centrales: ").append(centrales).append("\nri: ").append(resultado).append("\n\n");
            valor = Long.parseLong(centrales);
        }
        return new RegistroSimulacion(ri, internos, detalle.toString());
    }

    private static boolean semillaValida(String texto) {
        if (texto.length() < 2 || texto.length() > 9 || texto.charAt(0) == '0') return false;
        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) < '0' || texto.charAt(i) > '9') return false;
        }
        return true;
    }

    private static String completar(String texto, int longitud) {
        while (texto.length() < longitud) texto = "0" + texto;
        return texto;
    }
}
