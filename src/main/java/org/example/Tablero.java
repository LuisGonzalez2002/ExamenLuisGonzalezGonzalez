package org.example;

import java.awt.*;
import javax.swing.JPanel;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Tablero extends JPanel {
    private final int tableroWidth = 800;
    private final int tableroHeight = 400;
    private int bolaSize = 20;
    private Queue<Integer> bolasEnAire;
    private Random random = new Random();

    public Tablero() {
        bolasEnAire = new LinkedList<>();
    }

    public void lanzarBola() {
        int x = obtenerPosicionConDistribucionNormal();
        synchronized (bolasEnAire) {
            if (x >= 0 && x < tableroWidth) {
                bolasEnAire.offer(x);
            }
        }
        repaint();
    }

    private int obtenerPosicionConDistribucionNormal() {
        double media = tableroWidth / 2.0;
        double desviacionEstandar = tableroWidth / 6.0;
        double u = random.nextGaussian(); // Genera un valor aleatorio con distribución normal (campana de Gauss)
        int x = (int) (media + u * desviacionEstandar);
        return x;
    }

    private void moverBolas() {
        synchronized (bolasEnAire) {
            for (int i = 0; i < bolasEnAire.size(); i++) {
                int x = bolasEnAire.poll();
                if (x < tableroWidth - 1) {
                    bolasEnAire.offer(x + 1);
                }
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);

        synchronized (bolasEnAire) {
            for (int x : bolasEnAire) {
                int y = tableroHeight - (bolasEnAire.size() * bolaSize);
                g.fillOval(x, y, bolaSize, bolaSize);
            }
        }
        moverBolas();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(tableroWidth, tableroHeight);
    }
}