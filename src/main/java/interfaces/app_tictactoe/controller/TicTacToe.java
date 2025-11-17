package interfaces.app_tictactoe.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import interfaces.app_tictactoe.model.Coordenada;
import interfaces.app_tictactoe.model.Jugador;
import interfaces.app_tictactoe.model.Tablero;
import interfaces.app_tictactoe.model.Turno;
import interfaces.app_tictactoe.view.BotonInterior;
import interfaces.app_tictactoe.view.Configurador;
import interfaces.app_tictactoe.view.Vista;

/**
 * Controlador principal del juego (Patrón MVC).
 * Conecta la Vista (interfaz gráfica) con el Modelo (lógica interna)
 * y gestiona el flujo del juego y los eventos del usuario.
 */
public class TicTacToe {

    // --- Modelo y Estado ---
    private final Tablero TABLERO;
    private final Jugador[] JUGADORES;
    private final Turno TURNO;
    private final int tamanio;

    // --- Vista ---
    private final Vista VISTA;

    // --- Temporizador ---
    private static final int TIEMPO_TURNO = 15;
    private Timer timer;
    private int tiempoRestante;

    /**
     * Constructor. Prepara la partida completa.
     * Inicializa los objetos del Modelo (Tablero, Jugadores),
     * la Vista (Ventana), y conecta todos los listeners de eventos.
     */
    public TicTacToe(int tamanio, String nombreX, String nombreO, String tema, char simboloX, char simboloO) {
        this.tiempoRestante = TIEMPO_TURNO;
        this.tamanio = tamanio;
        
        // Inicializa el Modelo
        this.TABLERO = new Tablero(this.tamanio);
        this.JUGADORES = new Jugador[] { new Jugador(simboloX, nombreX), new Jugador(simboloO, nombreO) };
        this.TURNO = new Turno();
        
        // Inicializa la Vista
        this.VISTA = new Vista(this.tamanio, tema);
        
        // Configura el temporizador
        this.inicializarTimer();
        
        // Conecta la Vista y el Modelo
        actualizarEtiquetaTurno();
        this.agregarListenersBotones();
        this.agregarListenerNuevaPartida();
        
        // Arranca el juego
        this.VISTA.setVisible(true);
        this.reiniciarYEmpezarTimer();
    }

    /**
     * Asigna un "oyente" de clics (ActionListener) a cada botón del tablero.
     * Cada clic en una casilla ejecutará el método 'manejarClic'.
     */
    private void agregarListenersBotones() {
        BotonInterior[][] botones = VISTA.getPanelPrincipal().getBotones();

        for (int i = 0; i < this.tamanio; i++) {
            for (int j = 0; j < this.tamanio; j++) {
                botones[i][j].addActionListener(e -> {
                    BotonInterior botonClicado = (BotonInterior) e.getSource();
                    manejarClic(botonClicado);
                });
            }
        }
    }

    /**
     * Lógica central que se ejecuta al pulsar una casilla.
     * Detiene el timer, valida el movimiento, actualiza el Modelo y la Vista,
     * comprueba si hay ganador y pasa el turno.
     */
    private void manejarClic(BotonInterior boton) {
        Jugador jugadorActivo = JUGADORES[TURNO.toca()];
        int fila = boton.getFila();
        int col = boton.getColumna();

        try {
            Coordenada coord = new Coordenada(col + 1, fila + 1, tamanio);
            boolean exito = jugadorActivo.ponerFicha(this.TABLERO, coord);

            if (exito) {
                // Movimiento válido: detener el timer del turno actual
                timer.stop();
                
                // Actualizar la Vista
                boton.setText(String.valueOf(jugadorActivo.getSimbolo()));
                boton.setEnabled(false);

                // Comprobar estado del Modelo
                String estado = TABLERO.verificarEstadoPartida(jugadorActivo.getSimbolo());

                // Reaccionar al estado
                switch (estado) {
                    case Tablero.ESTADO_GANADOR -> {
                        List<Coordenada> celdas = TABLERO.getCeldasGanadoras();
                        VISTA.getPanelPrincipal().marcarGanador(celdas);
                        String msgGanador = "¡Ha ganado " + jugadorActivo.getNombre() + " (" + jugadorActivo.getSimbolo() + ")!";
                        VISTA.getPanelInformativo().setTextoEtiqueta(msgGanador);
                        JOptionPane.showMessageDialog(VISTA, msgGanador);
                    }
                    case Tablero.ESTADO_EMPATE -> {
                        String msgEmpate = "¡Empate!";
                        VISTA.getPanelInformativo().setTextoEtiqueta(msgEmpate);
                        JOptionPane.showMessageDialog(VISTA, msgEmpate);
                    }
                    case Tablero.ESTADO_CONTINUA -> {
                        // Pasar al siguiente turno y reiniciar el timer para él
                        TURNO.siguiente();
                        actualizarEtiquetaTurno();
                        reiniciarYEmpezarTimer();
                        break;
                    }
                }
            }
            // Si 'exito' es false (casilla ocupada), no hace nada y el timer sigue.
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(VISTA, "Error inesperado: " + ex.getMessage());
        }
    }

    /**
     * Actualiza la etiqueta de información en la Vista para mostrar
     * qué jugador tiene el turno actual.
     */
    private void actualizarEtiquetaTurno() {
        String nombre = JUGADORES[TURNO.toca()].getNombre();
        String simbolo = String.valueOf(JUGADORES[TURNO.toca()].getSimbolo());
        VISTA.getPanelInformativo().setTextoEtiqueta("Turno de: " + nombre + " (" + simbolo + ")");
    }

    /**
     * Asigna la funcionalidad al botón "Nueva Partida".
     * Detiene el timer, cierra la ventana de juego y abre la de configuración.
     */
    private void agregarListenerNuevaPartida() {
        JButton botonNuevaPartida = VISTA.getPanelInformativo().getBtnNuevaPartida();
        
        botonNuevaPartida.addActionListener(e -> {
            timer.stop(); // Detener el timer antes de cerrar
            VISTA.dispose();
            
            // Abrir la ventana de configuración en el hilo de Swing
            SwingUtilities.invokeLater(() -> {
                Configurador nuevoConfigurador = new Configurador();
                nuevoConfigurador.setVisible(true);
            });
        });
    }

    /**
     * Crea y configura el objeto Timer. Define el código
     * que se ejecutará cada segundo (1000ms).
     */
    private void inicializarTimer() {
        this.timer = new Timer(1000, (ActionEvent e) -> {
            tiempoRestante--;
            VISTA.getPanelInformativo().actualizarTiempo(tiempoRestante);
            
            // Si se agota el tiempo, parar el timer y gestionar la pérdida de turno
            if (tiempoRestante <= 0) {
                timer.stop();
                gestionarTiempoAgotado();
            }
        });
    }
    
    /**
     * Lógica a ejecutar cuando el timer de un jugador llega a 0.
     * Muestra un aviso, pasa el turno y reinicia el timer para el siguiente.
     */
    private void gestionarTiempoAgotado() {
        String nombre = JUGADORES[TURNO.toca()].getNombre();
        JOptionPane.showMessageDialog(VISTA, "¡Tiempo agotado para " + nombre + "! Pierdes el turno.");
        
        // Pasa al siguiente jugador
        TURNO.siguiente();
        actualizarEtiquetaTurno();
        
        // Reinicia el timer para el nuevo turno
        reiniciarYEmpezarTimer();
    }

    /**
     * Resetea el contador de tiempo a 15 segundos y arranca/reinicia
     * la cuenta atrás. Se llama al inicio de cada turno.
     */
    private void reiniciarYEmpezarTimer() {
        this.tiempoRestante = TIEMPO_TURNO;
        VISTA.getPanelInformativo().actualizarTiempo(this.tiempoRestante);
        this.timer.restart();
    }
}