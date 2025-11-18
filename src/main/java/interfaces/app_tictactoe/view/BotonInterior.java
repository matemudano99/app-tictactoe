package interfaces.app_tictactoe.view;

import javax.swing.*;
import java.awt.*;

// Botón usado para representar una celda del tablero en la UI.
public class BotonInterior extends JButton {
    private int fila;
    private int columna;

    // Crea un botón de celda con posición y tema (claro/oscuro).
    BotonInterior(int fila, int columna, String tema, int tamanio) {
        this.fila = fila;
        this.columna = columna;
        this.setSize(300, 300);

        // utiliza la fuente para ser inversamente proporcional al tamaño del tablero
        this.setFont(new Font("monospaced", Font.BOLD, 200 / tamanio));
        this.setFocusable(false);

        if (tema.equals("OSCURO")) {
            this.setBackground(new Color(80, 80, 80));
            this.setForeground(Color.WHITE); // Símbolo
            this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        } else {
            // Tema CLARO
            this.setBackground(Color.WHITE);
            this.setForeground(Color.BLACK); // Símbolo
            this.setBorder(BorderFactory.createLineBorder(Color.black));
        }
    }

    // Devuelve la fila (0-based) del botón.
    public int getFila() {
        return fila;
    }

    // Devuelve la columna (0-based) del botón.
    public int getColumna() {
        return columna;
    }
}