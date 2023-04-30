package main;

import Imputs.KeyBoardImputs;
import Imputs.MouseImputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Direction.*;
/**
 * Esto es el lienzo, el juego en si mismo
 */

public class GamePanel extends JPanel{

    private MouseImputs mouseImputs;

    private float xDelta=100, yDelta=100;
    private BufferedImage img;
    private int frames =0;
    private long lastCkeck =0;
    private BufferedImage[][] allAnimations;
    private int playerAction = ATTACK;
    private int aniTick,aniIndex,aniSpeed = 20;
    private int playerDir = -1;
    private boolean moving = false;


    public GamePanel(){
        importImg();
        setPanelSize();
        this.mouseImputs=new MouseImputs(this);
        addKeyListener(new KeyBoardImputs(this));
        addMouseListener(mouseImputs);
        addMouseMotionListener(mouseImputs);
    }


    public void setDirection(int direction){
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void importImg() {
        allAnimations = new BufferedImage[10][11];
        String aux = "/Sprites/01-King Human/";
        for(int i = 0;i<10;i++) {
            aux += (i+".png");
            InputStream is = getClass().getResourceAsStream(aux);
            try {
                img = ImageIO.read(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }finally {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                for(int j = 0; j < (img.getWidth()/78);j++){
                    allAnimations[i][j] = img.getSubimage(j*(78),0,78,58);
                }
            }
            aux = "/Sprites/01-King Human/";
        }
    }


    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    private void setAnimation() {
        if(moving){
            playerAction = RUN;
        }
        else{
            playerAction = IDLE;
        }
    }
    private void updatePos() {
        if(moving){
            switch(playerDir){
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePos();
        g.drawImage(allAnimations[playerAction][aniIndex],(int)xDelta,(int)yDelta,78*3,58*3,null);
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
