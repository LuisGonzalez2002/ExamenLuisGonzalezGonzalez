package org.example;

public class LanzarBolaTask implements Runnable {
    private Tablero tablero;
    private int cantidadBolas;

    public LanzarBolaTask(Tablero tablero, int cantidadBolas) {
        this.tablero = tablero;
        this.cantidadBolas = cantidadBolas;
    }

    @Override
    public void run() {
        for (int i = 0; i < cantidadBolas; i++) {
            try {
                Thread.sleep(500); // Aumentar el tiempo de espera para que las bolas caigan más lentamente
                for (int j = 0; j < 5; j++) { // Lanza 5 bolas en cada iteración
                    tablero.lanzarBola();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}