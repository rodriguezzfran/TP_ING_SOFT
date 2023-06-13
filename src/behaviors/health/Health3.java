package behaviors.health;

import entities.Enemy;

public class Health3 implements HealthBehavior{
    final static int health = 30;
    @Override
    public int getHealth() {
        return this.health;
    }
}
