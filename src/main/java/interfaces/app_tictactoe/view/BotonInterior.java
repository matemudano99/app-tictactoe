package interfaces.app_tictactoe.view;

import javax.swing.*;
import java.awt.*;

public class BotonInterior extends JButton {
    private int fila;
    private int columna; 

    BotonInterior(int fila, int columna, String tema) {
        this.fila = fila;
        this.columna = columna;

        this.setSize(300, 300);
        this.setFont(new Font("monospaced", Font.BOLD, 70));
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

    // Getters para que el controlador sepa qué botón se pulsó
    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna; 
    }
}