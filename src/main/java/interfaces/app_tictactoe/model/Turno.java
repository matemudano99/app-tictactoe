package interfaces.app_tictactoe.model;

public class Turno {
    private int toca; // 0 o 1

    public Turno() {
        this.toca = Math.random() < 0.5 ? 0 : 1;
    }

    public void siguiente() {
        this.toca = (this.toca == 0) ? 1 : 0;
    }

    public int toca() {
        return toca;
    }
}