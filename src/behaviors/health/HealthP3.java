package behaviors.health;

public class HealthP3 implements HealthBehavior{

    private final static int health = 170;

    @Override
    public int getHealth() {
        return this.health;
    }
}
