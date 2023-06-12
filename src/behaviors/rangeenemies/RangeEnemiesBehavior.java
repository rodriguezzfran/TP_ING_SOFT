package behaviors.rangeenemies;

import entities.Enemy;

//----------------Patron Strategy------------------//
public interface RangeEnemiesBehavior {
    float getAttackDistance();
    float getSightDistance();

}
