package laboratorio;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class PruebaPoker {
    public static String ejecutar(ArrayList<Double> datos, int decimales, int opcion) {
        if (datos == null || datos.isEmpty()) return "No hay datos para realizar la prueba.";
        String[] categorias = categorias(decimales);
        double[] probabilidades = probabilidades(decimales);
        int[] observados = new int[categorias.length];
        StringBuilder detalle = new StringBuilder();
        DecimalFormat numero = new DecimalFormat("0." + ceros(decimales));
        for (int i = 0; i < datos.size(); i++) {
            double valor = datos.get(i);
            if (valor < 0 || valor >= 1) return "Todos los datos deben estar entre 0 y 1.";
            String texto = numero.format(valor);
            String digitos = texto.substring(2);
            int categoria = clasificar(digitos, decimales);
            observados[categoria]++;
            detalle.append(texto).append(" = ").append(categorias[categoria]).append("\n");
        }
        DecimalFormat f = new DecimalFormat("0.000000");
        double calculada = 0;
        StringBuilder tabla = new StringBuilder();
        for (int i = 0; i < categorias.length; i++) {
            double esperado = probabilidades[i] * datos.size();
            double termino = (esperado - observados[i]) * (esperado - observados[i]) / esperado;
            calculada += termino;
            tabla.append(categorias[i]).append("\tOi=").append(observados[i]).append("\tEi=").append(f.format(esperado))
                .append("\tContribución=").append(f.format(termino)).append("\n");
        }
        int gl = categorias.length - 1;
        int[] columnas = {0,1,2,4};
        double critica = TablaCriticaChi.obtenerSuperior(gl, columnas[opcion]);
        if (critica < 0) return "No existe una fila para " + gl + " grados de libertad.";
        String[] niveles = {"80%","90%","95%","99%"};
        StringBuilder texto = new StringBuilder("PRUEBA DE PÓKER\n\n");
        texto.append(detalle).append("\n").append(tabla).append("\nDecimales = ").append(decimales);
        texto.append("\nGrados de libertad = ").append(gl).append("\nNivel = ").append(niveles[opcion]);
        texto.append("\nChi calculada = ").append(f.format(calculada)).append("\nChi crítica = ").append(f.format(critica));
        texto.append("\n\nHipótesis nula: los números son independientes.");
        if (calculada < critica) texto.append("\nNo se rechaza H0.");
        else texto.append("\nSe rechaza H0.");
        return texto.toString();
    }
    private static String ceros(int cantidad) {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < cantidad; i++) texto.append("0");
        return texto.toString();
    }
    private static int clasificar(String digitos, int decimales) {
        int[] cantidades = new int[10];
        for (int i = 0; i < digitos.length(); i++) cantidades[digitos.charAt(i)-'0']++;
        int pares = 0, mayor = 0;
        for (int i = 0; i < 10; i++) {
            if (cantidades[i] == 2) pares++;
            if (cantidades[i] > mayor) mayor = cantidades[i];
        }
        if (mayor == 1) return 0;
        if (mayor == 2 && pares == 1) return 1;
        if (mayor == 2 && pares == 2) return 2;
        if (mayor == 3 && pares == 0) return decimales == 3 ? 2 : 3;
        if (mayor == 3 && pares == 1) return 4;
        if (mayor == 4) return decimales == 4 ? 4 : 5;
        if (decimales == 5) return 6;
        return 4;
    }
    private static String[] categorias(int decimales) {
        if (decimales == 3) return new String[]{"Todos diferentes","Un par","Tercia"};
        if (decimales == 4) return new String[]{"Todos diferentes","Un par","Dos pares","Tercia","Póker"};
        return new String[]{"Todos diferentes","Un par","Dos pares","Tercia","Tercia y par","Póker","Quintilla"};
    }
    private static double[] probabilidades(int decimales) {
        if (decimales == 3) return new double[]{0.72,0.27,0.01};
        if (decimales == 4) return new double[]{0.5040,0.4320,0.0270,0.0360,0.0016};
        return new double[]{0.3024,0.5040,0.1080,0.0720,0.0090,0.0045,0.0001};
    }
}
