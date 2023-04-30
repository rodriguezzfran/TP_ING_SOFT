package main;

import Imputs.KeyBoardImputs;
import Imputs.MouseImputs;

import javax.swing.JPanel;
import java.awt.Graphics;

/**
 * Esto es el lienzo, el juego en si mismo
 */

public class GamePanel extends JPanel{

    private MouseImputs mouseImputs;
    private int xDelta=100, yDelta=100;
    public GamePanel(){
        this.mouseImputs=new MouseImputs(this);
        addKeyListener(new KeyBoardImputs(this));
        addMouseListener(mouseImputs);
        addMouseMotionListener(mouseImputs);
    }

    public void changeXDelta(int value){
        this.xDelta+=value;
        repaint();
    }
    public void changeYDelta(int value){
        this.yDelta+=value;
        repaint();
    }

    public void setRecPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
        repaint();
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.fillRect(xDelta,yDelta,200,50);

    }

}
