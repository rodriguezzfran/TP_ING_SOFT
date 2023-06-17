package behaviors.health;

public class Health6 implements HealthBehavior{

    private final static int health = 60;
    @Override
    public int getHealth() {
        return this.health;
    }
}
