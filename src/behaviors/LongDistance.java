package behaviors;

import entities.Enemy;
import main.Game;

public class LongDistance implements RangeEnemiesBehavior {

    public LongDistance(Enemy enemy){
        this.setAttackDistance(enemy);
        this.setSightDistance(enemy);
    }
    @Override
    public void setAttackDistance(Enemy enemy){
        enemy.setAttackDistance(Game.TILES_SIZE*2);
    }

    @Override
    public void setSightDistance(Enemy enemy){
        enemy.setSightDistance(Game.TILES_SIZE*4.5f);
    }


}
