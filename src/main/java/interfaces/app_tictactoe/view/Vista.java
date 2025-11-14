package interfaces.app_tictactoe.view;

import javax.swing.*;
import java.awt.*;

public class Vista extends JFrame {

    private PanelInformativo panelInformativo;
    private PanelPrincipal panelPrincipal;

    public Vista(int tamanio, String tema) {
        this.setTitle("TicTacToe");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        // Altura aumentada a 500 para el botón "Nueva Partida"
        this.setSize(400, 500); 
        
        ImageIcon icon = new ImageIcon("./img/cuadricula.png");
        this.setIconImage(icon.getImage());

        // Pasa el 'tema' a los paneles hijos
        this.panelInformativo = new PanelInformativo(tamanio, tema);
        this.panelPrincipal = new PanelPrincipal(tamanio, tema);

        this.add(this.panelInformativo, BorderLayout.NORTH);
        this.add(this.panelPrincipal, BorderLayout.CENTER);

        // setVisible(true) lo hace el controlador
    }

    public PanelInformativo getPanelInformativo() {
        return panelInformativo;
    }

    public PanelPrincipal getPanelPrincipal() {
        return panelPrincipal;
    }
}