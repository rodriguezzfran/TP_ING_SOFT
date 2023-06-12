package behaviors.health;

import entities.Enemy;

public class Health2 implements HealthBehavior{

    final static int health = 20;
    @Override
    public int getHealth() {
        return this.health;
    }
}
