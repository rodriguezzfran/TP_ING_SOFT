package behaviors.health;

public class Health7 implements HealthBehavior{

    private final static int health = 70;
    @Override
    public int getHealth() {
        return this.health;
    }
}
