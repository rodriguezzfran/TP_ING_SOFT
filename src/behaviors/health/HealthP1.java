package behaviors.health;

public class HealthP1 implements HealthBehavior{

    private final static int health = 100;

    @Override
    public int getHealth() {
        return this.health;
    }
}
