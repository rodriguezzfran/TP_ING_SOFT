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
    private int deltaX=0;
    private int deltaY=0;
    private int frames = 0;
    private long contFPS = 0;
    public gameScreen(){
        addKeyListener(new keyboardImputs(this));
        addMouseListener(new mouseImputs());
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.fillRect(100+deltaX,100+deltaY,200,50);
        repaint();
        frames++;
        if((System.currentTimeMillis() - contFPS) >= 1000 ) {
            contFPS = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }
    }

    public void cambiarDeltaX(int value){
        this.deltaX += value;
        //repaint();
    }
    public void cambiarDeltaY(int value){
        this.deltaY += value;
        //repaint();
    }

}
