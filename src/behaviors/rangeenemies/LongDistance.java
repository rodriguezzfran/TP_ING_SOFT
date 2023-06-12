package behaviors.rangeenemies;

import behaviors.rangeenemies.RangeEnemiesBehavior;
import entities.Enemy;
import main.Game;

public class LongDistance implements RangeEnemiesBehavior {

    final static float attackDistance = Game.TILES_SIZE*2;
    final static float sightDistance = Game.TILES_SIZE*4.5f;

    @Override
    public float getAttackDistance(){
        return this.attackDistance;
    }

    @Override
    public float getSightDistance(){
        return this.sightDistance;
    }


}
