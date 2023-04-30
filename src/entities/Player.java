package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Direction.*;
import static utils.Constants.Direction.DOWN;
import static utils.Constants.PlayerConstants.*;



public class Player extends Entity{
    private BufferedImage[][] allAnimations;
    private BufferedImage img;
    private int playerAction = DEAD;
    private int aniTick,aniIndex,aniSpeed = 20;
    private int playerDir = -1;
    private boolean moving = false , attacking = false;
    private boolean up,right,down,left;
    public Player(float x, float y,int speed) {
        super(x, y,speed);
        importSprites();
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g){
        g.drawImage(allAnimations[playerAction][aniIndex],(int)x,(int)y,78*3,58*3,null);
    }




    /**
     * Importación de los sprites.
     */


    private void setAnimation() {
        int startAnimation = playerAction;
        if(moving){
            playerAction = RUN;
        }
        else{
            playerAction = IDLE;
        }
        if(attacking){
            playerAction = ATTACK;
        }
        if(startAnimation != playerAction){
            resetAniTick();
        }

    }

    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    public void setAttacking(boolean attacking){
        this.attacking = attacking;
    }

    private void updatePos() {
        moving = false;
        if(left && !right){
            x-=speed;
            moving = true;
        }
        else if(!left && right){
            x+=speed;
            moving = true;
        }

        if(up && !down){
            y-=speed;
            moving = true;
        }
        else if(!up && down){
            y+=speed;
            moving = true;
        }

    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmount(playerAction)){
                aniIndex = 0;
                attacking = false;
            }
        }
    }



    private void importSprites() {
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

    public boolean getUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean getRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean getDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean getLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void resetDirBooleans() {
        up = false;
        left =false;
        down = false;
        right = false;
    }
}
