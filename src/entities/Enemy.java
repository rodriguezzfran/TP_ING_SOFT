package entities;

import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;
import static utilz.Constants.Directions.*;


public abstract class Enemy extends Entity{
    protected int aniIndex, enemyState,enemyType;
    protected int aniTick, aniSpeed =25;
    protected boolean firstUpdate = true;
    protected boolean inAir = false;
    protected float fallSpeed;
    protected float gravity = 0.04f * Game.SCALE;
    protected float walkSpeed = 0.45f * Game.SCALE;
    protected int walkDir = LEFT;
    protected int tileY;
    protected float attackDistance = Game.SCALE*9;
    protected int maxHealth;
    protected int currentHealth;
    protected boolean active = true;
    protected boolean attackChecked;
    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);
        this.enemyState=RUN;
        this.enemyType=enemyType;
        initHitbox(x,y,width,height);
        maxHealth = GetMaxHealth(enemyType);
        currentHealth = maxHealth;
    }

    protected void firstUpdateCheck(int[][] lvlData){
        if (!IsEntityOnFloor(hitBox, lvlData)) {
            inAir = true;
        }
        firstUpdate = false;
    }

    protected void updateInAir(int[][] lvlData){
        if (CanMoveHere(hitBox.x, hitBox.y + fallSpeed, hitBox.width, hitBox.height, lvlData)) {
            hitBox.y += fallSpeed;
            fallSpeed += gravity;
        } else {
            inAir = false;
            hitBox.y = GetEntityYPosUnderRoofOrAboveFloor(hitBox, fallSpeed);
            tileY=(int)(hitBox.y/Game.TILES_SIZE);
        }
    }

    protected void move(int[][] lvlData){
        float xSpeed = 0;
        if (walkDir == LEFT) {
            xSpeed = -walkSpeed;
        } else {
            xSpeed = walkSpeed;
        }
        if (CanMoveHere(hitBox.x + xSpeed, hitBox.y, hitBox.width, hitBox.height, lvlData)) {
            if (IsFloor(hitBox, xSpeed, lvlData)) {
                hitBox.x += xSpeed;
                return;
            }
        }
        changeWalkDir();
    }

    protected void turnTowardsPlayer(Player player){
        if(player.hitBox.x > hitBox.x){
            walkDir = RIGHT;
        }
        else{
            walkDir = LEFT;
        }
    }
    protected  boolean canSeePlayer(int[][] lvlData, Player player){
        int playerTileY = (int)(player.getHitBox().y / Game.TILES_SIZE);
        if(playerTileY == tileY){
            if(isPlayerInRange(player)){
                if(IsSightClear(lvlData,hitBox, player.hitBox,tileY)){
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean isPlayerInRange(Player player) {
        int absValue = (int)(Math.abs(player.hitBox.x - hitBox.x));
        return (absValue <= attackDistance*10);
    }

    protected boolean isPlayerCloseForAttack(Player player){
        int absValue = (int) Math.abs(player.hitBox.x - hitBox.x);
        return (absValue <= attackDistance*3);
    }

    protected void newState(int enemyState){
        this.enemyState = enemyState;
        aniTick=0;
        aniIndex=0;
    }

    public void hurt(int amount){
        currentHealth -= amount;
        if(currentHealth <= 0){
            newState(DEAD);
        }
        else{
            newState(HIT);
        }
    }

    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player){
        if(attackBox.intersects(player.hitBox)){
            player.changeHealth(-GetEnemyDmg(enemyType));
        }
        attackChecked = true;
    }
    protected void updateAnimationTick(){
        aniTick++;
        if(aniTick>=aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex >= GetSpriteAmount(enemyType,enemyState)){
                aniIndex = 0;

                switch (enemyState){
                    case ATTACK,HIT -> enemyState = IDLE;
                    case DEAD -> active=false;
                }
            }
        }
    }




    protected void changeWalkDir() {
        if(walkDir==LEFT){
            walkDir = RIGHT;
        }
        else{
            walkDir = LEFT;
        }
    }

    public void resetEnemy(){
        hitBox.x =x;
        hitBox.y = y;
        firstUpdate=true;
        currentHealth=maxHealth;
        newState(IDLE);
        active=true;
        fallSpeed=0;
    }


    public int getAniIndex(){
        return aniIndex;
    }
    public int getEnemyState(){
        return enemyState;
    }
    public boolean isActive(){ return active; }
}
