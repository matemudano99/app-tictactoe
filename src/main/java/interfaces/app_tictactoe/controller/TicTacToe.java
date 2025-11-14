package interfaces.app_tictactoe.controller;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import interfaces.app_tictactoe.model.Coordenada;
import interfaces.app_tictactoe.model.Jugador;
import interfaces.app_tictactoe.model.Tablero;
import interfaces.app_tictactoe.model.Turno;
import interfaces.app_tictactoe.view.BotonInterior;
import interfaces.app_tictactoe.view.Configurador;
import interfaces.app_tictactoe.view.Vista;

public class TicTacToe {

    private final Tablero TABLERO;
    private final Jugador[] JUGADORES;
    private final Turno TURNO;
    private final Vista VISTA;
    private int tamanio;

    public TicTacToe(int tamanio, String nombreX, String nombreO, String tema, char simboloX, char simboloO) {

        this.tamanio = tamanio;
        this.TABLERO = new Tablero(this.tamanio);
        this.JUGADORES = new Jugador[] { new Jugador(simboloX, nombreX), new Jugador(simboloO, nombreO) };
        this.TURNO = new Turno();
        this.VISTA = new Vista(this.tamanio, tema);

        actualizarEtiquetaTurno();
        this.agregarListenersBotones();
        this.agregarListenerNuevaPartida();
        this.VISTA.setVisible(true);
    }

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

    private void manejarClic(BotonInterior boton) {
        Jugador jugadorActivo = JUGADORES[TURNO.toca()];

        int fila = boton.getFila();
        int col = boton.getColumna();

        try {
            Coordenada coord = new Coordenada(col + 1, fila + 1, tamanio);

            boolean exito = jugadorActivo.ponerFicha(this.TABLERO, coord);

            if (exito) {
                boton.setText(String.valueOf(jugadorActivo.getSimbolo()));
                boton.setEnabled(false);

                String estado = TABLERO.verificarEstadoPartida(jugadorActivo.getSimbolo());

                switch (estado) {
                    case Tablero.ESTADO_GANADOR:
                        List<Coordenada> celdas = TABLERO.getCeldasGanadoras();
                        VISTA.getPanelPrincipal().marcarGanador(celdas);

                        String msgGanador = "¡Ha ganado " + jugadorActivo.getNombre() + " ("
                                + jugadorActivo.getSimbolo() + ")!";
                        VISTA.getPanelInformativo().setTextoEtiqueta(msgGanador);
                        JOptionPane.showMessageDialog(VISTA, msgGanador);

                        break;

                    case Tablero.ESTADO_EMPATE:
                        String msgEmpate = "¡Empate!";
                        VISTA.getPanelInformativo().setTextoEtiqueta(msgEmpate);
                        JOptionPane.showMessageDialog(VISTA, msgEmpate);

                        break;

                    case Tablero.ESTADO_CONTINUA:
                        TURNO.siguiente();
                        actualizarEtiquetaTurno();
                        break;
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(VISTA, "Error inesperado: " + ex.getMessage());
        }
    }

    private void actualizarEtiquetaTurno() {
        String nombre = JUGADORES[TURNO.toca()].getNombre();
        String simbolo = String.valueOf(JUGADORES[TURNO.toca()].getSimbolo());
        VISTA.getPanelInformativo().setTextoEtiqueta("Turno de: " + nombre + " (" + simbolo + ")");
    }

    private void agregarListenerNuevaPartida() {
        JButton botonNuevaPartida = VISTA.getPanelInformativo().getBtnNuevaPartida();
        botonNuevaPartida.addActionListener(e -> {
            VISTA.dispose();
            SwingUtilities.invokeLater(() -> {
                Configurador nuevoConfigurador = new Configurador();
                nuevoConfigurador.setVisible(true);
            });
        });
    }
}