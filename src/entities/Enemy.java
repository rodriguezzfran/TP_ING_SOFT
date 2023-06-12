package entities;

import behaviors.damage.DamageBehavior;
import behaviors.health.HealthBehavior;
import behaviors.rangeenemies.RangeEnemiesBehavior;
import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;
import static utilz.Constants.Directions.*;


public abstract class Enemy extends Entity{
    protected RangeEnemiesBehavior rangeBehavior;
    protected DamageBehavior damageBehavior;
    protected HealthBehavior healthBehavior;

    protected int aniIndex, enemyState, enemyType;
    protected int aniTick, aniSpeed = 25;
    protected boolean firstUpdate = true;
    protected boolean inAir = false;
    protected float fallSpeed;
    protected float gravity = 0.04f * Game.SCALE;
    protected float walkSpeed = 0.45f * Game.SCALE;
    protected int walkDir = LEFT;
    protected int enemyTileY;

    protected int currentHealth;
    protected boolean active = true;
    protected boolean attackChecked;
    protected Rectangle2D.Float attackBox;
    protected int xDrawOffset, yDrawOffset;
    protected String spritePath;
    protected int enemyIndex;





    public Enemy(float x, float y, int width, int height, int enemyType, int xDrawOffset, int yDrawOffset,HealthBehavior healthBehavior, DamageBehavior damageBehavior, RangeEnemiesBehavior rangeBehavior) {
        super(x, y, width, height);
        this.enemyState=RUN;
        this.enemyType=enemyType;
        initHitbox(x,y,width,height);
        this.xDrawOffset=xDrawOffset;
        this.yDrawOffset=yDrawOffset;
        setEnemyMaxHealth(healthBehavior);
        setEnemyDamage(damageBehavior);
        setRange(rangeBehavior);
        currentHealth = healthBehavior.getHealth();
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
            enemyTileY =(int)(hitBox.y/Game.TILES_SIZE);
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
        if(playerTileY == enemyTileY){
            if(isPlayerInRange(player)){
                if(IsSightClear(lvlData,hitBox, player.hitBox, enemyTileY)){
                    return true;
                }
            }
        }
        return false;
    }

    public int flipX(){
        if(walkDir==RIGHT){
            return width;
        }
        else{
            return 0;
        }
    }
    public int flipW(){
        if(walkDir==RIGHT){
            return -1;
        }
        else{
            return 1;
        }
    }


    protected boolean isPlayerInRange(Player player) {
        int absValue = (int)(Math.abs(player.hitBox.x - hitBox.x));
        return (absValue <= getRangeBehavior().getSightDistance());
    }

    protected boolean isPlayerCloseForAttack(Player player){
        int absValue = (int) Math.abs(player.hitBox.x - hitBox.x);
        return (absValue <= getRangeBehavior().getAttackDistance());
    }

    protected void newState(int enemyState){
        this.enemyState = enemyState;
        aniTick=0;
        aniIndex=0;
    }

    public void hurt(int amount){
        currentHealth -= amount;
        newState(HIT);
        if(currentHealth <= 0){
            newState(DEAD);
        }
    }

    private void updateBehavior(int[][] lvlData, Player player){
        if (firstUpdate) {
            firstUpdateCheck(lvlData);
        }
        if (inAir) {
            updateInAir(lvlData);
        } else { //patrol
            switch (enemyState) {
                case IDLE:
                    newState(RUN);
                    break;
                case RUN:
                    if(canSeePlayer(lvlData,player)) {
                        turnTowardsPlayer(player);
                        if (isPlayerCloseForAttack(player)) {
                            newState(ATTACK);
                        }
                    }
                    move(lvlData);
                    break;
                case ATTACK:
                    if(aniIndex == 0){
                        attackChecked = false;
                    }
                    if(aniIndex == 2 && !attackChecked){
                        checkEnemyHit(attackBox, player);
                    }
                    break;
                case HIT:
                    break;
            }
        }
    }


    private void updateAttackBox() {
        if(walkDir==RIGHT){
            attackBox.x = hitBox.x;
        }
        else if(walkDir==LEFT){
            attackBox.x = hitBox.x + hitBox.width - attackBox.width;
        }
        attackBox.y = hitBox.y - (7*Game.SCALE);
    }

    public void update(int[][] lvlData, Player player){
        updateBehavior(lvlData,player);
        updateAnimationTick();
        updateAttackBox();
    }

    protected void checkEnemyHit(Rectangle2D.Float attackBox, Player player){
        if(attackBox.intersects(player.hitBox)){
            player.changeHealth(-getDamageBehavior().getDamage());
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
        currentHealth= getHealthBehavior().getHealth();
        newState(IDLE);
        active=true;
        fallSpeed=0;
    }

    protected void setRange(RangeEnemiesBehavior rangeBehavior){
        this.rangeBehavior = rangeBehavior;
    }
    public void setEnemyDamage(DamageBehavior damageBehavior){
        this.damageBehavior = damageBehavior;
    }
    public void setEnemyMaxHealth(HealthBehavior healthBehavior){
        this.healthBehavior=healthBehavior;
    }

    public RangeEnemiesBehavior getRangeBehavior(){return this.rangeBehavior;}
    public HealthBehavior getHealthBehavior(){return this.healthBehavior;}
    public DamageBehavior getDamageBehavior(){return this.damageBehavior;}
    public int getAniIndex(){
        return aniIndex;
    }
    public int getEnemyState(){
        return enemyState;
    }

    public int getEnemyIndex() {
        return enemyIndex;
    }

    public boolean isActive(){ return active; }

    public int getXDrawOffset(){
        return xDrawOffset;
    }
    public int getYDrawOffset(){
        return yDrawOffset;
    }
    public int getEnemyWidth(){
        return width;
    }
    public int getEnemyHeight(){
        return height;
    }
}
