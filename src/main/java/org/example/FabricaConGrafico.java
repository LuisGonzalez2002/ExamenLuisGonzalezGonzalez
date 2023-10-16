package org.example;

import javax.swing.*;


public class FabricaConGrafico {
    public static void main(String[] args) {
        JFrame frame = new FabricaFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}