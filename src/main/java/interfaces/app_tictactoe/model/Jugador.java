package interfaces.app_tictactoe.model;

// Esta clase es necesaria para el controlador.
// Se asume esta implementación basada en cómo se usa.
public class Jugador {
    
    private char simbolo;
    private String nombre;

    public Jugador(char simbolo, String nombre) {
        this.simbolo = simbolo;
        this.nombre = nombre;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean ponerFicha(Tablero tablero, Coordenada coord) {
        int fila = coord.getFila() - 1; 
        int col = coord.getColumna() - 1; 

        if (tablero.matrizTablero[fila][col] == ' ') {
            tablero.matrizTablero[fila][col] = this.simbolo;
            return true; // Éxito
        }
        return false; // Casilla ocupada
    }
}