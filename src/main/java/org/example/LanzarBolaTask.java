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
                Thread.sleep(100);
                tablero.lanzarBola();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
