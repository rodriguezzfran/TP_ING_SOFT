package behaviors;

import entities.Enemy;

//----------------Patron Strategy------------------//
public interface RangeEnemiesBehavior {
    void setAttackDistance(Enemy enemy);
    void setSightDistance(Enemy enemy);

}
