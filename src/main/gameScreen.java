package main;

/**
 * Esto es el lienzo, el juego en si mismo
 */

import imputs.keyboardImputs;
import imputs.mouseImputs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gameScreen extends JPanel{
    public gameScreen(){
        addKeyListener(new keyboardImputs());
        addMouseListener(new mouseImputs());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect(100,100,200,50);
    }

}
