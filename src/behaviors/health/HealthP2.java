package behaviors.health;

public class HealthP2 implements HealthBehavior{

    final static int health = 200;

    @Override
    public int getHealth() {
        return this.health;
    }
}
