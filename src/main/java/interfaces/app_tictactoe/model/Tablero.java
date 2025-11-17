package interfaces.app_tictactoe.model;

import java.util.ArrayList;
import java.util.List;

// Modelo del tablero: almacena estado y verifica condiciones de victoria/empate.
public class Tablero {
    
    // ESTADOS DEL JUEGO 
    public static final String ESTADO_GANADOR = "GANADOR";
    public static final String ESTADO_EMPATE = "EMPATE";
    public static final String ESTADO_CONTINUA = "CONTINUA";
    
    // Propiedades
    public char[][] matrizTablero;
    int tamanio;
    private List<Coordenada> celdasGanadoras;

    public Tablero(int tamanio) {
        this.tamanio = tamanio;
        this.matrizTablero = new char[tamanio][tamanio];
        
        for (int i = 0; i < tamanio; i++) {
            for (int j = 0; j < tamanio; j++) {
                matrizTablero[i][j] = ' ';
            }
        }
    }

    public String verificarEstadoPartida(char simboloJugadorActual) throws Exception {
        // 1. Comprueba si el jugador actual ganó
        if (this.comprobarGanador(simboloJugadorActual)) {
            return ESTADO_GANADOR;
        }
        
        // 2. Si no, comprueba si hay empate
        if (this.estaLleno()) {
            return ESTADO_EMPATE;
        }
        
        // 3. Si nada de lo anterior, el juego sigue
        return ESTADO_CONTINUA;
    }

    /**
     * Método de ayuda para comprobar si un símbolo ganó
     */
    public boolean comprobarGanador(char simbolo) throws Exception {
        boolean gano;
        this.celdasGanadoras = null; 

        // 1. Comprobar Filas
        for (int i = 0; i < this.tamanio; i++) {
            gano = true;
            List<Coordenada> lineaActual = new ArrayList<>();
            for (int j = 0; j < this.tamanio; j++) {
                if (matrizTablero[i][j] != simbolo) {
                    gano = false;
                    break;
                }
                // (i, j) es (fila, col)
                // Coordenada es (col+1, fila+1)
                lineaActual.add(new Coordenada(j + 1, i + 1, this.tamanio));
            }
            if (gano) {
                this.celdasGanadoras = lineaActual; 
                return true;
            }
        }

        // 2. Comprobar Columnas
        for (int j = 0; j < this.tamanio; j++) {
            gano = true;
            List<Coordenada> lineaActual = new ArrayList<>();
            for (int i = 0; i < this.tamanio; i++) {
                if (matrizTablero[i][j] != simbolo) {
                    gano = false;
                    break;
                }
                lineaActual.add(new Coordenada(j + 1, i + 1, this.tamanio));
            }
            if (gano) {
                this.celdasGanadoras = lineaActual; 
                return true;
            }
        }

        // 3. Comprobar Diagonal Principal ( \ )
        gano = true;
        List<Coordenada> lineaDiagonal1 = new ArrayList<>();
        for (int i = 0; i < this.tamanio; i++) {
            if (matrizTablero[i][i] != simbolo) {
                gano = false;
                break;
            }
            lineaDiagonal1.add(new Coordenada(i + 1, i + 1, this.tamanio));
        }
        if (gano) {
            this.celdasGanadoras = lineaDiagonal1;
            return true;
        }

        // 4. Comprobar Diagonal Secundaria ( / )
        gano = true;
        List<Coordenada> lineaDiagonal2 = new ArrayList<>();
        for (int i = 0; i < this.tamanio; i++) {
            int j = this.tamanio - 1 - i; // Columna de la diag. secundaria
            if (matrizTablero[i][j] != simbolo) {
                gano = false;
                break;
            }
            lineaDiagonal2.add(new Coordenada(j + 1, i + 1, this.tamanio));
        }
        if (gano) {
            this.celdasGanadoras = lineaDiagonal2;
            return true;
        }

        return false;
    }

    /**
     * Método de ayuda para comprobar si el tablero está lleno
     */
    public boolean estaLleno() {
        for (int i = 0; i < this.tamanio; i++) {
            for (int j = 0; j < this.tamanio; j++) {
                if (matrizTablero[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Devuelve la lista de coordenadas ganadoras
     */
    public List<Coordenada> getCeldasGanadoras() {
        return celdasGanadoras;
    }
}