package org.example;


import java.util.Queue;
import java.util.concurrent.ExecutorService;

public class EstacionTrabajo implements Runnable {
    private String nombre;
    private String componente;
    private Buffer buffer;
    private Tablero tablero;
    private ExecutorService executor;

    public EstacionTrabajo(String nombre, String componente, Buffer buffer, Tablero tablero, ExecutorService executor) {
        this.nombre = nombre;
        this.componente = componente;
        this.buffer = buffer;
        this.tablero = tablero;
        this.executor = executor;
    }


    @Override
    public void run() {
        try {
            for (int i = 0; i < 10; i++) {
                // Simular la producción de componentes
                Thread.sleep(100);
                System.out.println(nombre + " produjo " + componente);

                // Colocar el componente en el buffer compartido
                buffer.producir(componente);

                // Lanzar bolas en el tablero
                executor.execute(new LanzarBolaTask(tablero, 5)); // Cambia el número de bolas si es necesario
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}