package behaviors.health;

public class HealthP4 implements HealthBehavior{

    private final static int health = 200;

    @Override
    public int getHealth() {
        return this.health;
    }
}
