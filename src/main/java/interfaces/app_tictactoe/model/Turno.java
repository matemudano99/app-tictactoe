package interfaces.app_tictactoe.model;

// Gestiona el turno: indica qué jugador (0 o 1) está activo.
public class Turno {
    private int toca; // 0 o 1

    // Inicializa el turno aleatoriamente entre los dos jugadores.
    public Turno() {
        this.toca = Math.random() < 0.5 ? 0 : 1;
    }

    // Cambia al siguiente jugador.
    public void siguiente() {
        this.toca = (this.toca == 0) ? 1 : 0;
    }

    // Devuelve el índice del jugador activo (0 o 1).
    public int toca() {
        return toca;
    }
}