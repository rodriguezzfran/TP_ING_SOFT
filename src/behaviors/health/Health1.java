package behaviors.health;

import entities.Enemy;

public class Health1 implements HealthBehavior{

    final static int health = 1;

    @Override
    public int getHealth() {
        return this.health;
    }
}
