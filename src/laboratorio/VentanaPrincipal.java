package laboratorio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame implements ActionListener {
    private JComboBox<String> metodo;
    private JComboBox<String> prueba;
    private JComboBox<String> confianza;
    private JComboBox<String> decimales;
    private JTextField campoAlfa;
    private JTextField campoBeta;
    private JLabel etiquetaAlfa;
    private JLabel etiquetaBeta;
    private JTextField[] entradas;
    private JLabel[] etiquetas;
    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextArea area;
    private JLabel estado;
    private JButton generar;
    private JButton ejecutar;
    private JButton limpiar;
    private JButton salir;
    private ArrayList<Double> datos;
    private ArrayList<Long> internos;
    private JPanel parametros;

    public VentanaPrincipal() {
        super("Laboratorio de simulación estadística");
        datos = new ArrayList<Double>();
        internos = new ArrayList<Long>();
        construir();
    }

    private void construir() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1050,700);
        setMinimumSize(new Dimension(900,600));
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245,247,248));

        JLabel titulo = new JLabel("Laboratorio de simulación");
        titulo.setFont(new Font("Segoe UI",Font.BOLD,22));
        titulo.setForeground(new Color(30,70,120));
        titulo.setBorder(BorderFactory.createEmptyBorder(12,16,12,16));
        add(titulo,BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(1,2,10,0));
        centro.setBorder(BorderFactory.createEmptyBorder(8,10,8,10));
        centro.setBackground(new Color(245,247,248));
        centro.add(crearIzquierda());
        centro.add(crearDerecha());
        add(centro,BorderLayout.CENTER);

        JPanel inferior = new JPanel(new BorderLayout());
        inferior.setBorder(BorderFactory.createEmptyBorder(6,10,10,10));
        estado = new JLabel("Listo.");
        estado.setForeground(new Color(30,70,120));
        inferior.add(estado,BorderLayout.CENTER);
        JPanel botones = new JPanel();
        limpiar = new JButton("Limpiar");
        salir = new JButton("Salir");
        limpiar.addActionListener(this);
        salir.addActionListener(this);
        botones.add(limpiar);
        botones.add(salir);
        inferior.add(botones,BorderLayout.EAST);
        add(inferior,BorderLayout.SOUTH);
        cambiarMetodo();
        cambiarPrueba();
    }

    private JPanel crearIzquierda() {
        JPanel panel = new JPanel(new BorderLayout(6,6));
        panel.setBorder(BorderFactory.createLineBorder(new Color(210,215,220)));
        panel.setBackground(Color.WHITE);
        JPanel superior = new JPanel(new GridLayout(0,1,4,4));
        superior.setBackground(Color.WHITE);
        superior.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        superior.add(new JLabel("Método de generación"));
        metodo = new JComboBox<String>();
        metodo.addItem("Cuadrados medios");
        metodo.addItem("Productos medios");
        metodo.addItem("Multiplicador constante");
        metodo.addItem("Congruencial lineal");
        metodo.addItem("Congruencial aditivo");
        metodo.addItem("Congruencial multiplicativo");
        metodo.addItem("Congruencial cuadratico");
        superior.add(metodo);
        superior.add(new JLabel("Parametros"));
        parametros = new JPanel(new GridLayout(5,2,5,5));
        parametros.setBackground(Color.WHITE);
        etiquetas = new JLabel[5];
        entradas = new JTextField[5];
        for (int i = 0; i < 5; i++) {
            etiquetas[i] = new JLabel();
            entradas[i] = new JTextField();
            parametros.add(etiquetas[i]);
            parametros.add(entradas[i]);
        }
        superior.add(parametros);
        generar = new JButton("Generar numeros");
        generar.setBackground(new Color(30,100,170));
        generar.setForeground(Color.WHITE);
        generar.addActionListener(this);
        superior.add(generar);
        panel.add(superior,BorderLayout.NORTH);

        modelo = new DefaultTableModel();
        modelo.addColumn("Iteracion");
        modelo.addColumn("Valor interno");
        modelo.addColumn("Resultado r");
        tabla = new JTable(modelo);
        tabla.setRowHeight(22);
        panel.add(new JScrollPane(tabla),BorderLayout.CENTER);

        metodo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evento) {
                cambiarMetodo();
            }
        });
        return panel;
    }

    private JPanel crearDerecha() {
        JPanel panel = new JPanel(new BorderLayout(6,6));
        panel.setBorder(BorderFactory.createLineBorder(new Color(210,215,220)));
        panel.setBackground(Color.WHITE);
        JPanel superior = new JPanel(new GridLayout(0,2,6,6));
        superior.setBackground(Color.WHITE);
        superior.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        superior.add(new JLabel("Prueba estadistica"));
        prueba = new JComboBox<String>();
        prueba.addItem("Prueba de medias");
        prueba.addItem("Prueba de varianza");
        prueba.addItem("Prueba Chi-cuadrada");
        prueba.addItem("Kolmogorov-Smirnov");
        prueba.addItem("Corridas arriba y abajo");
        prueba.addItem("Corridas arriba y abajo de la media");
        prueba.addItem("Prueba de Poker");
        prueba.addItem("Prueba de huecos");
        superior.add(prueba);
        superior.add(new JLabel("Nivel de confianza"));
        confianza = new JComboBox<String>();
        confianza.addItem("80%");
        confianza.addItem("85%");
        confianza.addItem("90%");
        confianza.addItem("95%");
        confianza.addItem("99%");
        superior.add(confianza);
        superior.add(new JLabel("Decimales de Poker"));
        decimales = new JComboBox<String>();
        decimales.addItem("3");
        decimales.addItem("4");
        decimales.addItem("5");
        superior.add(decimales);
        etiquetaAlfa = new JLabel("Alfa");
        campoAlfa = new JTextField();
        etiquetaBeta = new JLabel("Beta");
        campoBeta = new JTextField();
        superior.add(etiquetaAlfa);
        superior.add(campoAlfa);
        superior.add(etiquetaBeta);
        superior.add(campoBeta);
        ejecutar = new JButton("Ejecutar prueba");
        ejecutar.addActionListener(this);
        superior.add(ejecutar);
        panel.add(superior,BorderLayout.NORTH);

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font(Font.MONOSPACED,Font.PLAIN,13));
        area.setText("Los resultados apareceran aqui.");
        panel.add(new JScrollPane(area),BorderLayout.CENTER);
        prueba.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent evento) {
                cambiarPrueba();
            }
        });
        return panel;
    }

    private void cambiarMetodo() {
        int seleccionado = metodo.getSelectedIndex();
        String[] nombres = {"Semilla X0","","","",""};
        if (seleccionado == 1) nombres = new String[]{"Semilla 1","Semilla 2","","",""};
        if (seleccionado == 2) nombres = new String[]{"Semilla constante","Semilla variable","","",""};
        if (seleccionado == 3) nombres = new String[]{"X0","a","c","m",""};
        if (seleccionado == 4) nombres = new String[]{"m","Valores separados por coma","","",""};
        if (seleccionado == 5) nombres = new String[]{"X","K","G","",""};
        if (seleccionado == 6) nombres = new String[]{"X0","m","a","b","c"};
        for (int i = 0; i < 5; i++) {
            etiquetas[i].setText(nombres[i].length() == 0 ? "" : nombres[i]);
            entradas[i].setVisible(nombres[i].length() != 0);
        }
    }

    private void cambiarPrueba() {
        int seleccionado = prueba.getSelectedIndex();
        boolean huecos = seleccionado == 7;
        etiquetaAlfa.setVisible(huecos);
        campoAlfa.setVisible(huecos);
        etiquetaBeta.setVisible(huecos);
        campoBeta.setVisible(huecos);
        decimales.setVisible(seleccionado == 6);
        if (seleccionado == 0) {
            confianza.removeAllItems();
            confianza.addItem("80%"); confianza.addItem("85%"); confianza.addItem("90%");
            confianza.addItem("95%"); confianza.addItem("99%");
        } else if (seleccionado == 1 || seleccionado == 2 || seleccionado == 6 || seleccionado == 7) {
            confianza.removeAllItems();
            confianza.addItem("80%"); confianza.addItem("90%"); confianza.addItem("95%"); confianza.addItem("99%");
        } else {
            confianza.removeAllItems();
            confianza.addItem("80%"); confianza.addItem("85%"); confianza.addItem("90%");
            confianza.addItem("95%"); confianza.addItem("99%");
        }
    }

    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == generar) generarDatos();
        else if (evento.getSource() == ejecutar) ejecutarPrueba();
        else if (evento.getSource() == limpiar) limpiarPantalla();
        else if (evento.getSource() == salir) System.exit(0);
    }

    private void generarDatos() {
        int seleccionado = metodo.getSelectedIndex();
        RegistroSimulacion registro;
        if (seleccionado == 0) registro = GeneradorCuadrados.generar(valor(0));
        else if (seleccionado == 1) registro = GeneradorProductos.generar(valor(0),valor(1));
        else if (seleccionado == 2) registro = GeneradorMultiplicador.generar(valor(0),valor(1));
        else if (seleccionado == 3) registro = GeneradorLineal.generar(valor(0),valor(1),valor(2),valor(3));
        else if (seleccionado == 4) registro = GeneradorAditivo.generar(valor(0),valor(1));
        else if (seleccionado == 5) registro = GeneradorMultiplicativo.generar(valor(0),valor(1),valor(2));
        else registro = GeneradorCuadratico.generar(valor(0),valor(1),valor(2),valor(3),valor(4));
        if (registro.obtenerError().length() > 0) {
            estado.setText(registro.obtenerError());
            area.setText(registro.obtenerError());
            return;
        }
        datos = registro.obtenerAleatorios();
        internos = registro.obtenerEnteros();
        modelo.setRowCount(0);
        for (int i = 0; i < datos.size(); i++) {
            String valorInterno = internos.size() > i ? String.valueOf(internos.get(i)) : "-";
            modelo.addRow(new Object[]{i+1,valorInterno,String.format(java.util.Locale.ROOT,"%.6f",datos.get(i))});
        }
        area.setText(registro.obtenerDetalle());
        estado.setText("Datos generados: " + datos.size() + ". Puede ejecutar varias pruebas.");
    }

    private String valor(int indice) {
        return entradas[indice].getText().trim();
    }

    private void ejecutarPrueba() {
        if (datos.isEmpty()) {
            estado.setText("Genere al menos un dato antes de ejecutar una prueba.");
            area.setText("No existen datos generados.");
            return;
        }
        int nivel = confianza.getSelectedIndex();
        int tipo = prueba.getSelectedIndex();
        String resultado;
        if (tipo == 0) resultado = PruebaDeMedias.ejecutar(datos,nivel);
        else if (tipo == 1) resultado = PruebaDeVarianza.ejecutar(datos,nivel);
        else if (tipo == 2) resultado = PruebaChiCuadrada.ejecutar(datos,nivel);
        else if (tipo == 3) resultado = PruebaKolmogorov.ejecutar(datos,nivel);
        else if (tipo == 4) resultado = PruebaCorridas.ejecutar(datos,nivel);
        else if (tipo == 5) resultado = PruebaDeCorridasMedias.ejecutar(datos,nivel);
        else if (tipo == 6) resultado = PruebaPoker.ejecutar(datos,Integer.parseInt((String)decimales.getSelectedItem()),nivel);
        else resultado = solicitarHuecos(nivel);
        area.setText(resultado);
        estado.setText("Prueba finalizada.");
    }

    private String solicitarHuecos(int nivel) {
        try {
            double alfa = Double.parseDouble(campoAlfa.getText().trim());
            double beta = Double.parseDouble(campoBeta.getText().trim());
            return PruebaDeHuecos.ejecutar(datos,alfa,beta,nivel);
        } catch (NumberFormatException error) {
            return "Alfa y beta deben ser numeros validos.";
        }
    }

    private void limpiarPantalla() {
        datos = new ArrayList<Double>();
        internos = new ArrayList<Long>();
        modelo.setRowCount(0);
        area.setText("Los resultados apareceran aqui.");
        estado.setText("Pantalla limpiada.");
        for (int i = 0; i < entradas.length; i++) entradas[i].setText("");
        campoAlfa.setText("");
        campoBeta.setText("");
    }
}
