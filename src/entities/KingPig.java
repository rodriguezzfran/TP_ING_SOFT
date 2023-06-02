package entities;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.EnemyConstants.*;

public class KingPig  extends  Enemy{
    private Rectangle2D.Float attackBox;
    //private int attackBoxOffsetX;

    public KingPig(float x, float y) {
        super(x, y, KING_PIG_WIDTH,KING_PIG_HEIGHT, KING_PIG);
        initHitbox(x,y,(int)(18* Game.SCALE ),(int)(19*Game.SCALE));
        initAttackBox();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x,y-(4*Game.SCALE),(hitBox.width + attackDistance),(int)(26*Game.SCALE));
        //attackBoxOffsetX = (int)(Game.SCALE * 30);
    }

    public void update(int[][] lvlData, Player player){
        updateBehavior(lvlData,player);
        updateAnimationTick();
        updateAttackBox();
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
                    if(canSeePlayer(lvlData,player)){
                        turnTowardsPlayer(player);
                    }
                    if(isPlayerCloseForAttack(player)){
                        newState(ATTACK);
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

    public void drawAttackBox(Graphics g){
        g.setColor(Color.red);
        g.drawRect((int) attackBox.x, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
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

}
