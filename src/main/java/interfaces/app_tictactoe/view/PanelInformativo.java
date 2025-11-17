package interfaces.app_tictactoe.view;

import javax.swing.*;
import java.awt.*;

public class PanelInformativo extends JPanel {
    private JLabel etiqueta;
    private JLabel etiquetaTamanio;
    private JButton btnNuevaPartida;
    private JLabel etiquetaTiempo;

    public PanelInformativo(int tamanio, String tema) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Crear los labels
        this.etiquetaTamanio = new JLabel("Tamaño del tablero: " + tamanio + "x" + tamanio);
        this.etiqueta = new JLabel("Cargando...");
        this.etiquetaTiempo = new JLabel("Tiempo: 15s");

        // Fuentes de letra
        Font font = new Font("monospaced", Font.BOLD, 24);
        etiquetaTiempo.setFont(font);
        etiqueta.setFont(font);
        etiquetaTamanio.setFont(font);

        //Alineado al centro
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaTamanio.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaTiempo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Crear el botón
        this.btnNuevaPartida = new JButton("Nueva Partida");
        btnNuevaPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNuevaPartida.setFont(new Font("Arial", Font.BOLD, 14));
        btnNuevaPartida.setFocusable(false);

        // Lógica del Tema
        if (tema.equals("OSCURO")) {
            this.setBackground(new Color(30, 30, 30));
            etiqueta.setForeground(Color.WHITE);
            etiquetaTamanio.setForeground(Color.WHITE);
            etiquetaTiempo.setForeground(Color.WHITE);
            btnNuevaPartida.setBackground(new Color(80, 80, 80));
            btnNuevaPartida.setForeground(Color.WHITE);
            btnNuevaPartida.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        } else {
            // Tema CLARO
            this.setBackground(new Color(200, 200, 200));
            etiqueta.setForeground(Color.BLACK);
            etiquetaTamanio.setForeground(Color.BLACK);
            etiquetaTiempo.setForeground(Color.BLACK);
            btnNuevaPartida.setBackground(Color.WHITE);
            btnNuevaPartida.setForeground(Color.BLACK);
            btnNuevaPartida.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        }

        // Añadir componentes
        this.add(etiquetaTamanio);
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(new JSeparator(SwingConstants.HORIZONTAL));
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(etiqueta);
        this.add(Box.createRigidArea(new Dimension(0, 15))); // Espacio
        this.add(etiquetaTiempo);
        this.add(btnNuevaPartida); // Añadir botón
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public void setTextoEtiqueta(String texto) {
        this.etiqueta.setText(texto);
    }

    public void actualizarTiempo(int segundos) {
        this.etiquetaTiempo.setText("Tiempo: " + segundos + "s");
    }

    // Getter para que el controlador acceda al botón
    public JButton getBtnNuevaPartida() {
        return btnNuevaPartida;
    }
}