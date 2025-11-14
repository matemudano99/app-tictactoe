package interfaces.app_tictactoe.view;

import javax.swing.*;
import java.awt.*;
import java.util.List; // Importar List
import interfaces.app_tictactoe.model.Coordenada; // Importar Coordenada

public class PanelPrincipal extends JPanel {

    private BotonInterior[][] botones;
    
    public PanelPrincipal(int tamanio, String tema) {
        // 5px de espacio para las líneas
        this.setLayout(new GridLayout(tamanio, tamanio, 5, 5)); 
        this.setSize(900, 900);

        if (tema.equals("OSCURO")) {
            this.setBackground(new Color(50, 50, 50));
        } else {
            this.setBackground(Color.BLACK);
        }

        botones = new BotonInterior[tamanio][tamanio]; 

        for (int i = 0; i < tamanio; i++) { 
            for (int j = 0; j < tamanio; j++) { 
                // Pasa el 'tema' al botón
                botones[i][j] = new BotonInterior(i, j, tema); 
                this.add(botones[i][j]);
            }
        }
    }

    public BotonInterior[][] getBotones() {
        return botones;
    }

    public void marcarGanador(List<Coordenada> celdasGanadoras) {
        if (celdasGanadoras == null) return;
        
        for (Coordenada coordenada : celdasGanadoras) {
            int fila = coordenada.getFila() - 1;
            int col = coordenada.getColumna() - 1;
            
            // Asegurarse de que esté dentro de los límites (aunque no debería fallar)
            if (fila >= 0 && fila < botones.length && col >= 0 && col < botones[0].length) {
                 botones[fila][col].setBackground(Color.GREEN);
            }
        }
    }
}