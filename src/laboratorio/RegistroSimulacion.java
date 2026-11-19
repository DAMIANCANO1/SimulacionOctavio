package laboratorio;

import java.util.ArrayList;

public class RegistroSimulacion {
    private ArrayList<Double> aleatorios;
    private ArrayList<Long> enteros;
    private String detalle;
    private String error;

    public RegistroSimulacion(ArrayList<Double> aleatorios, ArrayList<Long> enteros, String detalle) {
        this.aleatorios = aleatorios;
        this.enteros = enteros;
        this.detalle = detalle;
        this.error = "";
    }

    public RegistroSimulacion(String error) {
        this.aleatorios = new ArrayList<Double>();
        this.enteros = new ArrayList<Long>();
        this.detalle = "";
        this.error = error;
    }

    public ArrayList<Double> obtenerAleatorios() {
        return aleatorios;
    }

    public ArrayList<Long> obtenerEnteros() {
        return enteros;
    }

    public String obtenerDetalle() {
        return detalle;
    }

    public String obtenerError() {
        return error;
    }
}
