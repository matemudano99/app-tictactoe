package interfaces.app_tictactoe.model;

// Representa una coordenada (fila, columna) válida en el tablero.
public class Coordenada {
    int columna;
    int fila;
    
    // Crea y valida una coordenada dentro del tamaño del tablero.
    public Coordenada(int columna, int fila, int tamanio) throws Exception {
        this.columna = columna;
        this.fila = fila;
        if(fila > tamanio || fila < 1 || columna > tamanio || columna < 1){
            throw new Exception("Coordenada no valida (1-"+tamanio+")");
        }
    }

    // Devuelve la columna (1-based).
    public int getColumna() {
        return columna;
    }

    // Devuelve la fila (1-based).
    public int getFila() {
        return fila;
    }
}