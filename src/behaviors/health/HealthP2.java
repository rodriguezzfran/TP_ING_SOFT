package behaviors.health;

public class HealthP2 implements HealthBehavior{

    private final static int health = 200;

    @Override
    public int getHealth() {
        return this.health;
    }
}
