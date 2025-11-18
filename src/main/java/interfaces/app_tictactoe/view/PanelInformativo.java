package interfaces.app_tictactoe.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class PanelInformativo extends JPanel {
    private JLabel etiqueta;
    private JLabel etiquetaTamanio;
    private JButton btnNuevaPartida;
    private JLabel etiquetaTiempo;

    public PanelInformativo(int tamanio, String tema) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Crear los labels
        this.etiquetaTamanio = new JLabel(tamanio + "x" + tamanio);
        this.etiqueta = new JLabel("Cargando...");
        this.etiquetaTiempo = new JLabel("Sin tiempo limite");

        // Fuentes de letra
        Font font = new Font("monospaced", Font.BOLD, 18);
        etiquetaTiempo.setFont(font);
        Font font2 = new Font("monospaced", Font.BOLD, 24);
        etiqueta.setFont(font2);
        Font font3 = new Font("monospaced", Font.BOLD, 20);
        etiquetaTamanio.setFont(font3);

        // Alineado al centro
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaTamanio.setAlignmentX(Component.CENTER_ALIGNMENT);
        etiquetaTiempo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Crear el botón
        this.btnNuevaPartida = new JButton("Nueva Partida");
        btnNuevaPartida.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNuevaPartida.setFont(new Font("monospaced", Font.BOLD, 20));
        btnNuevaPartida.setFocusable(false);
        // Ancho: 200px, Alto: 80px
        btnNuevaPartida.setPreferredSize(new Dimension(100, 40));

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
            this.setBackground(new Color(230, 230, 230));
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
        this.add(Box.createRigidArea(new Dimension(0, 10)));
        this.add(etiqueta);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(etiquetaTiempo);
        this.add(Box.createRigidArea(new Dimension(0, 15)));
        this.add(btnNuevaPartida); // Añadir botón
        this.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public void setTextoEtiqueta(String texto) {
        this.etiqueta.setText(texto);
    }

    public void actualizarTiempo(int segundos) {
        this.etiquetaTiempo.setText(segundos + "s");
    }

    // Getter para que el controlador acceda al botón
    public JButton getBtnNuevaPartida() {
        return btnNuevaPartida;
    }
}