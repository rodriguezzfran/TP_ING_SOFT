package entities;

import main.Game;

import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;

public class KingPig  extends  Enemy{

    public KingPig(float x, float y) {
        super(x, y, KING_PIG_WIDTH,KING_PIG_HEIGHT, KING_PIG);
        initHitbox(x,y,(int)(18* Game.SCALE ),(int)(19*Game.SCALE));
    }

    public void update(int[][] lvlData, Player player){
        updateMove(lvlData,player);
        updateAnimationTick();
    }

    private void updateMove(int[][] lvlData,Player player){
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
            }
        }
    }

}
