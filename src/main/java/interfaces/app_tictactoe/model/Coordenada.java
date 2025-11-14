package interfaces.app_tictactoe.model;

public class Coordenada {
    int columna;
    int fila;
    
    public Coordenada(int columna, int fila, int tamanio) throws Exception {
        this.columna = columna;
        this.fila = fila;
        if(fila > tamanio || fila < 1 || columna > tamanio || columna < 1){
            throw new Exception("Coordenada no valida (1-"+tamanio+")");
        }
    }

    public int getColumna() {
        return columna; 
    }

    public int getFila() {
        return fila; 
    }
}