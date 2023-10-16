package org.example;

import java.util.Queue;
import java.util.concurrent.ExecutorService;


public class LineaEnsamblaje implements Runnable {
    private Buffer buffer;
    private Tablero tablero;
    private ExecutorService executor;

    public LineaEnsamblaje(Buffer buffer, Tablero tablero, ExecutorService executor) {
        this.buffer = buffer;
        this.tablero = tablero;
        this.executor = executor;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 20; i++) {
                // Ensamblar el componente
                String componente = buffer.consumir();
                System.out.println("Línea de ensamblaje ensambló " + componente);
                Thread.sleep(50);

                // Lanzar una bola en el tablero
                executor.execute(() -> tablero.lanzarBola());
                //executor.execute(() -> tablero.lanzarBola((int) (Math.random() * tablero.getWidth()), 5));
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}