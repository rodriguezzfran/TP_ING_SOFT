package main;

import Imputs.KeyBoardImputs;
import Imputs.MouseImputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Esto es el lienzo, el juego en si mismo
 */

public class GamePanel extends JPanel{

    private MouseImputs mouseImputs;

   // private float xDir = 0.1F, yDir=0.1F;
    private float xDelta=100, yDelta=100;
    private BufferedImage img;
    private int frames =0;
    private long lastCkeck =0;
    private BufferedImage subImg;

    private float xDir = 1F, yDir=1F;


    public GamePanel(){
        importImg();
        setPanelSize();
        this.mouseImputs=new MouseImputs(this);
        addKeyListener(new KeyBoardImputs(this));
        addMouseListener(mouseImputs);
        addMouseMotionListener(mouseImputs);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/Sprites/01-King Human/Idle (78x58).png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
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

        subImg = img.getSubimage(2*72,0,72,58);
        g.drawImage(subImg,(int)xDelta,(int)yDelta,72*2,58*2,null);
    }

//    private void updateRectangle(){
//        xDelta+=xDir;
//        if(xDelta > 200 || xDelta <0) {
//            xDir *= -1;
//
//        }
//        yDelta += yDir;
//        if(yDelta > 350 || yDelta < 0) {
//           yDir *= -1;
//
//        }
//    }

    
}
