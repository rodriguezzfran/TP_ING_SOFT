package entities;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import static utilz.HelpMethods.canMoveHere;
import static utilz.Constants.PlayerConstants.*;



public class Player extends Entity{
    private BufferedImage[][] allAnimations;
    private BufferedImage img;
    private int playerAction = DEAD;
    private int aniTick,aniIndex,aniSpeed = 20;
    private int playerDir = -1;
    private boolean moving = false , attacking = false;
    private boolean up,right,down,left;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 16 * Game.SCALE;
    public Player(float x, float y,int speed,int width, int height) {
        super(x, y,speed,width,height);
        importSprites();
        initHitbox(x,y,20*Game.SCALE, (float) ((26.5)*Game.SCALE));
    }

    public void update(){
        updatePos();
        updateAnimationTick();
        setAnimation();
    }
    public void render(Graphics g){
        g.drawImage(allAnimations[playerAction][aniIndex],(int)(hitBox.x - xDrawOffset),(int)(hitBox.y - yDrawOffset),width,height,null);
        drawHitbox(g);
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
        if(!left && !right && !up && !down){
            return;
        }
        float xSpeed = 0, ySpeed = 0;
        if(left && !right){
            xSpeed = -speed;
        }
        else if(!left && right){
            xSpeed = speed;
        }

        if(up && !down){
            ySpeed = -speed;
        }
        else if(!up && down){
            ySpeed = speed;
        }

        if(canMoveHere(hitBox.x + xSpeed,hitBox.y+ySpeed, hitBox.width, hitBox.height,lvlData)){
            hitBox.x += xSpeed;
            hitBox.y += ySpeed;
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


    /**
     * mete al array todas las animaciones
     */
    private void importSprites() {
        BufferedImage playerAtlas[] = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
        allAnimations = new BufferedImage[10][11];
        for(int i = 0;i<playerAtlas.length;i++){
            for(int j = 0; j < (playerAtlas[i].getWidth()/78);j++){
                allAnimations[i][j] = playerAtlas[i].getSubimage(j*(78),0,78,58);
            }
        }
    }
    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
    }

    public void resetDirBooleans() {
        up = false;
        left =false;
        down = false;
        right = false;
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


}
