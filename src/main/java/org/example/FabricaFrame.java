package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FabricaFrame extends JFrame {
    private Tablero tablero;
    private ExecutorService executor;

    public FabricaFrame() {
        setTitle("Fábrica con Visualización de Bolas Concurrente");
        setLayout(new BorderLayout());

        tablero = new Tablero();
        add(tablero, BorderLayout.CENTER);

        executor = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 100; i++) { // Crear 100 bolas como hilos
            executor.execute(new LanzarBolaTask(tablero, 100));
        }
    }
}