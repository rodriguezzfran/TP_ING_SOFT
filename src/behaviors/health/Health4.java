package behaviors.health;

import entities.Enemy;

public class Health4 implements HealthBehavior{
    private final static int health = 40;
    @Override
    public int getHealth() {
        return this.health;
    }
}
