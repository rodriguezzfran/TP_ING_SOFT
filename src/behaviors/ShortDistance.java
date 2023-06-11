package behaviors;

import entities.Enemy;
import main.Game;

public class ShortDistance implements RangeEnemiesBehavior {

    public ShortDistance(Enemy enemy){
        this.setAttackDistance(enemy);
        this.setSightDistance(enemy);
    }
    @Override
    public void setAttackDistance(Enemy enemy) {
        enemy.setAttackDistance(Game.TILES_SIZE);
    }

    public void setSightDistance(Enemy enemy){
        enemy.setSightDistance(Game.TILES_SIZE*3);
    }

}
