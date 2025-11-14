package interfaces.app_tictactoe;

import javax.swing.SwingUtilities;
import interfaces.app_tictactoe.view.Configurador;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Configurador configurador = new Configurador();
            configurador.setVisible(true);
        });
    }
}