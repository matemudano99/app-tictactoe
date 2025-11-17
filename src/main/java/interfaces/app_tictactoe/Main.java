package interfaces.app_tictactoe;

import javax.swing.SwingUtilities;
import interfaces.app_tictactoe.view.Configurador;

// Clase principal que inicia la aplicación de Tic Tac Toe.
public class Main {
    // Crea y muestra la ventana principal en el hilo de Swing.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Configurador configurador = new Configurador();
            configurador.setVisible(true);
        });
    }
}