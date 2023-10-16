package org.example;

import java.awt.*;
import javax.swing.JPanel;


public class Tablero extends JPanel {
    private final int tableroWidth = 800;
    private final int tableroHeight = 400;
    private int bolaSize = 20;
    private int[] acumulacion; // Acumulación de bolas por posición en el eje X

    public Tablero() {
        acumulacion = new int[tableroWidth];
    }

    public void lanzarBola() {
        int x = (int) (Math.random() * (tableroWidth - bolaSize));
        synchronized (acumulacion) {
            acumulacion[x]++;
        }
        repaint();
    }

    private void moverBolas() {
        synchronized (acumulacion) {
            for (int i = tableroWidth - 1; i >= 0; i--) {
                if (acumulacion[i] > 0) {
                    acumulacion[i]--;
                    if (i < tableroWidth - 1) {
                        acumulacion[i + 1]++;
                    }
                }
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        for (int x = 0; x < tableroWidth; x++) {
            int y;
            synchronized (acumulacion) {
                y = tableroHeight - acumulacion[x] * bolaSize;
            }
            g.fillOval(x, y, bolaSize, bolaSize);
        }
        moverBolas();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(tableroWidth, tableroHeight);
    }
}