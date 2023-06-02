package entities;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import static utilz.Constants.PlayerConstants.*;
import static utilz.HelpMethods.*;




public class Player extends Entity{
    private int speed = 3;
    private BufferedImage[][] allAnimations;
    private BufferedImage img;
    private int playerAction = DEAD;
    private int aniTick,aniIndex,aniSpeed = 20;
    private int playerDir = -1;
    private boolean moving = false , attacking = false;
    private boolean up,right,down,left,jump;
    private int[][] lvlData;
    private float xDrawOffset = 21 * Game.SCALE;
    private float yDrawOffset = 16 * Game.SCALE;

    //Jumping / Gravity
    private float airSpeed = 0f;
    private float gravity = 0.04f * Game.SCALE;
    private float jumpSpeed = -2.25f * Game.SCALE;
    private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
    private boolean inAir = false;

    //StatusBarUI
    private BufferedImage statusBarImg;

    private int statusBarWidth = (int) (192*Game.SCALE);
    private int statusBarHeight = (int) (58*Game.SCALE);
    private int statusBarX = (int) (10*Game.SCALE);
    private int statusBarY = (int) (10*Game.SCALE);

    private int healthBarWidth = (int) (150*Game.SCALE);
    private int healthBarHeight = (int) (4*Game.SCALE);
    private int healthBarXStart = (int) (34*Game.SCALE);
    private int healthBarYStart = (int) (14*Game.SCALE);


    private int maxHealth = 100;
    private int currentHealth = maxHealth;
    private int healthWidth = healthBarWidth;

    //AttackBox
    private Rectangle2D.Float attackBox;

    private int flipX = 0;
    private int flipW = 1;

    private boolean attackChecked = false;
    private Playing playing;


    public Player(float x, float y,int width, int height, Playing playing) {
        super(x, y,width,height);
        this.playing=playing;
        importSprites();
        initHitbox(x,y,20*Game.SCALE, 27*Game.SCALE); //20x27 is the actual player's size
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x,y,(int)(25*Game.SCALE),(int)(40*Game.SCALE));
    }

    public void update(){
        updateHealthBar();
        if(currentHealth <= 0){
            playing.setGameOver(true);
            return;
        }
        updateAttackBox();
        updatePos();
        if(attacking){
            checkAttack();
        }
        updateAnimationTick();
        setAnimation();
    }

    private void checkAttack() {
        if(attackChecked || aniIndex != 0){
            return;
        }
        attackChecked = true;
        playing.checkEnemyHit(attackBox);
    }

    private void updateAttackBox() {
        if(right){
            attackBox.x = hitBox.x + hitBox.width + (int)(Game.SCALE * 10);
        }
        else if(left){
            attackBox.x = hitBox.x - hitBox.width - (int)(Game.SCALE * 10);
        }
        attackBox.y = hitBox.y - (Game.SCALE * 12);
    }

    private void updateHealthBar() {
        healthWidth = (int)((currentHealth/(float)maxHealth) * healthBarWidth); //escala
    }

    public void render(Graphics g){
        //con "hitBox.x-xDrawOffset" y "hitBox.y-yDrawOffset" hacemos que el
        //sprite del pj "siga" a la hitbox q es la q se mueve
        g.drawImage(allAnimations[playerAction][aniIndex],
                (int) (hitBox.x - xDrawOffset) + flipX,
                (int) (hitBox.y - yDrawOffset),
                width * flipW,height,null);
        drawHitbox(g);
        drawAttackBox(g);
        drawUI(g);
    }

    private void drawAttackBox(Graphics g) {
        g.setColor(Color.red);
        g.drawRect((int)attackBox.x, (int)attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }

    private void drawUI(Graphics g) {
        g.drawImage(statusBarImg,statusBarX,statusBarY,statusBarWidth,statusBarHeight,null);
        g.setColor(Color.RED);
        g.fillRect(healthBarXStart+statusBarX, healthBarYStart+statusBarY,healthWidth,healthBarHeight);
    }

    private void setAnimation() {
        int startAnimation = playerAction;
        if(moving){
            playerAction = RUN;
        }
        else{
            playerAction = IDLE;
        }

        if(inAir){
            if(airSpeed < 0){
                playerAction = JUMP;
            }
            else{
                playerAction = FALL;
            }
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
        if(jump){
            jump();
        }

        if(!left && !right && !inAir){
            return;
        }

        float xSpeed = 0;

        if(left){
            xSpeed -= speed;
            flipX = (width-30); //30 es el drawOffset del lado derecho
            flipW = -1;
        }
        if(right){
            xSpeed += speed;
            flipX = 0;
            flipW = 1;
        }
        if(!inAir){
            if(!IsEntityOnFloor(hitBox,lvlData)){
                inAir = true;
            }
        }
        if(inAir){

            if(CanMoveHere(hitBox.x, hitBox.y+airSpeed, hitBox.width, hitBox.height, lvlData)){
                hitBox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            }else {
                hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, airSpeed);
                if (airSpeed > 0) {
                    resetInAir();
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }


        }else{
            updateXPos(xSpeed);
        }
        moving = true;

    }



    private void jump() {
        if(inAir){
            return;
        }
        inAir = true;
        airSpeed = jumpSpeed;
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed) {
        if(CanMoveHere(hitBox.x + xSpeed,hitBox.y, hitBox.width, hitBox.height,lvlData)){
            //lo que se va a mover es la hitbox, y con el metodo de render
            //hacemos que el sprite del pj "siga" a la hitbox
            hitBox.x += xSpeed;
        }else{
            hitBox.x = GetEntityXPosNextToWall(hitBox,xSpeed);
        }
    }

    public void changeHealth(int value){
        currentHealth += value;

        if(currentHealth <= 0){
            currentHealth = 0;
            //gameOver();
        }
        else if(currentHealth >= maxHealth){
            currentHealth = maxHealth;
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
                attackChecked = false;
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
        statusBarImg = LoadSave.GetSpriteAtlas(LoadSave.LIVE_BAR_GRAPH)[4]; //toma la health bar del array de las
                                                                            //imagenes de "12-Live and Coins"
    }
    public void loadLvlData(int[][] lvlData){
        this.lvlData = lvlData;
        if(!IsEntityOnFloor(hitBox,lvlData)){
            inAir = true;
        }
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
    public void setJump(boolean jump){
        this.jump = jump;
    }


    public void resetAll() {
        resetDirBooleans();
        inAir = false;
        attacking=false;
        moving=false;
        playerAction=IDLE;
        currentHealth=maxHealth;

        hitBox.x=x;
        hitBox.y=y;

        if(!IsEntityOnFloor(hitBox,lvlData)){
            inAir = true;
        }
    }
}
