package behaviors.health;

import entities.Enemy;

public class Health1 implements HealthBehavior{

    final static int health = 10;

    @Override
    public int getHealth() {
        return this.health;
    }
}
