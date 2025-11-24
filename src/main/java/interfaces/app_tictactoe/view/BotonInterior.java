package interfaces.app_tictactoe.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; // CORREGIDO: Usar java.awt, no org.w3c

// Botón usado para representar una celda del tablero en la UI.
public class BotonInterior extends JButton {
    private int fila;
    private int columna;

    // Crea un botón de celda con posición y tema.
    public BotonInterior(int fila, int columna, String tema, int tamanio) {
        this.fila = fila;
        this.columna = columna;

        // 1. Configuración básica (Fuente, foco, tamaño)
        configurarBasicos(tamanio);

        // 2. Aplicar colores y lógica de Hover según el tema
        aplicarTema(tema);
    }

    /**
     * Configura propiedades que no dependen del color (Fuente, Foco).
     */
    private void configurarBasicos(int tamanio) {
        // La fuente es inversamente proporcional al tamaño del tablero para que la X o O quepan bien
        this.setFont(new Font("monospaced", Font.BOLD, 200 / tamanio));
        
        this.setFocusable(false);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
        
        this.setPreferredSize(new Dimension(100, 100)); 
    }

    /**
     * Define los colores y el comportamiento del mouse según el tema.
     */
    private void aplicarTema(String tema) {
        Color colorFondo;
        Color colorTexto;
        Color colorBorde;
        Color colorHover;

        if (tema.equals("OSCURO")) {
            colorFondo = new Color(80, 80, 80);
            colorTexto = Color.WHITE;
            colorBorde = Color.DARK_GRAY;
            colorHover = new Color(100, 100, 100);
        } else {
            // Tema CLARO
            colorFondo = Color.WHITE;
            colorTexto = Color.BLACK;
            colorBorde = Color.BLACK;
            colorHover = new Color(220, 220, 220);
        }

        // Aplicar colores estáticos
        this.setBackground(colorFondo);
        this.setForeground(colorTexto);
        this.setBorder(BorderFactory.createLineBorder(colorBorde));

        // Agregar la interacción (Hover)
        agregarEfectoHover(colorFondo, colorHover);
    }

    /**
     * Agrega el listener para cambiar el color al pasar el mouse.
     */
    private void agregarEfectoHover(Color colorNormal, Color colorHover) {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Al entrar, ponemos el color Hover
                setBackground(colorHover);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Al salir, regresamos al color Normal
                setBackground(colorNormal);
            }
        });
    }

    // --- Getters ---

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}