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
    private float xDir = 1F, yDir=1F;
    private float xDelta=100, yDelta=100;
    private int frames = 0;
    private long lastCkeck = 0;

    public GamePanel(){
        this.mouseImputs=new MouseImputs(this);
        addKeyListener(new KeyBoardImputs(this));
        addMouseListener(mouseImputs);
        addMouseMotionListener(mouseImputs);
    }

    public void changeXDelta(int value){
        this.xDelta+=value;
    }
    public void changeYDelta(int value){
        this.yDelta+=value;
    }

    public void setRecPos(int x, int y){
        this.xDelta = x;
        this.yDelta = y;
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateRectangle();
        g.fillRect((int)xDelta,(int)yDelta,200,50);

    }

    private void updateRectangle(){
        xDelta+=xDir;
        if(xDelta >= 185 || xDelta <0) {
            xDir *= -1;
        }

        yDelta += yDir;
        if(yDelta >= 315 || yDelta < 0) {
           yDir *= -1;
        }
    }

}
