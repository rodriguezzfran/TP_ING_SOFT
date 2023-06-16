package behaviors.health;

import entities.Enemy;

public class Health2 implements HealthBehavior{

    private final static int health = 20;
    @Override
    public int getHealth() {
        return this.health;
    }
}
