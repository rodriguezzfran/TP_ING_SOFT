package behaviors.health;

public class Health5 implements HealthBehavior{
    final static int health = 50;
    @Override
    public int getHealth() {
        return this.health;
    }
}
