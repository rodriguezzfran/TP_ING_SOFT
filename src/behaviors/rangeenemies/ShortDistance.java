package behaviors.rangeenemies;

import behaviors.rangeenemies.RangeEnemiesBehavior;
import entities.Enemy;
import main.Game;

public class ShortDistance implements RangeEnemiesBehavior {

    final static float attackDistance = Game.TILES_SIZE;
    final static float sightDistance = Game.TILES_SIZE*3;
    @Override
    public float getAttackDistance(){
        return this.attackDistance;
    }

    @Override
    public float getSightDistance(){
        return this.sightDistance;
    }

}
