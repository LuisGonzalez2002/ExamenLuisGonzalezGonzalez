package org.example;

import java.util.LinkedList;
import java.util.Queue;
public
class Buffer {

    private Queue<String> buffer = new LinkedList<>();
    private int capacidad = 10;
    private int contadorA = 0;
    private int contadorB = 0;

    public synchronized void producir(String componente) throws InterruptedException {
        while (buffer.size() == capacidad) {
            wait();
        }
        buffer.add(componente);
        if (componente.equals("A")) {
            contadorA++;
        } else if (componente.equals("B")) {
            contadorB++;
        }
        notifyAll();
    }

    public synchronized String consumir() throws InterruptedException {
        while (buffer.isEmpty()) {
            wait();
        }
        String componente = buffer.poll();
        if (componente.equals("A")) {
            contadorA--;
        } else if (componente.equals("B")) {
            contadorB--;
        }
        notifyAll();
        return componente;
    }

    public int getContadorA() {
        return contadorA;
    }

    public int getContadorB() {
        return contadorB;
    }
}