package interfaces.app_tictactoe.view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanelInformativo extends JPanel {
    private JLabel etiqueta;
    private JLabel etiquetaTamanio;
    private JButton btnNuevaPartida;
    private JLabel etiquetaTiempo;

    public PanelInformativo(int tamanio, String tema) {

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Padding del Panel principal
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

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

        // Padding interno del botón
        EmptyBorder paddingBoton = new EmptyBorder(10, 25, 10, 25);

        // Lógica del Tema
        if (tema.equals("OSCURO")) {
            this.setBackground(new Color(30, 30, 30));
            etiqueta.setForeground(Color.WHITE);
            etiquetaTamanio.setForeground(Color.WHITE);
            etiquetaTiempo.setForeground(Color.WHITE);

            // Colores base botón Oscuro
            Color bgNormal = new Color(80, 80, 80);
            Color bgHover = new Color(100, 100, 100);

            btnNuevaPartida.setBackground(bgNormal);
            btnNuevaPartida.setForeground(Color.WHITE);

            // Borde compuesto
            btnNuevaPartida.setBorder(new CompoundBorder(
                    BorderFactory.createLineBorder(Color.DARK_GRAY),
                    paddingBoton));

            // Efecto Hover Oscuro
            btnNuevaPartida.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btnNuevaPartida.setBackground(bgHover);
                }

                public void mouseExited(MouseEvent e) {
                    btnNuevaPartida.setBackground(bgNormal);
                }
            });

        } else {
            // Tema CLARO
            this.setBackground(new Color(230, 230, 230));
            etiqueta.setForeground(Color.BLACK);
            etiquetaTamanio.setForeground(Color.BLACK);
            etiquetaTiempo.setForeground(Color.BLACK);

            // Colores base botón Claro
            Color bgNormal = Color.WHITE;
            Color bgHover = new Color(220, 220, 220);

            btnNuevaPartida.setBackground(bgNormal);
            btnNuevaPartida.setForeground(Color.BLACK);

            // Borde compuesto
            btnNuevaPartida.setBorder(new CompoundBorder(
                    BorderFactory.createLineBorder(Color.GRAY),
                    paddingBoton));

            // Efecto Hover Claro
            btnNuevaPartida.addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    btnNuevaPartida.setBackground(bgHover);
                }

                public void mouseExited(MouseEvent e) {
                    btnNuevaPartida.setBackground(bgNormal);
                }
            });
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