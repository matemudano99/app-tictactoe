package interfaces.app_tictactoe.view;

import interfaces.app_tictactoe.controller.TicTacToe;
import javax.swing.*;
import java.awt.*;

// Ventana de configuración: recoge parámetros antes de iniciar la partida.
public class Configurador extends JFrame {

    // Componentes de la UI
    final private JSpinner spinnerTamanio;
    final private JTextField txtNombreJugadorX;
    final private JTextField txtNombreJugadorO;
    final private JRadioButton radioTemaClaro;
    final private JRadioButton radioTemaOscuro;
    final private ButtonGroup grupoTema;
    final private JButton btnIniciar;
    final private JTextField txtSimboloJugadorX;
    final private JTextField txtSimboloJugadorO;

    // Construye la UI de configuración y sus componentes.
    public Configurador() {
        setTitle("Configuración de Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 350); // Aumentado para los nuevos campos
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel panelFormulario = new JPanel(new GridLayout(0, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. Tamaño
        panelFormulario.add(new JLabel("Tamaño del Tablero (N x N):"));
        SpinnerNumberModel modelTamanio = new SpinnerNumberModel(3, 3, 10, 1);
        spinnerTamanio = new JSpinner(modelTamanio);
        panelFormulario.add(spinnerTamanio);

        // 2. Jugador 1 Nombre
        panelFormulario.add(new JLabel("Nombre Jugador 1:"));
        txtNombreJugadorX = new JTextField("Jugador 1");
        panelFormulario.add(txtNombreJugadorX);

        // 3. Jugador 1 Símbolo
        panelFormulario.add(new JLabel("Símbolo Jugador 1:"));
        txtSimboloJugadorX = new JTextField("X");
        panelFormulario.add(txtSimboloJugadorX);

        // 4. Jugador 2 Nombre
        panelFormulario.add(new JLabel("Nombre Jugador 2:"));
        txtNombreJugadorO = new JTextField("Jugador 2");
        panelFormulario.add(txtNombreJugadorO);

        // 5. Jugador 2 Símbolo
        panelFormulario.add(new JLabel("Símbolo Jugador 2:"));
        txtSimboloJugadorO = new JTextField("O");
        panelFormulario.add(txtSimboloJugadorO);

        // 6. Tema
        panelFormulario.add(new JLabel("Tema Visual:"));
        radioTemaClaro = new JRadioButton("Claro");
        radioTemaClaro.setSelected(true);
        radioTemaOscuro = new JRadioButton("Oscuro");
        grupoTema = new ButtonGroup();
        grupoTema.add(radioTemaClaro);
        grupoTema.add(radioTemaOscuro);
        JPanel panelTema = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTema.add(radioTemaClaro);
        panelTema.add(radioTemaOscuro);
        panelFormulario.add(panelTema);

        // Panel del botón
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnIniciar = new JButton("Iniciar Juego");
        panelBoton.add(btnIniciar);

        add(panelFormulario, BorderLayout.CENTER);
        add(panelBoton, BorderLayout.SOUTH);

        btnIniciar.addActionListener(e -> onIniciarJuego());
    }

    // Lee los campos, valida y lanza una nueva partida si todo es válido.
    private void onIniciarJuego() {
        // 1. Recopilar datos
        int tamanio = (int) spinnerTamanio.getValue();
        String nombreX = txtNombreJugadorX.getText().trim();
        String nombreO = txtNombreJugadorO.getText().trim();
        String tema = radioTemaClaro.isSelected() ? "CLARO" : "OSCURO";
        String strSimboloX = txtSimboloJugadorX.getText().trim();
        String strSimboloO = txtSimboloJugadorO.getText().trim();

        // 2. Validación (Nombres)
        if (nombreX.isEmpty() || nombreO.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los nombres no pueden estar vacíos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (nombreX.equalsIgnoreCase(nombreO)) {
            JOptionPane.showMessageDialog(this, "Los jugadores deben tener nombres diferentes.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Validación (Símbolos)
        if (strSimboloX.isEmpty() || strSimboloO.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Los símbolos no pueden estar vacíos.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (strSimboloX.length() > 1 || strSimboloO.length() > 1) {
            JOptionPane.showMessageDialog(this, "Los símbolos deben ser un solo carácter.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (strSimboloX.equalsIgnoreCase(strSimboloO)) {
            JOptionPane.showMessageDialog(this, "Los jugadores deben tener símbolos diferentes.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (strSimboloX.equals(" ") || strSimboloO.equals(" ")) {
            JOptionPane.showMessageDialog(this, "El símbolo no puede ser un espacio vacío.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        char simboloX = strSimboloX.charAt(0);
        char simboloO = strSimboloO.charAt(0);

        // 4. Crear el juego con los 6 argumentos
        TicTacToe juego = new TicTacToe(tamanio, nombreX, nombreO, tema, simboloX, simboloO);

        this.dispose();
    }
}