package behaviors.health;

public class HealthP2 implements HealthBehavior{

    private final static int health = 130;

    @Override
    public int getHealth() {
        return this.health;
    }
}
